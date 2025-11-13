import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shop {
    public GridPane equipmentgridpane = new GridPane();
    private StackPane[][] cells = new StackPane[4][4];
    boolean isVisible = false;

    public Shop() {
        equipmentgridpane.setVisible(false);
        this.equipmentgridpane = equipmentgridpane;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] != null && !cells[i][j].getChildren().isEmpty()) {
                    cells[i][j].getChildren().removeIf(node -> node instanceof Item);
                }
            }
        }
        for (int i = 0; i < 4; i++) { // row
            for (int j = 0; j < 4; j++) { // column
                StackPane cell = new StackPane();
                Rectangle border = new Rectangle(50, 50);
                border.setFill(Color.GRAY);
                border.setStroke(Color.BLACK);
                cell.getChildren().add(border);
                cells[i][j] = cell;
                equipmentgridpane.add(cell, j, i);
            }
        }
        Game.rootgame.getChildren().addAll(equipmentgridpane);
        for (int i = 0; i < 16; i++) {
            if (i < 16) {
                int row = i / 4;
                int col = i % 4;
                cells[row][col].getChildren().add(generaterandomequipment());
            }
        }
    }

    public Item generaterandomequipment (){
        int atk = (int) (Math.random() * 10);
        int def = (int) (Math.random() * 10);
        int price = (int) (Math.random() * 100);
        Item item = new Item(atk, def, price);

        Tooltip tooltip = new Tooltip("Atk: " + atk + "\nDef: " + def + "\nPrice: " + price);
        Tooltip.install(item, tooltip);

        item.setOnMouseClicked(event -> {
            Game.player.items.add(item);
            Game.player.recalculateStats();
        });
        return item;
    }
    public void toggle(boolean visible) {

        equipmentgridpane.setVisible(visible);
        isVisible=visible;

    }

}
