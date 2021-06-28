package lesson_6;

import java.util.ArrayList;
import java.util.List;

public class HomeWork6 {
    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat(200));
        animals.add(new Dog(1100, 500));
        animals.add(new Cat(500));
        animals.add(new Dog(700, 300));

        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Road(300));
        obstacles.add(new Road(1100));
        obstacles.add(new Road(100));
        obstacles.add(new Reservoir(250));
        obstacles.add(new Reservoir(400));

        for (Animal animal : animals) {         //Цикл для вывода результата, если длина бега или плавания для животного задана просто значением
            animal.run(350);
            animal.swim(100);
        }

        for (Animal animal : animals) {         //Цикл берет каждое животное, затем это животное пытается пройти препятствия из внутреннего цикла
            int count = 0;
            for (Obstacle obstacle : obstacles) {
                count++;
                if (animal.overcomeAnObstacle(obstacle)) {
                    System.out.printf("Животное смогло преодолеть препятствие номер %d и пробует дальше!\n", count);
                    if (count == obstacles.size()) {
                        System.out.printf("Животное смогло преодолеть все %d препятствий!!!\n", obstacles.size());
                    }
                } else {
                    System.out.printf("Животное не смогло преодолеть препятствие номер %d, выбывает и дальше не пробует!\n", count);
                    break;
                }
            }
        }

        System.out.println("Общее количество животных: " + Animal.getValue());
        System.out.println("Общее количество кошек: " + Cat.getValue());
        System.out.println("Общее количество собак: " + Dog.getValue());

    }
}
