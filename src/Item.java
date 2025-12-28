import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item extends ImageView {
    int atk;
    int def;
    int price;
    boolean used = false;

    public Item(int atk, int def, int price) {
        super();
        this.atk = atk;
        this.def = def;
        this.price = price;
        setImage(new Image("file:ground.png"));
        setFitWidth(40);
        setFitHeight(40);
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}