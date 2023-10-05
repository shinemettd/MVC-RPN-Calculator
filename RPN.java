/**
 * The `RPN` class represents a utility for performing Reverse Polish Notation (RPN) operations on mathematical expressions.
 * It can convert infix expressions to postfix notation and calculate the result of valid infix expressions.
 */

public class RPN {
    private String infixFormString; // The input infix expression in string format.
    private String postfixFormString; // The converted postfix expression in string format.

    /**
     * Initializes a new instance of the `RPN` class with an empty postfix expression.
     */
    public RPN(){
        postfixFormString = "";
    }

    /**
     * Converts the infix expression to postfix notation using the Shunting Yard algorithm.
     * The result is stored in the `postfixFormString` field.
     */
    private void infixToPostfix() {
        postfixFormString = "";
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < infixFormString.length(); i++) {
            char currentSymbol = infixFormString.charAt(i);
            if (Character.isLetterOrDigit(currentSymbol)) {
                String tempOperand = "";
                while (i < infixFormString.length() && (Character.isLetterOrDigit(infixFormString.charAt(i)) || infixFormString.charAt(i) == '.')) {
                    tempOperand += infixFormString.charAt(i);
                    i++;
                }
                postfixFormString += tempOperand + " ";
                i--;
            } else if (currentSymbol == '(') {
                operatorStack.push(currentSymbol);
            } else if (currentSymbol == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixFormString += operatorStack.pop() + " ";
                }
                operatorStack.pop();
            } else {
                if(currentSymbol == '-'
                    && Character.isDigit(infixFormString.charAt(i+1))
                    && (i == 0 || !Character.isDigit(infixFormString.charAt(i-1)) && infixFormString.charAt(i - 1) != ')')){
                    postfixFormString += "-";
                }else {
                    while (!operatorStack.isEmpty() && operatorPriority(currentSymbol) <= operatorPriority(operatorStack.peek())) {
                        postfixFormString += operatorStack.pop() + " ";
                    }
                    operatorStack.push(currentSymbol);
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixFormString += operatorStack.pop() + " ";
        }

        System.out.println(postfixFormString);
    }

    /**
     * Returns the priority of an operator for use in the Shunting Yard algorithm.
     *
     * @param operator The operator to determine the priority of.
     * @return The priority of the operator (1 for + and -, 2 for * and /, -1 for others).
     */
    private int operatorPriority(char operator) {
        if (operator == '+' || operator == '-') return 1;
        else if (operator == '*' || operator == '/') return 2;
        else return -1;
    }

    /**
     * Calculates the result of a valid infix expression provided as input.
     *
     * @param validInfixForm The valid infix expression to be evaluated.
     * @return The result of the expression evaluation as a double value.
     */
    public double calculate(String validInfixForm) {
        infixFormString = validInfixForm;
        infixToPostfix();
        Stack<Double> stack = new Stack<>();
        String[] array = postfixFormString.split(" ");

        for (int i = 0; i < array.length; i++) {
            if (isDigit(array[i])) {
                double operand = Double.parseDouble(array[i]);
                stack.push(operand);
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                try {
                    double result = applyOperator(array[i], operand1, operand2);
                    stack.push(result);
                } catch (ArithmeticException exception) {
                    return Double.NaN;
                }
            }
        }
        return stack.pop();
    }

    /**
     * Checks if a string token represents a numeric value.
     *
     * @param token The string token to check.
     * @return true if the token represents a numeric value, false otherwise.
     */
    private boolean isDigit(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Applies an operator to two operands and returns the result.
     *
     * @param operator The operator to apply.
     * @param operand1 The first operand.
     * @param operand2 The second operand.
     * @return The result of applying the operator to the operands.
     * @throws ArithmeticException if the operator is invalid or division by zero is attempted.
     */
    private double applyOperator(String operator, double operand1, double operand2) throws ArithmeticException {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new ArithmeticException("This operation is invalid");
        }
    }
}
