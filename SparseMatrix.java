package com.company;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

    public class SparseMatrix implements IMatrix {

        private static class Node{
            private final int rowIdx;
            private final int colIdx;
            private int val;

            public Node(int row, int col, int value){
                this.rowIdx = row;
                this.colIdx = col;
                this.val = value;
            }
        }

        private LinkedList<Node> data;
        private int row;
        private int column;

        SparseMatrix(int rows, int columns) {
            data = new LinkedList<>();
            this.column = columns;
            this.row = rows;
        }

        @Override
        public void setElement(int row, int column, int value) {
            ListIterator<Node> it = this.data.listIterator();

            while(it.hasNext()){
                Node cmpObject = (Node)it.next();
                if(cmpObject.rowIdx == row & cmpObject.colIdx == column) {
                    it.previous().val = value;
//                    return;
                }
            }
            this.data.add(new Node(row, column, value));
        }

        @Override
        public int getElement(int row, int column) {
            ListIterator<Node> it = this.data.listIterator();

            while(it.hasNext()){
                Node cmpObject = (Node)it.next();
                if(cmpObject.rowIdx == row & cmpObject.colIdx == column)
                    return cmpObject.val;
            }
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.column; j++)
                    sb.append(this.getElement(i, j)).append("\t\t");
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override

        public IMatrix sum(IMatrix mtx1) {
            IMatrix res = new SparseMatrix(this.row, this.column);
            for (int i = 0; i < this.row; i++)
                for (int j = 0; j < this.column; j++) {
                    int elem1 = this.getElement(i, j);
                    int elem2 = mtx1.getElement(i, j);
//                    if(elem1 != 0 & elem2 != 0)
                        res.setElement(i, j, elem1 + elem2);
                }
            return res;
        }

        @Override
        public IMatrix product(IMatrix mtx1) {
            int resVal = 0;
            IMatrix res = new SparseMatrix(this.row, mtx1.getColumns());
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < mtx1.getColumns(); j++) {
                    for (int k = 0; k < this.column; k++) {
                        resVal += this.getElement(i, k) * mtx1.getElement(k, j);//res.setElement(i, j, elem1 * elem2);
                    }
                    res.setElement(i, j, resVal);
                    resVal = 0;
                }
            }
            return res;
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






