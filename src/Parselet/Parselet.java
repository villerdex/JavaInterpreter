package Parselet;

import Expression.*;
import Tokens.Token;
import main.Parser;
import main.Varaiable;
import sun.security.krb5.internal.PAData;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;

/**
 * Created by Didoy on 8/22/2016.
 */
public class Parselet {

    private static final ArrayList<Varaiable> variableList = new ArrayList<Varaiable>();
    private Parser parser;
    private ArrayList<Token> paramList = new ArrayList<Token>();
    private ArrayList<Statement> statementsList = new ArrayList<Statement>();

    public Parselet(Parser parser) {
        this.parser = parser;
    }

    public static Varaiable findVarDefinition(String varName) {

        Varaiable var = null;
        for (Varaiable v : variableList) {
            Varaiable varaiable = (Varaiable) v;

            if (v.getName().equals(varName)) {
                varaiable.setName(varName);
                varaiable.setValue(v.getValue());
                var = varaiable;
            }
        }
        return var;
    }

    public static ArrayList<Varaiable> getVarList() {
        return variableList;
    }

    public Statement statementPrint() {
        parser.matchToken(Token.Type.PRINT);
        parser.matchToken(Token.Type.LEFT_PARENTHESIS);

        while (parser.getCurrentToken().getType() != Token.Type.PRINT && parser.getCurrentToken().getType() != Token.Type.VARIABLE &&
                parser.getCurrentToken().getType() != Token.Type.RIGHT_PARENTHESIS) {

            paramList.add(parser.getCurrentToken()); // ADD THE TOKEN into parameter list
            parser.matchToken(parser.getCurrentToken().getType());

        }

        PrintExpression printExpression = new PrintExpression(paramList, this);
        Statement result = new StatementPrint(printExpression);
        parser.matchToken(Token.Type.RIGHT_PARENTHESIS);
        paramList.clear();

        return result;
    }

    public Statement statementVariable() {
        Statement result = null;
        parser.matchToken(Token.Type.VARIABLE);

        String varName = parser.getCurrentToken().getText();

        parser.matchToken(Token.Type.KEYWORD);

        if (parser.getCurrentToken().getType() == Token.Type.OPERATOR) {
            if (findOperator(parser.getCurrentToken().getText().charAt(0)) == Token.Type.EQUALS) {
                parser.matchToken(parser.getCurrentToken().getType());
            } else {
                InterpreterException.ThrowException(" Invalid " + ExceptionType.ArgumentException);
            }
        }

        while (parser.getCurrentToken().getType() != Token.Type.PRINT && parser.getCurrentToken().getType() != Token.Type.VARIABLE) {

            paramList.add(parser.getCurrentToken()); // ADD THE TOKEN into parameter list
            parser.matchToken(parser.getCurrentToken().getType());

        }

        result = new StatementVariable(new VariableExpression(varName, paramList, parser));
        paramList.clear();
        return result;
    }

    public Statement statementIF() {
        Statement result = null;
        ArrayList paramList = new ArrayList<>();
        parser.matchToken(Token.Type.IF);
        parser.matchToken(Token.Type.LEFT_PARENTHESIS);

        while (parser.getCurrentToken().getType() != Token.Type.RIGHT_PARENTHESIS) {
            paramList.add(parser.getCurrentToken());
            parser.matchToken(parser.getCurrentToken().getType());
        }

        parser.matchToken(Token.Type.RIGHT_PARENTHESIS);
        parser.matchToken(Token.Type.LEFT_BRACE);

        while (parser.getCurrentToken().getType() != Token.Type.RIGHT_BRACE) {
           Statement statement =  parser.getStatementByKeyWord(parser.getCurrentToken().getType());
           statementsList.add(statement);
        }
        parser.matchToken(Token.Type.RIGHT_BRACE);

        result = new StatementIF(new BooleanExpression(paramList, this), parser, statementsList);

        statementsList.clear();
        paramList.clear();
        return result;
    }

    public Token.Type findOperator(char c) {
        Token.Type type = Token.Type.UNKNOWN;


        if (c == '=') {
            type = Token.Type.EQUALS;
        } else if (c == '+') {
            type = Token.Type.PLUS;

        } else if (c == '-') {
            type = Token.Type.MINUS;

        } else if (c == '/') {
            type = Token.Type.DIVISION;

        } else if (c == '*') {
            type = Token.Type.MULTIPLICATION;

        } else if (c == '>') {
            type = Token.Type.GREATER_THAN;
        } else if (c == '&') {
            type = Token.Type.AND;

        } else if (c == '|') {
            type = Token.Type.OR;
        } else if (c == '<') {
            type = Token.Type.LESS_THAN;

        }

        return type;
    }


}
