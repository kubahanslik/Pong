/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jakub
 */
public class Player extends JPanel {
    boolean up, down;
    float posY;
    static float speed = 0.5f;
    int points;
    
    public Player(int x, int y) {
        init(x, y);
    }
    
    // Making function for initialization of our object will make it easier in the future to reset position of our player
    public void init(int x, int y) {
        this.setBounds(x, y, Settings.playerWidth, Settings.playerHeight);
        this.setBackground(Color.gray);
        
        posY = y;
        points = 0;
    }
    
    public void scored(JLabel label) {
        points += 1;
        
        label.setText(String.valueOf(points));
    }
    
    public void moveUp() {
        posY -= speed; // Using position variables of float data type will give us much bigger range of options for speed

        this.setLocation(this.getX(), Math.round(posY));
    }
    public void moveDown() {
        posY += speed;
        
        this.setLocation(this.getX(), Math.round(posY));
    }
    
    // Creating getCenterY method will make some of our calculations more readeable
    public int getCenterY() {
        return this.getY() + this.getHeight()/2;
    }
}
