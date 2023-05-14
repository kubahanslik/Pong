/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jakub
 */
public class EndGamePanel extends JPanel {
    JLabel messageLabel, playAgainLabel;
    
    public static final int WIDTH = 500;
    public static final int HEIGHT = 100;
    
    public static final int MESSAGE_FONT_SIZE = 40;
    public static final int MESSAGE_WIDTH = 500;
    public static final int MESSAGE_HEIGHT = 60;
    
    public static final int PLAY_AGAIN_FONT_SIZE = 20;
    public static final int PLAY_AGAIN_WIDTH = 300;
    public static final int PLAY_AGAIN_HEIGHT = 40;
    
    public EndGamePanel(int width, int height) {
        this.setLayout(new BorderLayout()); // Setting border layout will make it easier to manage the size and positioning of our text
        this.setBounds(width/2 - WIDTH/2, height/2 - HEIGHT/2, WIDTH, HEIGHT);
        this.setBackground(Color.black);
        this.setVisible(false);
        
        messageLabel = new JLabel();
        messageLabel.setSize(MESSAGE_WIDTH, MESSAGE_HEIGHT);
        messageLabel.setForeground(Color.white);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.TOP);
        messageLabel.setFont(new Font("Arial", Font.BOLD, MESSAGE_FONT_SIZE));
        
        playAgainLabel = new JLabel("Press space to play again");
        playAgainLabel.setSize(PLAY_AGAIN_WIDTH, PLAY_AGAIN_HEIGHT);
        playAgainLabel.setForeground(Color.white);
        playAgainLabel.setHorizontalAlignment(JLabel.CENTER);
        playAgainLabel.setVerticalAlignment(JLabel.BOTTOM);
        playAgainLabel.setFont(new Font("Arial", Font.BOLD, PLAY_AGAIN_FONT_SIZE));
        
        this.add(messageLabel);
        this.add(playAgainLabel);
    }
    
    public void showEndingScreen(String message) {
        messageLabel.setText(message);
        
        this.setVisible(true);
    }
    public void hideEndingScreen() {
        this.setVisible(false);
    }
}
