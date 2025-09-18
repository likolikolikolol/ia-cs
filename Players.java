import javafx.scene.image.Image;

public enum Players {
    elf, dwarf, human;
    public Image player(){
        if (this == elf){
            return new Image("file:ground.png");
        }
        if (this == dwarf){
            return new Image("file:ground_1.png");
        }
        if (this==human) {
            return new Image("file:human.png");
        }
        return new Image("file;");
    }
}
