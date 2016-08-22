package Expression;

/**
 * Created by Didoy on 8/22/2016.
 */
public class PrintExpression implements Expression {

    String var;
    public PrintExpression(String var) {
        this.var = var;
    }

    @Override
    public void evaluate() {

        System.out.println(var);

    }
}
