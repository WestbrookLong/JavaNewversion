package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GridComponent extends JComponent {
    private int row;
    private int col;
    private final int id;
    private Hero hero;
    private Box box;
    static Color color = new Color(246, 246, 229);

    public GridComponent(int row, int col, int id, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.id = id;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        Color borderColor = color;
        switch (id % 10) {
            case 1:
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                borderColor = Color.DARK_GRAY;
                break;
            case 0:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                break;
            case 2:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.GREEN);
                int[] xPoints = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
                int[] yPoints = {0, getHeight() / 2, getHeight(), getHeight() / 2};
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, 4);
                break;
        }
        Border border = BorderFactory.createLineBorder(borderColor, 1);
        this.setBorder(border);
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

    public int getId() {
        return id;
    }

    public void setHeroInGrid(Hero hero) {
        this.hero = hero;
        this.add(hero);
    }

    public void setBoxInGrid(Box box) {
        this.box = box;
        this.add(box);
    }

    public Hero removeHeroFromGrid() {
        this.remove(this.hero);
        Hero h = this.hero;
        this.hero = null;
        this.revalidate();
        this.repaint();
        return h;
    }

    public Box removeBoxFromGrid() {
        this.remove(this.box);
        Box b = this.box;
        this.box = null;
        this.revalidate();
        this.repaint();
        return b;
    }
}