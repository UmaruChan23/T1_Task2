package entity;

public class Line implements Comparable<Line> {
    private long id;
    private String value;

    public Line(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public int compareTo(Line o) {
        if (id > o.getId()) {
            return 1;
        } else if (id < o.getId()) {
            return -1;
        }
        return 0;
    }
}
