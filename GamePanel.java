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
public class GamePanel extends JPanel {
    Player player1, player2;
    Ball ball;
    JLabel scoreLabel1, scoreLabel2, messageLabel, playAgainLabel;
    JPanel endingScreenPanel;
    
    public GamePanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null); // Setting layout manager to null, because we are programming a game and want to manage positions of our components ourselfs
        this.setBackground(Color.black);
        
        player1 = new Player( Settings.marginFromSideWall, this.getHeight()/2 - Settings.playerHeight/2);
        player2 = new Player(this.getWidth() - Settings.marginFromSideWall - Settings.playerWidth, this.getHeight()/2 - Settings.playerHeight/2);
        ball = new Ball(this.getWidth()/2 - Settings.ballRadius, this.getHeight()/2 - Settings.ballRadius);
        
        Font scoreFont = new Font("Arial", Font.BOLD, Settings.scoreFontSize); // Saving the font to a variable so if we want to change it we can do it just here
        
        scoreLabel1 = new JLabel("0");
        scoreLabel1.setBounds(Settings.scoreMarginX, Settings.scoreMarginY, Settings.scoreLabelWidth, Settings.scoreLabelHeight);
        scoreLabel1.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel1.setFont(scoreFont);
        
        scoreLabel2 = new JLabel("0");
        scoreLabel2.setBounds(this.getWidth() - Settings.scoreMarginX - Settings.scoreLabelWidth, Settings.scoreMarginY, Settings.scoreLabelWidth, Settings.scoreLabelHeight);
        scoreLabel2.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel2.setFont(scoreFont);
        
        endingScreenPanel = new JPanel(new BorderLayout()); // Setting border layout will make it easier to manage the size and positioning of our text
        endingScreenPanel.setBounds(this.getWidth()/2 - Settings.endingScreenWidth/2, this.getHeight()/2 - Settings.endingScreenHeight/2, Settings.endingScreenWidth, Settings.endingScreenHeight);
        endingScreenPanel.setBackground(Color.black);
        endingScreenPanel.setVisible(false);
        
        messageLabel = new JLabel();
        messageLabel.setSize(Settings.messageWidth, Settings.messageHeight);
        messageLabel.setForeground(Color.white);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.TOP);
        messageLabel.setFont(new Font("Arial", Font.BOLD, Settings.messageFontSize));
        
        playAgainLabel = new JLabel("Press space to play again");
        playAgainLabel.setSize(Settings.playAgainWidth, Settings.playAgainHeight);
        playAgainLabel.setForeground(Color.white);
        playAgainLabel.setHorizontalAlignment(JLabel.CENTER);
        playAgainLabel.setVerticalAlignment(JLabel.BOTTOM);
        playAgainLabel.setFont(new Font("Arial", Font.BOLD, Settings.playAgainFontSize));
        
        endingScreenPanel.add(messageLabel);
        endingScreenPanel.add(playAgainLabel);
        
        this.add(player1);
        this.add(player2);
        this.add(ball);
        this.add(scoreLabel1);
        this.add(scoreLabel2);
        this.add(endingScreenPanel);
    }
    
    public void showEndingScreen(String message) {
        messageLabel.setText(message);
        
        endingScreenPanel.setVisible(true);
    }
    public void hideEndingScreen() {
        endingScreenPanel.setVisible(false);
    }
    
    public void resetComponents() {
        player1.init(Settings.marginFromSideWall, this.getHeight()/2 - Settings.playerHeight/2);
        player2.init(this.getWidth() - Settings.marginFromSideWall - Settings.playerWidth, this.getHeight()/2 - Settings.playerHeight/2);
        ball.init(this.getWidth()/2 - Settings.ballRadius, this.getHeight()/2 - Settings.ballRadius);
        
        scoreLabel1.setText("0");
        scoreLabel2.setText("0");
    }
}
