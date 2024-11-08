package inventionexpo;

import javax.swing.*;
import java.util.ArrayList;

public class InventionExpo {
    // Main method
    public static void main(String[] args) {
        new InventionExpo().init();
    }

    // Method to initialize the process
    private void init() {
        ArrayList <Inventor> inventors = new ArrayList<>();
        // Prompting user for the number of inventors
        int numInventors = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(null, "WELCOME TO THE INVENTION EXPO COMPETITION!\n please enter the number of inventors:");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Operation cancelled.");
                    return;
                }
                numInventors = Integer.parseInt(input);
                if (numInventors <= 0) {
                    throw new NumberFormatException("Number must be greater than zero.");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive number greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Collection data for each inventor
        for (int i = 0; i < numInventors; i++) {
            String name = null;
            int score = -1;
            // Collecting the inventor's name
            while (name == null || name.trim().isEmpty()) {
                name = JOptionPane.showInputDialog(null, "Please enter the name of inventor " + (i + 1) + ":");
                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name cannot empty. Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Collecting the inventor's score
            boolean scoreValid = false;
            while (!scoreValid) {
                try {
                    String scoreInput = JOptionPane.showInputDialog(null, "Please enter the score for " + name + "'s invention (out of 100):");
                    if (scoreInput == null) {
                        JOptionPane.showMessageDialog(null, "Operation cancelled.");
                        return;
                    }
                    score = Integer.parseInt(scoreInput);
                    if (score < 1 || score > 100) {
                        throw new NumberFormatException("Score must be between 1 and 100.");
                    }
                    scoreValid = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a score between 1 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Adding the inventor to the list
            inventors.add(new Inventor(name, score));
        }
        // Finding the top inventor
        Inventor topInventor = findTopInventor(inventors);
        if (topInventor != null) {
            JOptionPane.showMessageDialog(null, "The top inventor is " + topInventor.getName() + " with am invention score: " + topInventor.getScore() + " out of 100", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No inventors were found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Displaying thank-you and developer info
        JOptionPane.showMessageDialog(null, "Thanks for participating in the invention Expo competition.\nDeveloped By Zafir Abdullah");
    }

    // Method to find the top inventor
    public Inventor findTopInventor(ArrayList <Inventor> inventors) {
        if (inventors.isEmpty()) {
            return null;
        }
        Inventor topInventor = inventors.get(0);
        for (Inventor inventor : inventors) {
            if (inventor.getScore() > topInventor.getScore()) {
                topInventor = inventor;
            }
        }
        return topInventor;
    }

    // Inner class Inventor
    private static class Inventor {
        private String name;
        private int score;

        // Parameterized constructor
        public Inventor(String name, int score) {
            this.name = name;
            this.score = score;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Getter for score
        public int getScore() {
            return score;
        }
    } // end of inner Inventor class        
} // end of Invention class

