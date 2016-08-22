package main;

import Tokens.Token;

import java.util.ArrayList;

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
        KEYWORD
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

        for (i = 0; i < source.length() -1; i++ ){

        char c = source.charAt(i);
            findToken(c);
        }
        System.out.println("Tokenize finish");
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
                    i--; // minus the index incase when symbols like parenthesis triggered this else code block
                }
                break;


            case STRING:
                if( c == y.charAt(0) )
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


}
