package main;

import Expression.Expression;
import Expression.PrintExpression;
import Parselet.Statement;
import Parselet.Parselet;
import Tokens.Token;

import java.util.ArrayList;

/**
 * Created by Didoy on 8/22/2016.
 */
public class Parser {

    private int currentTokenIndex = 0;

    private ArrayList<Token> tokenArrayList;

    private ArrayList<Statement> statementsAST = new ArrayList<>();

    private  Parselet parselet = new Parselet(this);

    public Parser() {

    }

    public ArrayList<Statement> getStatementsAST(){
        return statementsAST;
    }

    public void parse(ArrayList<Token> tokenList){
        tokenArrayList = tokenList;

        matchToken(Token.Type.START_BLOCK);
        while( getCurrentToken().getType() != Token.Type.END_BLOCK )
        {
            identifyKeyWord(getCurrentToken().getType());

        }
        matchToken(Token.Type.END_BLOCK);

    }

    private void identifyKeyWord(Token.Type token ){
        Statement statement;
        switch (token){
            case VARIABLE:
                statement = parselet.statementVariable();
                statementsAST.add(statement);
                break;
            case PRINT:
                 statement = parselet.statementPrint();
                 statementsAST.add(statement);
                break;
        }
    }


    public void matchToken(Token.Type token){
        if (token == getCurrentToken().getType()){
            eatToken(1);    // eat the current token
        }
    }

    public Token getCurrentToken(){

        Token token = tokenArrayList.get(currentTokenIndex);

        return token;
    }

    public Token getCurrentToken(int offset){

        Token token = tokenArrayList.get(currentTokenIndex + offset);

        return token;
    }

    private void eatToken(int offset){
        System.out.println(currentTokenIndex+1 + "---------"+ getCurrentToken().getType() + " ----- " + tokenArrayList.size() + " ---- " + getCurrentToken().getText() );               // print index of token eaten
        currentTokenIndex += offset;

    }

    public Token findOperator(char c){
        Token token = new Token(Token.Type.UNKNOWN, String.valueOf(c));

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


//    public Expression getExpression(){
//        Expression expression = null;
//
//        while (getCurrentToken().getType() == Token.Type.PRINT){
//            expression = new PrintExpression(getCurrentToken());
//        }
//        return expression;
//    }
}
