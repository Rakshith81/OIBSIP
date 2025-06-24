import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

class UserLoginWindow extends JFrame implements ActionListener {
    JButton loginButton;
    JPanel loginPanel;
    JLabel userLabel, passLabel;
    final JTextField userTextField;
    final JPasswordField passTextField;

    UserLoginWindow() {
        userLabel = new JLabel("    Username :");
        userTextField = new JTextField(15);

        passLabel = new JLabel("    Password :");
        passTextField = new JPasswordField(15);

        loginButton = new JButton("   LOGIN   ");

        loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.add(userLabel);
        loginPanel.add(userTextField);
        loginPanel.add(passLabel);
        loginPanel.add(passTextField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);
        loginButton.addActionListener(this);

        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String user = userTextField.getText();
        String pass = new String(passTextField.getPassword());
        if (!pass.isEmpty()) {
            dispose();
            new ExamWindow(user);
        } else {
            passTextField.setText("");
            JOptionPane.showMessageDialog(this, "Please enter your password.");
        }
    }
}

class ExamWindow extends JFrame implements ActionListener {
    JLabel quesLabel;
    JLabel countdownLabel;
    JRadioButton[] choiceButtons = new JRadioButton[4];
    JButton nextBtn, markBtn;
    ButtonGroup choiceGroup;
    int score = 0, quesNo = 0, reviewCount = 1, lastQuestion = 0;
    int[] markedQues = new int[10];
    Timer examTimer = new Timer();

    String[] quizQuestions = {
        "Who is known as father of java programming language?",
        "Number of primitive data types in java are?",
        "Where is system class defined?",
        "Expected created by try block is caught in which block?",
        "Which of the following is not an OOPS concept in java?",
        "Identify the infinite loop?",
        "When is the finalize() method called?",
        "What is the implicit return type of constructor?",
        "The class at the top of exception class hierarchy is....?",
        "Which provides runtime environment for java byte code to be executed?"
    };

    String[][] quizOptions = {
        {"Charles Babbage", "James Gosling", "M.P.Java", "Blaise Pascal"},
        {"6", "7", "8", "9"},
        {"java.lang.package", "java.util.package", "java.lo.package", "None"},
        {"catch", "throw", "final", "thrown"},
        {"Polymorphism", "Inheritance", "Compilation", "Encapsulation"},
        {"for(;;)", "for()i=0;j<1;i--", "for(int=0;i++)", "if(All of the above)"},
        {"Before garbage collection", "Before an object goes out of scope", "Before a variable goes out of scope", "None"},
        {"No return type", "A class object in which it is defined", "void", "None"},
        {"ArithmeticException", "Throwable", "Object", "Console"},
        {"JDK", "JVM", "JRE", "JAVAC"}
    };

    int[] correctAnswers = {1, 2, 0, 0, 2, 0, 0, 0, 1, 1};

    ExamWindow(String username) {
        super("Welcome, " + username);

        quesLabel = new JLabel();
        countdownLabel = new JLabel();

        choiceGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            choiceButtons[i] = new JRadioButton();
            choiceGroup.add(choiceButtons[i]);
            add(choiceButtons[i]);
        }

        nextBtn = new JButton("Save and Next");
        markBtn = new JButton("Mark for Review");
        nextBtn.addActionListener(this);
        markBtn.addActionListener(this);

        add(quesLabel);
        add(countdownLabel);
        add(nextBtn);
        add(markBtn);

        setLayout(null);
        setBounds(250, 100, 600, 350);

        quesLabel.setBounds(30, 40, 500, 20);
        countdownLabel.setBounds(400, 10, 150, 20);
        for (int i = 0; i < 4; i++) {
            choiceButtons[i].setBounds(50, 80 + i * 30, 300, 20);
        }
        nextBtn.setBounds(95, 240, 140, 30);
        markBtn.setBounds(270, 240, 150, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        displayQuestion();

        examTimer.scheduleAtFixedRate(new TimerTask() {
            int timeLeft = 600;

            public void run() {
                countdownLabel.setText("Time left: " + timeLeft + " sec");
                timeLeft--;
                if (timeLeft < 0) {
                    examTimer.cancel();
                    countdownLabel.setText("Time's Up!");
                    JOptionPane.showMessageDialog(ExamWindow.this, "Time's up! Your score: " + score);
                    System.exit(0);
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            if (isSelectedCorrect()) score++;
            quesNo++;
            displayQuestion();
            if (quesNo == 9) {
                nextBtn.setEnabled(false);
                markBtn.setText("Finish");
            }
        } else if (e.getSource() == markBtn && markBtn.getText().equals("Mark for Review")) {
            JButton markButton = new JButton("GoTo " + reviewCount);
            markButton.setBounds(480, 20 + 30 * reviewCount, 100, 30);
            add(markButton);
            markButton.addActionListener(this);
            markedQues[reviewCount] = quesNo;
            reviewCount++;
            quesNo++;
            displayQuestion();
            if (quesNo == 9) markBtn.setText("Finish");
            setVisible(false);
            setVisible(true);
        } else if (e.getActionCommand().startsWith("GoTo")) {
            if (isSelectedCorrect()) score++;
            lastQuestion = quesNo;
            int buttonIndex = Integer.parseInt(e.getActionCommand().substring(5));
            quesNo = markedQues[buttonIndex];
            displayQuestion();
            ((JButton) e.getSource()).setEnabled(false);
            quesNo = lastQuestion;
        } else if (markBtn.getText().equals("Finish")) {
            if (isSelectedCorrect()) score++;
            JOptionPane.showMessageDialog(this, "Final Score = " + score);
            System.exit(0);
        }
    }

    void displayQuestion() {
        choiceGroup.clearSelection();
        if (quesNo < quizQuestions.length) {
            quesLabel.setText("Q" + (quesNo + 1) + ": " + quizQuestions[quesNo]);
            for (int i = 0; i < 4; i++) {
                choiceButtons[i].setText(quizOptions[quesNo][i]);
            }
        }
    }

    boolean isSelectedCorrect() {
        return choiceButtons[correctAnswers[quesNo]].isSelected();
    }
}

public class ExamLauncher {
    public static void main(String[] args) {
        try {
            UserLoginWindow loginUI = new UserLoginWindow();
            loginUI.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
