package view.game;

import view.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class HerochoosingFrame extends JFrame {
    private GamePanel gamePanel;

    public HerochoosingFrame(int width, int height, GamePanel gamePanel){
        this.setTitle("choose your hero");
        this.setLayout(null);
        this.setSize(width, height);
        this.setLocation(120, 120);
        this.gamePanel = gamePanel;

        JButton herodefault = FrameUtil.createButton(this, "default", new Point(30, height/2 - 50), 120, 120);
        JButton hero2 = FrameUtil.createButton(this, "hero2", new Point(150, height/2 - 50), 120, 120);
        herodefault.addActionListener(e->{
            gamePanel.setHerochoice(1);
            this.setVisible(false);



        });
        hero2.addActionListener(e->{
            gamePanel.setHerochoice(2);
            this.setVisible(false);



        });
    }
}
