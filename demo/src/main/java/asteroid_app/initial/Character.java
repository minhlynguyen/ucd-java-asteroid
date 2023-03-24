package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;
//import point 2d to represent the movement of any object
import javafx.geometry.Point2D;

//this character class is the parent class of all the characters in the game
public abstract class Character{
    //define a private polygon object to reprent each object
    // and a 2d point to represent the movement of each object
    private Polygon object;
    private Point2D movement;
	private Point2D test;
	private static final double maxShipVelocity=7;
	private static final double shipAcceleration=0.06;
	private static final double shipTurnAngle=5;

    public Character(Polygon polygon, int x, int y){
        //create the user_ship shape
        this.object = polygon;
        this.object.setTranslateX(x);
        this.object.setTranslateY(y);

        //initialize the movement of the object to zero
        this.movement = new Point2D(0, 0);
    }

    //return the object
    public Polygon getChar(){
        return this.object;
    }
    // turn it left
    public void turnLeft(){
        this.object.setRotate(this.object.getRotate() - shipTurnAngle);
    }
    // turn it right
    public void turnRight(){
        this.object.setRotate(this.object.getRotate() + shipTurnAngle);
    }
    // move the object
    public void move() {
		//add any acceleration to the current movement
        this.object.setTranslateX(this.object.getTranslateX() + this.movement.getX());
        this.object.setTranslateY(this.object.getTranslateY() + this.movement.getY());


		//if the object is out of the screen, move it to the other side
		//exit the screen from the left side, enter from the right side
		if (this.object.getTranslateX() < 0) {
			this.object.setTranslateX(this.object.getTranslateX() + window.WIDTH);
		}
		//exit the screen from the right side, enter from the left side
		if (this.object.getTranslateX() > window.WIDTH) {
			this.object.setTranslateX(this.object.getTranslateX() % window.WIDTH);
		}
		//exit the screen from the top, enter from the bottom
		if (this.object.getTranslateY() < 0) {
			this.object.setTranslateY(this.object.getTranslateY() + window.HEIGHT);
		}
		//exit the screen from the bottom, enter from the top
		if (this.object.getTranslateY() > window.HEIGHT) {
			this.object.setTranslateY(this.object.getTranslateY() % window.HEIGHT);
		}
	}
    // accelerate the object
    public void accelerate() {
		
		double changeX = Math.cos(Math.toRadians(this.object.getRotate()));
		double changeY = Math.sin(Math.toRadians(this.object.getRotate()));
		//only need few percent of the possible acceleration
		changeX *= shipAcceleration;
		changeY *= shipAcceleration;
		//ensures that when the object is at maximum velocity
		// we can accelerate in a different direction instead of being stuck
		// at maximum velocity
		test=this.movement.add(changeX, changeY);

		if(this.movement.magnitude() < maxShipVelocity && test.magnitude()<maxShipVelocity) {
			this.movement = this.movement.add(changeX, changeY);
		}
    }


}