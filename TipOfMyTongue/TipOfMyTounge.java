import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TipOfMyTounge implements ActionListener{
    //TODO: Have a better way to display streaks?? maybe?
    //TODO: Add an instructions panel
    //TODO: add an instructions button to mainMenu panel
    //TODO: Find a better 6 letter word list
    //TODO: Hints? How? Is there a cost?
    //TODO: possible time factor
    
    JFrame frame;
    JPanel panel;
    CardLayout cardLayout;

    JPanel gamePanel;
    JTextField textField;
    JTextField[][] text = new JTextField[7][7]; 
    JButton enterButton;
    JButton quitButton;
    JButton playAgainButton;

    JLabel titleLabel;
    JPanel mainMenu;
    JButton newWordButton;
    JLabel difficultyLabel;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;

    JLabel mmStreakLabel; //main menu streak label
    JLabel gpStreakLabel; //game panel streak label

    ArrayList<String> wordList = getWordList(1);
    int[] flags;
    String targetWord;
    int turn = 0;
    int easyStreak = 0;
    int mediumStreak = 0;
    int hardStreak = 0;



    TipOfMyTounge() {
        frame = new JFrame();

        cardLayout = new CardLayout();
        panel = new JPanel();
        panel.setLayout(cardLayout);

        mmStreakLabel = new JLabel();
        mmStreakLabel.setText("Current Streaks | Easy: " + easyStreak + " | Medium: " + mediumStreak + " | Hard: " + hardStreak);
        mmStreakLabel.setForeground(Color.white);
        mmStreakLabel.setSize(500, 20);
        mmStreakLabel.setLocation(0, 450);

        gpStreakLabel = new JLabel();
        gpStreakLabel.setText("Current Streaks | Easy: " + easyStreak + " | Medium: " + mediumStreak + " | Hard: " + hardStreak);
        gpStreakLabel.setForeground(Color.white);
        gpStreakLabel.setSize(500, 20);
        gpStreakLabel.setLocation(0, 450);

        int x = 85, y = 35;
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
            x = 85;
            y += 55;
        }
        
        x = 50;
        y = 395;

        textField = new JTextField();
        textField.setSize(230, 50);
        textField.setLocation(x, y);
        textField.addActionListener(this);
        textField.setFont(new Font(null, 0,15));
        x += 235;

        enterButton = new JButton();
        enterButton.setText("Enter");
        enterButton.setFocusable(false);
        enterButton.setSize(70, 50);
        enterButton.setLocation(x, y);
        enterButton.addActionListener(this);

        playAgainButton = new JButton();
        playAgainButton.setText("New");
        playAgainButton.setFocusable(false);
        playAgainButton.setSize(70, 50);
        playAgainButton.setLocation(x, y);
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(this);
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
        gamePanel.add(playAgainButton);
        gamePanel.add(gpStreakLabel);
        panel.add(gamePanel,"1");
        
        x = 50;
        y = 50;

        titleLabel = new JLabel();
        titleLabel.setText("On The Tip of my Tounge");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Harlow Solid Italic", 0, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setSize(400, 50);
        titleLabel.setLocation(x, y);

        y += 55;
        x = 150;

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
        difficultyLabel.setFont(new Font("Harlow Solid Italic", 0, 23));
        difficultyLabel.setHorizontalAlignment(JLabel.CENTER);
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
        mainMenu.add(titleLabel);
        mainMenu.add(mmStreakLabel);
        panel.add(mainMenu,"2");
        

        ImageIcon icon = new ImageIcon("IMG_0701.PNG");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("On The Tip Of My Tounge");
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
            //System.out.println("[DEBUG]target word: " + targetWord);
        }
        
        if(e.getSource() == quitButton){
            turn = 0;
            cardLayout.show(panel,"2");
            textField.setText(null);
            textField.setEditable(true);
            enterButton.setVisible(true);
            playAgainButton.setVisible(false);
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    text[row][column].setText(null);
                    text[row][column].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        if(e.getSource() == enterButton || e.getSource() == textField){
            String userWord = textField.getText();
            int maxTurns = targetWord.length();

            //Assures no case inequalities
            targetWord = targetWord.toUpperCase();
            userWord = userWord.toUpperCase();

            //game is over, enterButton wont do anything
            if(turn == maxTurns)
                return;

            //Win case
            if(userWord.equals(targetWord)){
                for(int i = 0; i < userWord.length(); i++){
                    text[turn][i].setText("" + userWord.toUpperCase().charAt(i));
                    text[turn][i].setBackground(Color.green);
                }
                textField.setEditable(false);
                textField.setText("You win!");
                enterButton.setVisible(false);
                playAgainButton.setVisible(true);
                switch(maxTurns){
                    case 5:
                        easyStreak++;
                        break;
                    case 6:
                        mediumStreak++;
                        break;
                    case 7:
                        hardStreak++;
                        break;
                }
                mmStreakLabel.setText("Current Streaks | Easy: " + easyStreak + " | Medium: " + mediumStreak + " | Hard: " + hardStreak);
                gpStreakLabel.setText("Current Streaks | Easy: " + easyStreak + " | Medium: " + mediumStreak + " | Hard: " + hardStreak);
                turn = maxTurns;//set game over
                return;
            }

            //Assures that only words of the correct length pass
            if(userWord.length() != targetWord.length()){
                textField.setText("Please enter a " + targetWord.length() + " letter word");
                textField.selectAll();
                return;
            }

            //check for real words
            boolean isRealWord = false;
            for(String s: wordList){
                if(s.equalsIgnoreCase(userWord)){
                    isRealWord = true;
                    break;
                }
            }
            if(isRealWord == false){
                textField.setText(userWord.toLowerCase() + " is not on the word list");
                textField.selectAll();
                return;
            }

            flags = getFlags(userWord, targetWord);

            //prints userword to text array
            for(int i = 0; i < userWord.length(); i++){
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
                enterButton.setVisible(false);
                playAgainButton.setVisible(true);
                switch(maxTurns){
                    case 5:
                        easyStreak = 0;
                        break;
                    case 6:
                        mediumStreak = 0;
                        break;
                    case 7:
                        hardStreak = 0;
                        break;
                }
                mmStreakLabel.setText("Current Streaks | Easy: " + easyStreak + " | Medium: " + mediumStreak + " | Hard: " + hardStreak);
                gpStreakLabel.setText("Current Streaks | Easy: " + easyStreak + " | Medium: " + mediumStreak + " | Hard: " + hardStreak);

            }
            
        }
        if(e.getSource() == playAgainButton){
            turn = 0;
            textField.setText(null);
            textField.setEditable(true);
            enterButton.setVisible(true);
            playAgainButton.setVisible(false);
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    text[row][column].setText(null);
                    text[row][column].setBackground(Color.LIGHT_GRAY);
                }
            }

            int randomIndex = (int)(Math.random()*wordList.size());
            targetWord = wordList.get(randomIndex);
            //System.out.println("[DEBUG]target word: " + targetWord);
        }

        if(e.getSource() == easyButton){
            int x = 112, y = 60;
            wordList = getWordList(0);
            difficultyLabel.setText("Difficulty: Easy");
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    if(row > 4 || column > 4)
                        text[row][column].setVisible(false);
                    text[row][column].setLocation(x, y);
                    x += 55;
                }
                x = 112;
                y += 55;
            }
        }

        if(e.getSource() == mediumButton){
            int x = 85, y = 35;
            wordList = getWordList(1);
            difficultyLabel.setText("Difficulty: Medium");
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    if(row == 5 || column == 5)
                        text[row][column].setVisible(true);
                    if(row == 6 || column == 6)
                        text[row][column].setVisible(false);
                    text[row][column].setLocation(x, y);
                    x += 55;
                }
                x = 85;
                y += 55;
            }
        }

        if(e.getSource() == hardButton){
            int x = 50, y = 10;
            wordList = getWordList(2);
            difficultyLabel.setText("Difficulty: Hard");
            for(int row = 0; row < 7; row++){
                for(int column = 0; column < 7; column++){
                    text[row][column].setVisible(true);
                    text[row][column].setLocation(x, y);
                    x += 55;
                }
                x = 50;
                y += 55;
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
