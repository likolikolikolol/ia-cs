import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends ImageView {

    private static final String IMAGE_PATH = "human.png";
    private static final Image ENEMY_IMAGE = new Image(IMAGE_PATH);

    private int hp;
    private int atk;
    private double speed;

    public Enemy(int initialX, int initialY) {
        super(ENEMY_IMAGE);
        this.hp = 100;
        this.atk = 10;
        this.speed = 1.0;

        // Set initial position on the board
        setLayoutX(initialX);
        setLayoutY(initialY);

        Game.rootgame.getChildren().add(this);
    }

    public void move(double playerX, double playerY) {
        double distance = Math.sqrt(Math.pow(getLayoutX() - playerX, 2) + Math.pow(getLayoutY() - playerY, 2));

        if (distance < 200) { // Detection range of 200 pixels
            // Move towards the player
            double angle = Math.atan2(playerY - getLayoutY(), playerX - getLayoutX());
            setLayoutX(getLayoutX() + speed * Math.cos(angle));
            setLayoutY(getLayoutY() + speed * Math.sin(angle));
        } else {
            // Move randomly
            int randomDirection = (int) (Math.random() * 4);
            switch (randomDirection) {
                case 0: // Up
                    setLayoutY(getLayoutY() - speed);
                    break;
                case 1: // Down
                    setLayoutY(getLayoutY() + speed);
                    break;
                case 2: // Left
                    setLayoutX(getLayoutX() - speed);
                    break;
                case 3: // Right
                    setLayoutX(getLayoutX() + speed);
                    break;
            }
        }
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            // Handle enemy death (e.g., remove from game)
            Game.rootgame.getChildren().remove(this);
        }
    }
}
