import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends ImageView {

    private static final String IMAGE_PATH = "file:human.png";
    private static final Image ENEMY_IMAGE = new Image(IMAGE_PATH);

    private int hp;
    private int maxHp;
    private int atk;
    private double speed;


    public Enemy(int initialX, int initialY, int hp) {
        super(ENEMY_IMAGE);
        this.hp = hp;
        this.maxHp = hp;
        this.atk = 10;
        this.speed = 2.0;

        // Set initial position on the board
        setLayoutX(initialX);
        setLayoutY(initialY);

        Game.rootgame.getChildren().add(this);
    }
    public double distance(double playerX, double playerY){
        double distance = Math.sqrt(Math.pow(getLayoutX() - playerX, 2) + Math.pow(getLayoutY() - playerY, 2));
        return distance;
    }

    public void move(double playerX, double playerY) {
        double xRootChange = Game.rootboard.getLayoutX();
        double yRootChange = Game.rootboard.getLayoutY();
        double distance = Math.sqrt(Math.pow(getLayoutX() - playerX, 2) + Math.pow(getLayoutY() - playerY, 2));

        if (distance < 200) {
            double angle = Math.atan2(playerY - getLayoutY(), playerX - getLayoutX());
            setLayoutX(getLayoutX() + speed * Math.cos(angle));
            setLayoutY(getLayoutY() + speed * Math.sin(angle));
        } else {
            int randomDirection = (int) (Math.random() * 4);
            switch (randomDirection) {
                case 0:
                    setLayoutY(getLayoutY() - speed);
                    break;
                case 1:
                    setLayoutY(getLayoutY() + speed);
                    break;
                case 2:
                    setLayoutX(getLayoutX() - speed);
                    break;
                case 3:
                    setLayoutX(getLayoutX() + speed);
                    break;
            }
        }
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            Game.player.Xp += this.maxHp;
            Game.rootgame.getChildren().remove(this);
        }
    }
}