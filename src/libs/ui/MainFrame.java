package libs.ui;

import libs.entity.Enemy;
import libs.entity.Entity;
import libs.entity.Player;
import libs.entity.Projectile;
import libs.entity.item.HealthItem;
import libs.util.Collider2D;
import libs.util.ConfigData;
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
            new Transform2D(15 * ConfigData.entity_scale,15 * ConfigData.entity_scale), new Collider2D(30 * ConfigData.entity_scale,30 * ConfigData.entity_scale),
            "src/assets/player/player.png"
    );
    private Transform2D mouseTransform = new Transform2D();
    private boolean up, down, left, right, shooting;

    // Entities
    private int wave = 1;
    private int enemySpawnCooldown = 10000;
    private int minEnemySpawnCooldown = 750;
    private int timeSinceLastEnemySpawn = 3000;
    private static List<Entity> entities = new ArrayList<>();
    private List<Entity> entities2Add = new ArrayList<>();
    private List<Entity> entities2Destroy = new ArrayList<>();

    // Display
    private final GamePanel gamePanel = new GamePanel(this);

    // Main Game-Loop
    public MainFrame(){
        setSize(ConfigData.window_width,ConfigData.window_height);
        setResizable(true);

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
        int deltaTime = ConfigData.game_loop_delta_time;
        Timer timer = new Timer(deltaTime, e -> {
            // Close game when player dies
            if (player.isDead) return;

            // Get time
            long elapsedTime = System.currentTimeMillis() - startTime;

            // Spawn stuff
            if (timeSinceLastEnemySpawn <= 0) {
                final Transform2D[] spawnpoints = {
                        new Transform2D(0,0),
                        new Transform2D(getWidth(),getHeight()),
                        new Transform2D(0,getHeight()),
                        new Transform2D(getWidth(),0)
                };

                float scale = (float)(Math.random() * 0.8 + 0.5);
                float wave_scale = (float)( 1 + wave / 10);

                if(wave % 10 == 0){
                    // Spawn boss enemy
                    entities.add(
                        new Enemy(
                                (int)(500 * scale * wave_scale), 2f / scale * wave_scale, (int)(45 * scale * wave_scale),
                                spawnpoints[(int)Math.floor(Math.random()*spawnpoints.length)], new Collider2D((int)(70 * scale * ConfigData.entity_scale),(int)(70 * scale * ConfigData.entity_scale)),
                                "src/assets/enemy/boss_enemy.png"
                        ));
                }
                else{
                    entities.add(
                            new Enemy(
                                    (int)(100 * scale * wave_scale), 2f / scale * wave_scale, (int)(25 * scale * wave_scale),
                                    spawnpoints[(int)Math.floor(Math.random()*spawnpoints.length)], new Collider2D((int)(30 * scale * ConfigData.entity_scale),(int)(30 * scale * ConfigData.entity_scale)),
                                    "src/assets/enemy/common_enemy.png"
                            )
                    );
                }
                timeSinceLastEnemySpawn = enemySpawnCooldown;

                // Increase wave counter
                wave += 1;

                // Crank up enemy spawns
                enemySpawnCooldown = (int) (enemySpawnCooldown * .75 + minEnemySpawnCooldown);
            }
            timeSinceLastEnemySpawn -= deltaTime;

            // Entities
            for(Entity entity : entities){
                if(entity.getClass() != Player.class && !entity.isDead){
                    entity.move(player.getTransform(), this);
                }
                else if(entity.isDead){
                    entities2Destroy.add(entity);
                }
            }

            // Player Input
            if (up) player.getTransform().y -= (int) player.getMovementSpeed();
            if (down) player.getTransform().y += (int) player.getMovementSpeed();
            if (left) player.getTransform().x -= (int) player.getMovementSpeed();
            if (right) player.getTransform().x += (int) player.getMovementSpeed();

            if (shooting) player.shoot(mouseTransform, entities);

            // Destroy dead entities
            destroyEntites();

            // Add new entities
            addEntities();

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

    private void destroyEntites(){
        this.entities.removeAll(entities2Destroy);
        entities2Destroy.clear();
    }

    public void addEntity2AddingQueue(Entity entity){
        this.entities2Add.add(entity);
    }

    private void addEntities(){
        this.entities.addAll(entities2Add);
        entities2Add.clear();
    }
}
