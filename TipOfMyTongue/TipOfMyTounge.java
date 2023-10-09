import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TipOfMyTounge implements ActionListener{
    //TODO: Add a win streak label? maybe?
    //TODO: Add a Title Label to the mainMenu Panel?
    //TODO: Add an instructions panel
    //TODO: add an instructions button to mainMenu panel
    //TODO: Find a better 6 letter word list
    //TODO: add an error Label to the gamePanel (to let user know if they typed in word that was too long or doesnt exist)
    //TODO: maybe add game logo as an icon?????
    //TODO: figure out how to make the textfeild submit by hitting the enter key???
    
    JFrame frame;
    JPanel panel;
    CardLayout cardLayout;

    JPanel gamePanel;
    JTextField textField;
    JTextField[][] text = new JTextField[7][7]; 
    JButton enterButton;
    JButton quitButton;

    JPanel mainMenu;
    JButton newWordButton;
    JLabel difficultyLabel;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;

    ArrayList<String> wordList = getWordList(1);
    String targetWord;
    int count = 0;


    TipOfMyTounge() {
        frame = new JFrame();

        cardLayout = new CardLayout();
        panel = new JPanel();
        panel.setLayout(cardLayout);

        int x = 50, y = 10;
        for(int row = 0; row < 7; row++){
            for(int column = 0; column < 7; column++){
                
                text[row][column] = new JTextField("");
                text[row][column].setSize(50, 50);
                text[row][column].setLocation(x, y);
                text[row][column].setEditable(false);
                text[row][column].setBackground(Color.LIGHT_GRAY);
                text[row][column].setHorizontalAlignment(JTextField.CENTER);
                text[row][column].setFont(new Font(null, 1,16));
                
                if(row > 5 || column > 5)
                    text[row][column].setVisible(false);
                x += 55;
            }
            x = 50;
                y += 55;
        }
        
        

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
        for(int row = 0; row < 7; row++)
            for(int column = 0; column < 7; column++)
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
        difficultyLabel.setText("Difficulty: Medium");
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
            int randomIndex = (int)(Math.random()*wordList.size());
            targetWord = wordList.get(randomIndex);
        }
        
        if(e.getSource() == quitButton){
            cardLayout.show(panel,"2");
            textField.setText(null);
            textField.setEditable(true);
            count = 0;
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    text[row][column].setText(null);
                    text[row][column].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        if(e.getSource() == enterButton){
            String userWord = textField.getText();
            //TODO: check for real words
            //TODO: color boxes according to whether the word is in the right place or exists

            if(userWord.equalsIgnoreCase(targetWord)){
                textField.setEditable(false);
                for(int i = 0; i < userWord.length(); i++){
                    text[count][i].setText("" + userWord.toUpperCase().charAt(i));
                    text[count][i].setBackground(Color.green);
                }
                textField.setText("You win!");
                count = 10;
                return;
            }

            if(userWord.length() == targetWord.length()){
                for(int i = 0; i < userWord.length(); i++)
                    text[count][i].setText("" + userWord.toUpperCase().charAt(i));
                    count++;
            }
            
            if(count == targetWord.length()){
                textField.setEditable(false);
                textField.setText("The word was: " + targetWord);
            }
            else if(count < targetWord.length())
                textField.setText(null);
            
        }

        //TODO: Reorganize textArray to fit center on frame
        if(e.getSource() == easyButton){
            wordList = getWordList(0);
            difficultyLabel.setText("Difficulty: Easy");
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    if(row > 4 || column > 4)
                        text[row][column].setVisible(false);
                }
            }
        }

        //TODO: Reorganize textArray to fit center on frame
        if(e.getSource() == mediumButton){
            wordList = getWordList(1);
            difficultyLabel.setText("Difficulty: Medium");
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    if(row == 5 || column == 5)
                        text[row][column].setVisible(true);
                    if(row == 6 || column == 6)
                        text[row][column].setVisible(false);
                }
            }
        }

        //TODO: Reorganize textArray to fit center on frame
        if(e.getSource() == hardButton){
            wordList = getWordList(2);
            difficultyLabel.setText("Difficulty: Hard");
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    text[row][column].setVisible(true);
                }
            }
        }
        
    }

    static ArrayList<String> getWordList(int difficulty){
        //difficulty 0 = easy, 1 = medium, 2 = hard
        ArrayList<String> WordList = new ArrayList<String>();
        Scanner scan;
        File file;
        switch(difficulty){
            case 0:
                file = new File("fiveLetterWords.txt");
                break;
            case 1:
                file = new File("sixLetterWords.txt");
                break;
            case 2:
                file = new File("sevenLetterWords.txt");
                break;
            default:
                return WordList;
        }

        try{
            scan = new Scanner(file);
            while(scan.hasNext())
                WordList.add(scan.next());
            scan.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: txt file not found");
            e.printStackTrace();
        }
        return WordList;
    }
}
