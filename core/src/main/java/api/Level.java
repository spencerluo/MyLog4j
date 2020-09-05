package api;

public enum Level {

    ERROR(0),

    INFO(1),

    DEBUG(2);

    private int val;

    private Level(int val){
        this.val = val;
    }
}
