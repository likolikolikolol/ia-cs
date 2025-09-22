import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Equipment {
    private GridPane equipmentGrid;
    private boolean isVisible = false;
    private StackPane[][] cells = new StackPane[3][4];

    public Equipment() {
        equipmentGrid = new GridPane();
        equipmentGrid.setPadding(new Insets(10));
        equipmentGrid.setHgap(5);
        equipmentGrid.setVgap(5);
        equipmentGrid.setVisible(false);

        for (int i = 0; i < 3; i++) { // row
            for (int j = 0; j < 4; j++) { // column
                StackPane cell = new StackPane();
                Rectangle border = new Rectangle(50, 50);
                border.setFill(Color.GRAY);
                border.setStroke(Color.BLACK);
                cell.getChildren().add(border);
                cells[i][j] = cell;
                equipmentGrid.add(cell, j, i);
            }
        }

        Game.rootgame.getChildren().add(equipmentGrid);
    }

    public void toggle() {
        isVisible = !isVisible;
        equipmentGrid.setVisible(isVisible);
    }

    public void setItems(Player player) {
        // Clear the grid before adding new items
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j].getChildren().removeIf(node -> node instanceof Item);
            }
        }

        for (int i = 0; i < player.items.size(); i++) {
            if (i < 12) {
                int row = i / 4;
                int col = i % 4;
                cells[row][col].getChildren().add(player.items.get(i));
            }
        }
    }
}