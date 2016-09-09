package main;

import Expression.ExceptionType;
import Expression.InterpreterException;
import Tokens.Token;

/**
 * Created by Didoy on 9/9/2016.
 */
public class Utility {

    public static boolean checkCompatability(Token.Type state, Token.Type tokenState){
        boolean isCompatible  = false;
        if ( state == Token.Type.OBJECT ){
            state = tokenState;

            isCompatible =  checkCompatability(state, tokenState);
        }else if (state == tokenState){
            isCompatible =  true;
        }else {
            InterpreterException.ThrowException( state + " is not Compatible with " + tokenState, ExceptionType.InCompatibleException);
        }
        return isCompatible;
    }
}
