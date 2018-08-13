package Lesson1.Homework.Task3;

public class App {
    public static void main(String[] args) {
        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();
        Box box4 = new Box();

        for (int i = 0; i < 11; i++) {
            box1.inPutFruit(new Orange());
        }

        for (int i = 0; i < 20; i++) {
            box2.inPutFruit(new Orange());
        }

        for (int i = 0; i < 30; i++) {
            box3.inPutFruit(new Apple());
        }

        for (int i = 0; i < 9; i++) {
            box4.inPutFruit(new Orange());
        }

        box1.info();
        box2.info();
        box3.info();
        box4.info();

        System.out.println("----------------------");
        System.out.println("2-я и 3-я коробка " + box2.compare(box3));

        box3.pepper(box2);//проверяем перемешение фруктов из коробки с яблоками в коробку под апельсины
        box1.pepper(box4);//проверяем перемешение фруктов из коробки с апельсинами в кторобку под апельсинами

        box1.info();
        box2.info();
        box3.info();
        box4.info();
        System.out.println("----------------------");
        //как оявилась пуста коробка , переместим туда фрукты из коробки из под яблок

        box3.pepper(box1);


        box1.info();
        box2.info();
        box3.info();
        box4.info();

        System.out.println("2-я и 4-я коробка " + box2.compare(box4));


    }
}
