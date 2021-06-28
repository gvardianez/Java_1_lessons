package lesson_6;

public class Dog extends Animal {
    private final int run;
    private final int swim;
    private static int valueOfDog;

    public Dog(int run,int swim) {
        this.run = run;
        this.swim = swim;
        valueOfDog++;
    }

    public static int getValue() {
        return valueOfDog;
    }

    @Override
    public boolean run(int length) {
        if (length <= run) {
            System.out.printf("Собака пробежала %d м\n", length);
            return true;
        } else {
            System.out.println("Собака столько пробежать не может");
            return false;
        }
    }

    @Override
    public boolean swim(int length) {
        if (length <= swim) {
            System.out.printf("Собака проплыла %d м\n", length);
            return true;
        } else {
            System.out.println("Собака столько проплыть не может");
            return false;
        }
    }


}
