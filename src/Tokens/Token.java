package Tokens;

import main.Varaiable;

/**
 * Created by Didoy on 8/22/2016.
 */
public class Token {

    private Type type;
    private String text;
    private Varaiable varaiable;

    public Token( Type type, String text )
    {
        this.type = type;
        this.text = text;
    }

    public Token (Type type, Varaiable varaiable){
        this.type = type;
        this.varaiable = varaiable;
    }

    public String getText() {
        return text;
    }

    public Varaiable getVaraiable() {
        return varaiable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        UNKNOWN,

        STRING,
        KEYWORD,
        VARIABLE,
        VARNAME,
        PRINT,
        INPUT,
        NUMBER,
        DOT,
        START_BLOCK,
        END_BLOCK,

        LEFT_PARENTHESIS,
        RIGHT_PARENTHESIS,
        RIGHT_BRACE,
        LEFT_BRACE,
        LEFT_BRACKET,
        RIGHT_BRACKET,

        OPERATOR,
        EQUALS,
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,

        GREATER_THAN,
        LESS_THAN,
        OR,
        AND,

        SEMI_COLON,
        OBJECT


    }

}
