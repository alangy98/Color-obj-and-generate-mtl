import java.util.ArrayList;

public class Color {
    int[] tomtl = new int[3];
    String name;  //颜色的名称
    ArrayList<Double> startrange = new ArrayList<>();
    ArrayList<Double> endrange = new ArrayList<>();
    int type; //标示颜色种类的标记

    public Color(String name) {
        this.name = name;
        if (name.equals("red")) {
            tomtl[0] = 1;
            tomtl[1] = 0;
            tomtl[2] = 0;
            type = 1;
        } else if (name.equals("green")) {
            tomtl[0] = 0;
            tomtl[1] = 1;
            tomtl[2] = 0;
            type = 2;
        } else {
            assert (false) : "color not supported!";
        }
    }

    public void addrange(double start, double end) {
        startrange.add(start);
        endrange.add(end);
    }

    public boolean isinrange(double target) {
        for (int i = 0; i < startrange.size(); i++) {
            if (startrange.get(i) <= target && endrange.get(i) >= target) {
                return true;
            }
        }
        return false;
    }

    public String print() {
        String color = tomtl[0] + " " + tomtl[1] + " " + tomtl[2] + " ";
        String string = "Ka " + color + "\n" +
                "Kd " + color + "\n" +
                "Ks " + color + "\n";
        return string;
    }

}
