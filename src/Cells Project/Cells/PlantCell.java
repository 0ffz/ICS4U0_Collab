package Cells;

import Organelles.Organelle;

import java.util.List;

public class PlantCell extends Cell {
    public PlantCell(double s, double e, List<Organelle> o) {
        super(s, o, e);
    }

    @Override
    public void doAction(double e, String type) {
        Organelle.doAction(e, type);
    }
}
