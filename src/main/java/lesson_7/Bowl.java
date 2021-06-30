package lesson_7;

public class Bowl implements WhereCanEat {

    private int maxVolume;
    private int volume;
    private double fullness;
    private Edible somethingEdible;

    public Bowl(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public Edible getSomethingEdible() {
        return somethingEdible;
    }

    @Override
    public int getFullness() {
        return (int)fullness;
    }

    @Override
    public void put(Edible object) {        //Метод проверяет тип того, что может уже лежать в миске,
        double tempMaxVolume= maxVolume;    //и в зависимости от аппетита животного считает остаток и заполненность в процентах. Если
        if (fullness == 0) {                //кладется больше чем максимум вместимости, то миска становится просто полной
            somethingEdible = object;
        }
        if (!(somethingEdible.getClass() == object.getClass())){
            System.out.println("В миске уже лежит " + somethingEdible + ", в эту миску уже нельзя довавить " + object);
        return;
        }
        fullness = fullness + ((object.getVolume() * 100 / tempMaxVolume));
        volume = volume + object.getVolume();
        if (fullness > 100) {
            fullness = 100;
            volume = maxVolume;
            System.out.println("Миска уже полная");
        }
    }

    @Override
    public void pull(Edible object) {       //Проверяет можно ли взять, и есть ли в миске то, что хотите взять
        double tempMaxVolume = maxVolume;   //если например кошка хочет взять больше чем есть в миске, но она савится просто пустой
        if (fullness == 0){
            System.out.println("Миска пустая, положите сначала что нибудь");
            return;
        }
        if (!(somethingEdible.getClass() == object.getClass())){
            System.out.println("В миске лежит " + somethingEdible + ", из нее нельзя взять " + object);
            return;
        }
        fullness = fullness - ((object.getVolume() * 100 / tempMaxVolume));
        volume = volume - object.getVolume();
        if (fullness < 0) {
            fullness = 0;
            volume = 0;
            System.out.println("Миска пустая, добавьте еды");
        }
    }


}
