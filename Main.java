/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author jakub
 */
public class Main {
    Window window;
    Player player1, player2;
    Ball ball;
    
    AudioInputStream scoreSound, bounceSound;
    Clip scoreClip, bounceClip;
    
    int deltaTime;
    long roundStartTime;
    public static final int WINNING_POINTS = 10;
    
    public Main() {
        window = new Window();
        
        // Getting easier access to players and ball 
        player1 = window.gamePanel.player1;
        player2 = window.gamePanel.player2;
        ball = window.gamePanel.ball;
        
        try {
            scoreSound = AudioSystem.getAudioInputStream(new File(getClass().getResource("../audio/score.wav").getPath()));
            scoreClip = AudioSystem.getClip();
            scoreClip.open(scoreSound);
            
            bounceSound = AudioSystem.getAudioInputStream(new File(getClass().getResource("../audio/bounce.wav").getPath()));
            bounceClip = AudioSystem.getClip();
            bounceClip.open(bounceSound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println(ex);
        }
        
        // Starting main loop
        mainLoop();
    }
    
    public void mainLoop() {
        // Preventing ball having too much speed and glitching through players
        // In this case we pass in 22.4 as the highest possible ball speed for one iteration
        deltaTime = 1000/Window.FPS*ball.speed < 22.4 ? 1000/Window.FPS : (int)Math.round(22.4/ball.speed);
        
        // By multiplying speed with delta time the players and the ball will always look like they are moving the same speed, no matter what FPS you set
        Player.speed *= deltaTime;
        Ball.speed *= deltaTime;
        
        while (true) {
            gameLoop();
            endGameMenu();
        }
    }
    
    
    public void gameLoop() {
        roundStartTime = System.currentTimeMillis();
        ball.randomDir(); // Setting random direction for ball velocity
        
        while (window.repeat) {
            move();
            handleIntersects();
            
            try {
                Thread.sleep(deltaTime);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void endGameMenu() {
        ball.setVisible(false); // If we didnt set the ball unvisible, we wouldnt see the text clearly
        window.gamePanel.endGamePanel.showEndingScreen(player1.points == WINNING_POINTS ? "Left side won" : "Right side won");
        while (!window.repeat) { // Repeating the loop, until user presses space and repeat will be true
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        window.gamePanel.endGamePanel.hideEndingScreen();
        window.gamePanel.resetComponents(); // Reseting positions of our components for a new game
        ball.setVisible(true);
    }
    
    public void move() {
        movePlayers();
        if (System.currentTimeMillis() - roundStartTime >= 3000){ // Ball will always wait 3 seconds after starting game or scoring a point
            ball.move();
        }
    }
    
    public void movePlayers() {
        if (player1.up && player1.posY > 0) {
            player1.moveUp();
        }
        if (player1.down && player1.posY + Player.HEIGHT < window.gamePanel.getHeight()) {
            player1.moveDown();
        }
        if (player2.up && player2.posY > 0) {
            player2.moveUp();
        }
        if (player2.down && player2.posY + Player.HEIGHT < window.gamePanel.getHeight()) {
            player2.moveDown();
        }
        
    }
    
    public void handleIntersects() {
        checkBallPlayerColl();
        checkBallWallColl();
        checkForGoals();
    }
    
    public void checkBallPlayerColl() {
        // Checking if ball doesnt collide with player1
        if (player1.getX() + Player.WIDTH >= ball.getX() && player1.getX() <= ball.getX() && player1.getY() <= ball.getY() +  Ball.DIAMETER/2 && player1.getY() + Player.HEIGHT >= ball.getCenterY()) {
            ball.playerColl(player1, true);
            
            // Playing bounce sound
            bounceClip.setMicrosecondPosition(0);
            bounceClip.start();
        }
        // Checking if ball doesnt collide with player2
        else if (player2.getX() + Player.WIDTH >= ball.getX() + Ball.DIAMETER && player2.getX() <= ball.getX() + Ball.DIAMETER  && player2.getY() <= ball.getY() +  Ball.DIAMETER/2 && player2.getY() + Player.HEIGHT >= ball.getCenterY()) {
            ball.playerColl(player2, false);
            
            // Playing bounce sound
            bounceClip.setMicrosecondPosition(0);
            bounceClip.start();
        }
    }
    
    public void checkBallWallColl() {
        // Checking if the ball didnt hit top or bottom of our window
        if (ball.getY() <= 0 || ball.getY() + Ball.DIAMETER >= window.gamePanel.getHeight()) {
            ball.velY = -ball.velY; // Inverting the velY
            
            // Playing bounce sound
            bounceClip.setMicrosecondPosition(0);
            bounceClip.start();
        }
    }
    
    public void checkForGoals() {
        if (ball.getX() >= window.gamePanel.getWidth()) { // Checking if player1 didnt score
            player1.scored(window.gamePanel.scoreLabel1);
            ball.scored(window.gamePanel.getWidth(), window.gamePanel.getHeight(), true);
            roundStartTime = System.currentTimeMillis(); // Reseting round time
            window.repeat = player1.points != WINNING_POINTS; // Checking if player1 didnt win
            
            scoreClip.setMicrosecondPosition(0);
            scoreClip.start();
        } 
        else if (ball.getX() + ball.getWidth() <=0) { // Checking if player2 didnt score
            player2.scored(window.gamePanel.scoreLabel2);
            ball.scored(window.gamePanel.getWidth(), window.gamePanel.getHeight(), false);
            roundStartTime = System.currentTimeMillis(); // Reseting round time
            window.repeat = player2.points != WINNING_POINTS; // Checking if player2 didnt win
            
            scoreClip.setMicrosecondPosition(0);
            scoreClip.start();
        }
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
