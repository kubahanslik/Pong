/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

/**
 *
 * @author jakub
 */
public abstract class Settings {
    
    // Window settings
    public static final int FPS = 120;
    public static final int windowWidth = 850;
    public static final int windowHeight = 800;
    
    // Player settings
    public static final float playerSpeed = 0.5f;
    public static final int playerWidth = 12;
    public static final int playerHeight = 90;
    public static final int marginFromSideWall = 20;
    
    // Ball settings
    public static final float ballSpeed = 0.8f;
    public static final int ballDiameter = 10;
    public static final int ballRadius = ballDiameter/2;
    public static final int ballBounceAngle = 120;
    
    // ScoreLabel settings
    public static final int scoreFontSize = 40;
    public static final int scoreLabelWidth = 60;
    public static final int scoreLabelHeight = 40;
    public static final int scoreMarginX = 300;
    public static final int scoreMarginY = 20;
    
    // Ending screen panel
    public static final int endingScreenWidth = 500;
    public static final int endingScreenHeight = 100;
    
    // MessageLabel settings
    public static final int messageFontSize = 40;
    public static final int messageWidth = 500;
    public static final int messageHeight = 60;
    
    // PlayAgainLabel settings
    public static final int playAgainFontSize = 20;
    public static final int playAgainWidth = 300;
    public static final int playAgainHeight = 40;
    
    // General settings
    public static final int winningPoints = 3;
}
