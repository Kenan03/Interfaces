package com.company;
import com.company.UsualMatrix;

public class SquareMatrix extends UsualMatrix {
    public SquareMatrix(int rows, int columns) {
        super(rows, columns);
        mtx = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(i != j) {
                    mtx[i][j] = 0;
                }
                else {
                    mtx[i][j] = 1;
                }
            }
        }
    }
    @Override
    public IMatrix sum(IMatrix mtx1) {
        UsualMatrix result = new UsualMatrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                result.setElement(i, j,(mtx1.getElement(i, j)  + this.getElement(i, j)));//result.mtx[i][j] = this.mtx[i][j] + mtx1.mtx[i][j];//this.getElement(i,j) + mtx1.getElement(i, j);
            }
        }
        return result;
    }
}
