import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Shop {
    public GridPane equipmentgridpane = new GridPane();
    private StackPane[][] cells = new StackPane[2][4];
    boolean isVisible = false;
    private static final Random random = new Random();

    public Shop() {
        equipmentgridpane.setVisible(false);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                StackPane cell = new StackPane();
                Rectangle border = new Rectangle(50, 50);
                border.setFill(Color.GRAY);
                border.setStroke(Color.BLACK);
                cell.getChildren().add(border);
                cells[i][j] = cell;
                equipmentgridpane.add(cell, j, i);
            }
        }
        Game.rootgame.getChildren().add(equipmentgridpane);
    }

    public Item generateRandomItem() {
        Item.ItemType type = Item.ItemType.values()[random.nextInt(Item.ItemType.values().length)];
        int level = random.nextInt(10) + 1;
        Item item = new Item(type, level);

        item.setOnMouseClicked(event -> {
            if (Game.player.Xp >= item.price) {
                Game.player.Xp -= item.price;
                Game.player.items.add(item);
            }
        });
        return item;
    }

    private void populateShop() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] != null) {
                    cells[i][j].getChildren().removeIf(node -> node instanceof Item);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            int row = i / 4;
            int col = i % 4;
            cells[row][col].getChildren().add(generateRandomItem());
        }
    }

    public void toggle(boolean visible) {
        if (visible && !isVisible) {
            populateShop();
        }
        equipmentgridpane.setVisible(visible);
        isVisible = visible;
    }
}