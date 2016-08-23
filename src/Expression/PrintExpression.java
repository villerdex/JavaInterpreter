package Expression;

import Tokens.Token;

/**
 * Created by Didoy on 8/22/2016.
 */
public class PrintExpression implements Expression {

    Token token ;
    public PrintExpression(Token token) {
        this.token = token;
    }

    @Override
    public void evaluate() {

        if (token.getType() == Token.Type.STRING){
            System.out.println(token.getText());
        }else if (token.getType() == Token.Type.VARIABLE){

            // if the variable holds a integer value
             if (token.getType() == Token.Type.NUMBER){
                System.out.println(String.valueOf(token.getVaraiable().getValue()));
             }else{ // if the variable holds a string value
                 System.out.println(token.getVaraiable().getValue());
             }
        }

    }
}
