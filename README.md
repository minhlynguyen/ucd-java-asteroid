# asteroids-java
This is a group project of 4 members creating the arcade game asteroids using java and javaFX

The game is based on this website:
https://freeasteroids.org/

## Game Description

Asteroids is a classic arcade game, where a spaceship moves through space destroying asteroids and occasional alien ships. The difficulty of the game increases as the levels progress. 

### Spaceship

The player ship can perform 5 actions:
- Rotate Right: Rotate the ship in the clockwise direction
- Rotate Left: Rotate the ship in the anti-clockwise direction
- Fire: Fire a bullet in the direction the ship is currently pointing
- Apply Thrust: Add speed to the current motion in the direction the ship is currently pointing. When thrust is applied, the ship gains speed moving in the direction it is pointing. If we stop thrusting, the ship will continue to move at the same speed in the same direction until we apply some thrust in the opposite direction. This means that if I apply one second of thrust with the ship pointing up, it will need one second of thrust with the ship pointing down for it to stop. This is then further complicated when the ship is pointed at an angle.
- Hyperspace Jump: Disappear from the current location and reappear in a new location on the screen (The new location should not be in contact with another object)

### Bullet:
- Bullet speed is based on the speed of the ship when they were fired

### There are three types of asteroids
- Large: These typically move very slowly and are large and therefore easier to shoot. When destroyed, two medium-sized asteroids are created. These newly created asteroids will move in random directions and with random speeds but generally faster than the large asteroid was moving.
- Medium: These are a bit smaller than the large asteroids and a bit faster too. When destroyed, two small-sized asteroids are created. These newly created asteroids will move in random directions and with random speeds but generally faster than the medium asteroid was moving.
- Small: These are again smaller and faster than the medium-sized asteroids. When destroyed, no new asteroids are created.

### Alien ship
- Occasionally, an alien will appear and move through the screen from one side to the other. During this movement, it will fire randomly. 
- The player can either avoid it or attempt to destroy it.

### Looping through the screen
- When a ship, asteroid, or bullet moves into the edge of the screen, it should then appear on the opposite side of the screen moving in the same direction. This means that objects will continue looping through the screen indefinitely. 
- The only objects this is not the case for are bullets, these **disappear automatically after traveling a set distance**.

### Levels
The game is based on a series of increasingly more difficult levels. Once each is cleared, the next begins automatically. In the first level there will be only one slow-moving asteroid, in the second there will be two, and so on.
While the game is being played, some info should be displayed on the screen such as the player's current number of lives and their current score.
When the player is destroyed, either by hitting an asteroid or has been shot by the alien he must be placed back in the game safely. This means that it is either invincible for 2-3 seconds or is placed in a position that is calculated as safe.
After destruction one of the player's lives is removed. On some versions of the game, the player can regain lives by scoring 10,000 points, but this is based on the number of points given for each destroyed asteroid.

## My contributions to the project: 
- Create main control of the game: Start a new game, Stop the game, Restart the game, Upgrade game level
-	Create an IO stream to save and read high-score data
-	Implement bulleting firing behavior of the player's ship and aliens
-	Implement movement and collision of bullets with score increment, implement collision of alien