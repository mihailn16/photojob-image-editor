
package service;

import plugins.Plug;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Service {
    public int width(BufferedImage im) {
        return im.getWidth();
    }

    public int height(BufferedImage im) {
        return im.getHeight();
    }

    public BufferedImage openImageIcon(){
        File f = new File("icons//White.png");
        BufferedImage bim = null;
        try {
            bim = ImageIO.read(f);
        } catch (IOException e) {
        }
        return bim;
    }


    public int RGBUp(int rgb) {
        if(rgb+10 > 255) return rgb;
        return rgb+10;
    }
    public int RGBDown(int rgb,int value) {
        if(rgb-value<1) return 1;
        return rgb-value;
    }


    public BufferedImage load(JPanel panel, BufferedImage pim) {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(panel);
        BufferedImage ret = null;
        if (res != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File f = chooser.getSelectedFile();
        if (!f.getName().endsWith(".jpg")) {
            if(!f.getName().endsWith(".png"))
            {
                if(!f.getName().endsWith(".tiff"))
                {
                    return null;
                }
            }
        }
        try {
            pim = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(frames.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        ret = pim;
        Plug.ctrlZ_Save(ret);
        return ret;
    }



    public void save(JPanel panel, BufferedImage im) {
        if (im == null) {
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Save");
        int res = chooser.showOpenDialog(panel);
        if (res != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File f = new File(chooser.getSelectedFile() + "."+ImageTypeReader());
        if (!f.exists()) try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ImageIO.write(im, ImageTypeReader(), f);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage slider2(BufferedImage im, int value) {
        if (im == null) {
            return null;
        }



        return im;
    }

    public BufferedImage lightness(BufferedImage bim) {
        if (bim == null) {
            return null;
        }
        for (int i = 0; i < bim.getWidth(); i++) {
            for (int j = 0; j < bim.getHeight(); j++) {
                Color current = new Color(bim.getRGB(i, j));
                if(current.getRed()<=250 && current.getGreen()<=250 && current.getBlue()<=250) {
                    current = new Color(current.getRed() + 5, current.getGreen() + 5, current.getBlue() + 5);
                    bim.setRGB(i, j, current.getRGB());
                }
            }
        }
        return bim;
    }

    public BufferedImage darkness(BufferedImage bim) {
        if (bim == null) {
            return null;
        }
        for (int i = 0; i < bim.getWidth(); i++) {
            for (int j = 0; j < bim.getHeight(); j++) {
                Color current = new Color(bim.getRGB(i, j));
                if(current.getRed()>=5 && current.getGreen()>=5 && current.getBlue()>=5) {
                    current = new Color(current.getRed() - 5, current.getGreen() - 5, current.getBlue() - 5);
                    bim.setRGB(i, j, current.getRGB());
                }
            }
        }
        return bim;
    }

    public BufferedImage mirHig(BufferedImage im) {
        if (im == null) {
            return null;
        }


        for (int i = 0; i < im.getWidth(); i++) {
            for (int j = 0; j < im.getHeight() / 2; j++) {
                Color current = new Color(im.getRGB(i, j));

                Color current1 = new Color(im.getRGB(i, im.getHeight() - 1 - j));
                int red1 = current1.getRed();
                int blue1 = current1.getBlue();
                int green1 = current1.getGreen();
                Color newColor1 = new Color(current.getRed(), current.getGreen(), current.getBlue());
                im.setRGB(i, im.getHeight() - 1 - j, newColor1.getRGB());
                Color newColor = new Color(red1, green1, blue1);
                im.setRGB(i, j, newColor.getRGB());

            }
        }
        return im;
    }

    public BufferedImage mirWih(BufferedImage im) {

        if (im == null) {
            return null;
        }


        for (int i = 0; i < im.getWidth() / 2; i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                Color current = new Color(im.getRGB(i, j));

                Color current1 = new Color(im.getRGB(im.getWidth() - 1 - i, j));
                int red1 = current1.getRed();
                int blue1 = current1.getBlue();
                int green1 = current1.getGreen();
                Color newColor1 = new Color(current.getRed(), current.getGreen(), current.getBlue());
                im.setRGB(im.getWidth() - 1 - i, j, newColor1.getRGB());
                Color newColor = new Color(red1, green1, blue1);
                im.setRGB(i, j, newColor.getRGB());

            }
        }
        return im;
    }

    //Vividness
    public BufferedImage vivdness(BufferedImage im, int vividnessValue) {
        if (im == null) {
            return null;
        }
        BufferedImage bimage = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < im.getWidth(); i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                Color current = new Color(im.getRGB(i, j));
                current = new Color(current.getRed(), current.getGreen(), current.getBlue(), vividnessValue);
                bimage.setRGB(i, j, current.getRGB());
            }
        }
        return bimage;
    }

    public BufferedImage apply(BufferedImage im, int r, int g, int b, int a) {
        if (im == null) {
            return null;
        }
        BufferedImage bimage = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < im.getWidth(); i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                Color current = new Color(im.getRGB(i, j));
                int currentRed = current.getRed();
                int currentGreen = current.getGreen();
                int currentBlue = current.getBlue();

                int newRed = (int) ((currentRed + r) * 0.5);
                int newGreen = (int) ((currentGreen + g) * 0.5);
                int newBlue = (int) ((currentBlue + b) * 0.5);


                current = new Color(newRed, newGreen, newBlue, a);
                bimage.setRGB(i, j, current.getRGB());
            }
        }

        return bimage;
    }



    public BufferedImage colorIm(BufferedImage im, int r, int g, int b) {
        if (im == null) {
            return null;
        }
        for (int i = 0; i < im.getWidth(); i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                Color current = new Color(r, g, b, 255);
                im.setRGB(i, j, current.getRGB());
            }
        }
        return im;
    }


//    public BufferedImage ctrlZ_Load() {
//        BufferedImage bIm = null;
//        bIm = Plug.ctrlZ_Load();
//        return bIm;
//    }



    public void EnableWriter(boolean selected) {
        File f = new File("values/autoSaveSelected.txt");
        if (f.exists()) {
            f.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            if (selected == true) {
                bw.write("true");
            } else {
                bw.write("false");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean EnableReader() {
        boolean is = false;
        File ans = new File("values/autoSaveSelected.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(ans));
            if (br.readLine().charAt(0) == 't') {
                is = true;
            } else {
                is = false;
            }
        } catch (FileNotFoundException e) {
            return is;
        } catch (IOException e) {
            return is;
        }
        return is;

    }



    public void autoSaveNum(int num) {
        File f = new File("values/autoSaveNum.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int autoSaveNumGet() {
        int n = 0;
        try {
            Scanner scan = new Scanner(new File("values/autoSaveNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return n;
    }


    public static void saveNum(int num)
    {
        File f = new File("values/SaveNum.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int saveNumGet() {
        int n = 0;
        try {
            Scanner scan = new Scanner(new File("values/SaveNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return n;
    }




    public static void cekNum(int num)
    {
        File f = new File("values/CekNum.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static int cekNumGet() {
        int n = 0;
        try {
            Scanner scan = new Scanner(new File("values/CekNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return n;
    }



    public static void cekSel(int num)
    {
        File f = new File("values/CekSelected.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static int cekSelGet() {
        int n = 10;
        try {
            Scanner scan = new Scanner(new File("values/CekNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return n;
    }




    public void ClearWriter(boolean selected) {
        File f = new File("values/clearSelected.txt");
        if (f.exists()) {
            f.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            if (selected == true) {
                bw.write("true");
            } else {
                bw.write("false");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean ClearReader() {
        boolean is = false;
        File ans = new File("values/clearSelected.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(ans));
            if (br.readLine().charAt(0) == 't') {
                is = true;
            } else {
                is = false;
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return is;
    }




    public static void ImageTypeWriter(String type) {
        File f = new File("values/imageType.txt");
        if (f.exists()) {
            f.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            if (type == "png") {
                bw.write("png");
            }
            if (type == "jpg") {
                bw.write("jpg");
            }
            if (type == "tiff") {
                bw.write("tiff");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String ImageTypeReader() {
        String type = "";
        File f = new File("values/imageType.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            char t = br.readLine().charAt(0);
            if (t == 'p') {
                type = "png";
                return type;
            }
            if (t == 'j') {
                type = "jpg";
                return type;
            }
            if (t == 't') {
                type = "tiff";
                return type;
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return type;
    }

    public void cleanButton()
    {
        saveNum(0);
        ctrlZNum(0);
        File delOjpg = new File("Cache/Open_Save.jpg");
        File delOpng = new File("Cache/Open_Save.png");
        File delOtiff = new File("Cache/Open_Save.tiff");
        delOjpg.delete();
        delOpng.delete();
        delOtiff.delete();
        for (int i = 0; i < 1000; i++) {
        File delBjpg = new File("Cache/Buf_Save"+i+".jpg");
        File delBpng = new File("Cache/Buf_Save"+i+".png");
        File delBtiff = new File("Cache/Buf_Save"+i+".tiff");
        delBjpg.delete();
        delBpng.delete();
        delBtiff.delete();}
        for (int i = 0; i < 1000; i++) {
            File delApng = new File("Cache/autosave" + i + ".png");
            File delAjpg = new File("Cache/autosave" + i + ".jpg");
            File delAtiff = new File("Cache/autosave" + i + ".tiff");
            delApng.delete();
            delAjpg.delete();
            delAtiff.delete();
        }
    }

    public void ThemeWriter(String t) {
        File f = new File("values/theme.txt");
        if (f.exists()) {
            f.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            if (t == "dark") {
                bw.write("dark");
            }
            if(t == "light"){
                bw.write("light");
            }
            if(t == "default"){
                bw.write("default");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String ThemeReader() {
        String theme = "";
        File f = new File("values/theme.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            char t = br.readLine().charAt(1);
            if (t == 'a') {
                theme = "dark";
                return theme;
            }
            if (t == 'i') {
                theme = "light";
                return theme;
            }
            if (t == 'e') {
                theme = "default";
                return theme;
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return theme;
    }

    public static void ctrlZNum(int num)
    {
        File f = new File("values/ctrlZNum.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    public static int ctrlZNumGet() {
        int n = 0;
        try {
            Scanner scan = new Scanner(new File("values/ctrlZNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
        }

        return n;
    }

    public static void openNum(int num)
    {
        File f = new File("values/openNum.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    public static int openNumGet() {
        int n = 0;
        try {
            Scanner scan = new Scanner(new File("values/openNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
        }

        return n;
    }

    public static void closeNum(int num)
    {
        File f = new File("values/closeNum.txt");
        try {
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(Integer.toString(num).getBytes());
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    public static int closeNumGet() {
        int n = 0;
        try {
            Scanner scan = new Scanner(new File("values/closeNum.txt"));
            n = scan.nextInt();
        } catch (FileNotFoundException e) {
        }

        return n;
    }







}