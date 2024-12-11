package view.game;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero extends JComponent {
    private int row;
    private int col;
    private final int value = 20;
    private static Color color = new Color(87, 171, 220);
    private BufferedImage image;
    private int Herochoice;



    public Hero(int width, int height, int row, int col, int herochoice) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(8, 8);
        readfile(herochoice);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, getWidth(), getHeight());
//        g.setColor(color);
        g.fillOval(1, 1, getWidth() - 2, getHeight() - 2);
        if (image != null) {
            // 计算图片的绘制位置，使其居中显示
            double scaleX = (double) getWidth() / image.getWidth();
            double scaleY = (double) getHeight() / image.getHeight();
            double scale = Math.min(scaleX, scaleY);

            // 计算缩放后的图片尺寸
            int newWidth = (int) (image.getWidth() * scale);
            int newHeight = (int) (image.getHeight() * scale);

            // 绘制等比例缩放后的图片
            g.drawImage(image, 0, 0, newWidth, newHeight, this);
        }
    }

    public void readfile(int herochoice){
        if(herochoice == 1){
        File file = new File("src/img.png"); // 替换为你的图片文件路径
        try {
            image = ImageIO.read(file);
            // 现在你可以使用image对象进行进一步的操作
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        else{
            File file = new File("src/Hero2.jpg"); // 替换为你的图片文件路径
            try {
                image = ImageIO.read(file);
                // 现在你可以使用image对象进行进一步的操作
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}


    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public void setHerochoice(int a){
        this.Herochoice = a;
    }
    public int getHerochoice(){
        return Herochoice;
    }
}