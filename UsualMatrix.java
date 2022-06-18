package com.company;
import com.company.IMatrix;

public class UsualMatrix implements IMatrix {

    protected int [][] mtx;
    protected int row;
    protected int column;

    public UsualMatrix(int row, int column){
        this.row = row;
        this.column = column;
        mtx = new int[row][column];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                mtx[i][j] = 0;
            }
        }
    }

    @Override
    public void setElement(int row, int column, int value){
        this.mtx[row][column] = value;
    }
    @Override
    public int getElement(int row, int column){
        return this.mtx[row][column];
    }

    @Override
    public IMatrix product(IMatrix mtx1) {
        IMatrix result = new UsualMatrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                //result.mtx[i][j] = 0;
                for (int k = 0; k < column; k++) {
                    result.setElement(i, j, (result.getElement(i, j) + (this.getElement(i, k) * mtx1.getElement(k, j))));//result.mtx[i][j] += mtx[i][k] * mtx1.mtx[k][j];//this.getElement(i, k) * mtx1.getElement(k, i);
                }
            }
        }
        return result;
    }

    @Override
    public int getRows() {
        return this.row;
    }

    @Override
    public int getColumns() {
        return this.column;
    }

    @Override
    public IMatrix sum(IMatrix mtx1) {
        IMatrix result = new UsualMatrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                result.setElement(i, j,(mtx1.getElement(i, j)  + this.getElement(i, j)));//result.mtx[i][j] = this.mtx[i][j] + mtx1.mtx[i][j];//this.getElement(i,j) + mtx1.getElement(i, j);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.row; i++) {
            for(int j = 0; j < this.column; j++) {
                if(j == 0)
                    if(j == 0 && this.column == 1)
                    {
                        sb.append("[" + mtx[i][j] + "]").append(" ");
                    }
                    else {
                        sb.append("[" + mtx[i][j] + ",").append(" ");
                    }
                else if(j == this.column - 1)
                    sb.append(mtx[i][j] + "]").append(" ");
                else
                    sb.append(mtx[i][j] + ",").append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public final boolean equals(Object o){
        if(!(o instanceof IMatrix))
            return false;
        IMatrix matrix = (IMatrix) o;
        if(matrix.getColumns() != this.getColumns() & matrix.getRows() != this.getRows())
            return false;
        for(int i = 0; i < this.getRows(); i++)
            for(int j = 0; j < this.getColumns(); j++){
                if(!(this.getElement(i, j) == matrix.getElement(i, j)))
                    return false;
            }
        return true;
    }

}





//
//public class UsualMatrix {
//    protected int row;
//    protected int column;
//    protected int[][] mtx;
//
//    // Matrix(){}
//
//    public UsualMatrix(int row, int column) {
//        this.row = row;
//        this.column = column;
//        mtx = new int[row][column];
//        for(int i = 0; i < row; i++) {
//            for(int j = 0; j < column; j++) {
//                mtx[i][j] = 0;
//            }
//        }
//    }
//
//    public IMatrix sum(IMatrix m) {
//        UsualMatrix res = new UsualMatrix(this.row, this.column);
//        for(int i = 0; i < this.row; i++) {
//            for(int j = 0; j < this.column; j++)
//                res.setElement(i, j,(m.getElement(i, j)  + this.getElement(i, j)));
//        }
//        return res;
//    }
//
//    public IMatrix product(IMatrix m) {
//        UsualMatrix prod = new UsualMatrix(this.row, m.column);
//        for(int i = 0; i < this.row; i++) {
//            for(int j = 0; j < m.column; j++) {
//                for(int k = 0; k < this.column; k++) {
//                    prod.setElement(i, j, (prod.getElement(i, j) + (this.getElement(i, k) * m.getElement(k, j))));
//                }
//            }
//        }
//        return prod;
//    }
//
//    public  void setElement(int row, int column, int value){
//        this.mtx[row][column] = value;
//    }
//
//    public  int getElement(int row, int column){
//        return this.mtx[row][column];
//    }
//
//    //    public final String toString(){
////        StringBuilder arr = new StringBuilder();
////        for(int i = 0; i < this.row; i++){
////            arr.append('\n');
////            for(int j = 0; j < this.column; j++){
////                arr.append(this.getElement(i, j) + "\t");
////
////
////            }
////            arr.append('\n');
////        }
////        return arr.toString();
////    }
//    public final String toString(){
//        StringBuilder arr = new StringBuilder();
//        for(int i = 0; i < this.row; i++){
//            arr.append('\n');
//            for(int j = 0; j < this.column; j++){
//                if(j == 0) {
//                    arr.append(this.getElement(i, j) + "\t");
//                }
//                else
//                    arr.append(this.getElement(i, j)  + "\t");
//            }
//            arr.append('\n');
//        }
//        return arr.toString();
//    }
//
//}