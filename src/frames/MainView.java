package frames;

import service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainView extends JFrame {
    private BufferedImage im;
    private Service service;
    public static BufferedImage Auto_Save_image;
    private JPanel panel1;

    public MainView() {
        this.service = new Service();
        this.im = null;
//        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("PhotoJob.png")));
    }
}
