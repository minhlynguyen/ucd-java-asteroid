package asteroid_app.initial;

// A class that represents the creation of an alien ship in the game.
public class AlienShipCreation {

    // Constructor that takes the x and y coordinates as parameters.
    public AlienShipCreation() {
    }

    // Method that creates a new Alien object 
    // with the given x and y coordinates.
    public Alien createAlienShip(double x, double y) {
        Alien alien = new Alien(x, y); // Create a new Alien object
        return alien; // Return the created Alien object
    }
}
