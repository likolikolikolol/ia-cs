import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item extends ImageView {
    public enum ItemType {
        WEAPON,
        ARMOR,
        POTION
    }

    int atk;
    int defense;
    int healAmount;
    int price;
    ItemType type;

    public Item(ItemType type, int level) {
        super();
        this.type = type;
        switch (type) {
            case WEAPON:
                this.atk = 5 * level;
                this.defense = 0;
                this.healAmount = 0;
                setImage(new Image("file:ground.png"));
                break;
            case ARMOR:
                this.atk = 0;
                this.defense = 5 * level;
                this.healAmount = 0;
                setImage(new Image("file:ground.png"));
                break;
            case POTION:
                this.atk = 0;
                this.defense = 0;
                this.healAmount = 10 * level;
                setImage(new Image("file:ground.png"));
                break;
        }
        this.price = calculatePrice();
        setFitWidth(40);
        setFitHeight(40);
    }

    private int calculatePrice() {
        return (atk * 10) + (defense * 10) + (healAmount * 5);
    }
}