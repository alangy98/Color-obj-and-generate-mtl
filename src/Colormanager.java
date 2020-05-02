import java.util.ArrayList;
import java.util.HashMap;

public class Colormanager {
    HashMap<String, Color> colors = new HashMap<>();

    public void addcolor(String name) {
        assert !colors.containsKey(name) : "duplicate color name";
        Color color = new Color(name);
        colors.put(name, color);
    }

    public void addrange(String name, double start, double end) {
        Color target = colors.get(name);
        assert (target != null) : "no color name matching!";
        target.addrange(start, end);
    }

    public int gettype(double target) {
        for (Color color : colors.values()) {
            if (color.isinrange(target)) {
                return color.type;
            }
        }
        assert (false) : "there is no color range matching target face!, whose z value is " + target;
        return 0;
    }

    public HashMap<String, Color> getColors() {
        return colors;
    }
}
