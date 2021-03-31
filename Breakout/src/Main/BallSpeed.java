package Main;

public enum BallSpeed {

    FAST(1),
    NORMAL(10),
    SLOW(20);
    private int speed;

    BallSpeed(int speed) {
        this.speed = speed;

    }

    int speed(){
        return speed;
    }
}