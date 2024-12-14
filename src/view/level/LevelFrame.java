package view.level;

import model.MapMatrix;
import view.FrameUtil;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class LevelFrame extends JFrame {
    private GameFrame gameFrame;

    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);
        JButton level1Btn = FrameUtil.createButton(this, "Level1", new Point(30, height / 2 - 50), 120, 60);
        JButton level2Btn = FrameUtil.createButton(this, "Level2", new Point(160, height / 2 - 50), 120, 60);
        JButton level3Btn = FrameUtil.createButton(this, "Level3", new Point(290, height / 2 - 50), 120, 60);
        JButton level6Btn = FrameUtil.createButton(this, "Level3", new Point(290, height / 2 - 50), 120, 60);

        level1Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1},
                    {1, 20, 0, 0, 0, 1},
                    {1, 0, 0, 10, 2, 1},
                    {1, 0, 2, 10, 0, 1},
                    {1, 1, 1, 1, 1, 1},
            });
            this.gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
            gameFrame.getGamePanel().requestFocusInWindow();
        });

        level2Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 0},
                    {1, 20, 0, 0, 1, 1},
                    {1, 0, 10 ,10 ,0 ,0, 1},
                    {1, 0, 1, 2, 0,0,2, 1},
                    {1, 0, 0, 0, 0,0, 1},
                    {1, 1, 1, 1, 1, 1},
            });
            this.gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);//FUCK
            gameFrame.getGamePanel().requestFocusInWindow();
        });
        level3Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 1, 1, 1, 1, 0},
                    {1, 1, 1, 0, 0, 1, 0},
                    {1, 20, 0, 2, 10, 1,1},
                    {1, 0, 0, 0, 10, 0,1},
                    {1, 0, 1, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1},
            });
            this.gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
            gameFrame.getGamePanel().requestFocusInWindow();
        });

        //todo: complete all level.

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }
}
