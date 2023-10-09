package GuessTheWord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class TipOfMyTounge implements ActionListener{
    JFrame frame;
    JPanel panel;
    CardLayout cardLayout;

    JPanel gamePanel;
    JTextField textField;
    JTextField[][] text = new JTextField[6][6]; 
    JButton enterButton;
    JButton quitButton;

    JPanel mainMenu;
    JButton newWordButton;
    JLabel difficultyLabel;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;


    TipOfMyTounge() {
        frame = new JFrame();

        cardLayout = new CardLayout();
        panel = new JPanel();
        panel.setLayout(cardLayout);

        int x = 50, y = 10;
        for(int row = 0; row < 6; row++){
            for(int column = 0; column < 6; column++){
                text[row][column] = new JTextField("");
                text[row][column].setSize(50, 50);
                text[row][column].setLocation(x, y);
                text[row][column].setEditable(false);
                text[row][column].setBackground(Color.LIGHT_GRAY);
                x += 55;
            }
            x = 50;
            y += 55;
        }
        
        x-= 20;

        textField = new JTextField();
        textField.setSize(220, 50);
        textField.setLocation(x, y);
        x += 225;

        enterButton = new JButton();
        enterButton.setText("Enter");
        enterButton.setFocusable(false);
        enterButton.setSize(70, 50);
        enterButton.setLocation(x, y);
        enterButton.addActionListener(this);
        x += 75;

        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setFocusable(false);
        quitButton.setSize(70, 50);
        quitButton.setLocation(x, y);
        quitButton.addActionListener(this);


        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.DARK_GRAY);
        for(int row = 0; row < 6; row++)
            for(int column = 0; column < 6; column++)
                gamePanel.add(text[row][column]);
        gamePanel.add(textField);
        gamePanel.add(enterButton);
        gamePanel.add(quitButton);
        panel.add(gamePanel,"1");
        
        x = 150;
        y = 50;

        newWordButton = new JButton();
        newWordButton.setText("New Word?");
        newWordButton.setFocusable(false);
        newWordButton.setSize(200, 50);
        newWordButton.setLocation(x, y);
        newWordButton.addActionListener(this);
        y += 100;

        difficultyLabel = new JLabel();
        difficultyLabel.setText("Difficulty:");
        difficultyLabel.setForeground(Color.white);
        difficultyLabel.setFont(new Font(null, 0, 20));
        difficultyLabel.setSize(200, 50);
        difficultyLabel.setLocation(x, y);
        y += 55;

        easyButton = new JButton();
        easyButton.setText("Easy");
        easyButton.setFocusable(false);
        easyButton.setSize(200, 50);
        easyButton.setLocation(x, y);
        easyButton.addActionListener(this);
        y += 55;

        mediumButton = new JButton();
        mediumButton.setText("Medium");
        mediumButton.setFocusable(false);
        mediumButton.setSize(200, 50);
        mediumButton.setLocation(x, y);
        mediumButton.addActionListener(this);
        y += 55;

        hardButton = new JButton();
        hardButton.setText("Hard");
        hardButton.setFocusable(false);
        hardButton.setSize(200, 50);
        hardButton.setLocation(x, y);
        hardButton.addActionListener(this);
        y += 55;

        mainMenu = new JPanel();
        mainMenu.setLayout(null);
        mainMenu.setBackground(Color.DARK_GRAY);
        mainMenu.add(newWordButton);
        mainMenu.add(difficultyLabel);
        mainMenu.add(easyButton);
        mainMenu.add(mediumButton);
        mainMenu.add(hardButton);
        panel.add(mainMenu,"2");
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tip Of My Tounge");
        cardLayout.show(panel, "2");
        frame.add(panel);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        new TipOfMyTounge();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newWordButton){
            cardLayout.show(panel,"1");

        }
        if(e.getSource() == quitButton){
            cardLayout.show(panel,"2");
            textField.setText(null);
        }
    }
    
}
