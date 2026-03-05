package carSimulation;

import cars.Car;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Random;

public class App {
    private final CarController controller;
    private final CarView view;
    private final Timer timer = new Timer(50, new TimerListener());

    private static final Random RANDOM = new Random();

    public App() {
        controller = new CarController();
        view = new CarView("CarSim 1.0", controller, this::onAddCar, this::onRemoveCar);
    }

    private DrawableCar getRandomCar() throws IOException {
        int choice = RANDOM.nextInt(3);

        switch (choice) {
            case 0:
                return DrawableCarFactory.createVolvo240(new Point2D.Double(0, 200));
            case 1:
                return DrawableCarFactory.createSaab95(new Point2D.Double(0, 200));
            case 2:
                return DrawableCarFactory.createScania(new Point2D.Double(0, 200));
            default:
                throw new IllegalStateException("Unexpected random value: " + choice);
        }
    }

    private void onAddCar() {
        try {
            addCar(getRandomCar());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onRemoveCar() {
        removeLatestCar();
    }

    public void addCar(DrawableCar car) {
        view.addDrawable(car);
        controller.addCar(car.getCar());
    }

    public void removeLatestCar() {
        view.removeLatestDrawable();
        controller.removeLatestCar();
    }

    public void addCarWorkshop(DrawableCarWorkshop carWorkshop) {
        view.addDrawable(carWorkshop);
        controller.addCarWorkshop(carWorkshop.getCarWorkshop());
    }

    public void run() {
        timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.update(view.getWidth(), view.getHeight());
            view.repaint();
        }
    }
}
