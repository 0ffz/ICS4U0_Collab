package Cells;

import Organelles.Organelle;

import java.util.List;

public class AnimalCell extends Cell {

     public AnimalCell(double s, double e, List<Organelle> o) {
        super(s, o, e);
    }

    @Override
    public void doAction(double e, String type) {
        Organelle.doAction()
    }
}
