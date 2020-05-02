public class Face {
    Vpoint[] vpoints = new Vpoint[3];

    public double getaverage() {
        return (vpoints[0].z + vpoints[1].z + vpoints[2].z) / 3;
    }

    public Face(Vpoint vpoint1, Vpoint vpoint2, Vpoint vpoint3) {
        this.vpoints[0] = vpoint1;
        this.vpoints[1] = vpoint2;
        this.vpoints[2] = vpoint3;
    }

    public String print() {
        return "f " + vpoints[0].index + " " + vpoints[1].index + " " + vpoints[2].index + "\n";
    }
}
