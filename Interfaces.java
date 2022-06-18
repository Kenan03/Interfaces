package com.company;
import com.company.UsualMatrix;

interface IMatrix{
    public  void setElement(int row, int column, int value);
    public  int getElement(int row, int column);

    public IMatrix sum(IMatrix mtx1);
    public IMatrix product(IMatrix mtx1);

    public int getRows();
    public int getColumns();

    public String toString();
    public boolean equals(Object o);

}
