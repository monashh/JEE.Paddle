package business.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AlreadyExistTrainingException;
import business.api.exceptions.ApiException;
import business.api.exceptions.InvalidDateException;
import business.api.exceptions.InvalidTrainingRegisterationException;
import business.api.exceptions.NotFoundCourtIdException;
import business.api.exceptions.NotFoundPlayerInTrainingException;
import business.api.exceptions.NotFoundTrainingIdException;

import business.controllers.CourtController;
import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;


@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

	private TrainingController trainingController;

	private CourtController courtController;

	@Autowired
	public void setTrainingController(TrainingController trainingController) {
		this.trainingController = trainingController;
	}

	@RequestMapping(value = Uris.SCHEDULE, method = RequestMethod.GET)
	public List<TrainingWrapper> showTrainings(@RequestParam(required = false) Long day) throws InvalidDateException {
		Calendar baseDay = Calendar.getInstance();
		if (day != null) {
			baseDay.setTimeInMillis(day);
			if (!this.isValidDay(baseDay)) {
				throw new InvalidDateException("No se puede apuntar en las clases que ya se hayan comenzado");
			}
		}
		baseDay.set(Calendar.HOUR, 0);
		baseDay.set(Calendar.MINUTE, 0);
		baseDay.set(Calendar.SECOND, 0);
		baseDay.set(Calendar.MILLISECOND, 0);
		return trainingController.showTrainings(baseDay);
	}

	private boolean isValidDay(Calendar baseDay) {
		Calendar calendarDay = Calendar.getInstance();
		calendarDay.add(Calendar.DAY_OF_YEAR, -1);
		return !calendarDay.after(baseDay);
	}

	@RequestMapping(value = Uris.NEW_TRAINING, method = RequestMethod.POST)
	public void createTraining(@RequestBody TrainingWrapper trainingWrapper)
			throws InvalidDateException, AlreadyExistTrainingException, NotFoundCourtIdException {

		if (!this.trainingController.createTraining(trainingWrapper)) {
			throw new AlreadyExistTrainingException();
		}
		if (trainingWrapper.getFirstClassDate().getTimeInMillis() < trainingWrapper.getLastClassDate()
				.getTimeInMillis()) {
			throw new InvalidDateException();
		}
		if (!courtController.exist(trainingWrapper.getCourt().getCourtId())) {
			throw new NotFoundCourtIdException();
		}
	}

	@RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
	public void deleteTraining(@RequestParam(required = true) int trainingId)
			throws NotFoundTrainingIdException, ApiException {
		if (!trainingController.exist(trainingId)) {
			throw new NotFoundTrainingIdException();
		}
		if (!this.trainingController.deleteTraining(trainingId)) {
			throw new ApiException("Problemas al borrar", 1);
		}
	}

	@RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.DELETE)
	public void deletePlayerFromTraining(@RequestParam(required = true) int trainingId, int playerId)
			throws NotFoundTrainingIdException, NotFoundPlayerInTrainingException, ApiException {
		if (!trainingController.exist(trainingId)) {
			throw new NotFoundTrainingIdException();
		}
		if (!trainingController.existPlayerInTraining(trainingId, playerId)) {
			throw new NotFoundPlayerInTrainingException();
		}
		if (!this.trainingController.deletePlayerFromTraining(trainingId, playerId)) {
			throw new ApiException("Problemas al borrar", 1);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void registerTraining(@AuthenticationPrincipal User activeUser,
			@RequestParam(required = true) int trainingId) throws InvalidTrainingRegisterationException, ApiException {
		if (trainingController.getSignedUpPlayersNum(trainingId) == 4) {
			throw new InvalidTrainingRegisterationException("The class is full" + trainingId);
		}
		if (!trainingController.registerTraining(trainingId, activeUser.getUsername())) {
			throw new ApiException("Problemas al registrar", 1);
		}
	}

}
