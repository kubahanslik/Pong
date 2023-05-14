/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jakub
 */
public class MainPanel extends JPanel {
    Player player1, player2;
    Ball ball;
    JLabel scoreLabel1, scoreLabel2;
    EndGamePanel endGamePanel;
    
    public static final int SCORE_FONT_SIZE = 40;
    public static final int SCORE_WIDTH = 60;
    public static final int SCORE_HEIGHT = 40;
    public static final int SCORE_MARGIN_X = 300;
    public static final int SCORE_MARGIN_Y = 20;
    
    public MainPanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null); // Setting layout manager to null, because we are programming a game and want to manage positions of our components ourselfs
        this.setBackground(Color.black);
        
        player1 = new Player( Player.MARGIN_FROM_SIDE_WALL, this.getHeight()/2 - Player.HEIGHT/2);
        player2 = new Player(this.getWidth() - Player.MARGIN_FROM_SIDE_WALL - Player.WIDTH, this.getHeight()/2 - Player.HEIGHT/2);
        ball = new Ball(this.getWidth()/2 - Ball.RADIUS, this.getHeight()/2 - Ball.RADIUS);
        
        Font scoreFont = new Font("Arial", Font.BOLD, SCORE_FONT_SIZE);
        
        scoreLabel1 = new JLabel("0");
        scoreLabel1.setBounds(SCORE_MARGIN_X, SCORE_MARGIN_Y, SCORE_WIDTH, SCORE_HEIGHT);
        scoreLabel1.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel1.setFont(scoreFont);
        
        scoreLabel2 = new JLabel("0");
        scoreLabel2.setBounds(this.getWidth() - SCORE_MARGIN_X - SCORE_WIDTH, SCORE_MARGIN_Y, SCORE_WIDTH, SCORE_HEIGHT);
        scoreLabel2.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel2.setFont(scoreFont);
        
        endGamePanel = new EndGamePanel(this.getWidth(), this.getHeight());
        
        this.add(player1);
        this.add(player2);
        this.add(ball);
        this.add(scoreLabel1);
        this.add(scoreLabel2);
        this.add(endGamePanel);
    }
    
    public void resetComponents() {
        player1.init(Player.MARGIN_FROM_SIDE_WALL, this.getHeight()/2 - Player.HEIGHT/2);
        player2.init(this.getWidth() - Player.MARGIN_FROM_SIDE_WALL - Player.WIDTH, this.getHeight()/2 - Player.HEIGHT/2);
        ball.init(this.getWidth()/2 - Ball.RADIUS, this.getHeight()/2 - Ball.RADIUS);
        
        scoreLabel1.setText("0");
        scoreLabel2.setText("0");
    }
}
