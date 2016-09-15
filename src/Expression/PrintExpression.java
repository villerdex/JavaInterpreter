package Expression;

import Parselet.Parselet;
import Tokens.Token;
import main.Parser;
import main.Utility;
import main.Varaiable;

import java.util.ArrayList;

/**
 * Created by Didoy on 8/22/2016.
 */
public class PrintExpression implements Expression {

    Token.Type OP = null;
    private ArrayList<Token> parameters = new ArrayList<Token>();
    private Parselet parselet;
    private Varaiable varaiable;
    private String str = "";

    public PrintExpression(ArrayList<Token> parameters, Parselet parselet) {
        this.parameters.addAll(parameters);
        this.parselet = parselet;
    }

    @Override
    public void evaluate() {

        evaluateParam();
        System.out.println(str);

    }

    private void evaluateParam() {
        Token.Type state = Token.Type.OBJECT;

        for (Token token : parameters) {
            eval(state, token);
        }
    }

    private void eval(Token.Type state, Token token) {
        if (token.getType() == Token.Type.KEYWORD) {

            varaiable = Parselet.findVarDefinition(token.getText().trim());

            if (varaiable == null) {
                setValue("null", state, Token.Type.STRING);
            } else {
                setValue((String) varaiable.getValue(), state, Token.Type.STRING);
            }
            state = Token.Type.KEYWORD;
        } else if (token.getType() == Token.Type.STRING) {
            setValue(token.getText(), state, Token.Type.STRING);
            state = Token.Type.STRING;

        } else if (token.getType() == Token.Type.NUMBER) {

            setValue(token.getText(), state, Token.Type.NUMBER);
            state = Token.Type.NUMBER;

        } else if (token.getType() == Token.Type.OPERATOR) {
            Token.Type tokType = parselet.findOperator(token.getText().charAt(0));
            if (tokType == Token.Type.PLUS) {
                OP = tokType;
            }
        } else {
            System.out.println("Unknow print statement " + token.getType());
        }
    }

    private void setValue(String val, Token.Type state, Token.Type tokeType) {
        if (Utility.checkCompatability(state, tokeType)) {
            if (str.equals("")) {
                str = val;
            } else {
                if (OP != null) {
                    str = str + val;
                } else {
                    InterpreterException.ThrowException("Missing", ExceptionType.ArgumentException);
                    str = "";
                }
            }
        }

    }

}
