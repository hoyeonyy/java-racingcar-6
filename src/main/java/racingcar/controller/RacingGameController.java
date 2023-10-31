package racingcar.controller;

import static racingcar.model.GameCountValidator.gameCountValidation;
import static racingcar.utils.Utils.convertStringToList;
import static racingcar.view.InputView.inputCarsName;
import static racingcar.view.InputView.inputGameTryNumber;
import static racingcar.view.OutputView.gameStartMessage;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.RandomNumberGenerator;
import racingcar.view.OutputView;

public class RacingGameController {
    private int gameCount;
    private Cars cars;

    public void startGame() {
        List<String> carNameList = convertStringToList(inputCarsName());
        List<Car> carList = carNameList.stream()
                .map(Car::makeCarByName)
                .collect(Collectors.toList());
        cars = new Cars(carList);
        String gameTryNumber = inputGameTryNumber();
        gameCountValidation(gameTryNumber);
        gameCount = Integer.parseInt(gameTryNumber);
        gameStartMessage();
        for (int i = 0; i < gameCount; i++) {
            for (Car car : cars.getCars()) {
                int randomNumber = RandomNumberGenerator.makeNumber();
                if (randomNumber >= 4) {
                    car.go();
                }
            }
            OutputView.printOneGameResult(cars);
        }
        OutputView.printWinners(cars.winner());
    }
}
