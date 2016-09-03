package Parselet;

import main.Varaiable;

/**
 * Created by Didoy on 8/23/2016.
 */
public class StatementVariable implements Statement {

    String varName;
    Object value;
    Varaiable varaiable;

    public StatementVariable(Varaiable varaiable) {
        this.varaiable = varaiable;
    }

    @Override
    public void execute() {

        varName = varaiable.getName();
        value = varaiable.getValue();
    }
}
