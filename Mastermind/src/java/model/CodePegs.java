/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Enum used to represent the code pegs for a Mastermind game.
 * @author Blake Edwards
 */
public enum CodePegs {
    BLANK(0, "blank"), RED(1, "red"), BLUE(2, "blue"), GREEN(3, "green"), 
    YELLOW(4, "yellow"), PURPLE(5, "purple"), ORANGE(6, "orange");
    
    private int value;
    String color;
    
    /**
     * Private constructor to set the value and color a this code peg
     * @param value for the code peg
     * @param color for the code peg
     */
    private CodePegs(int value, String color) {
        this.value = value;
        this.color = color;
    }
    
    /**
     * Get the value for this code peg
     * @return the value of this code peg
     */
    public int value() {
        return value;
    }
    
    /**
     * Get the string representation of the color for this code peg
     * @return the string color code for this peg
     */
    public String color() {
        return color;
    }
    
    /**
     * Returns the color string for this peg
     * @return the color string for this peg
     */
    public String toString() {
        return color;
    }
}
