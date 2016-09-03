package Parselet;

import main.Varaiable;

import java.util.ArrayList;

/**
 * Created by Didoy on 8/23/2016.
 */
public class StatementVariable implements Statement {

    String varName;
    Object value;
    Varaiable varaiable;
    ArrayList param;


    public StatementVariable(ArrayList parameters) {
        this.param = parameters;
    }


    @Override
    public void execute() {

        varName = varaiable.getName();
        value = varaiable.getValue();
    }
}
