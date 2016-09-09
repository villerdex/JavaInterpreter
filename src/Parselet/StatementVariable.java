package Parselet;

import Expression.InterpreterException;
import Expression.VariableExpression;
import Tokens.Token;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import main.Interpreter;
import main.Parser;
import main.Varaiable;

import java.util.ArrayList;

import static Tokens.Token.Type.*;

/**
 * Created by Didoy on 8/23/2016.
 */
public class StatementVariable implements Statement {


    VariableExpression variableExpression;

    public StatementVariable(VariableExpression variableExpression) {
        this.variableExpression = variableExpression;
    }


    @Override
    public void execute() {

        variableExpression.evaluate();

    }


}
