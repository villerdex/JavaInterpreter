package Expression;

import Parselet.Parselet;
import Tokens.Token;
import main.Parser;
import main.Utility;
import main.Varaiable;

import java.util.ArrayList;

import static Tokens.Token.Type.KEYWORD;
import static Tokens.Token.Type.NUMBER;
import static Tokens.Token.Type.STRING;

/**
 * Created by Didoy on 9/9/2016.
 */
public class VariableExpression implements Expression {

    String varName;
    Object value = null;
    Varaiable varaiable = new Varaiable("", null);
    ArrayList<Token> params = new ArrayList<>();
    Parser parser;
    Token OP = null;
    Token.Type state = Token.Type.OBJECT; // default object

    public VariableExpression(String varName , ArrayList<Token> parameters, Parser parser ) {
        params.addAll(parameters);
        this.varName = varName;
        this.parser = parser;
    }

    private void evaluateParam(){

        for (Token token : params){
            eval(state, token);
        }
    }

    // this will map each element in paramlist and assign value to varname
    private void eval(Token.Type state, Token token){

        switch (token.getType()){
            case OPERATOR:
                OP = parser.findOperator(token.getText().charAt(0));
                break;
            case STRING:
                if (Utility.checkCompatability(state, STRING)){
                    if (value != null ){
                        if (OP.getType() == Token.Type.PLUS){
                            Varaiable var = Parselet.findVarDefinition(token.getText());
                            if (var != null){
                                value += var.getValue().toString(); // concatinate

                            }else {
                                value += token.getText(); // concatinate
                            }
                        }
                    }else {
                        value = token.getText(); // assgin the value
                    }
                    state = STRING;
                }
                break;

            case NUMBER:
                if (Utility.checkCompatability(state, NUMBER)){

                    if (value != null){
                        Varaiable var = Parselet.findVarDefinition(token.getText());
                        int val;
                        if (var != null){
                            val = (int) var.getValue();
                        }else {
                            val = Integer.parseInt(token.getText() );
                        }
                        value = operate(OP, val);  // get the final value

                    }else {
                        value = Integer.parseInt( token.getText() );

                    }
                    state = NUMBER;
                }
                break;

            case KEYWORD:
                varaiable = Parselet.findVarDefinition(token.getText());

                if (varaiable != null){
                    findVariableType(varaiable.getValue());
                    token.setType(findVariableType(varaiable.getValue())); // set the variable value type
                    // TODO optimize this code
                    if (value != null){
                        if (Utility.checkCompatability( state, token.getType() )){
                            eval(state, token);
                        }

                    }else {
                        varaiable = Parselet.findVarDefinition(token.getText());

                        value = varaiable.getValue();

                    }
                }
                state = KEYWORD;
                break;
        }

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

    private Token.Type findVariableType(Object varvalue){
        Token.Type type = null;
        if (varvalue instanceof String){
            type = STRING;
        }else if (varvalue instanceof Integer){
            type = NUMBER;
        }
        return type;
    }

    @Override
    public void evaluate() {
        evaluateParam();

        varaiable.setName(varName.trim());
        varaiable.setValue(value);

        Parselet.getVarList().add(varaiable);
    }
}
