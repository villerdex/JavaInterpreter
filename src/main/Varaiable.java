package main;

/**
 * Created by Didoy on 8/23/2016.
 */
public class Varaiable {

    String name;
    Object value;

    public Varaiable(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
