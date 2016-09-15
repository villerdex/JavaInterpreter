# JavaInterpreter

Ultra Simple java Interpreter

SUPPORTS:

-Variable expressions
-Print Statements
-If Statement

NOT SUPPORTED:
-IF elseif else statement
- >= and <= bitwise operator 


        String code = "START;" +
                "var Y = 'hell'; " +
                "var X = 'waaaaaa;'; " +
                "sulat(Y + X);" +
                "END;";



        Interpreter interpreter = new Interpreter(code);
        
        OUTPUT: hellwaaaaaa 
