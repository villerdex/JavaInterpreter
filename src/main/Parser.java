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
        currentTokenIndex += offset;
        System.out.println(currentTokenIndex + "---------" + tokenArrayList.size());
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
