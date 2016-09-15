package main;

import Expression.ExceptionType;
import Expression.InterpreterException;
import Tokens.Token;

import static Tokens.Token.Type.NUMBER;
import static Tokens.Token.Type.STRING;

/**
 * Created by Didoy on 9/9/2016.
 */
public class Utility {

    public static boolean checkCompatability(Token.Type state, Token.Type tokenState) {
        boolean isCompatible = false;
        if (state == Token.Type.OBJECT) {
            state = tokenState;

            isCompatible = checkCompatability(state, tokenState);
        } else if (state == tokenState) {
            isCompatible = true;
        } else {
            InterpreterException.ThrowException(state + " is not Compatible with " + tokenState, ExceptionType.InCompatibleException);
        }
        return isCompatible;
    }

    public static Token.Type findVariableType(Object varvalue) {
        Token.Type type = null;
        if (varvalue instanceof String) {
            type = STRING;
        } else if (varvalue instanceof Integer) {
            type = NUMBER;
        }
        return type;
    }
}
