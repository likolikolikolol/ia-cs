import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item extends ImageView {
    int atk;

    public Item(int atk) {
        this.atk = atk;
        setImage(new Image("ground.png"));
        setFitWidth(40);
        setFitHeight(40);
    }
}
