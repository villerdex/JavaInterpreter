package Tokens;

/**
 * Created by Didoy on 8/22/2016.
 */
public class Token {
    private Type type;
    private String text;

    public Token( Type type, String text )
    {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        UNKNOWN,

        STRING,
        KEYWORD,
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
    }

}
