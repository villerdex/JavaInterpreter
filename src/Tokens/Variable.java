package Tokens;

/**
 * Created by Didoy on 9/1/2016.
 */
public class Variable {

    String name;
    Token.Type value;


    public Variable(String name, Token.Type value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Token.Type getValue() {
        return value;
    }

    public void setValue(Token.Type value) {
        this.value = value;
    }
}
