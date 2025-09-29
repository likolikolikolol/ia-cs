import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.List;


public class Game {
    public static boolean up;
    public static boolean down;
    public static boolean right;
    public static boolean left;
    public static AnchorPane rootgame = new AnchorPane();
    public static GridPane rootboard = new GridPane();
    public static int Mapheight = 3200;
    public static int Mapwidth = 3200;
    public Timeline moving = new Timeline();
    public static Board board = new Board();
    private Timeline    timeline;
    static Player player = new Player();
    Shoot shoot;
    Equipment equipment = new Equipment();
    private List<Enemy> enemies = new ArrayList<>();


    public void show() {
        Stage stage = new Stage();
        Scene a = new Scene(rootgame, Main.Width, Main.Height);
        board.generate(Mapheight, Mapwidth);
        spawnEnemies(100);
        Button endgame = new Button("end");
        rootgame.getChildren().add(1,endgame);
        rootgame.getChildren().add(0,rootboard);

        endgame.setOnAction(event -> {
            stage.close();
            DBcontroler.addtodb(String.valueOf(board.groundjson),"kol", 2, 3,4);
        });

        rootboard.setOnMousePressed(event -> {
            shoot= new Shoot(player.atk, event.getX(), event.getY(), enemies);
        });


        a.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                left = true;

            }
            if (event.getCode() == KeyCode.D) {
                right = true;

            }
            if (event.getCode() == KeyCode.W) {
                up = true;

            }
            if (event.getCode() == KeyCode.S) {
                down = true;

            }
            if (event.getCode() == KeyCode.E) {
                equipment.toggle();
                equipment.setItems(player);
            }
        });
        a.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.A) {
                left = false;

            }
            if (event.getCode() == KeyCode.D) {
                right = false;

            }
            if (event.getCode() == KeyCode.W) {
                up = false;

            }
            if (event.getCode() == KeyCode.S) {
                down = false;

            }
        });
        move();

        stage.setScene(a);
        stage.show();
    }

    private void spawnEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; i++) {
            int x = (int) (Math.random() * Mapwidth);
            int y = (int) (Math.random() * Mapheight);
            enemies.add(new Enemy(x, y));
        }
    }

    public void move(){

        timeline = new Timeline(new KeyFrame(Duration.millis(100),event -> {
            double xRootChange = Game.rootboard.getLayoutX();
            double yRootChange = Game.rootboard.getLayoutY();
            double playerX = rootboard.getLayoutX() + 600 - xRootChange;
            double playerY = rootboard.getLayoutY() + 400 - yRootChange;
            player.onGround();
            if (Game.up) {
                rootboard.setLayoutY(Game.rootboard.getLayoutY()+2);
                for (Enemy enemy : enemies) {
                    if (enemy.distance(playerX, playerY)>200) {
                        enemy.setLayoutY(enemy.getLayoutY() + 2);
                    }
                }
            }
            if (Game.down) {
                rootboard.setLayoutY(Game.rootboard.getLayoutY()-2);
                for (Enemy enemy : enemies) {
                    if (enemy.distance(playerX, playerY)>200) {
                        enemy.setLayoutY(enemy.getLayoutY() - 2);
                    }
                }
            }
            if (Game.right) {
                rootboard.setLayoutX(Game.rootboard.getLayoutX()-2);
                for (Enemy enemy : enemies) {
                    if (enemy.distance(playerX, playerY)>200) {
                        enemy.setLayoutX(enemy.getLayoutX() - 2);
                    }
                }
            }
            if (Game.left) {
                rootboard.setLayoutX(Game.rootboard.getLayoutX()+2);
                for (Enemy enemy : enemies) {
                    if (enemy.distance(playerX, playerY)>200) {
                    enemy.setLayoutX(enemy.getLayoutX()+2);
                    }
                }
            }

            for (Enemy enemy : enemies) {
                enemy.move(playerX, playerY);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void read()  {
        Stage stage = new Stage();
        Scene a = new Scene(rootgame, Main.Width, Main.Height);
        DBcontroler.getgroundjson();
        board.read(Mapheight, Mapwidth);
        rootgame.getChildren().add(0,rootboard);


        a.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                left = true;

            }
            if (event.getCode() == KeyCode.D) {
                right = true;

            }
            if (event.getCode() == KeyCode.W) {
                up = true;

            }
            if (event.getCode() == KeyCode.S) {
                down = true;

            }
            if (event.getCode() == KeyCode.E) {
                equipment.toggle();
                equipment.setItems(player);
            }
        });

        rootboard.setOnMousePressed(event -> {
            shoot= new Shoot(player.atk, event.getX(), event.getY(), enemies);
        });


        a.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.A) {
                left = false;

            }
            if (event.getCode() == KeyCode.D) {
                right = false;

            }
            if (event.getCode() == KeyCode.W) {
                up = false;

            }
            if (event.getCode() == KeyCode.S) {
                down = false;

            }
        });
        move();

        stage.setScene(a);
        stage.show();

    }
}