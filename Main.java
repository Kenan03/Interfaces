package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int size = 100;
        Random rand = new Random();
        IMatrix sm1 = new SparseMatrix(size, size);
        IMatrix us1 = new UsualMatrix(size, size);
        for(int i = 0; i < size; i++){
            int val = rand.nextInt(9) + 1;
            int rowIdx = rand.nextInt(size - 1);
            int colIdx = rand.nextInt(size - 1);
            if (sm1.getElement(rowIdx, colIdx) == 0) {
                sm1.setElement(rowIdx, colIdx, val);
                us1.setElement(rowIdx, colIdx, val);
            }
        }
        long time = System.currentTimeMillis();
        System.out.println("before multiply: " + time);
////        System.out.println(sm1);
////        System.out.println(us1);

        IMatrix us2 = new UsualMatrix(size, size);
        IMatrix sam2 = new SparseArrayMatrix(size, size);
        for(int i = 0; i < size; i++){
            int val = rand.nextInt(9) + 1;
            int rowIdx = rand.nextInt(size - 1);
            int colIdx = rand.nextInt(size - 1);
            if (us2.getElement(rowIdx, colIdx) == 0) {
                us2.setElement(rowIdx, colIdx, val);
                sam2.setElement(rowIdx, colIdx, val);
            }
        }
        sam2.product(us2);

//        System.out.println(sm2);
//        System.out.println(us2);
        System.out.println(us1.equals(sm1));
        System.out.println(us2.equals(sam2));
//        System.out.println(sm1.sum(sm2));
//        System.out.println(us1.sum(us2));
        System.out.println((us1.sum(us2).equals(sm1.sum(sam2))));
        System.out.println((us1.product(us2).equals(sm1.product(sam2))));


        long timeafter = System.currentTimeMillis();
        System.out.println("after multiply: " + timeafter);

        System.out.println("result: " + (timeafter - time));

    }
}
