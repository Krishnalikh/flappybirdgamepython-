import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdvancedCalculator extends JFrame implements ActionListener {
    // Components
    private JTextArea display;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton eqButton, clearButton, delButton;
    private JButton sqrtButton, powButton, logButton, lnButton, sinButton, cosButton, tanButton, percButton;
    private JButton piButton, eButton, invButton, factButton;
    private JButton quadButton, diffButton, intButton;
    private JPanel panel;

    private String operator = "";
    private double num1 = 0, num2 = 0, result = 0;

    public AdvancedCalculator() {
        // Setup Frame
        this.setTitle("Advanced Calculator");
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create display with border
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Adding border
        this.add(display, BorderLayout.NORTH);

        // Create panel for buttons
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 4, 5, 5));

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setPreferredSize(new Dimension(60, 40)); // Smaller button size
            numberButtons[i].setBackground(Color.WHITE); // White background for number buttons
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
            button.setPreferredSize(new Dimension(60, 40)); // Smaller button size
            if (button.equals(clearButton) || button.equals(delButton)) {
                button.setBackground(Color.BLACK); // Black background for clear and delete
                button.setForeground(Color.WHITE); // White text for clear and delete
            }
            button.addActionListener(this);
            panel.add(button);
        }

        // Advanced Operation buttons
        sqrtButton = new JButton("√");
        powButton = new JButton("x^y");
        logButton = new JButton("log");
        lnButton = new JButton("ln");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        percButton = new JButton("%");
        piButton = new JButton("π");
        eButton = new JButton("e");
        invButton = new JButton("1/x");
        factButton = new JButton("!");

        // Set preferred size for advanced operation buttons
        JButton[] advancedOperationButtons = {sqrtButton, powButton, logButton, lnButton, sinButton, cosButton, tanButton, percButton, piButton, eButton, invButton, factButton};
        for (JButton button : advancedOperationButtons) {
            button.setPreferredSize(new Dimension(60, 40)); // Smaller button size
            button.addActionListener(this);
            panel.add(button);
        }

        // Additional Operation buttons
        quadButton = new JButton("Solve Ax^2 + Bx + C");
        diffButton = new JButton("d/dx");
        intButton = new JButton("∫dx");

        // Set preferred size for additional operation buttons
        JButton[] additionalOperationButtons = {quadButton, diffButton, intButton};
        for (JButton button : additionalOperationButtons) {
            button.setPreferredSize(new Dimension(60, 40)); // Smaller button size
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
            } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("x^y")) {
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
                    case "x^y":
                        result = Math.pow(num1, num2);
                        break;
                }
                display.setText(String.valueOf(result));
            } else if (text.equals("√")) {
                double number = Double.parseDouble(display.getText());
                result = Math.sqrt(number);
                display.setText(String.valueOf(result));
            } else if (text.equals("log")) {
                double number = Double.parseDouble(display.getText());
                result = Math.log10(number);
                display.setText(String.valueOf(result));
            } else if (text.equals("ln")) {
                double number = Double.parseDouble(display.getText());
                result = Math.log(number);
                display.setText(String.valueOf(result));
            } else if (text.equals("sin")) {
                double number = Double.parseDouble(display.getText());
                result = Math.sin(Math.toRadians(number));
                display.setText(String.valueOf(result));
            } else if (text.equals("cos")) {
                double number = Double.parseDouble(display.getText());
                result = Math.cos(Math.toRadians(number));
                display.setText(String.valueOf(result));
            } else if (text.equals("tan")) {
                double number = Double.parseDouble(display.getText());
                result = Math.tan(Math.toRadians(number));
                display.setText(String.valueOf(result));
            } else if (text.equals("%")) {
                double number = Double.parseDouble(display.getText());
                result = number / 100;
                display.setText(String.valueOf(result));
            } else if (text.equals("π")) {
                display.setText(String.valueOf(Math.PI));
            } else if (text.equals("e")) {
                display.setText(String.valueOf(Math.E));
            } else if (text.equals("1/x")) {
                double number = Double.parseDouble(display.getText());
                result = 1 / number;
                display.setText(String.valueOf(result));
            } else if (text.equals("!")) {
                int number = Integer.parseInt(display.getText());
                result = factorial(number);
                display.setText(String.valueOf(result));
            } else if (text.equals("Solve Ax^2 + Bx + C")) {
                String input = JOptionPane.showInputDialog(this, "Enter coefficients A, B, and C separated by spaces:");
                String[] parts = input.split(" ");
                double a = Double.parseDouble(parts[0]);
                double b = Double.parseDouble(parts[1]);
                double c = Double.parseDouble(parts[2]);

                double discriminant = b * b - 4 * a * c;
                if (discriminant > 0) {
                    double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                    double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                    display.setText(String.format("Roots: %.2f and %.2f", root1, root2));
                } else if (discriminant == 0) {
                    double root = -b / (2 * a);
                    display.setText(String.format("Root: %.2f", root));
                } else {
                    double realPart = -b / (2 * a);
                    double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
                    display.setText(String.format("Roots: %.2f + %.2fi and %.2f - %.2fi", realPart, imaginaryPart, realPart, imaginaryPart));
                }
            } else if (text.equals("d/dx")) {
                // Dummy implementation for differentiation
                double number = Double.parseDouble(display.getText());
                result = number; // Placeholder for differentiation result
                display.setText("d/dx of " + number + " is " + result);
            } else if (text.equals("∫dx")) {
                // Dummy implementation for integration
                double number = Double.parseDouble(display.getText());
                result = number; // Placeholder for integration result
                display.setText("∫ of " + number + " is " + result);
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
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
            AdvancedCalculator calculator = new AdvancedCalculator();
            calculator.setVisible(true);
        });
    }
}