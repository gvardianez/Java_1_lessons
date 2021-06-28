package lesson_6;

public abstract class Obstacle implements Swimming,Running {

    public  abstract boolean runAcross(AbleToRun object);

    public abstract boolean swimAcross(AbleToSwim object);

}
