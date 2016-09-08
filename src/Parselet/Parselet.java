package Parselet;

import Expression.ExceptionType;
import Expression.InterpreterException;
import Expression.PrintExpression;
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

    private Parser parser;
    private static final ArrayList<Varaiable> variableList = new ArrayList<Varaiable>();
    private ArrayList<Token> paramList = new ArrayList<Token>();

    public Parselet(Parser parser) {
        this.parser = parser;
    }

    public Statement statementPrint(){
        parser.matchToken(Token.Type.PRINT);
        parser.matchToken(Token.Type.LEFT_PARENTHESIS);

        PrintExpression printExpression = new PrintExpression(parser.getCurrentToken(), this);
        Statement result = new StatementPrint( printExpression );

        parser.matchToken(parser.getCurrentToken().getType());
        parser.matchToken(Token.Type.RIGHT_PARENTHESIS);
        return result;
    }


    public Statement statementVariable(){
        Statement result = null;
        parser.matchToken(Token.Type.VARIABLE);

        String varName  = parser.getCurrentToken().getText();

        parser.matchToken( Token.Type.KEYWORD );

            if (parser.getCurrentToken().getType() == Token.Type.OPERATOR){
              if (findOperator( parser.getCurrentToken() .getText().charAt(0) ) == Token.Type.EQUALS){
                    parser.matchToken( parser.getCurrentToken().getType() );
              }else {
                  InterpreterException.ThrowException(" Invalid " + ExceptionType.ArgumentException);
              }
            }
                while (parser.getCurrentToken().getType() != Token.Type.PRINT && parser.getCurrentToken().getType() != Token.Type.VARIABLE){

                     paramList.add(parser.getCurrentToken() ); // ADD THE TOKEN into parameter list
                     parser.matchToken( parser.getCurrentToken().getType() );

                }

        result = new StatementVariable( varName , paramList, parser );
        paramList.clear();
        return  result;
    }

    public static Varaiable findVarDefinition(String varName){

        Varaiable var = null ;
        for (Varaiable v : variableList ){
            Varaiable varaiable = (Varaiable) v;

            if ( v.getName().equals(varName) ){
                varaiable.setName(varName);
                varaiable.setValue(v.getValue());
                var = varaiable;
            }
        }
        return  var;
    }

    private Token.Type findOperator(char c){
        Token.Type type = Token.Type.UNKNOWN;


        if (c == '='){
            type = Token.Type.EQUALS;
        }else if (c == '+'){
            type = Token.Type.PLUS;

        }else if (c == '-'){
            type = Token.Type.MINUS;

        }else if (c == '/'){
            type = Token.Type.DIVISION;

        }else  if (c == '*'){
            type = Token.Type.MULTIPLICATION;

        }
        else  if (c == '>'){
            type = Token.Type.GREATER_THAN;
        }
        else  if (c == '&'){
            type = Token.Type.AND;

        }
        else  if (c == '|'){
            type = Token.Type.OR;
        }
        else  if (c == '<'){
            type = Token.Type.LESS_THAN;

        }

        return type;
    }


    public static ArrayList<Varaiable>  getVarList(){
        return variableList;
    }


}
