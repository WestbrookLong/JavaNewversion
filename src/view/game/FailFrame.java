package view.game;


import controller.GameController;
import view.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class FailFrame extends JFrame{

    private final JLabel FAIL;
    private JButton confirm;
    private GameFrame gameFrame;
    private GameController gameController;
    public FailFrame(int width, int height, GameController gameController){
        this.setTitle("level victory");
        this.setLayout(null);
        this.setSize(width, height);
        this.setLocation(120, 120);
        this.gameController = gameController;
        this.FAIL = FrameUtil.createJLabel(this, "FAIL", new Font("serif", Font.ITALIC, 22), new Point( 80, 70), 180, 50);
        this.confirm = FrameUtil.createButton(this, "confirm and restart", new Point(30, height / 2 - 50), 200, 60);

        confirm.addActionListener(e ->{
            gameController.restartGame();
            this.setVisible(false);

        });



}}
