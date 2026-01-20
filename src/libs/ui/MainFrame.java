package libs.ui;

import libs.entity.Enemy;
import libs.entity.Entity;
import libs.entity.Player;
import libs.entity.Projectile;
import libs.util.Collider2D;
import libs.util.Transform2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

//
// Main Game- and Input-Loop + managing and processing data to be shown on screen by the GamePanel-Class
//
public class MainFrame extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    // Player
    private Player player = new Player(
            100, 3f,
            new Transform2D(10,10), new Collider2D(25,25)
    );
    private Transform2D mouseTransform = new Transform2D();
    private boolean up, down, left, right, shooting;

    // Entities
    private final int enemySpawnCooldown = 10000;
    private int timeSinceLastEnemySpawn = 1000;
    private static List<Entity> entities = new ArrayList<>();

    // Display
    private final GamePanel gamePanel = new GamePanel(this);

    // Main Game-Loop
    public MainFrame(){
        setSize(500,500);

        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.createImage("");
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "invisibleCursor");
        setCursor(invisibleCursor);

        add(gamePanel);

        setVisible(true);

        entities.add(player);

        //
        // Main Game-Loop (60 FPS)
        //
        long startTime = System.currentTimeMillis();
        int deltaTime = 16;
        Timer timer = new Timer(deltaTime, e -> {
            // Get time
            long elapsedTime = System.currentTimeMillis() - startTime;

            // Spawn stuff
            if (timeSinceLastEnemySpawn <= 0) {
                entities.add(
                        new Enemy(
                                100, 20, 2f,
                                new Transform2D(250,250), new Collider2D(20,20)
                        )
                );
                timeSinceLastEnemySpawn = enemySpawnCooldown;
            }
            timeSinceLastEnemySpawn -= deltaTime;

            // Entities
            for(Entity entity : entities){
                if(entity.getClass() == Enemy.class || entity.getClass() == Projectile.class){
                    entity.move(player.getTransform(), this);
                }
            }

            // Player Input
            if (up) player.getTransform().y -= (int) player.getMovementSpeed();
            if (down) player.getTransform().y += (int) player.getMovementSpeed();
            if (left) player.getTransform().x -= (int) player.getMovementSpeed();
            if (right) player.getTransform().x += (int) player.getMovementSpeed();

            if (shooting) player.shoot(mouseTransform, entities);

            // Set sprites horizontally & vertically
//            if(up) player.setActiveSprite("/assets/player/up.png");
//            if(down) player.setActiveSprite("/assets/player/down.png");
//            if(right) player.setActiveSprite("/assets/player/right.png");
//            if(left) player.setActiveSprite("/assets/player/left.png");
//
//            // Set sprites diagonally
//            if(up && right) player.setActiveSprite("/assets/player/right-up.png");
//            if(up && left) player.setActiveSprite("/assets/player/left-up.png");
//            if(down && right) player.setActiveSprite("/assets/player/right-down.png");
//            if(down && left) player.setActiveSprite("/assets/player/left-down.png");

            // Display
            gamePanel.repaint();
        });
        timer.start();
    }


    //
    // Key Listener Methods
    //
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

    //
    // Mouse Listener Methods
    //
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) shooting = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) shooting = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    //
    // Mouse Motion Listener
    //
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseTransform.x = e.getX() - 25;
        mouseTransform.y = e.getY() - 45;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseTransform.x = e.getX() - 25;
        mouseTransform.y = e.getY() - 45;
    }


    public Player getPlayer(){
        return player;
    }

    public Transform2D getMouseTransform(){
        return mouseTransform;
    }

    public List<Entity> getEntities(){
        return entities;
    }
}
