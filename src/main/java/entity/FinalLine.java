package entity;

public class FinalLine extends Line{
    private String secondValue;

    public FinalLine(Line first, Line second) {
        super(first.getId(), first.getValue());
        secondValue = second.getValue();
    }

    public String getSecondValue() {
        return secondValue;
    }

    @Override
    public String toString() {
        return this.getId() + "," + this.getValue() + "," +secondValue + "\n";
    }
}
