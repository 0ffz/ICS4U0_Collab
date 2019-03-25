package Organelles;

public class Organelle {
    private double size;
    private boolean isAlive;
    private double energy;

    public Organelle(double s, double e) {
        size = s;
        isAlive = true;
        energy = e;
    }

    public double getSize() {
        return size;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void doAction(double e, String type) {
        energy -= e;
    }
}
