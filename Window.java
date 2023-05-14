/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author jakub
 */
public class Window extends JFrame implements KeyListener {
    MainPanel gamePanel;
    Player player1, player2;
    boolean repeat = true;
    
    public static final int FPS = 120;
    public static final int WIDTH = 850;
    public static final int HEIGHT = 800;
    
    public Window() {
        this.setTitle("Pong");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        
        /* 
           We need to set the game panel size to window's content pane size
           If we just passed in the size of the window, we wouldn't be able to set the location of the components right
        */
        gamePanel = new MainPanel(this.getContentPane().getWidth(), this.getContentPane().getHeight()); 
        
        // Getting easier access to players
        player1 = gamePanel.player1;
        player2 = gamePanel.player2;
        
        this.add(gamePanel);
    }
    
    public void handleKeyEvents(KeyEvent e, boolean type) { // By adding the type parameter we can easily save some lines of code and just pass in true or false according to what action we wanna do
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player1.up = type;
                break;
            case KeyEvent.VK_S:
                player1.down = type;
                break;
            case KeyEvent.VK_UP:
                player2.up = type;
                break;
            case KeyEvent.VK_DOWN:
                player2.down = type;
                break;
        }
        
        if (!repeat && e.getKeyCode() == KeyEvent.VK_SPACE) { // With this condition, we check if user pressed space in our endGameMenu
            repeat = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // We can just ignore this method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyEvents(e, true); // Passing type true because we want to say that a key was pressed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyEvents(e, false); // Passing type false because we want to say that a key was released
    }
}
