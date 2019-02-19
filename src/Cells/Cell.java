package Cells;

import Organelles.Organelle;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private double size;
    private List<Organelle> organelles = new ArrayList<>();
    private double energy;

    Cell(double s, List<Organelle> o, double e) {
        size = s;
        energy = e;
        organelles.addAll(o);
    }

    public double getSize() {
        return size;
    }

    public List<Organelle> getOrganelles() {
        return organelles;
    }

    public double getEnergy(){
        return energy;
    }

    public void doAction(double e) {
        energy -= e;
    }
}
