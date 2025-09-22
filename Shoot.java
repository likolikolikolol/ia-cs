import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Shoot {
    private Timeline timeline;
    private Circle circle;
    private double speed = 15; // Speed of the projectile

    public Shoot(int atk, double xmouse, double ymouse) {
        double xPlayer = 600;
        double yPlayer = 400;

        circle = new Circle();
        circle.setFill(Color.RED);
        circle.setCenterX(xPlayer);
        circle.setCenterY(yPlayer);
        circle.setRadius(5); // Increased radius for better visibility

        Game.rootgame.getChildren().add(circle);

        double angle = Math.atan2(ymouse - yPlayer, xmouse - xPlayer);
        double vx = speed * Math.cos(angle);
        double vy = speed * Math.sin(angle);

        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            circle.setCenterX(circle.getCenterX() + vx);
            circle.setCenterY(circle.getCenterY() + vy);

            // Stop condition: if the circle is out of bounds
            if (circle.getCenterX() < 0 || circle.getCenterX() > Main.Width ||
                circle.getCenterY() < 0 || circle.getCenterY() > Main.Height) {
                timeline.stop();
                Game.rootgame.getChildren().remove(circle);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
