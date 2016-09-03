package Parselet;

import Expression.Expression;
import main.Parser;

/**
 * Created by Didoy on 8/22/2016.
 */
public class StatementPrint implements Statement{

    Expression expression;

    public StatementPrint(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        expression.evaluate();
    }
}
