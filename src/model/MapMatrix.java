package model;

public class MapMatrix {
    int[][] matrix;
    int[][] originmatrix;

    public MapMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.originmatrix = cloneMatrix(matrix);
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
        this.matrix = cloneMatrix(originmatrix);
    }
    public void setMatrix(int[][] newMatrix) {
        this.matrix = newMatrix;
    }
    public int[][] cloneMatrix(int[][] matrix) {
        int[][] clonedMatrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            clonedMatrix[i] = matrix[i].clone();
        }
        return clonedMatrix;
    }
}
