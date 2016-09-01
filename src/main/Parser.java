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
            case PRINT:
             statement = parselet.statementPrint();
             statementArrayList.add(statement);
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

    private void eatToken(int offset){
        currentTokenIndex += offset;
        //System.out.println(currentTokenIndex + "---------" + tokenArrayList.size());
    }

    public Expression getExpression(){
        Expression expression = null;

        while (getCurrentToken().getType() == Token.Type.PRINT){
            expression = new PrintExpression(getCurrentToken());
        }
        return expression;
    }
}
