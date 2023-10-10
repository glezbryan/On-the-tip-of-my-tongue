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
    //TODO: Hints? How? Is there a cost?
    //TODO: newWord button appears after a win or lose to quickly play again
    
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
    int[] flags;
    String targetWord;
    int turn = 0;


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
        textField.setSize(230, 50);
        textField.setLocation(x, y);
        textField.addActionListener(this);
        x += 235;

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
        frame.setResizable(false);
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
            System.out.println("[DEBUG]target word: " + targetWord);
        }
        
        if(e.getSource() == quitButton){
            cardLayout.show(panel,"2");
            textField.setText(null);
            textField.setEditable(true);
            turn = 0;
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    text[row][column].setText(null);
                    text[row][column].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        if(e.getSource() == enterButton || e.getSource() == textField){
            //TODO: check for real words
            String userWord = textField.getText();
            int maxTurns = targetWord.length();

            //Assures no case inequalities
            targetWord = targetWord.toUpperCase();
            userWord = userWord.toUpperCase();

            //Win case
            if(userWord.equals(targetWord)){
                for(int i = 0; i < userWord.length(); i++){
                    text[turn][i].setText("" + userWord.toUpperCase().charAt(i));
                    text[turn][i].setBackground(Color.green);
                }
                textField.setEditable(false);
                textField.setText("You win!");
                turn = maxTurns;
                return;
            }

            if(turn == maxTurns)
                return;

            //Assures that only words of the correct length pass
            if(userWord.length() != targetWord.length()){
                textField.setText(null);
                return;
            }

            
            flags = getFlags(userWord, targetWord);
            for(int i = 0; i < userWord.length(); i++){
                //prints userword to text array
                text[turn][i].setText("" + userWord.toUpperCase().charAt(i));
                //colors text array according to flag array. 
                //1 = correct letter at index = green
                //2 = letter exist in target word = yellow
                //3 = letter does not exist = either leave is as is or maybe red?
                switch(flags[i]){
                    case 1:
                        text[turn][i].setBackground(Color.green);
                        break;
                    case 2:
                        text[turn][i].setBackground(Color.yellow);
                        break;
                    //case 3:
                    //    text[turn][i].setBackground(Color.red);
                    //    break;
                }
            }
            textField.setText(null);
            turn++;
            
            if(turn == maxTurns){
                textField.setEditable(false);
                textField.setText("The word was: " + targetWord.toLowerCase());
            }
            
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

    static int[] getFlags(String userWord, String targetWord){
        // 0 = null
        // 1 = letter at index is correct
        // 2 = letter at index existes in target word but not at this index
        // 3 = letter at index does not exist in target word
        int[] flags = new int[targetWord.length()];
        char[] targetWordArray = targetWord.toCharArray();

        for(int i = 0; i < targetWord.length(); i++){
            if(userWord.charAt(i) == targetWordArray[i]){
                flags[i] = 1;
                targetWordArray[i] = '.';
            }
        }
        
        //System.out.println("[DEBUG]");
        //System.out.println(targetWordArray);
        //for(int f: flags)
        //    System.out.print(f);
        //System.out.println();

        for(int i = 0; i < targetWord.length(); i++){
            int j;
            for(j = 0; j < targetWord.length(); j++){
                if(flags[i] != 1  && userWord.charAt(i) == targetWordArray[j]){
                    flags[i] = 2;
                    targetWordArray[j] = '.';
                    break;
                }
            }
            if(flags[i] != 1 && flags[i] != 2 && j == targetWord.length())
                flags[i] = 3;
        }

        //System.out.println("[DEBUG]");
        //System.out.println(targetWordArray);
        //for(int f: flags)
        //    System.out.print(f);
        //System.out.println();

        return flags;
    }
}
