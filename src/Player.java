//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//
//
//public class Player extends ImageView {
//int Hp;
//int Mp;
//int Lvl;
//int Xp;
//int atk;
//    Button b = new Button();
//
//Equipment[][] equipment= new Equipment[10][8];
//    public Player() {
//        Game.rootgame.getChildren().add(b);
//        b.setLayoutX(600);
//        b.setLayoutY(400);
//    }
//    public void onGround(){
//        if (Board.onGround(600,400)==3){
//            Game.up=false;
//        }
//        if (Board.onGround(600,400)==4){
//            Game.left=false;
//        }
//        if (Board.onGround(600,400)==1){
//            Game.down=false;
//        }
//        if (Board.onGround(600,400)==2){
//            Game.right=false;
//        }
//        if (Board.onGround(600,400)==5){
//            new Shop();
//        }
//    }
//
//}
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Player extends ImageView {
    int Hp;
    int Mp;
    int Lvl;
    int Xp;
    int atk;
    Button b = new Button();

    List<Item> items = new ArrayList<>();
    public Player() {
        Game.rootgame.getChildren().add(b);
        b.setLayoutX(600);
        b.setLayoutY(400);
        // Add some sample items
        items.add(new Item(10));
        items.add(new Item(20));
    }
    public void onGround(){
        if (Board.onGround(600,400)==3){
            Game.up=false;
        }
        if (Board.onGround(600,400)==4){
            Game.left=false;
        }
        if (Board.onGround(600,400)==1){
            Game.down=false;
        }
        if (Board.onGround(600,400)==2){
            Game.right=false;
        }
        if (Board.onGround(600,400)==5){
            new Shop();
        }
    }

}