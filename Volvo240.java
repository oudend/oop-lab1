import java.awt.*;

public class Volvo240 extends  Car{

    private final static double trimFactor = 1.25;

    public Volvo240(){
        super("Volvo240", Color.black, 100, 4);
    }

    private double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    @Override
    protected void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    @Override
    protected void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }
}
