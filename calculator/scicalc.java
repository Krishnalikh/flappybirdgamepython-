import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.commons.math3.analysis.function.Log;
import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.analysis.function.Cos;
import org.apache.commons.math3.analysis.function.Sin;
import org.apache.commons.math3.analysis.function.Tan;
import org.apache.commons.math3.analysis.function.Asin;
import org.apache.commons.math3.analysis.function.Acos;
import org.apache.commons.math3.analysis.function.Atan;

public class scicalc extends JFrame implements ActionListener {
    // Components
    private JTextArea display;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton eqButton, clearButton, delButton;
    private JButton sqrtButton, cbrtButton, powButton, logButton, lnButton;
    private JButton sinButton, cosButton, tanButton, asinButton, acosButton, atanButton;
    private JButton percButton, piButton, eButton, invButton, factButton;
    private JButton modButton, lbracketButton, rbracketButton;
    private JButton matrixAddButton, matrixMulButton;
    private JButton powerButton, quadButton, diffButton, intButton;
    private JPanel panel;

    private String operator = "";
    private double num1 = 0, num2 = 0, result = 0;

    public scicalc() {
        // Setup Frame
        this.setTitle("Scientific Calculator");
        this.setSize(600, 800); // Increased size to accommodate larger result screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create display with border
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24)); // Increased font size
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Adding border
        display.setPreferredSize(new Dimension(600, 100)); // Increased display size
        this.add(display, BorderLayout.NORTH);

        // Create panel for buttons
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 4, 2, 2)); // Increased rows to accommodate new buttons

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setPreferredSize(new Dimension(50, 50)); // Larger button size
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size
            numberButtons[i].addActionListener(this);
            panel.add(numberButtons[i]);
        }

        // Basic Operation buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clearButton = new JButton("C");
        delButton = new JButton("Del");

        // Set preferred size and background color for basic operation buttons
        JButton[] basicOperationButtons = {addButton, subButton, mulButton, divButton, eqButton, clearButton, delButton};
        for (JButton button : basicOperationButtons) {
            button.setPreferredSize(new Dimension(50, 50)); // Larger button size
            button.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size
            if (button.equals(clearButton) || button.equals(delButton)) {
                button.setBackground(Color.BLACK); // Black background for clear and delete
                button.setForeground(Color.WHITE); // White text for clear and delete
            }
            button.addActionListener(this);
            panel.add(button);
        }

        // Advanced Operation buttons
        sqrtButton = new JButton("√");
        cbrtButton = new JButton("∛");
        powButton = new JButton("10^x");
        logButton = new JButton("log");
        lnButton = new JButton("ln");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        asinButton = new JButton("asin");
        acosButton = new JButton("acos");
        atanButton = new JButton("atan");
        percButton = new JButton("%");
        piButton = new JButton("π");
        eButton = new JButton("e");
        invButton = new JButton("1/x");
        factButton = new JButton("!");

        // Set preferred size for advanced operation buttons
        JButton[] advancedOperationButtons = {sqrtButton, cbrtButton, powButton, logButton, lnButton, sinButton, cosButton, tanButton, asinButton, acosButton, atanButton, percButton, piButton, eButton, invButton, factButton};
        for (JButton button : advancedOperationButtons) {
            button.setPreferredSize(new Dimension(50, 50)); // Larger button size
            button.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size
            button.addActionListener(this);
            panel.add(button);
        }

        // Additional Operation buttons
        modButton = new JButton("mod");
        lbracketButton = new JButton("(");
        rbracketButton = new JButton(")");
        matrixAddButton = new JButton("Matrix +");
        matrixMulButton = new JButton("Matrix *");

        // Set preferred size for additional operation buttons
        JButton[] additionalOperationButtons = {modButton, lbracketButton, rbracketButton, matrixAddButton, matrixMulButton};
        for (JButton button : additionalOperationButtons) {
            button.setPreferredSize(new Dimension(50, 50)); // Larger button size
            button.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size
            button.addActionListener(this);
            panel.add(button);
        }

        // New Operation buttons
        powerButton = new JButton("x^y");
        quadButton = new JButton("Quad");
        diffButton = new JButton("Diff");
        intButton = new JButton("Int");

        // Set preferred size for new operation buttons
        JButton[] newOperationButtons = {powerButton, quadButton, diffButton, intButton};
        for (JButton button : newOperationButtons) {
            button.setPreferredSize(new Dimension(50, 50)); // Larger button size
            button.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size
            button.addActionListener(this);
            panel.add(button);
        }

        this.add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = source.getText();

        try {
            if (text.matches("\\d")) {
                display.append(text);
            } else if (text.equals("C")) {
                display.setText("");
                operator = "";
                num1 = num2 = result = 0;
            } else if (text.equals("Del")) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("10^x")) {
                num1 = Double.parseDouble(display.getText());
                operator = text;
                display.setText("");
            } else if (text.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    case "10^x":
                        result = Math.pow(10, num1);
                        break;
                }
                display.setText(String.format("%.6f", result));
            } else if (text.equals("√")) {
                double number = Double.parseDouble(display.getText());
                result = Math.sqrt(number);
                display.setText(String.format("%.6f", result));
            } else if (text.equals("∛")) {
                double number = Double.parseDouble(display.getText());
                result = Math.cbrt(number);
                display.setText(String.format("%.6f", result));
            } else if (text.equals("log")) {
                double number = Double.parseDouble(display.getText());
                result = Math.log10(number);
                display.setText(String.format("%.6f", result));
            } else if (text.equals("ln")) {
                double number = Double.parseDouble(display.getText());
                result = Math.log(number);
                display.setText(String.format("%.6f", result));
            } else if (text.equals("sin")) {
                double number = Double.parseDouble(display.getText());
                result = Math.sin(Math.toRadians(number));
                display.setText(String.format("%.6f", result));
            } else if (text.equals("cos")) {
                double number = Double.parseDouble(display.getText());
                result = Math.cos(Math.toRadians(number));
                display.setText(String.format("%.6f", result));
            } else if (text.equals("tan")) {
                double number = Double.parseDouble(display.getText());
                result = Math.tan(Math.toRadians(number));
                display.setText(String.format("%.6f", result));
            } else if (text.equals("asin")) {
                double number = Double.parseDouble(display.getText());
                result = Math.toDegrees(Math.asin(number));
                display.setText(String.format("%.6f", result));
            } else if (text.equals("acos")) {
                double number = Double.parseDouble(display.getText());
                result = Math.toDegrees(Math.acos(number));
                display.setText(String.format("%.6f", result));
            } else if (text.equals("atan")) {
                double number = Double.parseDouble(display.getText());
                result = Math.toDegrees(Math.atan(number));
                display.setText(String.format("%.6f", result));
            } else if (text.equals("%")) {
                double number = Double.parseDouble(display.getText());
                result = number / 100;
                display.setText(String.format("%.6f", result));
            } else if (text.equals("π")) {
                display.setText(String.format("%.6f", Math.PI));
            } else if (text.equals("e")) {
                display.setText(String.format("%.6f", Math.E));
            } else if (text.equals("1/x")) {
                double number = Double.parseDouble(display.getText());
                result = 1 / number;
                display.setText(String.format("%.6f", result));
            } else if (text.equals("!")) {
                int number = Integer.parseInt(display.getText());
                result = factorial(number);
                display.setText(String.format("%d", result));
            } else if (text.equals("mod")) {
                num2 = Double.parseDouble(display.getText());
                result = num1 % num2;
                display.setText(String.format("%.6f", result));
            } else if (text.equals("(")) {
                display.append("(");
            } else if (text.equals(")")) {
                display.append(")");
            } else if (text.equals("Matrix +")) {
                handleMatrixOperation("add");
            } else if (text.equals("Matrix *")) {
                handleMatrixOperation("multiply");
            } else if (text.equals("x^y")) {
                num1 = Double.parseDouble(display.getText());
                operator = text;
                display.setText("");
            } else if (text.equals("Quad")) {
                handleQuadraticEquation();
            } else if (text.equals("Diff")) {
                handleDifferentiation();
            } else if (text.equals("Int")) {
                handleIntegration();
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    private void handleQuadraticEquation() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter coefficients a, b, and c (separated by spaces):");
            String[] parts = input.split(" ");
            double a = Double.parseDouble(parts[0]);
            double b = Double.parseDouble(parts[1]);
            double c = Double.parseDouble(parts[2]);

            double discriminant = b * b - 4 * a * c;
            String resultText;
            if (discriminant > 0) {
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                resultText = String.format("%.6f", root1) + " and " + String.format("%.6f", root2);
            } else if (discriminant == 0) {
                double root = -b / (2 * a);
                resultText = String.format("%.6f", root);
            } else {
                double realPart = -b / (2 * a);
                double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
                resultText = String.format("%.6f", realPart) + " ± " + String.format("%.6f", imaginaryPart) + "i";
            }
            display.setText(resultText);
        } catch (Exception ex) {
            display.setText("Error in quadratic equation");
        }
    }

    private void handleDifferentiation() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter function (e.g., sin, cos, log):");
            String resultText;
            switch (input.toLowerCase()) {
                case "sin":
                    resultText = "cos";
                    break;
                case "cos":
                    resultText = "-sin";
                    break;
                case "tan":
                    resultText = "sec^2";
                    break;
                case "log":
                    resultText = "1/x";
                    break;
                case "exp":
                    resultText = "exp(x)";
                    break;
                case "sqrt":
                    resultText = "1/(2√x)";
                    break;
                case "x":
                    resultText = "1";
                    break;
                default:
                    resultText = "Unknown function";
                    break;
            }
            display.setText(resultText);
        } catch (Exception ex) {
            display.setText("Error in differentiation");
        }
    }

    private void handleIntegration() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter function (e.g., sin, cos, log):");
            String resultText;
            switch (input.toLowerCase()) {
                case "sin":
                    resultText = "-cos";
                    break;
                case "cos":
                    resultText = "sin";
                    break;
                case "tan":
                    resultText = "log|sec(x)|";
                    break;
                case "log":
                    resultText = "x * log(x) - x";
                    break;
                case "exp":
                    resultText = "exp(x)";
                    break;
                case "sqrt":
                    resultText = "2√x";
                    break;
                case "x":
                    resultText = "x^2/2";
                    break;
                default:
                    resultText = "Unknown function";
                    break;
            }
            display.setText(resultText);
        } catch (Exception ex) {
            display.setText("Error in integration");
        }
    }

    private void handleMatrixOperation(String operation) {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter matrix elements in the format:\n" +
                    "A11 A12\n" +
                    "A21 A22\n" +
                    "Separate rows with a newline and columns with a space.");
            String[] rows = input.split("\n");
            List<List<Double>> matrix = new ArrayList<>();
            for (String row : rows) {
                String[] elements = row.split(" ");
                List<Double> matrixRow = new ArrayList<>();
                for (String element : elements) {
                    matrixRow.add(Double.parseDouble(element));
                }
                matrix.add(matrixRow);
            }

            if (operation.equals("add")) {
                // Handle matrix addition
                display.setText("Matrix addition result displayed");
            } else if (operation.equals("multiply")) {
                // Handle matrix multiplication
                display.setText("Matrix multiplication result displayed");
            }
        } catch (Exception ex) {
            display.setText("Error in matrix operation");
        }
    }

    private long factorial(int number) {
        if (number < 0) return 0; // Factorial is not defined for negative numbers
        if (number == 0) return 1;
        long fact = 1;
        for (int i = 1; i <= number; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            scicalc calculator = new scicalc();
            calculator.setVisible(true);
        });
    }
}
