package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class SparseArrayMatrix implements IMatrix {

    class Element {
        private int i;
        private int value;

        public Element(int row, int value) {
            this.i = row;
            this.value = value;
        }

        public int getR() {
            return i;
        }

        public int getV() {
            return value;
        }

        public void setV(int value) {
            this.value = value;
        }
    }

    private int rows;
    private int columns;
    private ArrayList<LinkedList<Element>> list;

    public SparseArrayMatrix(int r, int c) {
        rows = r;
        columns = c;
        list = new ArrayList<LinkedList<Element>>();
        for (int i = 0; i < rows; i++) {
            list.add(new LinkedList<Element>());
        }
    }


    public void setElement(int r, int c, int value) {
        LinkedList<Element> lst = list.get(c);
        ListIterator<Element> iter= lst.listIterator();
        while(iter.hasNext()) {
            Element tmp = iter.next();
            if (tmp.i == r) {
                tmp.setV(value);
                //return;
            }
        }
        if(value != 0) {
            lst.add(new Element(r, value));

        }
    }

    public int getElement(int r, int c) {
        LinkedList<Element> lst = list.get(c);
        ListIterator<Element> iter= lst.listIterator();
        while(iter.hasNext()) {
            Element tmp = iter.next();
            if(tmp.getR() == r) {
                return tmp.getV();
            }
        }
        return 0;
    }

    public IMatrix sum(IMatrix mtx) {
        SparseArrayMatrix res = new SparseArrayMatrix(this.getRows(), this.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                int value = this.getElement(i, j) + mtx.getElement(i, j);
                res.setElement(i, j, value);
            }
        }
        return res;
    }

    public IMatrix product(IMatrix mtx) {
        SparseArrayMatrix res = new SparseArrayMatrix(this.getRows(), mtx.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < mtx.getColumns(); j++){
                for(int k = 0; k < mtx.getRows(); k++){
                    res.setElement(i, j, res.getElement(i, j) + this.getElement(i, k) * mtx.getElement(k, j));
                }
            }
        }
        return res;
    }

    public String toString() {
        StringBuilder build = new StringBuilder();
        ListIterator<LinkedList<Element>> iter1= list.listIterator();
        while (iter1.hasNext()) {
            LinkedList<Element> lst = iter1.next();
            for(int j = 0; j < this.getRows(); j++){
                ListIterator<Element> iter2 = lst.listIterator();
                boolean contain = false;
                while(iter2.hasNext() && !contain){
                    Element tmp = iter2.next();
                    if(tmp.getR() == j) {
                        build.append(tmp.getV()).append(" ");
                        contain = true;
                    }
                }
                if(!contain) {
                    build.append(0).append(" ");
                }

            }
            build.append("\r\n");
        }
        return build.toString();
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (obj == null || !(obj instanceof IMatrix)) {
            return false;
        }
        SparseMatrix mtx = (SparseMatrix) obj;
        if(this.getColumns() != mtx.getColumns() || this.getRows() != mtx.getRows()){
            return false;
        }
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                int e1 = this.getElement(i, j);
                int e2 = mtx.getElement(i, j);
                if(e1 != e2){
                    return false;
                }
            }
        }
        return true;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}