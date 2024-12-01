package view.game;

import controller.GameController;
import model.Direction;
import model.MapMatrix;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends ListenerPanel {
    private GridComponent[][] grids;
    private MapMatrix model;
    private GameController controller;
    private JLabel stepLabel;
    private int steps;
    private final int GRID_SIZE = 50;
    private Hero hero;

    public GamePanel(MapMatrix model) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(model.getWidth() * GRID_SIZE + 4, model.getHeight() * GRID_SIZE + 4);
        this.model = model;
        this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getId(i, j) % 10, this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);
                switch (model.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10));
                        break;
                    case 2:
                        this.hero = new Hero(GRID_SIZE - 16, GRID_SIZE - 16, i, j);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();
    }

    public void resetGamePanel() {
        // 重置游戏面板到初始状态
        this.removeAll(); // 移除所有子组件
        this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame(); // 调用 initialGame 方法重新初始化游戏面板
        this.repaint(); // 重绘游戏面板
    }

    @Override
    public void doMoveRight() {
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.RIGHT)) {
            afterMove();
        }
    }

    @Override
    public void doMoveLeft() {
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.LEFT)) {
            afterMove();
        }
    }

    @Override
    public void doMoveUp() {
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.UP)) {
            afterMove();
        }
    }

    @Override
    public void doMoveDown() {
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.DOWN)) {
            afterMove();
        }
    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        this.repaint();// Repaint the entire panel to update the UI

            if(controller.isVictory()){
                VictoryFrame victoryFrame = new VictoryFrame(500, 400);

                victoryFrame.setVisible(true);

            }
            if(controller.isFail()){
                FailFrame failFrame = new FailFrame(500, 400);
                failFrame.setVisible(true);

            }

    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GridComponent getGridComponent(int row, int col) {
        return grids[row][col];
    }
}