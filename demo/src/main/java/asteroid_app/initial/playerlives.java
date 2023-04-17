public class PlayerLives {
    private int lives;
    private boolean extraLife;
    private User_ship userShip;

    public PlayerLives(User_ship userShip) {
        this.lives = 3;
        this.extraLife = false;
        this.userShip = userShip;
    }

    public int getLives() {
        return lives;
    }

    public boolean getExtraLife() {
        return extraLife;
    }

    public void setExtraLife(boolean extraLife) {
        this.extraLife = extraLife;
    }

    public void decreaseLives() {
        lives--;
    }

    public void increaseLives() {
        lives++;
    }

    public User_ship getUserShip() {
        return userShip;
    }
}
