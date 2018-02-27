/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that represents the board for a game of mastermind.
 * @author Blake Edwards
 */
public class GameBoard implements Serializable {
    final private int MAX_GUESSES = 12;
    final private int NUM_OF_PEGS = 4;
    Random rand = new Random();
    private CodePegs[][] guesses;  // store the player's guesses
    private KeyPegs[][] hints;     // store the key pegs for each guess
    private CodePegs[] code;       // codemaker's code
    private String shield;         // shield to cover the code until game ends
    
    /**
     * Creates a new game board and resets all board variables
     */
    public GameBoard() {
        guesses = new CodePegs[MAX_GUESSES][NUM_OF_PEGS];
        hints = new KeyPegs[MAX_GUESSES][NUM_OF_PEGS];
        code = new CodePegs[NUM_OF_PEGS];
        reset();
    }
    
    /** 
     * Reset the board for a new game
     */
    final public void reset() {
        initBoard();
    }
    
    /**
     * Initialize the board by setting all guesses, hints, and code to 
     * blanks. Also creates and sets a random code for the player to break.
     */
    private void initBoard() {
        // Initialize all of the guesses to blank
        for(int i = 0; i < guesses.length; i++) {
            for(int j = 0; j < guesses[i].length; j++) {
                guesses[i][j] = CodePegs.BLANK;
            }
        }
        
        // Initialize all of the hints to blank
        for(int i = 0; i < hints.length; i++) {
            for(int j = 0; j < hints[i].length; j++) {
                hints[i][j] = KeyPegs.BLANK;
            }
        }
        
        // Initialze the code to blanks
        for(int i = 0; i < code.length; i++) {
            code[i] = CodePegs.BLANK;
        }
        
        shield = "closed";
        
        setCode();
        Logger.getLogger(GameBoard.class.getName()).log(Level.INFO, "Code: {0}", getCodeString());
    }
    
    /**
     * Get a copy of the guesses
     * @return copy of the guesses array
     */
    public CodePegs[][] getGuesses() {
        return guesses.clone();
    }
    
    /**
     * Sets the code pegs for a selected guess
     * @param turn current player turn, used as the index in the guesses array
     * @param pegs CodePegs array to insert in guesses array 
     */
    public void setGuess(int turn, CodePegs[] pegs) {
        for(int i = 0; i < pegs.length; i++) {
            guesses[turn - 1][i] = pegs[i];
        }
    }
    
    /**
     * Get a copy of the hints array
     * @return a copy of the hints array
     */
    public KeyPegs[][] getHints() {
        return hints.clone();
    }
    
    /**
     * Sets the key pegs for a selected guess
     * @param turn current player turn, used as the index in the hints array
     * @param pegs KeyPegs array to insert in hints array 
     */
    public void setHint(int turn, KeyPegs[] pegs) {
        for(int i = 0; i < pegs.length; i++) {
            hints[turn - 1][i] = pegs[i];
        }
    }
    
    /**
     * Get the max number of guesses for the game.
     * @return max number of guesses for this game.
     */
    public int getMaxGuesses() {
        return MAX_GUESSES;
    }
    
    /**
     * Get the number of code pegs that are in the code.
     * @return number of code pegs used in the code.
     */
    public int getNumPegs() {
        return NUM_OF_PEGS;
    }
    
    /**
     * Get a copy of the code.
     * @return a copy of the code array.
     */
    public CodePegs[] getCode() {
        return code.clone();
    }
    
    /**
     * Set the code with a random set of code pegs.
     */
    public void setCode() {
        
        for(int i = 0; i < NUM_OF_PEGS; i++) {
            int color = rand.nextInt(6) + 1;
            
            switch(color) {
                case 1:
                    code[i] = CodePegs.RED;
                    break;
                case 2:
                    code[i] = CodePegs.BLUE;
                    break;
                case 3:
                    code[i] = CodePegs.GREEN;
                    break;
                case 4:
                    code[i] = CodePegs.YELLOW;
                    break;
                case 5:
                    code[i] = CodePegs.PURPLE;
                    break;
                case 6:
                    code[i] = CodePegs.ORANGE;
                    break;
            }
        }
    }
    
    /**
     * Return a string representation of the code array.
     * @return a string representing the code array.
     */
    private String getCodeString() {
        return code[0].toString() + " " + code[1].toString() + " " + 
                code[2].toString() + " " + code[3].toString();
    }
    
    /**
     * Get the shield status.
     * @return current status of the shield.
     */
    public String getShield() {
        return shield;
    }
    
    /**
     * Set the status of the shield.
     * @param shield string to set the shield status
     */
    public void setShield(String shield) {
        this.shield = shield;
    }
}
