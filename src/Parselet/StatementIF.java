package Parselet;

import Expression.BooleanExpression;
import Tokens.Token;
import main.Parser;

import java.util.ArrayList;

/**
 * Created by Didoy on 9/10/2016.
 */
public class StatementIF implements Statement {

    private BooleanExpression booleanExpression;
    private Parser parser;
    private ArrayList<Statement> statementList = new ArrayList<>();

    public StatementIF(BooleanExpression booleanExpression, Parser parser, ArrayList<Statement> statementList) {

        this.statementList.addAll(statementList);
        this.booleanExpression = booleanExpression;
        this.parser = parser;
    }

    @Override
    public void execute() {
        boolean TRUE = booleanExpression.toBoolean();

        if (TRUE) {
            for (Statement statement : statementList){
                statement.execute();
            }
            //System.out.println("TRUE");

        } else {
            System.out.println("FALSE");
        }

    }


}
