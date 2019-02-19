import Cells.AnimalCell;
import Cells.PlantCell;
import Organelles.*;

import java.util.ArrayList;
import java.util.List;

public class CellDriver {

    public static void main(String[] args) {
        List <Organelle> animalOrganelles = new ArrayList<>();
        List <Organelle> plantOrganelles = new ArrayList<>();
        Mitochondria animalMitochondria = new Mitochondria(5, 10);
        Vacuole animalVacuole = new Vacuole(10, 10);
        Lysosome animalLysosome = new Lysosome(7, 10);
        CellWall plantCellWall = new CellWall(10, 10);
        Vacuole plantVacuole = new Vacuole(50, 10);
        Mitochondria plantMitochondria = new Mitochondria(20, 10);
        animalOrganelles.add (0, animalLysosome);
        animalOrganelles.add (1, animalMitochondria);
        animalOrganelles.add (2, animalVacuole);
        plantOrganelles.add(0, plantCellWall);
        plantOrganelles.add(1, plantVacuole);
        plantOrganelles.add (2, plantMitochondria);
        AnimalCell Wolf = new AnimalCell(50, 100, animalOrganelles);
        PlantCell Tree = new PlantCell(100, 200, plantOrganelles);
        Wolf.doAction(5, "eat");
        Wolf.doAction(6, "sleep");
    }
}
