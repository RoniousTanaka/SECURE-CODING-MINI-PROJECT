import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SignUpPage {
    private JPanel mainPanel;
    private JTextField PhoneNumberField;
    private JTextField usernameField;
    private JTextField facultyField;
    private JTextField emailField;
    private JTextField regNumberField;
    private JPasswordField passwordField;
    private JPasswordField passwordField1;
    private JButton signUpButton;
    private static final Map<String, String> userDatabase = new HashMap<>();

    public SignUpPage() {
        createUIComponents();
        frame = new JFrame("Sign Up");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel regNumberLabel = new JLabel("Reg Number:");
        regNumberLabel.setBounds(10, 30, 100, 25);
        frame.add(regNumberLabel);

        regNumberField = new JTextField();
        regNumberField.setBounds(120, 30, 160, 25);
        frame.add(regNumberField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 70, 80, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 160, 25);
        frame.add(passwordField);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 110, 100, 25);
        frame.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regNumber = regNumberField.getText();
                String password = new String(passwordField.getPassword());

                if (!regNumber.isEmpty() && !password.isEmpty()) {
                    userDatabase.put(regNumber, password);
                    JOptionPane.showMessageDialog(frame, "Signed Up Successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                }
            }
        });

        frame.setVisible(true);
    }

    private void createUIComponents() {
        facultyField = new JTextField();
        emailField = new JTextField();
    }

    public static Map<String, String> getUserDatabase() {
        return userDatabase;
    }

    public static void main(String[] args) {
        new SignUpPage();
    }

    private final JFrame frame;
}