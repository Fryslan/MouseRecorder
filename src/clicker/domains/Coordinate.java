package clicker.domains;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 1-1-2015
 * Time: 15:07
 */
public class Coordinate {
    private final int x;
    private final int y;
    private final MouseAction mouseAction;
    private final int sleep;

    public Coordinate(int x, int y, MouseAction mouseAction,int sleep){
        this.x = x;
        this.y = y;
        this.mouseAction = mouseAction;
        this.sleep = sleep;
    }

    public void handle(){
        switch(mouseAction){
            case LEFT:
                System.out.println("Right");
                Mouse.getInstance().click(x,y,true);
                Time.sleep(sleep);
                break;
            case RIGHT:
                System.out.println("Left");
                Mouse.getInstance().click(x,y,false);
                Time.sleep(sleep);
                break;
            case MOVE:
                System.out.println("Moving");
                Mouse.getInstance().moveMouse(x, y);
                Time.sleep(sleep);
                break;
            default:
                System.out.println("Default");
                Time.sleep(100);
                break;
        }
    }
}
