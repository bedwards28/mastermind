/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Enum used to represent the key pegs for a Mastermind game
 * @author Blake Edwards
 */
public enum KeyPegs {
    BLANK(0, "blank"), WHITE(1, "white"), BLACK(2, "black");
    
    private int value;
    String color;
    
    /**
     * Private constructor that sets the value and color for this peg.
     * @param value for this peg
     * @param color string representing the color of this peg
     */
    private KeyPegs(int value, String color) {
        this.value = value;
        this.color = color;
    }
    
    /**
     * Get the int value for this peg.
     * @return the int value for this peg.
     */
    public int value() {
        return value;
    }
    
    /**
     * Get the color string for this peg.
     * @return color string for this peg.
     */
    public String color() {
        return color;
    }
}
