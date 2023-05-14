/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author jakub
 */
public class Ball extends JPanel {
    float posX, posY, velX, velY;
    
    static float speed = 0.8f;
    public static final float ballSpeed = 0.8f;
    public static final int ballDiameter = 10;
    public static final int ballRadius = ballDiameter/2;
    public static final int ballBounceAngle = 120;
    
    public Ball(int x, int y) {
        init(x, y);
    }
    
    public void init(int x, int y) {
        this.setBounds(x, y, ballDiameter, ballDiameter);
        
        posX = x;
        posY = y;
        
        velX = 0;
        velY = 0;
    }
    
    public void move() {
        posX += velX;
        posY += velY;
        
        this.setLocation(Math.round(posX), Math.round(posY));
    }
    
    public void randomDir() {
        velX = Math.random() < 0.5 ? speed : -speed; 
    }
    
    public void scored(int width, int height, boolean side) { // Setting side parameter as a boolean will make it easy to give the ball right direction
        this.setLocation(width / 2 - this.getWidth() / 2, height / 2 - this.getHeight() / 2);
        
        posX = this.getX();
        posY = this.getY();
        
        velX = side ? speed : -speed;
        velY = 0;
    }
    
    public void playerColl(Player p, boolean side) {
        int angle = Math.round((this.getCenterY() - p.getCenterY())/((float)p.getHeight()/2)*(ballBounceAngle/2));
        
        velY = (float)(Math.sin(Math.toRadians(angle))*speed);
        velX = side ? (float)(Math.cos(Math.toRadians(angle))*speed) : -(float)(Math.cos(Math.toRadians(angle))*speed);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(Color.white);
        g2d.fillOval(0, 0, this.getWidth(), this.getWidth());
    }
    
    public int getCenterY() {
        return this.getY() + this.getHeight()/2;
    }
}
