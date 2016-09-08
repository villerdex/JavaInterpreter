package main;

import Parselet.Statement;

import java.util.ArrayList;

/**
 * Created by Didoy on 8/20/2016.
 */
public class Interpreter {


    public Interpreter() {
    }

    public Interpreter(String source) {

        run(source);

    }

    private Lexer lexer = new Lexer();
    private Parser parser = new Parser();

    public void  run(String source){
        source = validate(source);
        lexer.tokenize(source);
        //lexer.printTokens();

        parser.parse(lexer.getTokenArrayList());

        ArrayList<Statement> statementArrayList = parser.getStatementsAST();

        for (Statement statement : statementArrayList){
            statement.execute();
        }
    }

    private String validate(String s){
        String source = "";

          String[] raw  = s.split(";");
                for (String str : raw){
                   str = str + " ";    // add space every after semicolon
                    source += str;
                }
        return source;
    }

}
