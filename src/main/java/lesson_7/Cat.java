package lesson_7;

public class Cat implements AbleToEat {

    private String name;
    private int appetite;
    private double satiety;
    private boolean happy;
    private final int MAX_APPETITE;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.MAX_APPETITE = appetite;
    }

    @Override
    public boolean isHappy() {
        return happy;
    }

    @Override
    public void eat(WhereCanEat object) {   //В методе проверяется лежит ли в миске кошачий корм, если кошка наевшаяся она ничего не берет, в противном случае заберет все пока не наестся
        if (satiety == 100) {
            System.out.println(this + " уже наелась и больше не хочет");
            return;
        }
        if (!(object.getSomethingEdible() instanceof CatFood)) {
            System.out.println("В этой миске нет кошачей еды");
            return;
        }
        if (object.getVolume() == 0) {
            System.out.println("Миска пустая, добавьте еды, кошка хочет кушать");
            return;
        }
        int tempAppetite = appetite;
        appetite -= object.getVolume();
        if (appetite <= 0) {
            object.pull(new CatFood(tempAppetite));
            appetite = 0;
            happy = true;
            satiety = 100;
            System.out.println(this + " наелась и стала сыта и счастлива");
        } else {
            satiety = satiety + (object.getVolume() * 100 / (double) MAX_APPETITE);
            System.out.println(this + " Сыта на " + (int) satiety + " процента, ее надо покормить еще");
            object.pull(new CatFood(tempAppetite));
        }
    }

    @Override
    public String toString() {
        return "Кошка " +
                name;
    }
}
