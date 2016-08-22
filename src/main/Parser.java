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

    private ArrayList<Statement> statementArrayList = new ArrayList<>();

    private  Parselet parselet = new Parselet(this);

    public Parser() {

    }

    public ArrayList<Statement> getStatementArrayList(){
        return statementArrayList;
    }

    public void parse(ArrayList<Token> tokenList){
        tokenArrayList = tokenList;

        System.out.println("parse started");
        matchToken(Token.Type.START_BLOCK);
        while( getCurrentToken().getType() != Token.Type.END_BLOCK )
        {
            identifyKeyWord(getCurrentToken().getType());

        }
        matchToken(Token.Type.END_BLOCK);

        System.out.println("parse finish");

    }

    private void identifyKeyWord(Token.Type token ){
        Statement statement;
        switch (token){
            case PRINT:
             statement = parselet.statementPrint();
             statementArrayList.add(statement);
        }
    }


    public void matchToken(Token.Type token){
        if (token == getCurrentToken().getType()){
            eatToken();
        }
    }

    public Token getCurrentToken(){

        Token token = tokenArrayList.get(currentTokenIndex);

        return token;
    }

    private void eatToken(){
        currentTokenIndex += 1;
        System.out.println(currentTokenIndex + "---------" + tokenArrayList.size());
    }

    public Expression getExpression(){
        Expression expression = null;

        while (getCurrentToken().getType() == Token.Type.PRINT){
            expression = new PrintExpression(String.valueOf(getCurrentToken().getText()));
        }
        return expression;
    }
}
