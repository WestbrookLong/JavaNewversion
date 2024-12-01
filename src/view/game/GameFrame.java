package view.game;

import javax.swing.*;
import java.awt.*;

import controller.GameController;
import model.MapMatrix;
import view.FrameUtil;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton UP;
    private JButton DOWN;
    private JButton LEFT;
    private JButton RIGHT;


    private JLabel stepLabel;
    private GamePanel gamePanel;

    public GameFrame(int width, int height, MapMatrix mapMatrix) {
        this.setTitle("2024 CS109 Project Demo: Pushbox");
        this.setLayout(null);
        this.setSize(width, height);
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.controller = new GameController(gamePanel, mapMatrix);

        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 210), 80, 50);
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        this.UP = FrameUtil.createButton(this, "UP", new Point(gamePanel.getWidth() + 20, 50), 100, 50);
        this.DOWN = FrameUtil.createButton(this, "DOWN", new Point(gamePanel.getWidth() - 120, 50), 100, 50);
        this.LEFT = FrameUtil.createButton(this, "LEFT", new Point(gamePanel.getWidth() - 220, 50), 100, 50);
        this.RIGHT = FrameUtil.createButton(this, "RIGHT", new Point(gamePanel.getWidth() -320, 50), 100, 50);
        gamePanel.setStepLabel(stepLabel);







        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {//这是一个事件监听器
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.UP.addActionListener(e ->{
            this.gamePanel.doMoveUp();
        });
        this.DOWN.addActionListener(e ->{
            this.gamePanel.doMoveDown();
        });
        this.LEFT.addActionListener(e ->{
            this.gamePanel.doMoveLeft();
        });
        this.RIGHT.addActionListener(e ->{
            this.gamePanel.doMoveRight();
        });

        //todo: add other button here
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
