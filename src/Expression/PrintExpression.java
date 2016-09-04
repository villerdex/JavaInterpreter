package Expression;

import Parselet.Parselet;
import Tokens.Token;
import main.Parser;
import main.Varaiable;

import java.util.ArrayList;

/**
 *
 * Created by Didoy on 8/22/2016.
 */
public class PrintExpression implements Expression {

    private Token token ;
    private Parselet parselet;
    private Varaiable varaiable;

    public PrintExpression(Token token,  Parselet parselet) {
        this.token = token;
        this.parselet = parselet;

    }

    @Override
    public void evaluate() {

        if (token.getType() == Token.Type.KEYWORD){

            token.setType(Token.Type.VARIABLE);
            varaiable = parselet.findVarDefinition(token.getText().trim());

            if (varaiable == null){
                varaiable = new Varaiable(token.getText(), "null");
            }
        }

        if (token.getType() == Token.Type.STRING){
            System.out.println(token.getText());
        }
        else if (token.getType() == Token.Type.NUMBER){
            System.out.println(token.getText());

        }
        else if (token.getType() == Token.Type.VARIABLE){
            // if the variable holds a integer value
             if (token.getType() == Token.Type.NUMBER){
                System.out.println(String.valueOf(token.getVaraiable().getValue()));
             }else{ // if the variable holds a string value
                 System.out.println(varaiable.getValue());
             }
        }else {
            System.out.println("Unknow print statement " + token.getType());
        }
    }

}
