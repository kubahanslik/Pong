/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

/**
 *
 * @author jakub
 */
public class Main {
    Window window;
    Player player1, player2;
    Ball ball;
    
    long roundStartTime;
    
    public Main() {
        window = new Window(Settings.windowWidth, Settings.windowHeight);
        
        // Getting easier access to players and ball 
        player1 = window.gamePanel.player1;
        player2 = window.gamePanel.player2;
        ball = window.gamePanel.ball;
        
        // Starting main loop
        mainLoop();
    }
    
    public void mainLoop() {
        while (true) {
            startRound();
            endGameMenu();
        }
    }
    
    
    public void startRound() {
        // Preventing ball having too much speed and glitching through players
        // In this case we pass in 22.4 as a safe speed for one frame
        int deltaTime = 1000/Settings.FPS*ball.speed < 22.4 ? 1000/Settings.FPS : (int)Math.round(22.4/ball.speed);
        
        // By multiplying speed with delta time the players and the ball will always look like it is moving the same speed, no matter what FPS you set
        Player.speed = Settings.playerSpeed*deltaTime;
        Ball.speed = Settings.ballSpeed*deltaTime;
        
        roundStartTime = System.currentTimeMillis();
        ball.randomDir(); // Setting random direction for ball velocity
        
        while (window.repeat) {
            move();
            handleEvents();
            
            try {
                Thread.sleep(deltaTime);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void endGameMenu() {
        ball.setVisible(false); // If we didnt set the ball unvisible, we wouldnt see the text clearly
        window.gamePanel.showEndingScreen(player1.points == Settings.winningPoints ? "Left side won" : "Right side won");
        while (!window.repeat) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        window.gamePanel.hideEndingScreen();
        window.gamePanel.resetComponents(); // Reseting positions of our components
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
        if (player1.down && player1.posY + player1.getHeight() < window.gamePanel.getHeight()) {
            player1.moveDown();
        }
        if (player2.up && player2.posY > 0) {
            player2.moveUp();
        }
        if (player2.down && player2.posY + player2.getHeight() < window.gamePanel.getHeight()) {
            player2.moveDown();
        }
        
    }
    
    public void handleEvents() {
        checkBallPlayerColl();
        checkBallWallColl();
        checkForGoals();
    }
    
    public void checkBallPlayerColl() {
        // Checking if ball doesnt collide with player1
        if (player1.getX() + player1.getWidth() >= ball.getX() && player1.getX() <= ball.getX() && player1.getY() <= ball.getY() +  ball.getWidth()/2 && player1.getY() + player1.getHeight() >= ball.getCenterY()) {
            ball.playerColl(player1, true);
        }
        // Checking if ball doesnt collide with player2
        else if (player2.getX() + player2.getWidth() >= ball.getX() + ball.getWidth() && player2.getX() <= ball.getX() + ball.getWidth()  && player2.getY() <= ball.getY() +  ball.getWidth()/2 && player2.getY() + player2.getHeight() >= ball.getCenterY()) {
            ball.playerColl(player2, false);
        }
    }
    
    public void checkBallWallColl() {
        // Checking if the ball didnt hit top or bottom of our window
        if (ball.getY() <= 0 || ball.getY() + ball.getHeight() >= window.gamePanel.getHeight()) {
            ball.velY = -ball.velY; // Inverting the velY
        }
    }
    
    public void checkForGoals() {
        if (ball.getX() >= window.gamePanel.getWidth()) { // Checking if player1 didnt score
            player1.scored(window.gamePanel.scoreLabel1);
            ball.scored(window.gamePanel.getWidth(), window.gamePanel.getHeight(), true);
            roundStartTime = System.currentTimeMillis();
            window.repeat = player1.points != Settings.winningPoints; // Checking if player1 didnt win
        } 
        else if (ball.getX() + ball.getWidth() <=0) { // Checking if player2 didnt score
            player2.scored(window.gamePanel.scoreLabel2);
            ball.scored(window.gamePanel.getWidth(), window.gamePanel.getHeight(), false);
            roundStartTime = System.currentTimeMillis(); // Reseting our round time
            window.repeat = player2.points != Settings.winningPoints; // Checking if player2 didnt win
        }
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
