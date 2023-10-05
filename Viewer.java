import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Color;

public class Viewer {
  /**
   * Controller for handling user interactions.
   */
  private final Controller controller;

  /**
   * TextField to display the current expression.
   */
  private JTextField textField;

  /**
   * Initializes a new instance of the `Viewer` class.
   * It creates an instance of the `Controller` for handling user interactions.
   * Also it creates GUI objects as a TextField, JButtons, JFrame, etc and initializes it.
   */
  Viewer() {
    controller = new Controller(this);

    Font textFont = new Font("", Font.PLAIN, 45);
    Font buttonFont = new Font("", Font.BOLD, 32);

    Color calculatorBackgroundColor = new Color(22, 22, 22);
    Color buttonColor = new Color(66, 66, 66);
    Color textColor = new Color(212, 212, 212);
    Color textFieldColor = new Color(66, 66, 66);

    Border buttonsBorder = new LineBorder(buttonColor, 1);

    textField = new JTextField();
    textField.setFont(textFont);
    textField.setForeground(textColor);
    textField.setBackground(textFieldColor);
    textField.setBounds(5, 7, 350, 95);
    textField.setEditable(false);
    textField.setBorder(buttonsBorder);
    textField.setRequestFocusEnabled(false);
    textField.setHorizontalAlignment(4);

    JButton buttonClear = new JButton("C");
    buttonClear.setFont(buttonFont);
    buttonClear.setForeground(textColor);
    buttonClear.setBackground(buttonColor);
    buttonClear.setBounds(5, 108, 85, 85);
    buttonClear.setRequestFocusEnabled(false);
    buttonClear.setBorder(buttonsBorder);
    buttonClear.addActionListener(controller);
    buttonClear.setActionCommand("C");


    JButton buttonLeftBracket = new JButton("(");
    buttonLeftBracket.setFont(buttonFont);
    buttonLeftBracket.setForeground(textColor);
    buttonLeftBracket.setBackground(buttonColor);
    buttonLeftBracket.setBounds(93, 108, 85, 85);
    buttonLeftBracket.setRequestFocusEnabled(false);
    buttonLeftBracket.setBorder(buttonsBorder);
    buttonLeftBracket.addActionListener(controller);
    buttonLeftBracket.setActionCommand("(");

    JButton buttonRightBracket = new JButton(")");
    buttonRightBracket.setFont(buttonFont);
    buttonRightBracket.setForeground(textColor);
    buttonRightBracket.setBackground(buttonColor);
    buttonRightBracket.setBounds(181, 108, 85, 85);
    buttonRightBracket.setRequestFocusEnabled(false);
    buttonRightBracket.setBorder(buttonsBorder);
    buttonRightBracket.addActionListener(controller);
    buttonRightBracket.setActionCommand(")");

    JButton buttonDiv = new JButton("\u00f7");
    buttonDiv.setFont(buttonFont);
    buttonDiv.setForeground(textColor);
    buttonDiv.setBackground(buttonColor);
    buttonDiv.setBounds(269, 108, 85, 85);
    buttonDiv.setRequestFocusEnabled(false);
    buttonDiv.setBorder(buttonsBorder);
    buttonDiv.addActionListener(controller);
    buttonDiv.setActionCommand("Div");

    JButton buttonSeven = new JButton("7");
    buttonSeven.setFont(buttonFont);
    buttonSeven.setForeground(textColor);
    buttonSeven.setBackground(buttonColor);
    buttonSeven.setBounds(5, 196, 85, 85);
    buttonSeven.setRequestFocusEnabled(false);
    buttonSeven.setBorder(buttonsBorder);
    buttonSeven.addActionListener(controller);
    buttonSeven.setActionCommand("7");

    JButton buttonEight = new JButton("8");
    buttonEight.setFont(buttonFont);
    buttonEight.setForeground(textColor);
    buttonEight.setBackground(buttonColor);
    buttonEight.setBounds(93, 196, 85, 85);
    buttonEight.setRequestFocusEnabled(false);
    buttonEight.setBorder(buttonsBorder);
    buttonEight.addActionListener(controller);
    buttonEight.setActionCommand("8");

    JButton buttonNine = new JButton("9");
    buttonNine.setFont(buttonFont);
    buttonNine.setForeground(textColor);
    buttonNine.setBackground(buttonColor);
    buttonNine.setBounds(181, 196, 85, 85);
    buttonNine.setRequestFocusEnabled(false);
    buttonNine.setBorder(buttonsBorder);
    buttonNine.addActionListener(controller);
    buttonNine.setActionCommand("9");

    JButton buttonMult = new JButton("\u00d7");
    buttonMult.setFont(buttonFont);
    buttonMult.setForeground(textColor);
    buttonMult.setBackground(buttonColor);
    buttonMult.setBounds(269, 196, 85, 85);
    buttonMult.setRequestFocusEnabled(false);
    buttonMult.setBorder(buttonsBorder);
    buttonMult.addActionListener(controller);
    buttonMult.setActionCommand("*");

    JButton buttonFour = new JButton("4");
    buttonFour.setFont(buttonFont);
    buttonFour.setForeground(textColor);
    buttonFour.setBackground(buttonColor);
    buttonFour.setBounds(5, 284, 85, 85);
    buttonFour.setRequestFocusEnabled(false);
    buttonFour.setBorder(buttonsBorder);
    buttonFour.addActionListener(controller);
    buttonFour.setActionCommand("4");

    JButton buttonFive = new JButton("5");
    buttonFive.setFont(buttonFont);
    buttonFive.setFont(buttonFont);
    buttonFive.setForeground(textColor);
    buttonFive.setBackground(buttonColor);
    buttonFive.setBounds(93, 284, 85, 85);
    buttonFive.setRequestFocusEnabled(false);
    buttonFive.setBorder(buttonsBorder);
    buttonFive.addActionListener(controller);
    buttonFive.setActionCommand("5");

    JButton buttonSix = new JButton("6");
    buttonSix.setFont(buttonFont);
    buttonSix.setForeground(textColor);
    buttonSix.setBackground(buttonColor);
    buttonSix.setBounds(181, 284, 85, 85);
    buttonSix.setRequestFocusEnabled(false);
    buttonSix.setBorder(buttonsBorder);
    buttonSix.addActionListener(controller);
    buttonSix.setActionCommand("6");

    JButton buttonMinus = new JButton("-");
    buttonMinus.setFont(buttonFont);
    buttonMinus.setForeground(textColor);
    buttonMinus.setBackground(buttonColor);
    buttonMinus.setBounds(269, 284, 85, 85);
    buttonMinus.setRequestFocusEnabled(false);
    buttonMinus.setBorder(buttonsBorder);
    buttonMinus.addActionListener(controller);
    buttonMinus.setActionCommand("-");

    JButton buttonOne = new JButton("1");
    buttonOne.setFont(buttonFont);
    buttonOne.setForeground(textColor);
    buttonOne.setBackground(buttonColor);
    buttonOne.setBounds(5, 372, 85, 85);
    buttonOne.setRequestFocusEnabled(false);
    buttonOne.setBorder(buttonsBorder);
    buttonOne.addActionListener(controller);
    buttonOne.setActionCommand("1");

    JButton buttonTwo = new JButton("2");
    buttonTwo.setFont(buttonFont);
    buttonTwo.setForeground(textColor);
    buttonTwo.setBackground(buttonColor);
    buttonTwo.setBounds(93, 372, 85, 85);
    buttonTwo.setRequestFocusEnabled(false);
    buttonTwo.setBorder(buttonsBorder);
    buttonTwo.addActionListener(controller);
    buttonTwo.setActionCommand("2");

    JButton buttonThree = new JButton("3");
    buttonThree.setFont(buttonFont);
    buttonThree.setForeground(textColor);
    buttonThree.setBackground(buttonColor);
    buttonThree.setBounds(181, 372, 85, 85);
    buttonThree.setRequestFocusEnabled(false);
    buttonThree.setBorder(buttonsBorder);
    buttonThree.addActionListener(controller);
    buttonThree.setActionCommand("3");

    JButton buttonPlus = new JButton("+");
    buttonPlus.setFont(buttonFont);
    buttonPlus.setForeground(textColor);
    buttonPlus.setBackground(buttonColor);
    buttonPlus.setBounds(269, 372, 85, 85);
    buttonPlus.setRequestFocusEnabled(false);
    buttonPlus.setBorder(buttonsBorder);
    buttonPlus.addActionListener(controller);
    buttonPlus.setActionCommand("+");

    JButton buttonDelete = new JButton("\u232b");
    buttonDelete.setFont(buttonFont);
    buttonDelete.setForeground(textColor);
    buttonDelete.setBackground(buttonColor);
    buttonDelete.setBounds(5, 460, 85, 85);
    buttonDelete.setRequestFocusEnabled(false);
    buttonDelete.setBorder(buttonsBorder);
    buttonDelete.addActionListener(controller);
    buttonDelete.setActionCommand("Del");

    JButton buttonZero = new JButton("0");
    buttonZero.setFont(buttonFont);
    buttonZero.setForeground(textColor);
    buttonZero.setBackground(buttonColor);
    buttonZero.setBounds(93, 460, 85, 85);
    buttonZero.setRequestFocusEnabled(false);
    buttonZero.setBorder(buttonsBorder);
    buttonZero.addActionListener(controller);
    buttonZero.setActionCommand("0");

    JButton buttonPoint = new JButton(".");
    buttonPoint.setFont(buttonFont);
    buttonPoint.setForeground(textColor);
    buttonPoint.setBackground(buttonColor);
    buttonPoint.setBounds(181, 460, 85, 85);
    buttonPoint.setRequestFocusEnabled(false);
    buttonPoint.setBorder(buttonsBorder);
    buttonPoint.addActionListener(controller);
    buttonPoint.setActionCommand(".");

    JButton buttonEqual = new JButton("=");
    buttonEqual.setFont(buttonFont);
    buttonEqual.setForeground(textColor);
    buttonEqual.setBackground(buttonColor);
    buttonEqual.setBounds(269, 460, 85, 85);
    buttonEqual.setRequestFocusEnabled(false);
    buttonEqual.setBorder(buttonsBorder);
    buttonEqual.addActionListener(controller);
    buttonEqual.setActionCommand("=");

    JFrame frame = new JFrame("Calculator");
    frame.setSize(375, 590);
    frame.setResizable(false);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(calculatorBackgroundColor);
    frame.add(textField);

    frame.add(buttonClear);
    frame.add(buttonLeftBracket);
    frame.add(buttonRightBracket);
    frame.add(buttonDiv);

    frame.add(buttonSeven);
    frame.add(buttonEight);
    frame.add(buttonNine);
    frame.add(buttonMult);

    frame.add(buttonFour);
    frame.add(buttonFive);
    frame.add(buttonSix);
    frame.add(buttonMinus);

    frame.add(buttonThree);
    frame.add(buttonTwo);
    frame.add(buttonOne);
    frame.add(buttonPlus);

    frame.add(buttonDelete);
    frame.add(buttonZero);
    frame.add(buttonPoint);
    frame.add(buttonEqual);


    frame.setVisible(true);
  }

  /**
   * Updates the displayed result and full expression on the GUI.
   *
   * @param result         The result of the expression evaluation to be displayed.
   *
   */
  public void update(String result) {
    // A variable that changes size of text depending on the number of digits entered by the user.
    int adaptedSize = 45;
    if (result.length() > 12 && result.length() <= 19) {
      adaptedSize = 45 - ((result.length() - 12) * 2);
    }
    else if (result.length() > 19 && result.length() <= 29) {
      adaptedSize = 45 - ((20 - 12) * 2 + (result.length() - 19))  ;
    }
    else if (result.length() > 29){
      adaptedSize = 14;
    }
    Font newTextFont = new Font("", Font.PLAIN, adaptedSize);
    textField.setFont(newTextFont);

    textField.setText(result);
  }
}
