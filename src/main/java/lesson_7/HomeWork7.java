package lesson_7;

import java.util.ArrayList;
import java.util.List;

public class HomeWork7 {

    public static void main(String[] args) {
        List<AbleToEat> cats = new ArrayList<>();
        cats.add(new Cat("Murka", 55));
        cats.add(new Cat("Kisa",120));
        cats.add(new Cat("Busya",600));

        WhereCanEat bowl = new Bowl(500);
        bowl.put(new CatFood(400));
        bowl.put(new DogFood(100));
        System.out.printf("Миска заполнена на %d процентов\n", bowl.getFullness());

        for (AbleToEat cat:cats) {
            for (int i = 0; i < 2; i++) {
                cat.eat(bowl);
                System.out.println(cat+ " счастлива: "+ cat.isHappy());
            }
        }

        bowl.put(new CatFood(500));
        cats.get(2).eat(bowl);
        System.out.printf("Миска наполнена на %d процентов", bowl.getFullness());

    }
}
//Консоль
//В миске уже лежит Кошачий корм, в эту миску уже нельзя довавить Собачий корм
//Миска заполнена на 80 процентов
//Кошка Murka наелась и стала сыта и счастлива
//Кошка Murka счастлива: true
//Кошка Murka уже наелась и больше не хочет
//Кошка Murka счастлива: true
//Кошка Kisa наелась и стала сыта и счастлива
//Кошка Kisa счастлива: true
//Кошка Kisa уже наелась и больше не хочет
//Кошка Kisa счастлива: true
//Кошка Busya Сыта на 37 процента, ее надо покормить еще
//Миска пустая, добавьте еды
//Кошка Busya счастлива: false
//Миска пустая, добавьте еды, кошка хочет кушать
//Кошка Busya счастлива: false
//Кошка Busya наелась и стала сыта и счастлива
//Миска наполнена на 25 процентов
//Process finished with exit code 0
