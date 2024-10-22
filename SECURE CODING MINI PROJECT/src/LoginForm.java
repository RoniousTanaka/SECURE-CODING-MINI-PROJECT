
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.net.URI;

    public class LoginForm {
        private JFrame frame;
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;

        public LoginForm() {
            frame = new JFrame("Login");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(10, 30, 80, 25);
            frame.add(usernameLabel);

            usernameField = new JTextField();
            usernameField.setBounds(100, 30, 160, 25);
            frame.add(usernameField);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(10, 70, 80, 25);
            frame.add(passwordLabel);

            passwordField = new JPasswordField();
            passwordField.setBounds(100, 70, 160, 25);
            frame.add(passwordField);

            loginButton = new JButton("Login");
            loginButton.setBounds(100, 110, 100, 25);
            frame.add(loginButton);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if (username.equals("admin") && password.equals("password")) {
                        try {
                            Desktop.getDesktop().browse(new URI("http://www.yourwebpage.com"));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    }
                }
            });

            frame.setVisible(true);
        }

        public static void main(String[] args) {
            new LoginForm();
        }
    }
