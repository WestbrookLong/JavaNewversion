package model;

public class MapMatrix {
    int[][] matrix;
    int[][] originmatrix;

    public MapMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.originmatrix = matrix;
    }

    public int getWidth() {
        return this.matrix[0].length;
    }

    public int getHeight() {
        return this.matrix.length;
    }

    public int getId(int row, int col) {
        return matrix[row][col];
    }

    public void updateMatrix(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public int[][] getMatrix() {
        return matrix;
    }
    public void resetMatrix() {
        // 这里应该是您的初始地图矩阵
        int[][] initialMatrix = originmatrix;
        this.matrix = initialMatrix;
    }
}
