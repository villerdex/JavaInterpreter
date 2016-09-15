package Parselet;

import Expression.BooleanExpression;

/**
 * Created by Didoy on 9/10/2016.
 */
public class StatmentIfElse implements Statement {

    private BooleanExpression booleanExpression;

    public StatmentIfElse(BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;

    }

    @Override
    public void execute() {
        booleanExpression.evaluate();
    }
}
