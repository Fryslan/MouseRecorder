package clicker.strategies;

import clicker.domains.Coordinate;
import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 1-1-2015
 * Time: 15:06
 */
public class Clicker implements Strategy {

    private final ArrayList<Coordinate> coordinates;

    public Clicker(ArrayList<Coordinate> coordinates){
        this.coordinates = coordinates;
    }

    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        try {
            for(Coordinate c : coordinates){
                System.out.println("handle");
                c.handle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
