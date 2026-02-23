package carSimulation;

import cars.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    CarWorkshop<Volvo240> volvoWorkshop;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(0, 0));
        cc.cars.add(new Saab95(0, 100));
        cc.cars.add(new Scania(0, 200));

        cc.volvoWorkshop = new CarWorkshop<>(1, 300, 0);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if(x < 0 || x > frame.drawPanel.getWidth() - 100 || y < 0 || y > frame.drawPanel.getHeight() - 60) {
                    // turn the car 180 degrees
                    double totalAmount = Math.PI / car.getRotationSpeed();

                    int fullSteps = (int)Math.floor(totalAmount);
                    double remainder = totalAmount - fullSteps;

                    for (int i = 0; i < fullSteps; i++) {
                        car.turn(1);
                    }

                    car.turn(remainder);
                }

                if(car instanceof Volvo240) {
                    if(car.getPosition().distance(volvoWorkshop.getPosition()) < 50 && !volvoWorkshop.isLoaded((Volvo240) car)) {
                        try {
                        volvoWorkshop.load((Volvo240) car);
                        } catch(RuntimeException error) {
                            error.printStackTrace();
                        }
                    }
                }

                frame.drawPanel.moveit(car, x, y);

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    void start() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stop() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void raiseBed() {
        for (Car car : cars) {
            if(car instanceof Scania)
                ((Scania) car).raiseBed(1);
        }
    }

    void lowerBed() {
        for (Car car : cars) {
            if(car instanceof Scania)
                ((Scania) car).lowerBed(1);
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if(car instanceof Saab95)
                ((Saab95) car).setTurboOn();
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if(car instanceof Saab95)
                ((Saab95) car).setTurboOff();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }
}
