package Cells;

import Organelles.Organelle;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private double size;
    private List<Organelle> organelles = new ArrayList<>();

    Cell(double s, List<Organelle> o) {
        size = s;
        organelles.addAll(o);
    }

    public double getSize() {
        return size;
    }

    public List<Organelle> getOrganelles() {
        return organelles;
    }

    public void doAction() {

    }
}
