import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Tiles {
    ground1(new Image("file:ground.png")),
    ground2(new Image("file:ground_1.png")),
    water(new Image("file:water.png"));

//    public Image ground(){
//        if (this == ground1){
//            return new Image("file:ground.png");
//        }
//        if (this == ground2){
//            return new Image("file:ground_1.png");
//        }
//        if (this==water) {
//            return new Image("file:water.png");
//        }
//        return new Image("file;");
//    }

    final Image image;

    Tiles(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Tiles{" +
                "image=" + image +
                '}';
    }
}
