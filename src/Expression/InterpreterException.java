package Expression;

/**
 * Created by Didoy on 9/4/2016.
 */
public class InterpreterException {

    public static void ThrowException(String ex) {

        System.out.println(ex);

    }

    public static void ThrowException(String ex, ExceptionType exceptionType) {

        System.out.println(ex + " " + exceptionType);

    }
}
