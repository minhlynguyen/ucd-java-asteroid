package asteroid_app.initial;

// A class that represents the creation of an alien ship in the game.
public class AlienShipCreation {

    // Method that creates a new Alien object 
    // with the given x and y coordinates.
    public Alien createAlienShip(double x, double y) {
        Alien alien = new Alien(); // Create a new Alien object
        alien.setTranslateX(x); // Set the x coordinate
        alien.setTranslateY(y); // Set the y coordinate
        return alien; // Return the created Alien object
    }
}
