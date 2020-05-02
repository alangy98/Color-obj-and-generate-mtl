public class Vpoint {
    int index;
    double x;
    double y;
    double z;

    public Vpoint(int index, double x, double y, double z) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String write() {
        return "v " + x + " " + y + " " + z + "\n";
    }
}
