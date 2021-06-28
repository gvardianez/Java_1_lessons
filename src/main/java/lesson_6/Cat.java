package lesson_6;

public class Cat extends Animal {
    private final int run;
    private final String swim = "Кошки плавать не умеют" ;
    private static int valueOfCat;

    public Cat(int run) {
        this.run = run;
        valueOfCat++;
    }

    public static int getValue() {
        return valueOfCat;
    }

    @Override
    public boolean swim(int length) {
        System.out.println(swim);
        return false;
    }

    public boolean run(int length) {
        if (length <= run) {
            System.out.printf("Кошка пробежала %d м\n", length);
            return true;
        } else {
            System.out.println("Кошка столько пробежать не может");
            return false;
        }
    }


}
