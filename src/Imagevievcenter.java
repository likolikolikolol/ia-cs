import javafx.scene.image.ImageView;

public class Imagevievcenter {
    ImageView image = new ImageView();

    public Imagevievcenter(ImageView image) {
        this.image = image;
    }
    public double centerX(){
        return image.getX()/2;
    }
    public double centerY(){
        return image.getY()/2;
    }
}
