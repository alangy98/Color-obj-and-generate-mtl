import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IOhelper {
    private int pointindex = 1;
    private ArrayList<Vpoint> points = new ArrayList<>();
    private ArrayList<Face> faces = new ArrayList<>();
    private ArrayList<Vnormal> vnormals = new ArrayList<>();
    private HashMap<Integer, ArrayList<Face>> answer = new HashMap<>();  //对应的左边index是该种颜色对应的Color内编号
    Colormanager colormanager;
    private String outputname;

    public IOhelper(Colormanager colormanager, String outputname) {
        this.colormanager = colormanager;
        this.outputname = outputname;
    }

    public void dealwithinput(String line) {
        if (line.startsWith("vn ")) {
            vnormals.add(new Vnormal(line));
        } else if (line.startsWith("v ")) {
            line = line.replace("v ", "");
            String[] splits = line.split("\\s");
            assert (splits.length == 3);
            Vpoint vpoint = new Vpoint(pointindex, Double.parseDouble(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[2]));
            points.add(vpoint);
            pointindex++;
        } else if (line.startsWith("f ")) {
            line = line.replace("f ", "");
            String[] splits = line.split("\\s");
            assert (splits.length == 3);
            int[] vpointindex = new int[3];
            vpointindex[0] = Integer.parseInt(splits[0].split("//")[0]) - 1;
            vpointindex[1] = Integer.parseInt(splits[1].split("//")[0]) - 1;
            vpointindex[2] = Integer.parseInt(splits[2].split("//")[0]) - 1;  //注意下标相差1
            Face face = new Face(points.get(vpointindex[0]), points.get(vpointindex[1]), points.get(vpointindex[2]), Integer.parseInt(splits[0].split("//")[1]), Integer.parseInt(splits[1].split("//")[1]), Integer.parseInt(splits[2].split("//")[1]));
            faces.add(face);
        } else {
            System.out.println("ignoring " + line);
        }
    }

    public void switchcolor() {
        for (Face face : faces) {
            int index = colormanager.gettype(face.getaverage());
            ArrayList<Face> targetlist = answer.get(index);
            if (targetlist == null) {
                targetlist = new ArrayList<>();
                answer.put(index, targetlist);
            }
            targetlist.add(face);
        }
    }

    public void printobj() {
        try {
            File file = new File(outputname + ".obj");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getName());
            fileWriter.write("# obj file written by alangy's software\n");
            fileWriter.write("\n");
            fileWriter.write("mtllib " + outputname + ".mtl\n");
            fileWriter.write("\n");
            for (Vpoint vpoint : points) {
                fileWriter.write(vpoint.write());
            }
            fileWriter.write("\n");
            for(Vnormal vnormal:vnormals)
            {
                fileWriter.write(vnormal.print()+"\n");
            }
            fileWriter.write("\n");
            HashMap<String, Color> allcolors = colormanager.getColors();
            for (Color color : allcolors.values()) {
                //fileWriter.write("g grp" + color.type + "\n");
                fileWriter.write("usemtl color" + color.type + "\n");
                ArrayList<Face> list = answer.get(color.type);
                for (Face face : list) {
                    fileWriter.write(face.print());
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void printmtl() {
        try {
            File file = new File(outputname + ".mtl");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getName());
            fileWriter.write("# mtl file written by alangy's software\n");
            fileWriter.write("\n");
            HashMap<String, Color> allcolors = colormanager.getColors();
            for (Color color : allcolors.values()) {
                fileWriter.write("newmtl color" + color.type + "\n");
                fileWriter.write("Ns 32\n");
                fileWriter.write("d 1\n");
                fileWriter.write(color.print());
                fileWriter.write("Tr 0 \nillum 3\n");
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}

