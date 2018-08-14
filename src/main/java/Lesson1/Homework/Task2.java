package Lesson1.Homework;

import java.util.ArrayList;
import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {
        Object[] mass = {12, "21q", 7.7, 1.2f};
        System.out.println(Arrays.toString(mass));
        ArrayList<Object> oal = converts(mass);
        System.out.println(oal.toString());
    }

    /** Метод преобразует массив(mass) в ArrayList
     * @param mass массив который нужно преобразовать
     * @return преобразованный ArrayList
     */
    static ArrayList<Object> converts (Object[] mass){
        ArrayList<Object> temp = new ArrayList<Object>();
        for (Object O:mass){
            temp.add(O);
        }
        return temp;
    }
}
