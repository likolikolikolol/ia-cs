import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Shoot {
    double count = 0;
    Timeline timeline;
    public Shoot(int atk , double xmouse, double ymouse) {


        Circle circle = new Circle();

        double xPlayer = 600;
        double yPlayer = 400;
        double xmove = xmouse-xPlayer;
        double ymove = ymouse-yPlayer;



        Game.rootgame.getChildren().add(circle);
        circle.setFill(Color.RED);
        circle.setCenterX(xPlayer);
        circle.setCenterY(yPlayer);
        circle.setRadius(2);

        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {

                for (int j = 0; j < xmove / 2; j++) {
                    circle.setCenterX(circle.getCenterX() + 2);
                }

                for (int j = 0; j < ymove / 2; j++) {
                    circle.setCenterY(circle.getCenterX() + 2);
                }
                if (count == xmouse){
                    timeline.stop();
                }


        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
}
