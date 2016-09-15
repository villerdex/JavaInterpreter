package Expression;

import Parselet.Parselet;
import Tokens.Token;
import main.Interpreter;
import main.Utility;
import main.Varaiable;

import java.util.ArrayList;

import static Tokens.Token.Type.*;

/**
 * Created by Didoy on 9/10/2016.
 */
public class BooleanExpression implements Expression {

    Token.Type state = Token.Type.OBJECT;
    Token.Type logicalOp;
    private ArrayList<Token> parameterList = new ArrayList<Token>();
    private ArrayList<Term> termList = new ArrayList<Term>();
    private ArrayList<Token.Type> logicalOperatorList = new ArrayList<>();
    private Varaiable varaiable;
    private Parselet parselet;
    private Object value1 = null;
    private Object value2 = null;
    ;
    private Boolean aBoolean1 = null;
    private Boolean aBoolean2 = null;


    public BooleanExpression(ArrayList<Token> paramlist, Parselet parselet) {
        if (paramlist == null || paramlist.isEmpty()){
            InterpreterException.ThrowException( "Empty parameter List on Boolean expression" );
            System.exit(0);
        }

        parameterList.addAll(paramlist);
        this.parselet = parselet;
    }

    @Override
    public void evaluate() {


    }

    public boolean toBoolean() {

        for (Token token : parameterList) {
            state = Token.Type.OBJECT;
            eval(state, token);
        }

        return finalEvaluation();
    }


    private void eval(Token.Type state, Token token) {

        if (token.getType() == Token.Type.KEYWORD) {

            varaiable = Parselet.findVarDefinition(token.getText().trim());
            if (varaiable != null) {
                Token.Type type = Utility.findVariableType(varaiable.getValue());
                setValue(varaiable.getValue(), state, type);
                this.state = type;
            } else {
                InterpreterException.ThrowException("Null Exception for Variable" + token.getText());
            }
        } else if (token.getType() == Token.Type.STRING) {
            setValue(token.getText(), state, Token.Type.STRING);
            this.state = Token.Type.STRING;

        } else if (token.getType() == Token.Type.NUMBER) {

            setValue(token.getText(), state, Token.Type.NUMBER);
            this.state = Token.Type.NUMBER;

        } else if (token.getType() == Token.Type.OPERATOR) {
            logicalOp = parselet.findOperator(token.getText().charAt(0));

            if (logicalOp == Token.Type.AND || logicalOp == Token.Type.OR) {
                logicalOperatorList.add(logicalOp);
            }
        } else {
            System.out.println("Unknown Expression for IF statement " + token.getType());
        }
    }

    private void setValue(Object val, Token.Type state, Token.Type tokeType) {
        if (Utility.checkCompatability(state, tokeType)) {
            if (value1 == null) {
                value1 = val;
            } else {
                if (logicalOp != null) {
                    value2 = val;
                    termList.add(new Term(value1, value2, logicalOp, tokeType));
                    value1 = null;
                    value2 = null;
                } else {
                    InterpreterException.ThrowException("Operator is null" + ExceptionType.ArgumentException);
                }
            }

        }

    }


    private boolean finalEvaluation() {
        boolean bool = false;
        int X = 0;

        for (Term term : termList) {

            value1 = term.getValue1();
            value2 = term.getValue2();
            logicalOp = term.getOperator();
            state = term.getState();

            switch (state) {

                case STRING:
                    bool = StringEvaluation();
                    break;
                case NUMBER:
                    bool = NumberEvaluation();
                    break;
            }

            if (aBoolean1 == null) {
                aBoolean1 = bool;
            } else if (aBoolean2 == null) {
                aBoolean2 = bool;
            }

            if (aBoolean2 != null) {
                bool = false;
                logicalOp = logicalOperatorList.get(X);

                if (logicalOp == Token.Type.OR) {
                    if (aBoolean1 || aBoolean2) {
                        bool = true;
                    }
                } else if (logicalOp == Token.Type.AND) {
                    if (aBoolean1 && aBoolean2) {
                        bool = true;
                    }
                }
                aBoolean1 = bool;
                aBoolean2 = null;
                X++;
            }

        }

        return bool;
    }

    private boolean NumberEvaluation() {
        boolean bool = false;

        switch (logicalOp) {
            case EQUALS:
                if ((Integer.valueOf((String) value1)) == (Integer.valueOf((String) value2))) {
                    bool = true;
                }
                break;
            case GREATER_THAN:
                if ((Integer.valueOf((String) value1)) > (Integer.valueOf((String) value2))) {
                    bool = true;
                }
                break;
            case LESS_THAN:
                if ((Integer.valueOf((String) value1)) < (Integer.valueOf((String) value2))) {
                    bool = true;
                }
                break;
            default:
                InterpreterException.ThrowException("Unknow Logical Exception " + logicalOp + ExceptionType.ArgumentException);
                break;
        }

        return bool;
    }

    private boolean StringEvaluation() {
        boolean bool = false;
        if (logicalOp == EQUALS) {
            if (value1.equals(value2)) {
                bool = true;
            }
        } else {
            InterpreterException.ThrowException("Invalid Operator for String Comparison");
            System.exit(0);
        }
        return bool;
    }


    private class Term {
        Object value1 = null;
        Object value2 = null;
        Token.Type operator;
        Token.Type state;


        public Term(Object value1, Object value2, Token.Type operator, Token.Type state) {
            this.value1 = value1;
            this.value2 = value2;
            this.operator = operator;
            this.state = state;
        }

        public Token.Type getOperator() {
            return operator;
        }

        public void setOperator(Token.Type operator) {
            this.operator = operator;
        }

        public Object getValue1() {
            return value1;
        }

        public void setValue1(Object value1) {
            this.value1 = value1;
        }

        public Object getValue2() {
            return value2;
        }

        public void setValue2(Object value2) {
            this.value2 = value2;
        }

        public Token.Type getState() {
            return state;
        }

        public void setState(Token.Type state) {
            this.state = state;
        }
    }
}
