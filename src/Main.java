
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    static int Height = 800;
    static int Width =1200;
    static AnchorPane root = new AnchorPane();
    static boolean working = false ;
    static Stage stage;
    static Scene Mainscene;
    static Gameover endscreen = new Gameover();

    public static void main(String[] args) {
        DBcontroler.connect();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root,Width,Height);
        Mainscene = scene;
        stage = primaryStage;
        Button newgame = new Button("newgame");
        Button ccontinue = new Button("continue");
        newgame.setLayoutY(Height/2);
        newgame.setLayoutX(Width/2);
        root.getChildren().addAll(newgame, ccontinue);
        newgame.setOnAction(event -> {
            stage.close();
            Game game = new Game();
            game.show();
        });
        ccontinue.setOnAction(event -> {
            stage.close();
            Game game = new Game();
            game.read();
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}