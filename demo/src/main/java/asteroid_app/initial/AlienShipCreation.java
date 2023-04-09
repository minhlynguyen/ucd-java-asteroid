package asteroid_app.initial;

import java.util.Random;

public class AlienShipCreation {
    private static final int MIN_ALIEN_SHIP_SPAWN_DELAY = 10000;
    private static final int MAX_ALIEN_SHIP_SPAWN_DELAY = 15000;
    private static final int ALIEN_SHIP_SPEED = 5;

    private int screenWidth;
    private int screenHeight;
    private long lastAlienShipSpawnTime;
    private int alienShipSpawnDelay;
    private Random random;

    public AlienShipCreation(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        lastAlienShipSpawnTime = System.currentTimeMillis();
        alienShipSpawnDelay = newRandomAlienShipSpawnDelay();
        random = new Random();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAlienShipSpawnTime >= alienShipSpawnDelay) {
            spawnAlienShip();
            lastAlienShipSpawnTime = currentTime;
            alienShipSpawnDelay = newRandomAlienShipSpawnDelay();
        }
    }

    private int newRandomAlienShipSpawnDelay() {
        return random.nextInt(MAX_ALIEN_SHIP_SPAWN_DELAY - MIN_ALIEN_SHIP_SPAWN_DELAY) + MIN_ALIEN_SHIP_SPAWN_DELAY;
    }

    private void spawnAlienShip() {
        int x = -AlienShip.RADIUS;
        int y = random.nextInt(screenHeight);
        int angle = random.nextInt(90) + 45;
        AlienShip alienShip = new AlienShip(x, y, angle, ALIEN_SHIP_SPEED);
        Game.getInstance().addAlienShip(alienShip);
    }
}
