package main;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Util {
    
    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;
    
    private static volatile boolean upPressed = false;
    private static volatile boolean downPressed = false;
    private static volatile boolean rightPressed = false;
    private static volatile boolean leftPressed = false;
    
    public static JFrame frame = new JFrame("HelloGame");
    
    public static boolean isWPressed() {
        synchronized (Table.class) {
            return wPressed;
        }
    }
    
    public static boolean isAPressed() {
        synchronized (Table.class) {
            return aPressed;
        }
    }
    
    public static boolean isSPressed() {
        synchronized (Table.class) {
            return sPressed;
        }
    }
    
    public static boolean isDPressed() {
        synchronized (Table.class) {
            return dPressed;
        }
    }
    
    public static boolean isUpPressed() {
        synchronized (Table.class) {
            return upPressed;
        }
    }
    
    public static boolean isDownPressed() {
        synchronized (Table.class) {
            return downPressed;
        }
    }
    
    public static boolean isRightPressed() {
        synchronized (Table.class) {
            return rightPressed;
        }
    }
    
    public static boolean isLeftPressed() {
        synchronized (Table.class) {
            return leftPressed;
        }
    }

    
    /**
     * A játékot ténylegesen elindító main függvény.
     * @param args 
     */
    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (Util.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            switch (ke.getKeyCode()) {
                                case KeyEvent.VK_W:
                                    wPressed = true;
                                    break;
                                case KeyEvent.VK_A:
                                    aPressed = true;
                                    break;
                                case KeyEvent.VK_S:
                                    sPressed = true;
                                    break;
                                case KeyEvent.VK_D:
                                    dPressed = true;
                                    break;
                                case KeyEvent.VK_UP:
                                    upPressed = true;
                                    break;
                                case KeyEvent.VK_DOWN:
                                    downPressed = true;
                                    break;
                                case KeyEvent.VK_RIGHT:
                                    rightPressed = true;
                                    break;
                                case KeyEvent.VK_LEFT:
                                    leftPressed = true;
                                    break;
                            }
                            break;
                            
                        case KeyEvent.KEY_RELEASED:
                            switch (ke.getKeyCode()) {
                                case KeyEvent.VK_W:
                                    wPressed = false;
                                    break;
                                case KeyEvent.VK_A:
                                    aPressed = false;
                                    break;
                                case KeyEvent.VK_S:
                                    sPressed = false;
                                    break;
                                case KeyEvent.VK_D:
                                    dPressed = false;
                                    break;
                                case KeyEvent.VK_UP:
                                    upPressed = false;
                                    break;
                                case KeyEvent.VK_DOWN:
                                    downPressed = false;
                                    break;
                                case KeyEvent.VK_RIGHT:
                                    rightPressed = false;
                                    break;
                                case KeyEvent.VK_LEFT:
                                    leftPressed = false;
                                    break;
                                }
                                break;
                    }
                    return false;
                }
            }
        });
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("HelloGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        
        JLabel label = new JLabel("Hello game");
        label.setBounds(10, 280, 100, 20);
        frame.getContentPane().add(label);

        JButton newGameButton = new JButton("Új játék");
        newGameButton.setBounds(10, 480, 100, 20);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().init();
            }
        });
        
        frame.getContentPane().add(newGameButton);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}


