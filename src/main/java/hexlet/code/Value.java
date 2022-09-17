package hexlet.code;

public final class Value {
    private final Object firstOb;
    private final Object secondOb;
    private final Status status;


    public Value(Object firstValue, Object secondValue, Status statusValue) {
        this.firstOb = firstValue;
        this.secondOb = secondValue;
        this.status = statusValue;
    }

    public Object getFirstOb() {
        return this.firstOb;
    }

    public Object getSecondOb() {
        return this.secondOb;
    }

    public Status getStatus() {
        return this.status;
    }


}
