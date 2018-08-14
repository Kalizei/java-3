package Lesson1.Homework;

import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {

        Object[] mass = {12,32,"32",2.1f,0};
        System.out.println(Arrays.toString(mass));
        System.out.println(Arrays.toString(swap(1,3,mass)));
    }

    /** Метод реализует в массиве(mass) перемещение двух элементов с индексами n1 и n2
     * @param n1 - индекс первого элемента массива
     * @param n2 - индекс второго элемента массива
     * @param mass - сам массив где нужно произвести перемещение элементов
     * @return готовый массив
     */
    static Object[] swap (int n1,int n2,Object[] mass){
        Object temp = mass[n2];
        mass[n2] = mass[n1];
        mass[n1] = temp;
        return mass;
    }
}
