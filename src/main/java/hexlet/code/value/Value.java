package hexlet.code.value;


public final class Value {
    private final Object value1;
    private final Object value2;
    private final Status status;


    public Value(Object firstValue, Object secondValue, Status statusValue) {
        this.value1 = firstValue;
        this.value2 = secondValue;
        this.status = statusValue;
    }

    public Object getValue1() {
        return this.value1;
    }

    public Object getValue2() {
        return this.value2;
    }

    public Status getStatus() {
        return this.status;
    }


}
