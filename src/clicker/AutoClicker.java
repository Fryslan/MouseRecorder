package clicker;

import clicker.domains.Coordinate;
import clicker.strategies.Clicker;
import clicker.ui.AutoClickerUI;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 1-1-2015
 * Time: 15:04
 */
@ScriptManifest(
        name = "Fryslan Mouse Recorder",
        author = "Fryslan",
        description = "Can record an perform Mouse Actions.",
        version = 1.0,
        servers = "All Servers",
        category = Category.OTHER
)
public class AutoClicker extends Script implements Paintable {

    public static ArrayList<Strategy> strategies;
    public static ArrayList<Coordinate> coordinates;
    public static boolean loaded;

    public AutoClicker() {
        coordinates = new ArrayList<>();
        strategies = new ArrayList<>();
    }

    @Override
    public boolean onExecute() {

        AutoClickerUI.initialize();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return loaded;
            }
        }, Integer.MAX_VALUE);
        Time.sleep(1000);

        strategies.add(new Clicker(coordinates));
        provide(strategies);

        return true;
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void paint(Graphics graphics) {
        if(!loaded) {
            Point mouse = Mouse.getInstance().getPoint();
            graphics.drawLine(mouse.x - 5, mouse.y, mouse.x + 5, mouse.y);
            graphics.drawLine(mouse.x, mouse.y - 5, mouse.x, mouse.y + 5);
            graphics.drawString(mouse.toString(), mouse.x - 10, mouse.y - 10);
        }
    }

}

