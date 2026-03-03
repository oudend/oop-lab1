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
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<CarWorkshop<?>> carWorkshops = new ArrayList<>();

    void addCar(Car car) {
        cars.add(car);
    }

    void addCarWorkshop(CarWorkshop<?> carWorkshop) {
        carWorkshops.add(carWorkshop);
    }

    void update(int width, int height) {
        for (Car car : cars) {
            car.move();
            int x = (int) Math.round(car.getPosition().getX());
            int y = (int) Math.round(car.getPosition().getY());

            if(x < 0 || x > width - 100 || y < 0 || y > height - 60) {
                // turn the car 180 degrees
                double totalAmount = Math.PI / car.getRotationSpeed();

                int fullSteps = (int)Math.floor(totalAmount);
                double remainder = totalAmount - fullSteps;

                for (int i = 0; i < fullSteps; i++) {
                    car.turn(1);
                }

                car.turn(remainder);
            }

            //this is very bad but doesn't require changing the model
//            for (CarWorkshop<?> workshop : carWorkshops) {
//                try {
//                    workshop.load(car); // unchecked cast
//                    break;
//                } catch (RuntimeException e) {
//                    // ignore or continue to next workshop
//                }
//            }
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
