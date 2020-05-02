import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String inputname = "input.obj";
        String outputname = "output";

        Colormanager colormanager = new Colormanager();
        IOhelper iOhelper = new IOhelper(colormanager, outputname);
        colormanager.addcolor("red");
        colormanager.addcolor("green");
        colormanager.addrange("red", 0, 30);
        colormanager.addrange("green", 30, 300);

        File file = new File(inputname);
        BufferedReader reader = null;
        try {
            System.out.println("读取obj文件中：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                System.out.println("正在处理 " + tempString);
                iOhelper.dealwithinput(tempString);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("未能打开文件!");
            e.printStackTrace();
            System.exit(0);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        iOhelper.switchcolor();
        iOhelper.printobj();
        iOhelper.printmtl();
    }

}
