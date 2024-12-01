package controller;

import model.Direction;
import model.MapMatrix;
import view.game.Box;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;

public class GameController {
    private final GamePanel view;
    private final MapMatrix model;

    public GameController(GamePanel view, MapMatrix model) {
        this.view = view;
        this.model = model;
        view.setController(this);
    }

    public void restartGame() {
        System.out.println("Do restart game here");
        view.resetGamePanel();



    }

    public boolean doMove(int row, int col, Direction direction) {
        int[][] map = model.getMatrix();
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();

        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {
            // 移动英雄
            updateMapAndView(row, col, tRow, tCol, 20); // 假设20代表英雄
            return true;
        } else if (map[tRow][tCol] == 10 || map[tRow][tCol] == 12) { // 假设11代表箱子
            int nextTRow = tRow + direction.getRow();
            int nextTCol = tCol + direction.getCol();
            if (map[nextTRow][nextTCol] == 0 || map[nextTRow][nextTCol] == 2) {
                // 移动箱子
                updateMapAndView(tRow, tCol, nextTRow, nextTCol, 10); // 假设10代表箱子
                // 移动英雄
                updateMapAndView(row, col, tRow, tCol, 20); // 假设20代表英雄
                return true;
            }
        }
        return false;
    }

    private void updateMapAndView(int fromRow, int fromCol, int toRow, int toCol, int value) {
        int[][] map = model.getMatrix();
        GridComponent fromGrid = view.getGridComponent(fromRow, fromCol);
        GridComponent toGrid = view.getGridComponent(toRow, toCol);

        if (value == 20) { // 英雄
            model.updateMatrix(fromRow, fromCol, map[fromRow][fromCol] - 20);
            model.updateMatrix(toRow, toCol, map[toRow][toCol] + 20);
            Hero hero = fromGrid.removeHeroFromGrid();
            toGrid.setHeroInGrid(hero);
            hero.setRow(toRow);
            hero.setCol(toCol);
        } else if (value == 10) { // 箱子
            model.updateMatrix(fromRow, fromCol, map[fromRow][fromCol] - 10);
            model.updateMatrix(toRow, toCol, map[toRow][toCol] + 10);
            Box box = fromGrid.removeBoxFromGrid();
            box.setRow(toRow);
            box.setCol(toCol);
            toGrid.setBoxInGrid(box);

        }
    }
    public boolean isGameover(){
        if(isVictory()){
            return true;
        } else if (isFail()) {
            return true;

        }
        else{
            return false;
        }

    }
    public boolean isVictory(){
        int[][] map = model.getMatrix();
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 12) { // 检查目标位置
                    count++;
                }
            }
        }
        // 如果所有目标位置都被箱子占据，则游戏结束
        if(count == 2){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isFail(){
        int[][] map = model.getMatrix();
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 10) { // 检查目标位置
                    if(isTrap(i, j))
                    count++;
                }
            }
        }
        if(count != 0 ){
            return true;
        }
        else {
            return false;
        }


    }
    public boolean isTrap(int i, int j){
        int[][] map = model.getMatrix();
        int leftmap = map[i][j-1];
        int rightmap = map[i][j+1];
        int upmap = map[i-1][j];
        int downmap = map[i+1][j];
        boolean isTrap = false;
        if(Barrier(leftmap) && Barrier(upmap)){
            isTrap = true;
        }
        else if (Barrier(leftmap) && Barrier(downmap)) {
            isTrap = true;

        }
        else if (Barrier(rightmap) && Barrier(downmap)) {
            isTrap = true;

        }
        else if (Barrier(rightmap) && Barrier(upmap)) {
            isTrap = true;

        }
        return isTrap;

    }
    public boolean Barrier(int m){
        if(m == 1 || m == 10 || m == 12){
            return true;
        }
        else {
            return false;
        }

    }

}