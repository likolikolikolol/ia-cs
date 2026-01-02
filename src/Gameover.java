import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Gameover {
    private Game game;

    public Gameover(Game game) {
        this.game = game;
    }

    public void show (){
        AnchorPane root = new AnchorPane();
        Stage stage = new Stage();
        Scene a = new Scene(root,Main.Width,Main.Height);

        Button restart = new Button("Restart");
        HBox hBox = new HBox();
        hBox.setLayoutX(600);
        hBox.setLayoutY(400);
        hBox.getChildren().add(restart);
        root.getChildren().add(hBox);

        restart.setOnAction(event -> {
            stage.close();
            game.resetGame();
            game.timeline.play();
        });

        stage.setScene(a);
        stage.show();
    }
}
