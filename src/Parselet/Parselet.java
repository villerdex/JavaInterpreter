package Parselet;

import Expression.PrintExpression;
import Tokens.Token;
import main.Parser;
import main.Varaiable;

import java.util.ArrayList;

/**
 * Created by Didoy on 8/22/2016.
 */
public class Parselet {

    private Parser parser;
    private ArrayList<Varaiable> variableList = new ArrayList<Varaiable>();
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
        parser.matchToken( Token.Type.EQUALS) ;

        if (parser.getCurrentToken().getType() == Token.Type.KEYWORD){
            Varaiable varaiable =   findVarDefinition(parser.getCurrentToken().getText());
                    if (varaiable != null){
                        
                    }
        }

        while (parser.getCurrentToken().getType() == Token.Type.KEYWORD  ||  parser.getCurrentToken().getType() == Token.Type.VARIABLE  ||
                parser.getCurrentToken().getType() == Token.Type.NUMBER  || parser.getCurrentToken().getType() == Token.Type.STRING ||
                parser.getCurrentToken().getType() == Token.Type.OPERATOR ){

            paramList.add(parser.getCurrentToken());

            result = new StatementVariable( paramList );

            parser.matchToken(parser.getCurrentToken().getType());
        }

        return  result;
    }

    public Varaiable findVarDefinition(String varName){

        Varaiable var = null ;
        for (Object o : variableList ){
            Varaiable varaiable = (Varaiable) o;

            if ( ((Varaiable) o).getName().equals(varName) ){
                varaiable.setName(varName);
                varaiable.setValue(((Varaiable) o).getValue());
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
            type = Token.Type.EQUALS;

        }else if (c == '-'){
            type = Token.Type.EQUALS;


        }else if (c == '/'){
            type = Token.Type.EQUALS;

        }else  if (c == '*'){
            type = Token.Type.EQUALS;

        }
        else  if (c == '>'){
            type = Token.Type.EQUALS;
        }
        else  if (c == '&'){
            type = Token.Type.EQUALS;

        }
        else  if (c == '|'){
            type = Token.Type.EQUALS;
        }
        else  if (c == '<'){
            type = Token.Type.EQUALS;

        }else {
            type = Token.Type.EQUALS;
        }

        return type;
    }



}
