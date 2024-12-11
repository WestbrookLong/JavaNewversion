package view.game;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import controller.GameController;
import model.MapMatrix;
import view.FrameUtil;
import view.login.LoginFrame;




public class GameFrame extends JFrame {
    String username;
    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    //改动
    private JButton saveBtn;
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
        //改动
        this.saveBtn = FrameUtil.createButton(this, "Save", new Point(gamePanel.getWidth() + 80, 300), 80, 50);
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        this.UP = FrameUtil.createButton(this, "UP", new Point(gamePanel.getWidth() + 20, 50), 100, 50);
        this.DOWN = FrameUtil.createButton(this, "DOWN", new Point(gamePanel.getWidth() - 120, 50), 100, 50);
        this.LEFT = FrameUtil.createButton(this, "LEFT", new Point(gamePanel.getWidth() - 220, 50), 100, 50);
        this.RIGHT = FrameUtil.createButton(this, "RIGHT", new Point(gamePanel.getWidth() -320, 50), 100, 50);
        gamePanel.setStepLabel(stepLabel);
        HerochoosingFrame herochoosingFrame = new HerochoosingFrame(500, 200, this.getGamePanel());
        herochoosingFrame.setVisible(true);

        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.loadBtn.addActionListener(e -> {
            username = LoginFrame.getUsername();
            String dataPath = "C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata/" + username + "/" + username + "data/" + username + "save.txt";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(dataPath));
                String line;
                // 读取第一行，获取矩阵的步数、行数和列数
                line = reader.readLine();
                String[] firstLine = line.split(" ");
                int steps = Integer.parseInt(firstLine[0]);
                int rows = Integer.parseInt(firstLine[1]);
                int cols = Integer.parseInt(firstLine[2]);

                int[][] matrix = new int[rows][cols];
                for (int i = 0; i < rows; i++) {
                    line = reader.readLine();
                    String[] values = line.split(" ");
                    for (int j = 0; j < cols; j++) {
                        matrix[i][j] = Integer.parseInt(values[j]);
                    }
                    mapMatrix.setMatrix(matrix);
                    gamePanel.resetGamePanel(); // 这将重新初始化游戏面板
                    gamePanel.setSteps(steps); 
                    this.stepLabel.setText(String.format("Step: %d", steps));
                }
                reader.close();
                JOptionPane.showMessageDialog(this, "读档成功！");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.saveBtn.addActionListener(e -> {
            username= LoginFrame.getUsername();
            String dataPath = "C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata/"+username+"/"+username+"data/"+username+"save.txt";
            try {
                FileWriter writer = new FileWriter(dataPath);
                int[][] matrix = mapMatrix.getMatrix();
                int steps = gamePanel.getSteps();
                writer.write(steps + " " + matrix.length + " " + matrix[0].length);
                writer.write("\n");
                for(int[]row:matrix){
                    for(int value:row){
                        writer.write(value+" ");
                    }
                    writer.write("\n");
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "存档成功！");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
        gamePanel.requestFocusInWindow();

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
