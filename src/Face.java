public class Face {
    Vpoint[] vpoints = new Vpoint[3];
    int[] vnormals = new int[3];

    public double getaverage() {
        return (vpoints[0].z + vpoints[1].z + vpoints[2].z) / 3;
    }


    public Face(Vpoint vpoint1, Vpoint vpoint2, Vpoint vpoint3, int vnormals0, int vnormals1, int vnormals2) {
        this.vpoints[0] = vpoint1;
        this.vpoints[1] = vpoint2;
        this.vpoints[2] = vpoint3;
        vnormals[0] = vnormals0;
        vnormals[1] = vnormals1;
        vnormals[2] = vnormals2;
    }

    public String print() {
        return "f " + vpoints[0].index+"//"+vnormals[0] + " " + vpoints[1].index+"//"+vnormals[1] + " " + vpoints[2].index+"//"+vnormals[2] + "\n";
    }
}
