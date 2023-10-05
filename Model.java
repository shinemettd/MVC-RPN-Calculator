/**
 * The `Model` class represents the core logic of a calculator application.
 * It manages the calculator's state, processes user input, and performs calculations.
 *
 * This class maintains the current infix expression, the full expression, and the Reverse
 * Polish Notation (RPN) calculator for evaluating expressions. It also interacts with a
 * viewer component to display the current state of the calculator.
 *
 */
public class Model {

    private String infixForm;         // Current infix expression
    private String lastAnswer;        // Saving last answer to facilitate the work of algorithms
    private String lastOperation;     //Saving last operation to facilitate the work of algorithms
    private RPN rpn;                  // Reverse Polish Notation calculator
    private Viewer viewer;            // Viewer component for displaying calculator output
    Stack<Character> bracketsStack;   // Stack of right side brackets to track total number of closing brackets
    Stack<Boolean> pointFlags;        // Stack of glags to track if a decimal point is set

    /**
     * Constructs a new `Model` instance
     * Sets all instances to default value
     *
     * @param viewer The viewer component responsible for displaying calculator output.
     */
    public Model(Viewer viewer) {
        infixForm = "";
        lastAnswer = "";
        this.viewer = viewer;
        rpn = new RPN();
        bracketsStack = new Stack<>();
        pointFlags = new Stack<>();
        pointFlags.push(false);
    }

    /**
     * Performs an action based on the provided symbol.
     *
     * @param symbol The symbol representing a user action or input.
     */
    public void doAction(String symbol) {
        if (infixForm.equals("NaN")) {
          clearTextField();
        }
        if (symbol.equals("Del")) {
            deleteLastSym();
        } else if (symbol.equals("C")) {
            clearTextField();
        } else if (symbol.equals("Div")) {
            handleSymbol('/');
        } else if (symbol.equals("=")) {
            calculateAndUpdate();
            return;
        } else {
            handleSymbol(symbol.charAt(0));
        }
        viewer.update(infixForm);
    }

    /**
     * Deletes the whole users text field
     * and sets all instances to default value
     */
    private void clearTextField() {
      infixForm = "";
      bracketsStack = new Stack<>();
      pointFlags = new Stack<>();
      pointFlags.push(false);
    }

    /**
     * Deletes the last character in the infix expression if it is not empty.
     */
    private void deleteLastSym() {
        if (infixForm.length() != 0) {
            char lastSymbol = infixForm.charAt(infixForm.length() - 1);
            if (isOperator(lastSymbol)) {
                pointFlags.pop();
            } else if (lastSymbol == '('){
                bracketsStack.pop();
            }
            infixForm = infixForm.substring(0, infixForm.length() - 1);
        }
    }

    /**
     * Calculates and updates the result based on the current infix expression.
     */
    private void calculateAndUpdate() {
        closeAllBrackets();
        if (infixForm.equals(lastAnswer) && !haveAnyOperator()) {
            infixForm += lastOperation;
        }
        if (haveAnyOperator()) {
            pointFlags.push(false);
            getLastOperation();
            Double result = rpn.calculate(infixForm);
            String stringResult = resultToString(result);
            lastAnswer = stringResult;
            viewer.update(stringResult);
            infixForm = stringResult;
        }
    }

    /**
     * Gets and sets value to the lastOperation
     */
    private void getLastOperation() {
      int lastOpnFlag = -1;
      for (int i = infixForm.length() - 1; i >= 0; i--) {
        if (isOperator(infixForm.charAt(i))) {
          lastOpnFlag = i;
          break;
        }
      }
      String temp = "";
      for (int i = lastOpnFlag; i < infixForm.length(); i++) {
        temp += infixForm.charAt(i);
      }
      lastOperation = temp;
    }

    /**
     * Checks if mathematical expression have any operator
     *
     * @return True if the infixForm instance have one of the operators; otherwise, false.
     */
    private boolean haveAnyOperator() {
      for (int i = 0; i < infixForm.length(); i++) {
        if (isOperator(infixForm.charAt(i)) || infixForm.charAt(i) == '(' || infixForm.charAt(i) == ')') {
          return true;
        }
      }
      return false;
    }

    /**
     * Handles a symbol (operand, operator, or special symbol) in the infix expression.
     *
     * @param symbol The symbol to handle.
     */
    private void handleSymbol(char symbol) {
        char lastChar = infixForm.isEmpty() ? '&' : infixForm.charAt(infixForm.length() - 1);

        if (isOperand(symbol)) {
            if (lastChar == ')') {
                infixForm += "*" + symbol;
            } else if (!haveAnyOperator() && symbol == '0' && infixForm.equals("0")) {
                infixForm = infixForm;
            } else {
                infixForm += symbol;
            }
        } else if (isOperator(symbol)) {
            handleOperator(symbol, lastChar);
        } else if (isSpecialSymbol(symbol)) {
            handleSpecialSymbol(symbol, lastChar);
        }
    }

    /**
     * Handles an operator symbol in the infix expression.
     *
     * @param symbol   The operator symbol to handle.
     * @param lastChar The last character in the infix expression.
     */
    private void handleOperator(char symbol, char lastChar) {
        pointFlags.push(false);
        if (infixForm.isEmpty() && symbol == '-') {
            infixForm += symbol;
        } else if (isOperator(lastChar)) {
            deleteLastSym();
            infixForm += symbol;
        } else if (lastChar == '(' && symbol == '-') {
            infixForm += symbol;
        } else if (isOperand(lastChar)) {
            infixForm += symbol;
        } else if (lastChar == ')') {
            infixForm += symbol;
        }
    }

    /**
     * Handles a special symbol (parentheses or decimal point) in the infix expression.
     *
     * @param symbol   The special symbol to handle.
     * @param lastChar The last character in the infix expression.
     */
    private void handleSpecialSymbol(char symbol, char lastChar) {
        if (symbol == '(') {
            if (isOperand(lastChar) || lastChar == ')') {
                infixForm += "*" + symbol;
                bracketsStack.push('(');
                pointFlags.push(false);
            } else if (lastChar != '.') {
                infixForm += symbol;
                bracketsStack.push('(');
            }
        } else if (symbol == ')' && "+-*/".indexOf(lastChar) == -1 && isOperand(lastChar)) {
            if (!bracketsStack.isEmpty()) {
                infixForm += symbol;
                bracketsStack.pop();
            }
        } else if (symbol == '.' && pointFlags.peek() != true) {
            if (infixForm.isEmpty() || "(+-*/".indexOf(lastChar) != -1) {
                infixForm += "0" + symbol;
                pointFlags.push(true);
            } else {
                infixForm += symbol;
                pointFlags.push(true);
            }
        }
    }

    /**
     * Closes all open parentheses in the infix expression using bracketsStack
     */
    private void closeAllBrackets() {
        while (!bracketsStack.isEmpty()) {
            bracketsStack.pop();
            infixForm += ")";
        }
    }

    /**
     * Checks if a character is an operand (digit).
     *
     * @param input The character to check.
     * @return True if the character is an operand; otherwise, false.
     */
    private boolean isOperand(Character input) {
        if (Character.isDigit(input)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a character is an operator (+, -, *, /).
     *
     * @param input The character to check.
     * @return True if the character is an operator; otherwise, false.
     */
    private boolean isOperator(Character input) {
        if (input == '+' || input == '-' || input == '*' || input == '/') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a character is a special symbol (parentheses or decimal point).
     *
     * @param input The character to check.
     * @return True if the character is a special symbol; otherwise, false.
     */
    private boolean isSpecialSymbol(char input) {
        if (input == '(' || input == ')' || input == '.') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Converts a Double result to a formatted String, removing trailing zeros and decimal point
     * if necessary.
     *
     * @param result The Double result to convert.
     * @return The formatted result as a String.
     */
    private String resultToString(Double result) {
        String stringResult = String.valueOf(result);
        char expectedPoint = stringResult.charAt(stringResult.length() - 2);
        char expectedZero = stringResult.charAt(stringResult.length() - 1);

        if (expectedPoint == '.' && expectedZero == '0') {
            return stringResult.substring(0, stringResult.length() - 2);
        } else {
            pointFlags = new Stack<>();
            pointFlags.push(true);
            return stringResult;
        }
    }
}
