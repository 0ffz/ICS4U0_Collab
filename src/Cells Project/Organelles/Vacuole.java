package Organelles;

public class Vacuole extends Organelle{
    public Vacuole (double s, double e){
        super (s, e);
    }

    @Override
    public void doAction (double e){
        super(e) += e;
    }
}
