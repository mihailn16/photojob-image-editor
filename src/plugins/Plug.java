package plugins;

import service.Service;

import static frames.Main.Auto_Save_image;
import static service.Service.saveNum;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Plug extends Thread {

    private static int saveNum = getSaveNum();
    private static int amount;
    private static int cekNum;
    private static int ctrlZNum = 0;
    private static String type = "png";

    @Override
    public void run(){
        try {
            Timered_runer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**-------------------------------------------------------------------------------------------------------------------*/
    public static void ctrlZ_Save(BufferedImage bufIm) {
        ctrlZNum = getCtrlZNum();
        ctrlZNum++;
        File saverfile = new File( "Cache/Buf_Save"+ctrlZNum+"."+imageType());
        System.out.println(imageType());

        try {
            String type1 = "";
            if(imageType() == "png"){type1 = "PNG";}
            if(imageType() == "jpg"){type1 = "JPG";}
            if(imageType() == "tiff"){type1 = "TIFF";}
            ImageIO.write(bufIm, type1, saverfile);
        } catch (IOException e) {
        }
        Service.ctrlZNum(ctrlZNum);

    }


    public static BufferedImage ctrlZ_Load(int num) {
        BufferedImage buf = null;
        File f = new File("Cache/Buf_Save"+num+"."+imageType());
        try {
            buf = ImageIO.read(f);
        } catch (IOException e) {
            return null;
        }
        return buf;
    }


    public static int getCtrlZNum()
    {
        ctrlZNum = Service.ctrlZNumGet();
        return ctrlZNum;
    }
/**-------------------------------------------------------------------------------------------------------------------*/
public static void Open_Save(BufferedImage bufIm) {

    File saverfile = new File( "Cache/Open_Save."+imageType());
    try {
        String type1 = "";
        if(imageType() == "png"){type1 = "PNG";}
        if(imageType() == "jpg"){type1 = "JPG";}
        if(imageType() == "tiff"){type1 = "TIFF";}
        ImageIO.write(bufIm, type1, saverfile);
    } catch (IOException e) {
    }
}


    public static BufferedImage Open_Load() {
        BufferedImage buf = null;
        File f = new File("Cache/Open_Save."+imageType());
        try {
            buf = ImageIO.read(f);
        } catch (IOException e) {
            return null;
        }
        return buf;
    }

/**-------------------------------------------------------------------------------------------------------------------*/

    public static int getAmount()
    {
        amount = Service.autoSaveNumGet();
        return amount;
    }

    public static int getSaveNum()
    {
        saveNum = Service.saveNumGet();
        return saveNum;
    }

    public static int getCekNum()
    {
        cekNum = Service.cekNumGet();
        return cekNum;
    }

    public static String imageType()
    {
        type = Service.ImageTypeReader();
        return type;
    }

    
    public static void Auto_Save() throws IOException, InterruptedException {

        ctrlZNum = getSaveNum();
        File saverfile = new File("Cache/autosave" + getSaveNum() + "."+imageType());
        ImageIO.write(Auto_Save_image, imageType(), saverfile);
        ctrlZNum++;
        Service.saveNum(ctrlZNum);
    }


    
    public static void Timered_runer() throws InterruptedException, IOException {
        for(int i = 0; i<getCekNum()/1000; i++) {
            Thread.sleep(1000);
        }

        System.out.println("\n[Timered_runer]: timer is out of time");
        if(Auto_Save_image!=null){
            if(saveNum<getAmount())
            {
                Auto_Save();
                System.out.println("[Timered_runer]: image was saved");
            }
            else
            {
                System.out.println("[Timered_runer]: Maximum amount of AutoSave images is reached. Please, clear the cache or increase the amount");
            }
        }
        else{
            System.out.println("[Timered_runer]: image not found");
        }

        System.out.println("[Timered_runer]: timer was started\n");
        Timered_runer();
    }
    }

