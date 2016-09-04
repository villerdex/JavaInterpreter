package Parselet;

import Expression.InterpreterException;
import Tokens.Token;
import main.Interpreter;
import main.Parser;
import main.Varaiable;

import java.util.ArrayList;

import static Tokens.Token.Type.NUMBER;
import static Tokens.Token.Type.STRING;

/**
 * Created by Didoy on 8/23/2016.
 */
public class StatementVariable implements Statement {

    String varName;
    Object value = null;
    Varaiable varaiable = new Varaiable("", null);
    ArrayList<Token> param;
    Parser parser;
    Token OP = null;
    Token.Type state = Token.Type.OBJECT; // default object


    public StatementVariable(String varName , ArrayList<Token> parameters, Parser parser ) {
        this.param = parameters;
        this.varName = varName;
        this.parser = parser;
    }


    @Override
    public void execute() {
        evaluateParam(); // this will map and create data

        varaiable.setName(varName.trim());
        varaiable.setValue(value);

        Parselet.getVarList().add(varaiable);

    }

    private void evaluateParam(){

        for (Token token : param){
            eval(state, token);
        }
    }

    private void eval(Token.Type state, Token token){


        switch (token.getType()){
            case OPERATOR:
                OP = parser.findOperator(token.getText().charAt(0));
                break;
            case STRING:
                if (checkCompatability(state, STRING)){
                    if (value != null ){
                            if (OP.getType() == Token.Type.PLUS){
                                value += token.getText(); // concatinate
                            }
                    }else {
                        value = token.getText(); // assgin the value
                    }
                    state = STRING;
                }
                break;

            case NUMBER:
                if (checkCompatability(state, NUMBER)){

                    if (value != null){
                        int val = Integer.parseInt(token.getText() );
                        value = operate(OP, val);  // get the final value

                    }else {
                        value = Integer.parseInt( token.getText() );

                    }
                    state = NUMBER;
                }
                break;
        }

    }

    private boolean checkCompatability(Token.Type state, Token.Type tokenState){
        boolean isCompatible  = false;
        if ( state == Token.Type.OBJECT ){
            state = tokenState;

            isCompatible =  checkCompatability(state, tokenState);
        }else if (state == tokenState){
                isCompatible =  true;
        }else {
            InterpreterException.ThrowException( state + " is not Compatible with " + tokenState, Expression.ExceptionType.InCompatibleException);
        }
        return isCompatible;
    }

    private Integer operate(Token OP, int val){
        if (OP.getType() == Token.Type.PLUS){
            value =( Integer ) value + val; // ADD
        }
        else if (OP.getType() == Token.Type.MINUS){
            value =( Integer ) value - val; // MINUS
        }
        else if (OP.getType() == Token.Type.DIVISION){
            value =( Integer ) value/ val; // DIVISION
        }
        else if (OP.getType() == Token.Type.MULTIPLICATION){
            value = ( Integer )value * val; // MULTIPLICATION
        }
        return (Integer) value;
    }
}
