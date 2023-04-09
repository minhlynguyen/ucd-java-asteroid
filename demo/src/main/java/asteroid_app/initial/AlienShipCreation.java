package asteroid_app.initial;

// A class that represents the creation of an alien ship in the game.
public class AlienShipCreation {

    private double x;
    private double y;

    public AlienShipCreation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Method that creates a new Alien object with the given x and y coordinates.
    public Alien createAlienShip() {
        Alien alien = new Alien();
        alien.setTranslateX(x);
        alien.setTranslateY(y);
        return alien;
    }
}
