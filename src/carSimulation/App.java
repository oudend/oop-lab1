package carSimulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private final CarController controller;
    private final CarView view;
    private final Timer timer = new Timer(50, new TimerListener());

    public App() {
        controller = new CarController();
        view = new CarView("CarSim 1.0", controller);
    }

    public void addCar(DrawableCar car) {
        view.addDrawable(car);
        controller.addCar(car.getCar());
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
