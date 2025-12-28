import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
public class Player extends ImageView {
    int Hp = 100;
    int Mp;
    int Lvl = 0;
    int Xp = 1000;
    int atk;

    Button b = new Button();

    public void setHp(int hp) {
        Hp = hp;
    }
    List<Item> items = new ArrayList<>();
    public Player() {
        Game.rootgame.getChildren().add(b);
        b.setLayoutX(600);
        b.setLayoutY(400);
        recalculateStats();
    }

    public void recalculateStats() {
        atk = 50; // Base attack
        Hp = getHp(); // Base defense
        for (Item item : items) {
            atk += item.atk;
            Hp += item.def;
        }
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
            Game.shop.toggle(true);
        }
    }
    public void takeDamage(int damage) {
        this.Hp -= damage;
        if (this.Hp <= 0) {
            new Gameover().show();
        }
    }
    public int getHp() {
        return Hp;
    }

    public int getXp() {
        return Xp;
    }
}