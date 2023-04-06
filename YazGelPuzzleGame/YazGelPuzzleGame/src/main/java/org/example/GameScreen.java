package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends JFrame {
    // DEĞİŞKENLER
    List<JButton> listForButtons = new ArrayList<>();
    String username;
    String image;
    int count = 0;
    Integer score = 100;
    int count1;
    int b1cnt = 0;
    int b2cnt = 0;
    int b3cnt = 0;
    JButton firstButton;
    JButton secondButton;

    //BUTON KONTROL FONKSİYONU
    public void buttonClick(java.awt.event.ActionEvent e) throws IOException {

        JButton clickedButton = (JButton) e.getSource();

        if (firstButton == null) {
            firstButton = clickedButton;

        } else {
            if (!firstButton.getName().equals(clickedButton.getName())) {
                secondButton = clickedButton;

                Icon iconn1 = firstButton.getIcon();
                Icon iconn2 = secondButton.getIcon();

                firstButton.setIcon(iconn2);
                secondButton.setIcon(iconn1);

                boolean b1 = isMatch(firstButton);

                boolean b2 = isMatch(secondButton);

                if (b1 == true && b2 == true) {
                    count1++;

                }
                if (b1 == true) {
                    firstButton.setEnabled(false);
                    count++;
                    b1cnt++;
                }
                if (b2 == true) {
                    secondButton.setEnabled(false);
                    count++;
                    b2cnt++;

                } else {
                    b3cnt++;
                }

                firstButton = null;
                secondButton = null;
            }
        }

        if (count == 16) {
            b1cnt -= count1;
            b2cnt -= count1;
            score += count1 * 4;
            score -= b1cnt * 2;
            score -= b2cnt * 2;
            score -= b3cnt * 4;

            if (score > 100) {
                score = 100;
            } else if (score < 0) {
                score = 0;
            }
            scoreDynamicLabel.setText(score.toString());
            String line = username + ":" + score.toString();
            bestScoreWriter(line);
            BufferedImage img = ImageIO.read(new File(image));
            Image img1 = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(img1);
            JOptionPane.showMessageDialog(null, "", "TEBRİKLER", 0, ico);

        }

    }

    //OYUNCU VE SKORLARI DOSYAYA YAZDIRAN FONKSİYON
    public void bestScoreWriter(String line) throws IOException {
        File file = new File("bestscore.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(line.toUpperCase());
        bufferedWriter.newLine();
        bufferedWriter.close();
        System.out.println("Dosya Yazdırma İşlemi Başarılı");
    }

    //İSİM KONTROL FONKSİYONU
    public boolean nameIsUse(String name) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("bestscore.txt"));
        String line = br.readLine();

        while (line != null && line != " ") {
            String spl[] = line.split(":");
            if (name.equalsIgnoreCase(spl[0])) {
                JOptionPane.showMessageDialog(null, "BU OYUNCU ADI KULLANILIYOR!", "", 2);
                return true;
            }
            line = br.readLine();
        }
        return false;
    }


    // RESIM EKLEME ANA RESIM
    public Image imageForGame(int i) {
        try {
            BufferedImage bufimage1 = ImageIO.read(new File("images/images" + Integer.toString(i) + ".jpg"));
            Image image1 = bufimage1.getScaledInstance(listForButtons.get(i - 1).getWidth(), listForButtons.get(i - 1).getHeight(), Image.SCALE_SMOOTH);
            //  ImageIcon icon1 = new ImageIcon(image1);

            return image1;
        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // RESMI PARCALAYAN FONKSIYON
    public void divideFunction(String path) throws IOException {

        image = path;
        FileInputStream fs = new FileInputStream(path);

        BufferedImage bfimage = ImageIO.read(fs);

        int row = 4;
        int column = 4;
        int imageNumber = row * column;

        int newImageWidth = bfimage.getWidth() / column;
        int newImageHeight = bfimage.getHeight() / row;
        int imageNo = 0;
        BufferedImage bfimage1[] = new BufferedImage[imageNumber];

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                bfimage1[imageNo] = new BufferedImage(newImageWidth, newImageHeight, 1);
                Graphics2D g = bfimage1[imageNo].createGraphics();
                imageNo++;
                g.drawImage(bfimage, 0, 0, newImageWidth, newImageHeight, newImageWidth
                                * y, newImageHeight * x, newImageWidth * y + newImageWidth,
                        newImageHeight * x + newImageHeight, null);
                g.dispose();
            }
        }
        System.out.println("Resim parcalandi");


        for (int i = 0; i < bfimage1.length; i++) {
            int j = i + 1;
            ImageIO.write(bfimage1[i], "jpg", new File("images/images" + j + ".jpg"));

        }
    }

    // RESMI 4X4 SEKLINDE SIRALAYAN FONKSIYON
    public void buttonsTable() {

        listForButtons.add(button1);
        listForButtons.add(button2);
        listForButtons.add(button3);
        listForButtons.add(button4);
        listForButtons.add(button5);
        listForButtons.add(button6);
        listForButtons.add(button7);
        listForButtons.add(button8);
        listForButtons.add(button9);
        listForButtons.add(button10);
        listForButtons.add(button11);
        listForButtons.add(button12);
        listForButtons.add(button13);
        listForButtons.add(button14);
        listForButtons.add(button15);
        listForButtons.add(button16);

        Container con = getContentPane();
        con.setLayout(null);
        int x = -110;
        int y = 90;

        for (int i = 0; i < 16; i++) {
            x += 160;

            switch (i) {
                case 4:
                    x = 50;
                    y += 160;
                    break;
                case 8:
                    x = 50;
                    y += 160;
                    break;
                case 12:
                    x = 50;
                    y += 160;
                    break;
                default:
                    break;
            }

            listForButtons.get(i).setBounds(x, y, 160, 160);
            listForButtons.get(i).setBorder(new LineBorder(Color.WHITE));
            con.add(listForButtons.get(i));
        }

    }

    //BUTONLARA DAĞTILMIŞ RESİM
    public void imageForButtons(ArrayList list) throws IOException {

        for (int i = 0; i < 16; i++) {

            BufferedImage bufimage1 = ImageIO.read(new File("images/images" + list.get(i) + ".jpg"));
            Image image1 = bufimage1.getScaledInstance(listForButtons.get(i).getWidth(), listForButtons.get(i).getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon111 = new ImageIcon(image1);
            listForButtons.get(i).setIcon(icon111);
            boolean bool = isMatch(listForButtons.get(i));
            listForButtons.get(i).setEnabled(!bool);

            if (bool == true) {
                count++;
            }
        }

        if (count >= 1) {
            mixButton.setEnabled(false);
        } else {

            mixFunction();
        }
    }

    //BUTONLARI RANDOM DAĞITAN FONKSİYON
    public void mixFunction() throws IOException {

        count = 0;
        count1 = 0;
        score = 100;
        b1cnt = 0;
        b2cnt = 0;
        b3cnt = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        imageForButtons(list);

    }

    //RESMİN POZİSYONUYLA EŞLEŞMESİNİ KONTROL EDEN FONKSİYON
    public boolean isMatch(JButton btn) {

        boolean match = true;
        Integer bid = Integer.parseInt(btn.getName());


        Image image = imageForGame(bid);
        Image btnImage = ((ImageIcon) btn.getIcon()).getImage();

        try {

            PixelGrabber pgrab1 = new PixelGrabber(image, 0, 0, -1, -1, false);
            PixelGrabber pgrab2 = new PixelGrabber(btnImage, 0, 0, -1, -1, false);

            int[] data1 = null;

            if (pgrab1.grabPixels()) {
                int width = pgrab1.getWidth();
                int height = pgrab1.getHeight();
                data1 = new int[width * height];
                data1 = (int[]) pgrab1.getPixels();
            }

            int[] data2 = null;

            if (pgrab2.grabPixels()) {
                int width = pgrab2.getWidth();
                int height = pgrab2.getHeight();
                data2 = new int[width * height];
                data2 = (int[]) pgrab2.getPixels();
            }

            boolean isEqual = java.util.Arrays.equals(data1, data2);

            if (!isEqual) {
                match = false;
            }
        } catch (InterruptedException e) {
            e.getMessage();
        }
        return match;
    }
    //EŞLEŞEN BUTONLARI DİSABLED HALE GETİREN FONKSİYON
    public void buttonDisabled() {
        for (int i = 0; i < 16; i++) {
            listForButtons.get(i).setEnabled(false);
        }
    }

    //EN İYİ OYUNCUYU EKRANA DOSYADAN YAZDIRAN FONKSİYON
    public void bestScoreFileReader() throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("bestscore.txt"));
        String line = br.readLine();
        Integer bestScore = 0;
        String bestPlayer = null;
        while (line != null && line != " ") {
            String spl[] = line.split(":");

            if (bestScore < Integer.parseInt(spl[1])) {
                bestScore = Integer.parseInt(spl[1]);
                bestPlayer = spl[0];

            }
            line = br.readLine();
        }
        if (bestPlayer == null) {

            bestPlayerNameDynamicLabel.setText("0");
            bestScoreDynamicLabel.setText(" ");
        } else {
            bestPlayerNameDynamicLabel.setText(bestScore.toString());
            bestScoreDynamicLabel.setText(bestPlayer.toUpperCase());
        }


    }

    //GİRİŞ EKRANI
    public GameScreen() throws FileNotFoundException, IOException {
        creatUIComponents();
        mixButton.setEnabled(false);

        boolean bool = true;
        while (bool) {
            username = JOptionPane.showInputDialog(null, "OYUNCU ADI:", "OYUNCU GİRİŞ", 3);
            if (username == null) {
                System.exit(0);
            }
            if (username.isEmpty() || username.equals(" ")) {
                JOptionPane.showMessageDialog(null, "ALAN BOŞ BIRAKILAMAZ!", "ERROR", 2);
            } else {
                if (nameIsUse(username)) {
                    bool = true;
                } else {
                    bool = false;
                    playerNameDynamicLabel.setText(username.toUpperCase());
                }
            }
        }
        buttonsTable();
        buttonDisabled();
        bestScoreFileReader();
    }


    //TASARIM
    @SuppressWarnings("unchecked")
    private void creatUIComponents() {
        button1 = new JButton("button1");
        button2 = new JButton("button2");
        button3 = new JButton("button3");
        button4 = new JButton("button4");
        button5 = new JButton("button5");
        button6 = new JButton("button6");
        button7 = new JButton("button7");
        button8 = new JButton("button8");
        button9 = new JButton("button9");
        button10 = new JButton("button10");
        button11 = new JButton("button11");
        button12 = new JButton("button12");
        button13 = new JButton("button13");
        button14 = new JButton("button14");
        button15 = new JButton("button15");
        button16 = new JButton("button16");
        imageChooseButton = new JButton("imageChooseButton");
        mixButton = new JButton("mixButton");
        bestPlayerNameStaticLabel = new JLabel("bestPlayerNameStaticLabel");
        bestPlayerNameDynamicLabel = new JLabel("bestPlayerNameDynamicLabel");
        scoreStaticLabel = new JLabel("scoreStaticLabel");
        scoreDynamicLabel = new JLabel("scoreDynamicLabel");
        bestScoreStaticLabel = new JLabel("bestScoreStaticLabel");
        bestScoreDynamicLabel = new JLabel("bestScoreDynamicLabel");
        playerNameStaticLabel = new JLabel("playerNameStaticLabel");
        playerNameDynamicLabel = new JLabel("playerNameDynamicLabel");
        imageLabel = new JLabel("imageLabel");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(500, 500));

        button1.setBackground(new java.awt.Color(102, 102, 102));
        button1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button1.setName("1");
        button1.setPreferredSize(new java.awt.Dimension(160, 160));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button2.setBackground(new java.awt.Color(102, 102, 102));
        button2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button2.setName("2");
        button2.setPreferredSize(new java.awt.Dimension(160, 160));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button3.setBackground(new java.awt.Color(102, 102, 102));
        button3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button3.setName("3");
        button3.setPreferredSize(new java.awt.Dimension(160, 160));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button4.setBackground(new java.awt.Color(102, 102, 102));
        button4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button4.setName("4");
        button4.setPreferredSize(new java.awt.Dimension(160, 160));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button5.setBackground(new java.awt.Color(102, 102, 102));
        button5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button5.setName("5");
        button5.setPreferredSize(new java.awt.Dimension(160, 160));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button6.setBackground(new java.awt.Color(102, 102, 102));
        button6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button6.setName("6");
        button6.setPreferredSize(new java.awt.Dimension(160, 160));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button7.setBackground(new java.awt.Color(102, 102, 102));
        button7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button7.setName("7");
        button7.setPreferredSize(new java.awt.Dimension(160, 160));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button8.setBackground(new java.awt.Color(102, 102, 102));
        button8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button8.setName("8");
        button8.setPreferredSize(new java.awt.Dimension(160, 160));
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button9.setBackground(new java.awt.Color(102, 102, 102));
        button9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button9.setName("9");
        button9.setPreferredSize(new java.awt.Dimension(160, 160));
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button10.setBackground(new java.awt.Color(102, 102, 102));
        button10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button10.setName("10");
        button10.setPreferredSize(new java.awt.Dimension(160, 160));
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button11.setBackground(new java.awt.Color(102, 102, 102));
        button11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button11.setName("11");
        button11.setPreferredSize(new java.awt.Dimension(160, 160));
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });


        button12.setBackground(new java.awt.Color(102, 102, 102));
        button12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button12.setName("12");
        button12.setPreferredSize(new java.awt.Dimension(160, 160));
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button13.setBackground(new java.awt.Color(102, 102, 102));
        button13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button13.setName("13");
        button13.setPreferredSize(new java.awt.Dimension(160, 160));
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button14.setBackground(new java.awt.Color(102, 102, 102));
        button14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button14.setName("14");
        button14.setPreferredSize(new java.awt.Dimension(160, 160));
        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button15.setBackground(new java.awt.Color(102, 102, 102));
        button15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        button15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button15.setName("15");
        button15.setPreferredSize(new java.awt.Dimension(160, 160));
        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        button16.setBackground(new java.awt.Color(222, 222, 102));
        button16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 56, 44)));
        button16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button16.setName("16");
        button16.setPreferredSize(new java.awt.Dimension(160, 160));
        button16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    buttonClick(e);
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        imageChooseButton.setBackground(new java.awt.Color(222, 222, 222));
        imageChooseButton.setText("RESİM SEÇ");
        imageChooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(fileChooser);
                File f = fileChooser.getSelectedFile();
                String path = f.getAbsolutePath();
                try {
                    BufferedImage img = ImageIO.read(new File(path));
                    Image img1 = img.getScaledInstance(640, 640, Image.SCALE_SMOOTH);
                    ImageIcon ico = new ImageIcon(img1);
                    imageLabel.setIcon(ico);
                    divideFunction(path);

                } catch (IOException message) {
                    message.getMessage();
                }
                mixButton.setEnabled(true);
            }
        });

        mixButton.setBackground(new java.awt.Color(0, 153, 153));
        mixButton.setText("KARIŞTIR");
        mixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    mixFunction();
                } catch (IOException message) {
                    message.getMessage();
                }
            }
        });

        playerNameStaticLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        playerNameStaticLabel.setText("OYUNCU:");

        playerNameDynamicLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        playerNameDynamicLabel.setForeground(new java.awt.Color(255, 0, 51));

        scoreStaticLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        scoreStaticLabel.setText("SKOR:");
        scoreStaticLabel.setPreferredSize(new java.awt.Dimension(34, 19));

        scoreDynamicLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        scoreDynamicLabel.setForeground(new java.awt.Color(255, 0, 0));

        bestScoreStaticLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        bestScoreStaticLabel.setText("EN YÜKSEK SKOR:");

        bestScoreDynamicLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        bestScoreDynamicLabel.setForeground(new java.awt.Color(0, 51, 204));

        bestPlayerNameStaticLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        bestPlayerNameStaticLabel.setText("EN İYİ OYUNUCU:");

        bestPlayerNameDynamicLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        bestPlayerNameDynamicLabel.setForeground(new java.awt.Color(0, 153, 51));

        imageLabel.setFont(new java.awt.Font("PuzzleImage", 1, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(imageChooseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(mixButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(bestScoreStaticLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bestPlayerNameDynamicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(bestPlayerNameStaticLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bestScoreDynamicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(scoreStaticLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(playerNameStaticLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(scoreDynamicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                        .addComponent(playerNameDynamicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(imageLabel, 900, 1000, Short.MAX_VALUE))
                                .addContainerGap(75, Short.MAX_VALUE))

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bestScoreStaticLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(imageChooseButton))
                                                        .addComponent(bestPlayerNameDynamicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(scoreDynamicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(scoreStaticLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(bestPlayerNameStaticLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bestScoreDynamicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(mixButton))
                                        .addComponent(playerNameStaticLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(playerNameDynamicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(button12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                                .addComponent(imageLabel, 818, 818, Short.MAX_VALUE))

        );

        pack();
    }

    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JButton button3;
    private javax.swing.JButton button4;
    private javax.swing.JButton button5;
    private javax.swing.JButton button6;
    private javax.swing.JButton button7;
    private javax.swing.JButton button8;
    private javax.swing.JButton button9;
    private javax.swing.JButton button10;
    private javax.swing.JButton button11;
    private javax.swing.JButton button12;
    private javax.swing.JButton button13;
    private javax.swing.JButton button14;
    private javax.swing.JButton button15;
    private javax.swing.JButton button16;
    private javax.swing.JButton imageChooseButton;
    private javax.swing.JButton mixButton;
    private javax.swing.JLabel bestPlayerNameStaticLabel;
    private javax.swing.JLabel bestPlayerNameDynamicLabel;
    private javax.swing.JLabel scoreStaticLabel;
    private javax.swing.JLabel scoreDynamicLabel;
    private javax.swing.JLabel bestScoreStaticLabel;
    private javax.swing.JLabel bestScoreDynamicLabel;
    private javax.swing.JLabel playerNameStaticLabel;
    private javax.swing.JLabel playerNameDynamicLabel;
    private javax.swing.JLabel imageLabel;

    public static void main(String[] args) {
        try {
            new GameScreen().setVisible(true);
        } catch (FileNotFoundException message) {
            message.getMessage();
        } catch (IOException message1) {
            message1.getMessage();
        }
    }
}

