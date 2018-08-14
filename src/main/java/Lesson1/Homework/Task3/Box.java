package Lesson1.Homework.Task3;

import java.util.ArrayList;

public class Box implements ActionsOnFruit{
    ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    final float WEIGHT_EMPTY_BOX = 0.0f;//редко увидиш невесомые коробки :)

    float getWeight(){
        float sum = WEIGHT_EMPTY_BOX;
        for (Fruit f:fruits){
            sum += f.getWeight();
        }
        return sum;
    }

    boolean compare(Box box){
        return getWeight() == box.getWeight();
    }

    void info (){
        if (fruits.size() > 0){
            System.out.println("В коробке("+ fruits.get(0).type_fruit +") содержиться " + fruits.size() + " шт.");

        } else {
            System.out.println("Пустая каробка");
        }
        System.out.println("Вес коробки составляет: " + getWeight());
        System.out.println();
    }

    void pepper (Box box){
        //смотрим есть ли фрукты в коробке
        if (fruits.size() > 0) {
            Fruit temp = fruits.get(0);
            // берем фрукт из этой коробки
            if (outPutFruit(temp)){
                //пытаемся положить в коробку (box)
                if (box.inPutFruit(temp)){
                    //прошло все успешно, повторяем еще раз
                    pepper(box);
                } else {
                    // не можем положить фкукт во вторую коробку, но его сейчас тоит вернуть в первую коробку
                    inPutFruit(temp);
                }
            }
        }
    }


    public boolean inPutFruit(Fruit fruit) {
        String temp = (fruits.size() == 0) ? (fruit.type_fruit) : (fruits.get(0).type_fruit);
        if (temp.equals(fruit.type_fruit)) {
            //положить фрукт можно

            fruits.add(fruit);

            return true;//фрукт успешно поместили в коробку
        } else {
            // положить фрукт нельзя, т.к. не выполнено условие однотипных фруктов в коробке
            return false;//фрукт не был помещен в коробку
        }
    }

    public boolean outPutFruit(Fruit fruit) {
        if ((fruits.size() > 0)&&(fruit.type_fruit.equals(fruits.get(0).type_fruit))){

            fruits.remove(0);

            return true;
        } else {
            return false;
        }
    }
}
