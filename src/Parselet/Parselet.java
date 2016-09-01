package Parselet;

import Expression.PrintExpression;
import Tokens.Token;
import main.Parser;

/**
 * Created by Didoy on 8/22/2016.
 */
public class Parselet {

    private Parser parser;

    public Parselet(Parser parser) {
        this.parser = parser;
    }

    public Statement statementPrint(){
        parser.matchToken(Token.Type.PRINT);
        parser.matchToken(Token.Type.LEFT_PARENTHESIS);

        PrintExpression printExpression = new PrintExpression(parser.getCurrentToken());
        Statement result = new StatementPrint( printExpression );

        Token.Type x = Token.Type.STRING;
        parser.matchToken(x);
        parser.matchToken(Token.Type.RIGHT_PARENTHESIS);
        return result;
    }

}
