package main;

/**
 * Created by Didoy on 8/20/2016.
 */
public class Main {

    public static void main(String[] args) {


        String code = "START;" +
                "sulat('xxx');" +
                "END;";


        Interpreter interpreter = new Interpreter(code);

    }

}

