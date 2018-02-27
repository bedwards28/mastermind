/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class to represent a game of Mastermind
 * @author Blake Edwards
 */
public class MastermindGame {
    Random rand = new Random();
    private GameBoard board;   // the game board
    private int turn;          // current player turn number
    private boolean winner;    // winner flag
    private boolean quit;      // player quit flag
    CodePegs[] currentGuess;   // array to hold the code pegs of the current guess
    KeyPegs[] currentHints;    // array to hold the key pegs that correspond
                               // to the current guess
    
    /**
     * Constructor to initialize a new Mastermind game
     */
    public MastermindGame() {
        reset();
    }
    
    /**
     * Resets this game and reinitializes all board components
     */
    final public void reset() {
        board = new GameBoard();
        turn = 1;
        winner = false;
        quit = false;
        currentGuess = new CodePegs[board.getNumPegs()];
        currentHints = new KeyPegs[board.getNumPegs()];
    }
    
    /**
     * Starts a new game of Mastermind
     */
    public void newGame() {
        reset();
        board.setCode();
    }
    
    /**
     * Get a copy of the code for the this game
     * @return a copy of the code for this game
     */
    public CodePegs[] getCode() {
        CodePegs[] code = board.getCode();
        return code;
    }
    
    /**
     * Get the turn number that the player is currently on
     * @return the current player turn number
     */
    public int getTurn() {
        return turn;
    }
    
    /**
     * Set the turn number
     * @param turn the new value for the turn number
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    /**
     * Get the status of the shield for this game
     * @return the shield status for this game
     */
    public String getShield() {
        return board.getShield();
    }
    
    /**
     * Get a guess from the player input
     * @param peg1 first peg of the current guess
     * @param peg2 second peg of the current guess
     * @param peg3 third peg of the current guess
     * @param peg4 fourth peg of the current guess
     */
    public void makeGuess(String peg1, String peg2, String peg3, String peg4) {
        
        // Convert the color string to CodePeg objects and store them in
        // the currentGuess array
        currentGuess[0] = convertToCodePeg(peg1);
        currentGuess[1] = convertToCodePeg(peg2);
        currentGuess[2] = convertToCodePeg(peg3);
        currentGuess[3] = convertToCodePeg(peg4);
        
        // Store the currentGuess array in the guesses array of the game board
        board.setGuess(turn, currentGuess);
        
        turn++;
    }
    
    /**
     * Converts a string object to a CodePeg object
     * @param peg string that represents the peg to convert
     * @return the CodePeg object created by the string
     */
    private CodePegs convertToCodePeg(String peg) {
        String color = peg;
        CodePegs result = null;
        
        switch(color) {
            case "red":
                result = CodePegs.RED;
                break;
            case "blue":
                result = CodePegs.BLUE;
                break;
            case "green":
                result = CodePegs.GREEN;
                break;
            case "yellow":
                result = CodePegs.YELLOW;
                break;
            case "purple":
                result = CodePegs.PURPLE;
                break;
            case "orange":
                result = CodePegs.ORANGE;
                break;
        }
        
        return result;
    }
    
    /**
     * Get a copy of the guesses array stored in the board object
     * @return a copy of the guesses array
     */
    public CodePegs[][] getGuesses() {
        CodePegs[][] guesses = 
                new CodePegs[board.getMaxGuesses()][board.getNumPegs()];
        
        guesses = board.getGuesses();
        
        return guesses;
    }
    
    /**
     * Get a copy of the hints array stored in the board object
     * @return a copy of the hints array
     */
    public KeyPegs[][] getHints() {
        KeyPegs[][] hints =
                new KeyPegs[board.getMaxGuesses()][board.getNumPegs()];
        
        hints = board.getHints();
        return hints;
    }
    
    /**
     * Get the maximum number of guesses allowed for this game
     * @return the maximum number of guesses for this game
     */
    public int getNumGuesses() {
        return board.getMaxGuesses();
    }
    
    /**
     * Check to see if the player has won this game
     * @return true if the player has won the game, otherwise false
     */
    public boolean isWinner() {
        return winner;
    }
    
    /**
     * Check to see if the game has ended. The game can end if a winner
     * has been declared, the maximum number of guesses has been reached, or
     * the player has opted to quit the game.
     * @return true if the game is over, otherwise false
     */
    public boolean isGameOver() {
        if(winner || turn == board.getMaxGuesses() + 1 || quit == true) {
            board.setShield("open");
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Sets the key pegs for the current guess and stores the results in
     * the hints array.  The key pegs are shuffled before being inserted
     * into the hints array.  Also checks to see if the player has won with
     * this guess.
     */
    public void setHints() {
        int whitePegs = 0, blackPegs = 0;
        CodePegs[] code = this.getCode();
        ArrayList<Integer> processed = new ArrayList<>();
        currentHints = new KeyPegs[board.getNumPegs()];
        
        for(int i = 0; i < code.length; i++) {
            if(currentGuess[i] == code[i]){
                blackPegs++;
            }
        }
        
        for(int i = 0; i < code.length; i++) {
            for(int j = 0; j < code.length; j++) {
                if(code[i] == currentGuess[j] && !processed.contains(j)) {
                    processed.add(j);
                    whitePegs++;
                    break;
                }
            }
        }
        
        whitePegs -= blackPegs;
        
        for(int i = 0; i < currentHints.length; i++) {
            currentHints[i] = KeyPegs.BLANK;
        }
        
        for(int i = 0; i < whitePegs; i++) {
            currentHints[i] = KeyPegs.WHITE;
        }
        
        for(int i = whitePegs; i < whitePegs + blackPegs; i++) {
            currentHints[i] = KeyPegs.BLACK;
        }
        
        scrambleHints(currentHints);
        board.setHint(turn - 1, currentHints);
        
        checkForWinner();
    }
    
    /**
     * Check to see if the player has won the game
     */
    private void checkForWinner() {
        winner = true;
        
        for(int i = 0; i < currentHints.length; i++) {
            if(currentHints[i] != KeyPegs.BLACK) {
                winner = false;
            }
        }
    }
    
    /**
     * Scrambles the position of key pegs in the given array
     * @param pegs an array to shuffle the key pegs for
     */
    private void scrambleHints(KeyPegs[] pegs) {
        for(int i = pegs.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            KeyPegs peg = pegs[index];
            pegs[index] = pegs[i];
            pegs[i] = peg;
        }
    }
    
    /**
     * Quits the current game
     * @return true to signify that the game has been quit by the player
     */
    public boolean quitGame() {
        quit = true;
        board.setShield("open");
        return quit;
    }
}
