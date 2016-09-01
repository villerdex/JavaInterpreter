package main;

import Tokens.Token;

import java.util.ArrayList;

import static Tokens.Token.Type.VARNAME;
import static java.lang.Enum.valueOf;

/**
 * Created by Didoy on 8/20/2016.
 */
public class Lexer {
    private String y = "'";

    private enum State{
        DEFAULT,
        STRING,
        NUMBER,
        PUNCTUATION,
        PARENTHESIS,
        KEYWORD,
        OPERATORS

    }



    public Lexer() {

    }

    //@ holds the list of tokens
    private ArrayList<Token> tokenArrayList = new ArrayList<Token>();

    //@  holds the current state of token
    private State state = State.DEFAULT;

    //@ hold string token or unfinish token
    private String token = "";

    //@ holds the string token type
    private Token.Type tokType = null;

    int i = 0;

    public ArrayList<Token> getTokenArrayList(){
        return tokenArrayList;
    }

    public void tokenize(String source){

        for (i = 0; i < source.length(); i++ ){

        char c = source.charAt(i);
            findToken(c);
        }
    }

    private void findToken(char c){
        switch (state){
            case DEFAULT:

                if(isParenthesis(c)) {
                tokType = findParenthesisType(c);
                tokenArrayList.add(new Token(tokType, Character.toString(c)));
                    state = State.DEFAULT;
                }

                else if (Character.isLetterOrDigit(c)){

                    token += c;
                    state = State.KEYWORD;
                }

                else if (c == y.charAt(0) ){

                    state = State.STRING;
                }
                else if (Character.isDigit(c)){
                    state = State.NUMBER;
                }
                else{
                    state = State.OPERATORS;
                }
                break;

            // after default state
            case KEYWORD:
                if(Character.isLetterOrDigit(c) ||
                        c == '_' || c == '!')
                {
                    token += c;
                }
                else {
                    tokType = findKeyWord(token);
                    tokenArrayList.add( new Token(tokType, token) );
                    token = "";
                    state = State.DEFAULT;
                    i--; // minus the index in-case symbols like parenthesis, it will triggered this else code block
                }
                break;


            case STRING:
                if( c == y.charAt(0) ) // if it is single colon
                {
                    tokenArrayList.add( new Token(Token.Type.STRING, token) );
                    token = "";
                    state = State.DEFAULT;
                }
                else
                {
                    token += c;
                }
                break;
            case NUMBER:
                if( Character.isDigit(c) || (c == '.'))
                {
                    token += c;
                }
                else
                {
                    tokenArrayList.add( new Token(Token.Type.NUMBER, token ) );
                    token = "";
                    state = State.DEFAULT;
                    i--;
                }
                break;
            case OPERATORS:
                tokenArrayList.add( findOperator(c) );
                state = State.DEFAULT;
                break;
        }
    }

    public void printTokens(){
        for (Token tok : tokenArrayList){
            System.out.println(String.valueOf(tok.getType() + " ------- " + tok.getText()));
        }
    }

    private Token.Type findKeyWord(String s){
        s = s.toLowerCase();

        switch (s){
            case "sulat":
            tokType = Token.Type.PRINT;
                break;
            case "start":
            tokType = Token.Type.START_BLOCK;
                break;
            case "end":
                tokType = Token.Type.END_BLOCK;
                break;
            case "var":
                tokType = Token.Type.VARIABLE;
                break;
        }


        return tokType;
    }
    private boolean isParenthesis( char c )
    {
        boolean prntOp = c == '(' || c == ')';
        boolean braceOp = c == '{' || c == '}';
        boolean brakOp = c == '[' || c == ']';

        return prntOp || braceOp || brakOp;
    }

    private Token.Type findParenthesisType( char c )
    {
        Token.Type type = Token.Type.UNKNOWN;
        switch( c )
        {
            case '(':
                type = Token.Type.LEFT_PARENTHESIS;
                break;
            case ')':
                type = Token.Type.RIGHT_PARENTHESIS;
                break;
            case '{':
                type = Token.Type.LEFT_BRACE;
                break;
            case '}':
                type = Token.Type.RIGHT_BRACE;
                break;
            case '[':
                type = Token.Type.LEFT_BRACKET;
                break;
            case ']':
                type = Token.Type.RIGHT_BRACKET;
                break;
        }

        return type;

    }

    private Token findOperator(char c){
        Token token = new Token(Token.Type.UNKNOWN, "Unknows");

        if (c == '='){
            token = new Token(Token.Type.EQUALS, "=");
        }else if (c == '+'){
            token = new Token(Token.Type.PLUS, "+");

        }else if (c == '-'){
            token = new Token(Token.Type.MINUS, "-");

        }else if (c == '/'){
            token = new Token(Token.Type.DIVISION, "/");

        }else  if (c == '*'){
            token = new Token(Token.Type.MULTIPLICATION, "*");
        }
        else  if (c == '>'){
            token = new Token(Token.Type.MULTIPLICATION, "*");
        }
        else  if (c == '&'){
            token = new Token(Token.Type.AND, "&");
        }
        else  if (c == '|'){
            token = new Token(Token.Type.OR, "|");
        }
        else  if (c == '>'){
            token = new Token(Token.Type.GREATER_THAN, ">");
        }
        else  if (c == '<'){
            token = new Token(Token.Type.LESS_THAN, "<");
        }

        return token;
    }



}
