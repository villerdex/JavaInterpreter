# JavaInterpreter

Ultra Simple java Interpreter

SUPPORTS:

-Variable expressions
-Print Statements
-If Statement

NOT SUPPORTED:
-IF elseif else statement
- >= and <= bitwise operator 


//        String code = "START;" +
//                "var Y = 'hell'; " +
//                "var X = 'waaaaaa;'; " +
//                "sulat(Y X);" +
//                "END;";

        String code = "START;" +
                "var Y = 'hell'; " +
                "var X = 'waaaaaa;'; " +
                "sulat(Y + X);" +
                "END;";


//        String code = "START;" +
//                "kung('Hello' = 'hello' & 3 < 5 & 3 = 3){ };" +
//                "END;";
//
//        String code = "START;" +
//                "kung('hello' = 'hello' & 3 < 5 & 3 = 3) { sulat('NICE') };  " +
//                "" +
//                "END;";


        Interpreter interpreter = new Interpreter(code);
