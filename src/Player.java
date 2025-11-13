import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Player extends ImageView {
    int Hp = 100;
    int Mp;
    int Lvl = 0;
    int Xp = 0;
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
        if (items.size() > 1 && items.get(1) != null) {
            atk = 50 + items.get(1).atk;
        }
    }
    public void onGround() {
        int groundType = Board.onGround(600, 400);

        if (groundType == 3) {
            Game.up = false;
        }
        if (groundType == 4) {
            Game.left = false;
        }
        if (groundType == 1) {
            Game.down = false;
        }
        if (groundType == 2) {
            Game.right = false;
        }

        if (groundType == 5) {
            Game.shop.toggle(true);
        } else {
            if (Game.shop.isVisible) {
                Game.shop.toggle(false);
            }
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
}
