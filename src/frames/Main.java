/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.Toolkit;
import javax.swing.event.ChangeListener;


import plugins.Plug;
import service.Service;
import utilites.ComponentResizer;


public class Main extends javax.swing.JFrame {
    static{



    }
    private int X = 0;
    private int Y = 0;
    private int H = 500;
    private int W = 1000;
    private int positionX;
    private int positionY;
    private int ctrlZNum;
    private String type;
    private BufferedImage im; //Рисунок
    private BufferedImage oIm; //Рисунок на JDialog
    private Service service; //Сервис
    public static BufferedImage Auto_Save_image; //Рисунок Auto_Save


    public Main() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Не закрывать окно по нажатию на крестик

        this.addWindowListener(new WindowListener() //Подключение слушателя окна
        {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                File values = new File("values");
                if(!values.exists()) {values.mkdir();}
                File type = new File("values/imageType.txt");
                File ctrlZ = new File("values/ctrlZNum.txt");
                File open = new File("values/openNum.txt");

                if (!type.exists()) {
                    try {
                        type.createNewFile();
                    } catch (IOException e) {
                    }
                    Service.ImageTypeWriter("png");
                }
                if(!ctrlZ.exists()){
                    try {
                        ctrlZ.createNewFile();
                    } catch (IOException e) {
                    }
                    Service.ctrlZNum(0);
                }
                if(!open.exists()){
                    try {
                        ctrlZ.createNewFile();
                    } catch (IOException e) {
                    }
                    Service.openNum(0);
                }



                if(Service.EnableReader() == true) {
                    System.out.println("Auto save is enabled");
                    if(Service.ClearReader() == true)
                    {
                        System.out.println("Cache is being cleared automatically");
                    }
                }
                else{
                    System.out.println("Auto save is disabled");
                }
                Plug plug = new Plug();
                plug.start();

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) { //Операции, которые происходят во время закрытия окна
//                if(im == null) {
//                    System.exit(0);
//                }
//                Jdialog.setVisible(true);
//                Jdialog.setResizable(false);
            }

            public void windowClosed(WindowEvent event)
            {



            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });


        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                display();

            }
        });








        this.service = new Service();
        this.im = null;
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("PhotoJob.png")));







    }






    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(); //Панель, на которой расположен рисунок

        jPanel2 = new javax.swing.JPanel(); //Панель для JDialog
        Jdialog = new javax.swing.JDialog(); //JDialog, который выскакивает при закрытии приложения
        openButton = new javax.swing.JButton(); //JButton, при нажатии на которую открывается OpenDialog
        OpenDialog = new javax.swing.JDialog(); //JDialog, на котором расположен OpenLabel
        OpenLabel = new javax.swing.JLabel(); //OpenLabel, на котором рисунок
        OpenApplyButton = new javax.swing.JButton(); //JButton, на OpenDialog

        DialogLabel = new javax.swing.JLabel(); //Надпись на JDialog
        ansYes = new javax.swing.JButton(); //1 кнопка Диалога
        ansNo = new javax.swing.JButton(); //2 кнопка диалога

        LoadInImage = new javax.swing.JButton(); //Кнопка "Load"
        apply = new javax.swing.JButton(); //Кнопка "apply"

        SliderVividness = new javax.swing.JSlider(); //JSlider Vividness
        sliderContrast = new javax.swing.JSlider(); //JSlider Contrast
        buttonToDark = new javax.swing.JButton(); //JButton dark
        buttonToLight = new javax.swing.JButton(); //JButton light
        jLabel1 = new javax.swing.JLabel(); //Надпись возле слайдера Vividness
        jLabel2 = new javax.swing.JLabel(); //Надпись возле слайдера Contrast
        jLabel6 = new javax.swing.JLabel(); //Надпись возле JButton dark & light




        MirrorH = new javax.swing.JButton(); //Кнопка MirrorH
        MirrorW = new javax.swing.JButton(); //Кнопка MirrorW

        SliderToBlue = new javax.swing.JSlider(); //JSlider "Blue"
        SliderToRed = new javax.swing.JSlider(); //JSlider "Red"
        SliderToGreen = new javax.swing.JSlider(); //JSlider "Green"

        jLabel3 = new javax.swing.JLabel(); //Надпись возле слайдера Blue
        jLabel4 = new javax.swing.JLabel(); //Надпись возле слайдера Red
        jLabel5 = new javax.swing.JLabel(); //Надпись возле слайдера Green

        ctrlZ = new javax.swing.JButton(); //Кнопка "CtrlZ"
        SaveImage = new javax.swing.JButton(); //Кнопка "save"

        photoField = new javax.swing.JLabel(); //JLabel, на которой расположен рисунок
        colorField = new javax.swing.JLabel(); //JLabel, на которой расположен цвет, который меняется от действий слайдеров

        menuBar = new javax.swing.JMenuBar(); //Menubar, на котором расположены элементы управления

        iconLabel = new javax.swing.JLabel(); //JLabel с иконкой Photojob
        file = new javax.swing.JMenu(); //элемент JMenu
        edit = new javax.swing.JMenu(); //элемент JMenu
        view = new javax.swing.JMenu(); //элемент JMenu

        plug = new javax.swing.JMenu(); //элемент JMenu
        autoSave = new javax.swing.JMenu(); //элемент меню Plug
        cache = new javax.swing.JMenu(); //элемент меню Plug
        AutoSaveSlider = new javax.swing.JSlider(); //JSlider в меню Plug->Autosave
        autoSaveLabel = new javax.swing.JLabel(); //JLabel в меню Plug->Autosave
        clearCache = new javax.swing.JCheckBox(); //JCheckBox в меню Plug->Autosave
        clearCacheButton = new javax.swing.JButton(); //JButton в меню Plug


        settings = new javax.swing.JMenu(); //элемент JMenu
        imageType = new javax.swing.JMenu(); //элемент в меню settings
        jpg = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Settings -> ImageType
        png = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Settings -> ImageType
        tiff = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Settings -> ImageType


        help = new javax.swing.JMenu(); //элемент JMenu




        Default = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в Views, которая меняет стиль на дефолт
        Dark = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в Views, которая меняет стиль на тёмный
        Light = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в Views, которая меняет стиль на светлый
        Enable = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Plug->Autosave

        cek5 = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Plug->Autosave
        cek10 = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Plug->Autosave
        cek30 = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Plug->Autosave
        cek60 = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Plug->Autosave
        min5 = new javax.swing.JRadioButtonMenuItem(); //Radiobutton в меню Plug->Autosave
        timeLabel = new javax.swing.JLabel(); //Label в меню Plug->Autosave


        Exit = new javax.swing.JButton(); //Кнопка управления, которая закрывает окно
        Roll = new javax.swing.JButton(); //Кнопка управления, которая сворачивает окно
        FullSize = new javax.swing.JButton(); //Кнопка управления, которая открывает окно в полном размере


        MoveUndecorated(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getPreferredSize();

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }



        rootPane.setBorder(BorderFactory.createLineBorder(Color.black));
        setUndecorated(true);
        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(this);
        setTitle("Photojob - image redactor");
        setMaximumSize(new java.awt.Dimension(frameSize.width, frameSize.height));
        setMinimumSize(new java.awt.Dimension(1000, 600));


        JPanel p = new JPanel();
        SpringLayout openPanelLayout = new SpringLayout();
        openPanelLayout.putConstraint(SpringLayout.EAST, OpenApplyButton,0,SpringLayout.EAST, p);
        openPanelLayout.putConstraint(SpringLayout.WEST, OpenLabel,0,SpringLayout.WEST, p);
        openPanelLayout.putConstraint(SpringLayout.NORTH, OpenLabel,0,SpringLayout.NORTH, p);
        openPanelLayout.putConstraint(SpringLayout.SOUTH, OpenLabel,0,SpringLayout.SOUTH, p);
//        openPanelLayout.putConstraint(SpringLayout.WEST, OpenLabel,20,SpringLayout.WEST, OpenDialog);





        OpenDialog.setVisible(false);
        OpenDialog.setResizable(false);
        OpenDialog.setMinimumSize(new java.awt.Dimension(600,600));
        OpenDialog.setLocation((this.getX()+this.getWidth())/2,this.getY()/2);

        //todo


        OpenApplyButton.setBorder(BorderFactory.createLineBorder(Color.black));
        OpenApplyButton.setText("Apply");
        OpenApplyButton.setForeground(new java.awt.Color(0,0,0));
        OpenApplyButton.setBackground(new java.awt.Color(255,255,255));
        OpenApplyButton.addActionListener(OpenApplyButtonListener());

        OpenLabel.setMinimumSize(new java.awt.Dimension(600,600));
        OpenLabel.setPreferredSize(new java.awt.Dimension(600,600));
        OpenLabel.setSize(new java.awt.Dimension(600,600));
        OpenLabel.setMaximumSize(new java.awt.Dimension(1920,1080));



        p.setSize(600,600);
        p.add(OpenLabel);
        p.add(OpenApplyButton);
        p.setLayout(openPanelLayout);
        OpenDialog.add(p);




        Jdialog.add(jPanel2);
        Jdialog.setResizable(false);
        Jdialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Jdialog.setVisible(false);
        Jdialog.setBounds(660,290,300,160);

        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel2.setVisible(false);
        jPanel2.setBounds(660,290,300,150);
        jPanel2.setLayout(null);
        jPanel2.setVisible(true);
        jPanel2.add(ansYes);
        jPanel2.add(ansNo);
        jPanel2.add(DialogLabel);

        ansYes.setBounds(50,80,60,25);
        ansYes.setBackground(new Color(70,139, 61));
        ansYes.setVisible(true);
        ansYes.addActionListener(yesListener());
        ansYes.setText("Yes");


        ansNo.setBounds(160,80,60,25);
        ansNo.setBackground(new Color(140, 53, 50));
        ansNo.setVisible(true);
        ansNo.addActionListener(noListener());
        ansNo.setText("No");

        DialogLabel.setBounds(50,10,200,40);
        DialogLabel.setVisible(true);
        DialogLabel.setText("Do you want to save your image?");





        menuBar.setBackground(new Color(102, 102, 102));


        menuBar.setPreferredSize(new java.awt.Dimension(this.getWidth(),25));
        /***********************/

        iconLabel.setIcon(new ImageIcon("icons//PhotoJob.png"));
        iconLabel.setPreferredSize(new java.awt.Dimension(23,23));

        file.setText("File");
        file.setPreferredSize(new java.awt.Dimension(40,25));
        file.setForeground(new Color(25, 25, 25));

        edit.setText("Edit");
        edit.setPreferredSize(new java.awt.Dimension(40,25));
        edit.setForeground(new Color(25, 25, 25));

        view.setText("View");
        view.setPreferredSize(new java.awt.Dimension(40,25));
        view.setForeground(new Color(25, 25, 25));

        plug.setText("Plug");
        plug.setPreferredSize(new java.awt.Dimension(40,25));
        plug.setForeground(new Color(25, 25, 25));

        settings.setText("Settings");
        settings.setPreferredSize(new java.awt.Dimension(57,25));
        settings.setForeground(new Color(25, 25, 25));

        help.setText("Help");
        help.setPreferredSize(new java.awt.Dimension(40,25));
        help.setForeground(new Color(25, 25, 25));

        autoSave.setText("AutoSave");
        autoSave.setPreferredSize(new java.awt.Dimension(80,25));
        autoSave.setForeground(new Color(25, 25, 25));

        cache.setText("Cache");
        cache.setPreferredSize(new java.awt.Dimension(80,25));
        cache.setForeground(new Color(25, 25, 25));

        clearCacheButton.setText("Clear Cache");
        clearCacheButton.setPreferredSize(new java.awt.Dimension(175,35));
        clearCacheButton.setMinimumSize(new java.awt.Dimension(175,35));
        clearCacheButton.setMaximumSize(new java.awt.Dimension(175,35));
        clearCacheButton.setForeground(new Color(25, 25, 25));
        clearCacheButton.addActionListener(clearCacheButtonListener());

        Enable.setText("Enable");
        Enable.setSelected(service.EnableReader());
        Enable.setPreferredSize(new java.awt.Dimension(60,25));
        Enable.setForeground(new Color(25, 25, 25));
        Enable.addChangeListener(enableListener());

        cek5.setText("5sec");
        cek5.setEnabled(service.EnableReader());
        cek5.addChangeListener(cek5Listener());

        cek10.setText("10sec");
        cek10.setEnabled(service.EnableReader());
        cek10.addChangeListener(cek10Listener());

        cek30.setText("30sec");
        cek30.setEnabled(service.EnableReader());
        cek30.addChangeListener(cek30Listener());

        cek60.setText("1min");
        cek60.setEnabled(service.EnableReader());
        cek60.addChangeListener(cek60Listener());

        min5.setText("5min");
        cek5.addChangeListener(min5Listener());
        min5.setEnabled(service.EnableReader());

        if(service.cekSelGet() == 5000) {cek5.setSelected(true);}
        if(service.cekSelGet() == 10000) {cek10.setSelected(true);}
        if(service.cekSelGet() == 30000) {cek30.setSelected(true);}
        if(service.cekSelGet() == 60000) {cek60.setSelected(true);}
        if(service.cekSelGet() == 300000) {min5.setSelected(true);}
        clearCache.setText("Clear cache automatically");
        clearCache.setPreferredSize(new java.awt.Dimension(175,35));
        clearCache.setMinimumSize(new java.awt.Dimension(175,35));
        clearCache.setMaximumSize(new java.awt.Dimension(175,35));
        clearCache.setAlignmentX(Component.LEFT_ALIGNMENT);
        clearCache.setForeground(new Color(25, 25, 25));
        clearCache.addActionListener(clearCacheListener());
        clearCache.setSelected(service.ClearReader());

        AutoSaveSlider.setMinimum(0);
        AutoSaveSlider.setMaximum(1000);
        AutoSaveSlider.setMinorTickSpacing(25);
        AutoSaveSlider.setMajorTickSpacing(100);
        AutoSaveSlider.setPaintTicks(true);
        AutoSaveSlider.setPaintLabels(true);
        AutoSaveSlider.setPaintTrack(false);
        AutoSaveSlider.setPreferredSize(new java.awt.Dimension(510,50));
        AutoSaveSlider.setForeground(new Color(25, 25, 25));
        AutoSaveSlider.addChangeListener(autoSaveSliderListener());

        autoSaveLabel.setPreferredSize(new java.awt.Dimension(100,25));
        autoSaveLabel.setText("Number: "+Integer.toString(service.autoSaveNumGet()));
        AutoSaveSlider.setValue(service.autoSaveNumGet());



        imageType.setText("Image Type");
        imageType.setPreferredSize(new java.awt.Dimension(90,25));
        imageType.setForeground(new Color(25, 25, 25));

        png.setText("png");
        png.addChangeListener(pngListener());
        png.setForeground(new Color(25, 25, 25));
        png.setSelected(false);

        jpg.setText("jpg");
        jpg.addChangeListener(jpgListener());
        jpg.setForeground(new Color(25, 25, 25));
        jpg.setSelected(false);

        tiff.setText("tiff");
        tiff.addChangeListener(tiffListener());
        tiff.setForeground(new Color(25, 25, 25));
        tiff.setSelected(false);

        if(service.ImageTypeReader() == "png") {png.setSelected(true);}
        if(service.ImageTypeReader() == "jpg") {jpg.setSelected(true);}
        if(service.ImageTypeReader() == "tiff") {tiff.setSelected(true);}

        if(service.ThemeReader() == "dark")
        {
            Dark.setSelected(true);
            dark();
        }
        if(service.ThemeReader() == "light")
        {
            Light.setSelected(true);
            light();
        }
        if(service.ThemeReader() == "default")
        {
            Default.setSelected(true);
            def();
        }

        if(Default.isSelected() == true){
        Roll.setIcon(new ImageIcon("icons//RollDefault.png"));}
        Roll.setPreferredSize(new java.awt.Dimension(45,23));
        Roll.setForeground(new Color(255,25,50));
        Roll.setBackground(new Color(0,0,0));
        Roll.setBorder(null);

        if(Default.isSelected() == true){
        FullSize.setIcon(new ImageIcon("icons//FullSizeDefault.png"));}
        FullSize.setPreferredSize(new java.awt.Dimension(45,23));
        FullSize.setForeground(new Color(255,25,50));
        FullSize.setBackground(new Color(0,0,0));
        FullSize.setBorder(null);

        if(Default.isSelected() == true){
        Exit.setIcon(new ImageIcon("icons//ExitDefault.png"));}
        Exit.setBackground(new java.awt.Color(102,102,102));
        Exit.setForeground(new java.awt.Color(102,102,102));
        Exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(im == null) {
                    clear();
                    System.exit(0);
                }
                Jdialog.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

                Exit.setIcon(new ImageIcon("icons//ExitHighlited.png"));

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if(Dark.isSelected())
                {
                    Exit.setIcon(new ImageIcon("icons//ExitDark.png"));
                }
                if(Light.isSelected())
                {
                    Exit.setIcon(new ImageIcon("icons//ExitLight.png"));
                }
                if(Default.isSelected())
                {
                    Exit.setIcon(new ImageIcon("icons//ExitDefault.png"));
                }

            }
        });
        FullSize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                fullSize();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

                FullSize.setIcon(new ImageIcon("icons//FullSizeHighlited.png"));

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if(Dark.isSelected())
                {
                    FullSize.setIcon(new ImageIcon("icons//FullSizeDark.png"));
                }
                if(Light.isSelected())
                {
                    FullSize.setIcon(new ImageIcon("icons//FullSizeLight.png"));
                }
                if(Default.isSelected())
                {
                    FullSize.setIcon(new ImageIcon("icons//FullSizeDefault.png"));
                }

            }
        });
        Roll.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                roll();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

                Roll.setIcon(new ImageIcon("icons//RollHighlited.png"));

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if(Dark.isSelected())
                {
                    Roll.setIcon(new ImageIcon("icons//RollDark.png"));
                }
                if(Light.isSelected())
                {
                    Roll.setIcon(new ImageIcon("icons//RollLight.png"));
                }
                if(Default.isSelected())
                {
                    Roll.setIcon(new ImageIcon("icons//RollDefault.png"));
                }

            }
        });
        Exit.setPreferredSize(new java.awt.Dimension(45,23));
        Exit.setForeground(new Color(255,25,50));
        Exit.setBackground(new Color(0,0,0));
        Exit.setBorder(null);
        /***********************/

        var New = new JMenuItem();
        New.setText("New");
        New.addActionListener(LoadInImageActionListener());


        var Save = new JMenuItem();
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveImageActionPerformed(evt);
            }
        });

        var Undo = new JMenuItem();
        Undo.setText("Undo");
        Undo.addActionListener(ctrlZListener());

        var theme = new JMenu();
        theme.setText("Theme");


        Default.setText("Default");
        if(Dark.isSelected() == false && Light.isSelected() == false) {
            Default.setSelected(true);
        }
        Default.addActionListener(defaultListener());


        Dark.setText("Dark");
        Dark.addActionListener(darkListener());


        Light.setText("Light");
        Light.addActionListener(lightListener());


        view.add(theme);
        theme.add(Default);
        theme.addSeparator();
        theme.add(Dark);
        theme.addSeparator();
        theme.add(Light);

        file.add(New);
        file.addSeparator();
        file.add(Save);
        edit.add(Undo);

        plug.add(autoSave);
        plug.add(cache);
        autoSave.add(Enable);
        autoSave.addSeparator();
        autoSave.add(cek5);
        autoSave.add(cek10);
        autoSave.add(cek30);
        autoSave.add(cek60);
        autoSave.add(min5);
        autoSave.addSeparator();
        autoSave.add(autoSaveLabel);
        autoSave.add(AutoSaveSlider);

        cache.add(clearCacheButton);
        cache.add(clearCache);

        settings.add(imageType);
        imageType.add(png);
        imageType.add(jpg);
        imageType.add(tiff);




        menuBar.add(iconLabel);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(plug);
        menuBar.add(settings);
        menuBar.add(help);


        SpringLayout buttonsLayout = new SpringLayout();
        menuBar.setLayout(buttonsLayout);
        buttonsLayout.putConstraint(SpringLayout.WEST, iconLabel,3,SpringLayout.WEST, menuBar);
        buttonsLayout.putConstraint(SpringLayout.WEST, file,30,SpringLayout.WEST, iconLabel);
        buttonsLayout.putConstraint(SpringLayout.WEST, edit,40,SpringLayout.WEST, file);
        buttonsLayout.putConstraint(SpringLayout.WEST, view,40,SpringLayout.WEST, edit);
        buttonsLayout.putConstraint(SpringLayout.WEST, plug,40,SpringLayout.WEST, view);
        buttonsLayout.putConstraint(SpringLayout.WEST, settings,40,SpringLayout.WEST, plug);
        buttonsLayout.putConstraint(SpringLayout.WEST, help,57,SpringLayout.WEST, settings);

        buttonsLayout.putConstraint(SpringLayout.EAST, Exit, -0, SpringLayout.EAST, menuBar);
        buttonsLayout.putConstraint(SpringLayout.EAST, FullSize, -45, SpringLayout.EAST, Exit);
        buttonsLayout.putConstraint(SpringLayout.EAST, Roll, -45, SpringLayout.EAST, FullSize);



        menuBar.add(Roll);
        menuBar.add(FullSize);
        menuBar.add(Exit);




        this.setJMenuBar(menuBar);




        //Панель инструментов

        if(service.ThemeReader() != "dark" &&service.ThemeReader() != "light"){
        jPanel1.setBackground(new Color(139, 139, 139));}
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 500));
        jPanel1.setMaximumSize(new java.awt.Dimension(300, this.getHeight()));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 500));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        openButton.setSize(60,40);
        openButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        openButton.setEnabled(false);
        openButton.setText("Preview");
        openButton.addActionListener(openButtonListener());




        if(service.ThemeReader()!="dark" && service.ThemeReader()!="light"){
        LoadInImage.setBackground(new Color(0, 51, 255));
        LoadInImage.setForeground(new Color(255, 255, 255));}
        LoadInImage.setText("Load");
        LoadInImage.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        LoadInImage.addActionListener(LoadInImageActionListener());

        SliderVividness.setBackground(new Color(102, 102, 102));
        SliderVividness.setMaximum(255);
        SliderVividness.setMinimum(0);
        SliderVividness.setValue(255);
        SliderVividness.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SliderVividness.addChangeListener(VividnessListener());

        sliderContrast.setBackground(new Color(102, 102, 102));
        sliderContrast.setMaximum(100);
        sliderContrast.setMinimum(0);
        sliderContrast.setValue(0);
        sliderContrast.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sliderContrast.addChangeListener(ContrastListener());

        buttonToDark.setBackground(new Color(102, 102, 102));
        buttonToDark.setText("-");
        buttonToDark.setPreferredSize(new java.awt.Dimension(30,30));
        buttonToDark.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonToDark.addActionListener(toDarkListener());

        buttonToLight.setBackground(new Color(102, 102, 102));
        buttonToLight.setText("+");
        buttonToLight.setPreferredSize(new java.awt.Dimension(30,30));
        buttonToLight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonToLight.addActionListener(toLightListener());


        jLabel1.setText("Vividness");
        jLabel2.setText("Contrast");
        jLabel6.setText("Brightness");
        if(service.ThemeReader() != "dark" && service.ThemeReader() != "light") {
            jLabel1.setForeground(new Color(20, 20, 20));
            jLabel2.setForeground(new Color(20, 20, 20));
            jLabel3.setForeground(new Color(20, 20, 20));
            jLabel4.setForeground(new Color(20, 20, 20));
            jLabel5.setForeground(new Color(20, 20, 20));
            jLabel6.setForeground(new Color(20, 20, 20));
        }


        if(service.ThemeReader()!="dark" && service.ThemeReader()!="light"){MirrorH.setBackground(new Color(0, 51, 255));
        MirrorH.setForeground(new Color(255, 255, 255));}
        MirrorH.setPreferredSize(new java.awt.Dimension(90,30));
        MirrorH.setText("Mirror height");
        MirrorH.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        MirrorH.addActionListener(MirrorHListener());

        if(service.ThemeReader()!="dark" && service.ThemeReader()!="light"){MirrorW.setBackground(new Color(0, 51, 255));
        MirrorW.setForeground(new Color(255, 255, 255));}
        MirrorW.setPreferredSize(new java.awt.Dimension(90,30));
        MirrorW.setText("Mirror width");
        MirrorW.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        MirrorW.addActionListener(MirrorWListener());





        SliderToBlue.setBackground(new Color(0, 102, 204));
        SliderToBlue.setForeground(new Color(0, 0, 0));
        SliderToBlue.setMaximum(255);
        SliderToBlue.setMinimum(0);
        SliderToBlue.setValue(0);
        SliderToBlue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SliderToBlue.addChangeListener(BlueListener());

        SliderToRed.setBackground(new Color(204, 0, 0));
        SliderToRed.setForeground(new Color(0, 0, 0));
        SliderToRed.setMaximum(255);
        SliderToRed.setMinimum(0);
        SliderToRed.setValue(0);
        SliderToRed.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SliderToRed.addChangeListener(RedListener());

        jLabel3.setText("BLUE");


        jLabel4.setText("RED");


        SliderToGreen.setBackground(new Color(0, 153, 0));
        SliderToGreen.setForeground(new Color(0, 0, 0));
        SliderToGreen.setMaximum(255);
        SliderToGreen.setMinimum(0);
        SliderToGreen.setValue(0);
        SliderToGreen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SliderToGreen.addChangeListener(GreenListener());

        jLabel5.setText("GREEN");


        if(service.ThemeReader()!="dark" && service.ThemeReader()!="light"){ctrlZ.setBackground(new Color(0, 51, 255));
        ctrlZ.setForeground(new Color(255, 255, 255));}
        ctrlZ.setSize(50,50);
        ctrlZ.setText("Ctrl + Z");
        ctrlZ.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        ctrlZ.addActionListener(ctrlZListener());


        if(service.ThemeReader()!="dark" && service.ThemeReader()!="light"){SaveImage.setBackground(new Color(0, 51, 255));
        SaveImage.setForeground(new Color(255, 255, 255));}
        SaveImage.setSize(50,50);
        SaveImage.setText("Save");
        SaveImage.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        SaveImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveImageActionPerformed(evt);
            }
        });

        if(service.ThemeReader()!="dark" && service.ThemeReader()!="light"){apply.setBackground(new Color(0, 51, 255));
        apply.setForeground(new Color(255, 255, 255));}
        apply.setSize(60,40);
        apply.setPreferredSize(new java.awt.Dimension(60, 40));
        apply.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        apply.setText("Apply");
        apply.addActionListener(applyListener());




        photoField.setBorder(null);
        photoField.setMaximumSize(new java.awt.Dimension( (int) screenSize.getWidth()-jPanel1.getPreferredSize().width-20, (int) screenSize.getHeight()));
        photoField.setMinimumSize(new java.awt.Dimension( 500, 500));
        photoField.setPreferredSize(new java.awt.Dimension((int) screenSize.getWidth()-jPanel1.getPreferredSize().width-20, 500));

        BufferedImage pim = new BufferedImage(photoField.getPreferredSize().width, (int) screenSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < pim.getWidth(); i++)
        {
            for (int j = 0; j < pim.getHeight(); j++)
            {
                Color def = new Color(255,255,255);
                pim.setRGB(i, j, def.getRGB());
                ImageIcon picon = new ImageIcon(pim);
                photoField.setIcon(picon);
            }
        }


        BufferedImage cim = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < cim.getWidth(); i++)
        {
            for (int j = 0; j < cim.getHeight(); j++)
            {
                Color def = new Color(255,255,255);
                cim.setRGB(i, j, def.getRGB());
                ImageIcon cicon = new ImageIcon(cim);
                colorField.setIcon(cicon);
            }
        }
        colorField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        colorField.setSize(20,20);
        colorField.setPreferredSize(new java.awt.Dimension(20,20));



        this.add(jPanel1);
        //слайдеры контраст и прозрачность + надписи
        jPanel1.add(SliderVividness);
        jPanel1.add(jLabel1);
        jPanel1.add(sliderContrast);
        jPanel1.add(jLabel2);
        jPanel1.add(buttonToLight);
        jPanel1.add(jLabel6);
        jPanel1.add(buttonToDark);
        jPanel1.add(apply);

        //слайдеры RGB + надписи
        jPanel1.add(SliderToRed);
        jPanel1.add(jLabel4);
        jPanel1.add(SliderToBlue);
        jPanel1.add(jLabel3);
        jPanel1.add(SliderToGreen);
        jPanel1.add(jLabel5);
        jPanel1.add(colorField);

        //кнопки MirrorH и MirrorW
        jPanel1.add(MirrorH);
        jPanel1.add(MirrorW);

        //кнопки ctrlZ, Load и Save
        jPanel1.add(ctrlZ);
        jPanel1.add(LoadInImage);
        jPanel1.add(SaveImage);
        jPanel1.add(openButton);

        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, SliderVividness, 5, SpringLayout.NORTH, jPanel1);
        springLayout.putConstraint(SpringLayout.WEST, SliderVividness, 70, SpringLayout.WEST, jPanel1);
        springLayout.putConstraint(SpringLayout.WEST, jLabel1, -57, SpringLayout.WEST, SliderVividness);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel1, 1, SpringLayout.NORTH, SliderVividness);

        springLayout.putConstraint(SpringLayout.NORTH, sliderContrast, 25, SpringLayout.NORTH, SliderVividness);
        springLayout.putConstraint(SpringLayout.WEST, sliderContrast, 0, SpringLayout.WEST, SliderVividness);
        springLayout.putConstraint(SpringLayout.WEST, jLabel2, -57, SpringLayout.WEST, sliderContrast);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel2, 1, SpringLayout.NORTH, sliderContrast);

        springLayout.putConstraint(SpringLayout.NORTH, buttonToDark, 25, SpringLayout.NORTH, sliderContrast);
        springLayout.putConstraint(SpringLayout.WEST, buttonToDark, 0, SpringLayout.WEST, sliderContrast);

        springLayout.putConstraint(SpringLayout.NORTH, buttonToLight, 0, SpringLayout.NORTH, buttonToDark);
        springLayout.putConstraint(SpringLayout.EAST, buttonToLight, 0, SpringLayout.EAST, sliderContrast);

        springLayout.putConstraint(SpringLayout.WEST, jLabel6, 72, SpringLayout.WEST, buttonToDark);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel6, 7, SpringLayout.NORTH, buttonToDark);



        springLayout.putConstraint(SpringLayout.NORTH, SliderToRed, 50, SpringLayout.NORTH, buttonToDark);
        springLayout.putConstraint(SpringLayout.WEST, SliderToRed, 0, SpringLayout.WEST, buttonToDark);
        springLayout.putConstraint(SpringLayout.WEST, jLabel4, -45, SpringLayout.WEST, SliderToRed);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel4, 1, SpringLayout.NORTH, SliderToRed);

        springLayout.putConstraint(SpringLayout.NORTH, SliderToBlue, 25, SpringLayout.NORTH, SliderToRed);
        springLayout.putConstraint(SpringLayout.WEST, SliderToBlue, 0, SpringLayout.WEST, SliderToRed);
        springLayout.putConstraint(SpringLayout.WEST, jLabel3, -45, SpringLayout.WEST, SliderToBlue);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel3, 1, SpringLayout.NORTH, SliderToBlue);

        springLayout.putConstraint(SpringLayout.NORTH, SliderToGreen, 25, SpringLayout.NORTH, SliderToBlue);
        springLayout.putConstraint(SpringLayout.WEST, SliderToGreen, 0, SpringLayout.WEST, SliderToBlue);
        springLayout.putConstraint(SpringLayout.WEST, jLabel5, -45, SpringLayout.WEST, SliderToGreen);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel5, 1, SpringLayout.NORTH, SliderToGreen);

        springLayout.putConstraint(SpringLayout.WEST, colorField, 0, SpringLayout.WEST, SliderToGreen);
        springLayout.putConstraint(SpringLayout.NORTH, colorField, 30, SpringLayout.NORTH, SliderToGreen);

        springLayout.putConstraint(SpringLayout.WEST, openButton, 40, SpringLayout.WEST, colorField);
        springLayout.putConstraint(SpringLayout.NORTH, openButton, -5, SpringLayout.NORTH, colorField);
        springLayout.putConstraint(SpringLayout.SOUTH, openButton, 5, SpringLayout.SOUTH, colorField);

        springLayout.putConstraint(SpringLayout.WEST, apply, 10, SpringLayout.EAST, openButton);
        springLayout.putConstraint(SpringLayout.SOUTH, apply, 0, SpringLayout.SOUTH, openButton);
        springLayout.putConstraint(SpringLayout.NORTH, apply, 0, SpringLayout.NORTH, openButton);

        springLayout.putConstraint(SpringLayout.WEST, MirrorH, 0, SpringLayout.WEST, colorField);
        springLayout.putConstraint(SpringLayout.NORTH, MirrorH, 40, SpringLayout.NORTH, colorField);

        springLayout.putConstraint(SpringLayout.WEST, MirrorW, 0, SpringLayout.WEST, MirrorH);
        springLayout.putConstraint(SpringLayout.NORTH, MirrorW, 40, SpringLayout.NORTH, MirrorH);

        springLayout.putConstraint(SpringLayout.EAST, ctrlZ, -21, SpringLayout.EAST, jPanel1);
        springLayout.putConstraint(SpringLayout.SOUTH, ctrlZ, -5, SpringLayout.SOUTH, jPanel1);

        springLayout.putConstraint(SpringLayout.WEST, LoadInImage, 21, SpringLayout.WEST, jPanel1);
        springLayout.putConstraint(SpringLayout.SOUTH, LoadInImage, -35, SpringLayout.SOUTH, jPanel1);

        springLayout.putConstraint(SpringLayout.WEST, SaveImage, 21, SpringLayout.WEST, jPanel1);
        springLayout.putConstraint(SpringLayout.SOUTH, SaveImage, -5, SpringLayout.SOUTH, jPanel1);

        jPanel1.setLayout(springLayout);

        SpringLayout panelsLayout = new SpringLayout();
        this.add(photoField);

        panelsLayout.putConstraint(SpringLayout.WEST, jPanel1,2,SpringLayout.WEST, this.getContentPane());
        panelsLayout.putConstraint(SpringLayout.NORTH, jPanel1,0,SpringLayout.NORTH, this.getContentPane());
        panelsLayout.putConstraint(SpringLayout.SOUTH, jPanel1,0,SpringLayout.SOUTH, this.getContentPane());

        panelsLayout.putConstraint(SpringLayout.EAST, photoField,-2,SpringLayout.EAST, this.getContentPane());
        panelsLayout.putConstraint(SpringLayout.NORTH, photoField,0,SpringLayout.NORTH, this.getContentPane());
        panelsLayout.putConstraint(SpringLayout.SOUTH, photoField,0,SpringLayout.SOUTH, this.getContentPane());
        panelsLayout.putConstraint(SpringLayout.WEST, photoField,2,SpringLayout.EAST, jPanel1);

        this.setLayout(panelsLayout);


        OpenDialog.setAlwaysOnTop(true);
        OpenDialog.setResizable(false);
        if(im == null){
            SliderToGreen.setEnabled(false);
            SliderToRed.setEnabled(false);
            SliderToBlue.setEnabled(false);
            SliderVividness.setEnabled(false);
            sliderContrast.setEnabled(false);
            SaveImage.setEnabled(false);
            MirrorW.setEnabled(false);
            MirrorH.setEnabled(false);
            ctrlZ.setEnabled(false);
            openButton.setEnabled(false);
            apply.setEnabled(false);
            buttonToDark.setEnabled(false);
            buttonToLight.setEnabled(false);
            sliderContrast.setEnabled(false);
        }

    }

    private ActionListener OpenApplyButtonListener() {
        return a->{
            im = Plug.Open_Load();
            Plug.ctrlZ_Save(im);
            display();
        };
    }

    private ActionListener openButtonListener() {
        return a->{
            if(OpenDialog.isVisible() == false) {
                OpenDialog.setVisible(true);
                OpenLabel.setText(null);
                display();
            }
            else{
                OpenDialog.setVisible(false);
            }
        };
    }

    private void clear(){
        if(clearCache.isSelected() == true) {
            service.ctrlZNum(0);
            service.saveNum(0);
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
    }

    private ActionListener clearCacheButtonListener() {
        return a->{
            service.cleanButton();
        };
    }

    private ChangeListener pngListener() {
        return a->{
            if(png.isSelected()) {
                service.ImageTypeWriter("png");
                jpg.setSelected(false);
                tiff.setSelected(false);
            }
        };
    }

    private ChangeListener jpgListener() {
        return a->{
            if(jpg.isSelected()) {
                service.ImageTypeWriter("jpg");
                png.setSelected(false);
                tiff.setSelected(false);
            }
        };
    }

    private ChangeListener tiffListener() {
        return a->{
            if(tiff.isSelected()) {
                service.ImageTypeWriter("tiff");
                png.setSelected(false);
                jpg.setSelected(false);
            }
        };
    }

    private ChangeListener cek5Listener() {
        return a->{
            if(cek5.isSelected()==true)
            {
                service.cekNum(5000);
                service.cekSel(5);
                cek10.setSelected(false);
                cek30.setSelected(false);
                cek60.setSelected(false);
                min5.setSelected(false);
            }

        };
    }

    private ChangeListener cek10Listener() {
        return a->{
            if(cek10.isSelected()==true)
            {
                service.cekNum(10000);
                service.cekSel(10);
                cek5.setSelected(false);
                cek30.setSelected(false);
                cek60.setSelected(false);
                min5.setSelected(false);
            }
        };
    }

    private ChangeListener cek30Listener() {
        return a->{
            if(cek30.isSelected()==true)
            {
                service.cekNum(30000);
                service.cekSel(30);
                cek5.setSelected(false);
                cek10.setSelected(false);
                cek60.setSelected(false);
                min5.setSelected(false);
            }
        };
    }

    private ChangeListener cek60Listener() {
        return a->{
            if(cek60.isSelected()==true)
            {
                service.cekNum(60000);
                service.cekSel(60);
                cek5.setSelected(false);
                cek10.setSelected(false);
                cek30.setSelected(false);
                min5.setSelected(false);
            }
        };
    }

    private ChangeListener min5Listener() {
        return a->{
            if(min5.isSelected()==true)
            {
                service.cekNum(300000);
                service.cekSel(300);
                cek5.setSelected(false);
                cek10.setSelected(false);
                cek30.setSelected(false);
                cek60.setSelected(false);
            }
        };
    }

    private ActionListener clearCacheListener() {
        return a->{
            service.ClearWriter(clearCache.isSelected());
        };
    }

    private ChangeListener enableListener() {
        return a->{
            service.EnableWriter(Enable.isSelected());
          if(Enable.isSelected() && im !=null)
          {
              try {
                  Plug.Auto_Save();
              } catch (IOException | InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
    }

    private ChangeListener autoSaveSliderListener() {
        return a->{
           service.autoSaveNum(AutoSaveSlider.getValue());
            autoSaveLabel.setText("Number: "+Integer.toString(service.autoSaveNumGet()));

        };
    }




    private void fullSize() {
            if(this.getWidth() <Toolkit.getDefaultToolkit().getScreenSize().width) {W = this.getWidth();}
            if(this.getHeight() <Toolkit.getDefaultToolkit().getScreenSize().height-40) {H = this.getHeight();}
            if(this.getX() != 0) {X = this.getX();}
            if(this.getY() != 0) {Y = this.getY();}

            if (this.getWidth() == Toolkit.getDefaultToolkit().getScreenSize().width && this.getHeight() == Toolkit.getDefaultToolkit().getScreenSize().height-40) {
                this.setBounds(X,Y,W,H);
            }
            else{
                this.setLocation(0,0);
                this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-40);

            }
    }

    private void roll() {
        this.setExtendedState(this.ICONIFIED);
    }


    private ActionListener defaultListener() {
        return a->{
           def();
        };
    }
    private void def(){
        service.ThemeWriter("default");

        Exit.setIcon(new ImageIcon("icons//ExitDefault.png"));
        FullSize.setIcon(new ImageIcon("icons//FullSizeDefault.png"));
        Roll.setIcon(new ImageIcon("icons//RollDefault.png"));

        file.setForeground(new Color(25, 25, 25));
        edit.setForeground(new Color(25, 25, 25));
        view.setForeground(new Color(25, 25, 25));
        plug.setForeground(new Color(25, 25, 25));
        settings.setForeground(new Color(25, 25, 25));
        help.setForeground(new Color(25, 25, 25));

        Dark.setSelected(false);
        Light.setSelected(false);

        this.getContentPane().setBackground(new Color(255, 255, 255));

        menuBar.setBackground(new Color(102, 102, 102));

        jPanel1.setBackground(new Color(139, 139, 139));

        jLabel1.setForeground(new Color(20, 20, 20));
        jLabel2.setForeground(new Color(20, 20, 20));
        jLabel3.setForeground(new Color(20, 20, 20));
        jLabel4.setForeground(new Color(20, 20, 20));
        jLabel5.setForeground(new Color(20, 20, 20));
        jLabel6.setForeground(new Color(20, 20, 20));


        LoadInImage.setBackground(new Color(0, 51, 255));
        LoadInImage.setForeground(new Color(255, 255, 255));

        openButton.setBackground(new Color(0, 51, 255));
        openButton.setForeground(new Color(255, 255, 255));

        apply.setBackground(new Color(0, 51, 255));
        apply.setForeground(new Color(255, 255, 255));

        ctrlZ.setBackground(new Color(0, 51, 255));
        ctrlZ.setForeground(new Color(255, 255, 255));

        SaveImage.setBackground(new Color(0, 51, 255));
        SaveImage.setForeground(new Color(255, 255, 255));

        MirrorH.setBackground(new Color(0, 51, 255));
        MirrorH.setForeground(new Color(255, 255, 255));

        MirrorW.setBackground(new Color(0, 51, 255));
        MirrorW.setForeground(new Color(255, 255, 255));

        SliderVividness.setBackground(new Color(102, 102, 102));

        sliderContrast.setBackground(new Color(102, 102, 102));
    }

    private ActionListener darkListener() {
        return a->{
            dark();
        };
    }
    private void dark()
    {
        service.ThemeWriter("dark");
        Exit.setIcon(new ImageIcon("icons//ExitDark.png"));
        FullSize.setIcon(new ImageIcon("icons//FullSizeDark.png"));
        Roll.setIcon(new ImageIcon("icons//RollDark.png"));

        file.setForeground(new Color(255, 255, 255));
        edit.setForeground(new Color(255, 255, 255));
        view.setForeground(new Color(255, 255, 255));
        plug.setForeground(new Color(255, 255, 255));
        settings.setForeground(new Color(255, 255, 255));
        help.setForeground(new Color(255, 255, 255));

        Default.setSelected(false);
        Light.setSelected(false);
        this.getContentPane().setBackground(new Color(30, 30, 30));
        menuBar.setBackground(new Color(30, 30, 30));

        jPanel1.setBackground(new Color(60, 60, 60));

        jLabel1.setForeground(new Color(250, 250, 250));
        jLabel2.setForeground(new Color(250, 250, 250));
        jLabel3.setForeground(new Color(250, 250, 250));
        jLabel4.setForeground(new Color(250, 250, 250));
        jLabel5.setForeground(new Color(250, 250, 250));
        jLabel6.setForeground(new Color(250, 250, 250));


        LoadInImage.setBackground(new Color(180, 180, 180));
        LoadInImage.setForeground(new Color(20, 20, 20));

        openButton.setBackground(new Color(180, 180, 180));
        openButton.setForeground(new Color(20, 20, 20));

        apply.setBackground(new Color(180, 180, 180));
        apply.setForeground(new Color(20, 20, 20));

        ctrlZ.setBackground(new Color(180, 180, 180));
        ctrlZ.setForeground(new Color(20, 20, 20));

        SaveImage.setBackground(new Color(180, 180, 180));
        SaveImage.setForeground(new Color(20, 20, 20));

        MirrorH.setBackground(new Color(180, 180, 180));
        MirrorH.setForeground(new Color(20, 20, 20));

        MirrorW.setBackground(new Color(180, 180, 180));
        MirrorW.setForeground(new Color(20, 20, 20));

        SliderVividness.setBackground(new Color(102, 102, 102));

        sliderContrast.setBackground(new Color(102, 102, 102));
    }



    private ActionListener lightListener() {
        return a->{
           light();
        };
    }

    private void light(){
        //todo
        service.ThemeWriter("light");
        Exit.setIcon(new ImageIcon("icons//ExitLight.png"));
        FullSize.setIcon(new ImageIcon("icons//FullSizeLight.png"));
        Roll.setIcon(new ImageIcon("icons//RollLight.png"));

        file.setForeground(new Color(0, 0, 0));
        edit.setForeground(new Color(0, 0, 0));
        view.setForeground(new Color(0, 0, 0));
        plug.setForeground(new Color(0, 0, 0));
        settings.setForeground(new Color(0, 0, 0));
        help.setForeground(new Color(0, 0, 0));

        Default.setSelected(false);
        Dark.setSelected(false);
        this.getContentPane().setBackground(new Color(255, 255, 255));


        menuBar.setBackground(new Color(255, 255, 255));

        jPanel1.setBackground(new Color(255, 255, 255));

        jLabel1.setForeground(new Color(20, 20, 20));
        jLabel2.setForeground(new Color(20, 20, 20));
        jLabel3.setForeground(new Color(20, 20, 20));
        jLabel4.setForeground(new Color(20, 20, 20));
        jLabel5.setForeground(new Color(20, 20, 20));
        jLabel6.setForeground(new Color(20, 20, 20));


        LoadInImage.setBackground(new Color(60, 60, 60));
        LoadInImage.setForeground(new Color(255, 255, 255));

        openButton.setBackground(new Color(60, 60, 60));
        openButton.setForeground(new Color(255, 255, 255));

        apply.setBackground(new Color(60, 60, 60));
        apply.setForeground(new Color(255, 255, 255));

        ctrlZ.setBackground(new Color(60, 60, 60));
        ctrlZ.setForeground(new Color(255, 255, 255));


        SaveImage.setBackground(new Color(60, 60, 60));
        SaveImage.setForeground(new Color(255, 255, 255));

        MirrorH.setBackground(new Color(60, 60, 60));
        MirrorH.setForeground(new Color(255, 255, 255));

        MirrorW.setBackground(new Color(60, 60, 60));
        MirrorW.setForeground(new Color(255, 255, 255));

        SliderVividness.setBackground(new Color(102, 102, 102));

        sliderContrast.setBackground(new Color(102, 102, 102));
    }


    private ActionListener ctrlZListener() {
        return a->{
            if(OpenDialog.isVisible() ==false){

                if(Plug.getCtrlZNum()>1){
            ctrlZNum = Plug.getCtrlZNum()-1;}
            Service.ctrlZNum(ctrlZNum);
            SliderToRed.setValue(0);
            SliderToBlue.setValue(0);
            SliderToGreen.setValue(0);
            SliderVividness.setValue(255);
            im = Plug.ctrlZ_Load(ctrlZNum);
            }
            if(OpenDialog.isVisible() == true){
            oIm = Plug.ctrlZ_Load(1);
            display();}

            display();
        };
    }


    private ActionListener noListener()
    {
        return a->{
            clear();
            System.exit(0);
        };
    }

    private ActionListener yesListener() {
        return a->{
            service.save(jPanel1, im);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clear();
            System.exit(0);
        };
    }

    private ActionListener applyListener() {
        return a->{
            ctrlZ.setEnabled(true);
            if(OpenDialog.isVisible() == true)
            {
                oIm = service.apply(oIm, SliderToRed.getValue(), SliderToGreen.getValue(), SliderToBlue.getValue(), SliderVividness.getValue());
                display();
                Plug.Open_Save(oIm);
            }
            else{
            im = service.apply(im, SliderToRed.getValue(), SliderToGreen.getValue(), SliderToBlue.getValue(), SliderVividness.getValue());
            Plug.ctrlZ_Save(im);
            display();}
        };
    }

    private ChangeListener GreenListener() {
        return a ->{
            apply.setEnabled(true);
            ccolor(SliderToRed.getValue(), SliderToGreen.getValue(), SliderToBlue.getValue());
        };
    }

    private ChangeListener RedListener() {
        return a ->{
            apply.setEnabled(true);
            ccolor(SliderToRed.getValue(), SliderToGreen.getValue(), SliderToBlue.getValue());
        };
    }

    private ChangeListener BlueListener() {
        return a ->{
            apply.setEnabled(true);
            ccolor(SliderToRed.getValue(), SliderToGreen.getValue(), SliderToBlue.getValue());
        };
    }
    private void ccolor(int r, int g, int b)
    {
        BufferedImage cim = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        cim = service.colorIm(cim, r, g, b);
        ImageIcon cicon = new ImageIcon(cim);
        colorField.setIcon(cicon);
    }



    private ChangeListener ContrastListener() {
        //ToDo
        return a ->{
            if(OpenDialog.isVisible() == true){
                oIm = service.slider2(oIm,sliderContrast.getValue());
                display();
            }
            else {
            service.slider2(im, sliderContrast.getValue());
            display();}
        };
    }

    private ActionListener toDarkListener(){
        return a->{
            if(OpenDialog.isVisible() == true){
                oIm = service.darkness(oIm);
            }
            if(OpenDialog.isVisible() == false){
            im = service.darkness(im);
            }
            display();
            ctrlZ.setEnabled(true);
        };
    }

    private ActionListener toLightListener(){
        return a->{
            if(OpenDialog.isVisible() == true){
                oIm = service.lightness(oIm);

            }
            if(OpenDialog.isVisible() == false){
            im = service.lightness(im);
            }
            display();
            ctrlZ.setEnabled(true);
        };
    }

    private ChangeListener VividnessListener() {
        return a->{
            ctrlZ.setEnabled(true);
            int current = SliderVividness.getValue();
            if(OpenDialog.isVisible() == true){
                oIm = service.vivdness(oIm,current);

                display();
            }
            else {
                im = service.vivdness(im, current);
                display();
            }
        };
    }


    private ActionListener LoadInImageActionListener()
    {
        return a->{
            if(service.ImageTypeReader() == "png") {png.setSelected(true);}
            if(service.ImageTypeReader() == "jpg") {jpg.setSelected(true);}
            if(service.ImageTypeReader() == "tiff") {tiff.setSelected(true);}
            SliderToGreen.setEnabled(true);
            SliderToRed.setEnabled(true);
            SliderToBlue.setEnabled(true);
            SliderVividness.setEnabled(true);
            sliderContrast.setEnabled(true);
            SaveImage.setEnabled(true);
            MirrorW.setEnabled(true);
            MirrorH.setEnabled(true);
            ctrlZ.setEnabled(true);
            openButton.setEnabled(true);
            apply.setEnabled(true);
            buttonToDark.setEnabled(true);
            buttonToLight.setEnabled(true);
            sliderContrast.setEnabled(true);
            openButton.setEnabled(true);
            im = service.load(jPanel1, im);
            oIm = Plug.ctrlZ_Load(Plug.getCtrlZNum());
//            Plug.ctrlZ_Save(im);
            service.width(im);
            service.height(im);
            display();
        };
    }

   public void MoveUndecorated(final Main frame) {
       menuBar.addMouseListener(new MouseAdapter() {
           public void mousePressed(MouseEvent e) {
               positionX = e.getX();
               positionY = e.getY();
           }
       });

       menuBar.addMouseMotionListener(new MouseMotionAdapter() {
           @Override
           public void mouseDragged(MouseEvent e) {
               int thisX = frame.getLocation().x;
               int thisY = frame.getLocation().y;

               int xMoved = (thisX + e.getX()) - (thisX + positionX);
               int yMoved = (thisY + e.getY()) - (thisY + positionY);

               int x = thisX + xMoved;
               int y = thisY + yMoved;
               if(frame.getWidth() >= Toolkit.getDefaultToolkit().getScreenSize().width-600 && frame.getHeight() >= Toolkit.getDefaultToolkit().getScreenSize().height-300){
                   frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-600,Toolkit.getDefaultToolkit().getScreenSize().height-300);
                   if(positionX>=Toolkit.getDefaultToolkit().getScreenSize().width-600-Exit.getHeight()*3)
                   {
                       frame.setLocation(positionX+x-Toolkit.getDefaultToolkit().getScreenSize().width+600+135,y);
                   }
                   else frame.setLocation(x,y);
               }
                else{frame.setLocation(x, y);}

            }
        });
    }



    private ActionListener MirrorHListener() {

        return a-> {
            if(OpenDialog.isVisible() == true){
                oIm = service.mirHig(oIm);
//                Plug.Open_Save(oIm);
            }
            else {
                im = service.mirHig(im);
                Plug.ctrlZ_Save(im);
            }
            display();
        };
    }

    private ActionListener MirrorWListener() {
        return a-> {
            if(OpenDialog.isVisible() == true){
                oIm = service.mirWih(oIm);
//                Plug.Open_Save(oIm);
            }
            else {
                im = service.mirWih(im);
                Plug.ctrlZ_Save(im);
            }
            display();
        };
    }


    private void SaveImageActionPerformed(java.awt.event.ActionEvent evt) {
        service.save(jPanel1, im);
    }


    private void display() {
        //todo
        Auto_Save_image = im;

        if (im == null) return;
        ImageIcon icon = new ImageIcon(im.getScaledInstance(photoField.getWidth(),
        photoField.getHeight(), Image.SCALE_SMOOTH));
        photoField.setIcon(icon);
        if(oIm!=null){
            ImageIcon icon1 = new ImageIcon(oIm.getScaledInstance(OpenLabel.getWidth(), OpenLabel.getHeight(), Image.SCALE_SMOOTH));
            OpenLabel.setIcon(icon1);
        }
    }

    private javax.swing.JButton LoadInImage;
    private javax.swing.JButton MirrorH;
    private javax.swing.JButton MirrorW;
    private javax.swing.JButton Profile;
    private javax.swing.JButton SaveImage;
    private javax.swing.JButton ansYes;
    private javax.swing.JButton ansNo;
    private javax.swing.JButton apply;
    private javax.swing.JButton ctrlZ;
    private javax.swing.JSlider SliderToBlue;
    private javax.swing.JSlider SliderToGreen;
    private javax.swing.JSlider SliderToRed;
    private javax.swing.JSlider SliderVividness;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel DialogLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JDialog Jdialog;
    private javax.swing.JButton openButton;
    private JDialog OpenDialog;
    private javax.swing.JLabel OpenLabel;
    private javax.swing.JButton OpenApplyButton;
    private javax.swing.JLabel photoField;
    private javax.swing.JLabel colorField;
    private javax.swing.JSlider sliderContrast;
    private javax.swing.JButton buttonToDark;
    private javax.swing.JButton buttonToLight;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JMenu file;
    private javax.swing.JMenu edit;

    private javax.swing.JMenu view;
    private javax.swing.JRadioButtonMenuItem Default;
    private javax.swing.JRadioButtonMenuItem Dark;
    private javax.swing.JRadioButtonMenuItem Light;

    private javax.swing.JMenu plug;

    private javax.swing.JMenu autoSave;
    private javax.swing.JRadioButtonMenuItem Enable;
    public javax.swing.JSlider AutoSaveSlider;
    private javax.swing.JLabel autoSaveLabel;
    private javax.swing.JRadioButtonMenuItem cek5;
    private javax.swing.JRadioButtonMenuItem cek10;
    private javax.swing.JRadioButtonMenuItem cek30;
    private javax.swing.JRadioButtonMenuItem cek60;
    private javax.swing.JRadioButtonMenuItem min5;
    private javax.swing.JMenu cache;
    private javax.swing.JCheckBox clearCache;
    private javax.swing.JButton clearCacheButton;


    private javax.swing.JMenu settings;
    private javax.swing.JMenu imageType;
    private javax.swing.JRadioButtonMenuItem png;
    private javax.swing.JRadioButtonMenuItem jpg;
    private javax.swing.JRadioButtonMenuItem tiff;
    private javax.swing.JMenu help;



    private javax.swing.JLabel timeLabel;

    private javax.swing.JButton Exit;
    private javax.swing.JButton Roll;
    private javax.swing.JButton FullSize;




}

