import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Board extends ImageView {

    Tiles tiles;
    static Tiles[][] ground = new Tiles[Game.Mapheight][Game.Mapwidth];
    static Board[][] groundg = new Board[Game.Mapheight][Game.Mapwidth];
    StringBuilder groundjson = new StringBuilder();
    int gr;

    public Board() {
    }

    public Board(Image grounds) {
        super(grounds);
    }

    public void generate(int height, int width){
       int vertical = height/32;
       int horizontal = width/32;
        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical ; j++) {
                tiles = Tiles.ground1;
                Board board = new Board(tiles.image);
                ground[i][j] = tiles;
                groundg[i][j] = board;
                Game.rootboard.add(board,i,j);
            }
        }


        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                int b = (int) (Math.random()*30);
                if (b==9){
                    tiles= Tiles.water;
                    Board board = new Board(tiles.image);
                    ground[i][j] = tiles;
                    groundg[i][j] = board;
                    Game.rootboard.add(board,i,j);
                    int c = (int) (Math.random()*2);
                    if (c==1 && i!=vertical){
                        Game.rootgame.getChildren().remove(ground[i+1][j]);
                        Board board1 = new Board(tiles.image);
                        ground[i+1][j] = tiles;
                        groundg[i+1][j] = board1;
                        Game.rootboard.add(board1,i+1,j);
                    }
                    int d = (int) (Math.random()*2);
                    if (d==1 && i!=0){
                        Game.rootgame.getChildren().remove(ground[i-1][j]);
                        Board board2 = new Board(tiles.image);
                        ground[i-1][j] = tiles;
                        groundg[i-1][j] = board2;
                        Game.rootboard.add(board2,i-1,j);

                    }
                    int e = (int) (Math.random()*2);
                    if (e==1 && j!=horizontal){
                        Game.rootgame.getChildren().remove(ground[i][j+1]);
                        Board board3 = new Board(tiles.image);
                        ground[i][j+1] = tiles;
                        groundg[i][j+1] = board3;
                        Game.rootboard.add(board3,i,j+1);
                    }
                    int f = (int) (Math.random()*2);
                    if (f==1 && j!=0){
                        Game.rootgame.getChildren().remove(ground[i][j-1]);
                        Board board4 = new Board(tiles.image);
                        ground[i][j-1] = tiles;
                        groundg[i][j-1] = board4;
                        Game.rootboard.add(board4,i,j-1);
                    }
                }
            }
        }
        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                int g = (int) (Math.random()*1000);
                if (g==10){
                    tiles = Tiles.ground2;
                    Board board = new Board(tiles.image);
                    ground[i][j] = tiles ;
                    groundg[i][j] = board;
                    Game.rootboard.add(board,i,j);
                }

            }
        }

        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                if (groundg[j][i].getImage().equals(Tiles.ground1.image) ){
                    groundjson.append("1");
                } else if (groundg[j][i].getImage().equals(Tiles.ground2.image) ) {
                    groundjson.append("2");
                }
                else {
                    groundjson.append("3");
                }

            }
        }
        System.out.println(groundjson);


    }
    public static int onGround(int xPlayer, int yPlayer){
        double xRootChange = Game.rootboard.getLayoutX();
        double yRootChange = Game.rootboard.getLayoutY();
        double xFarPlayer = xPlayer-xRootChange;
        double yFarPlayer = yPlayer-yRootChange;


        Board which = groundg[(int) (xFarPlayer/32)][(int) (yFarPlayer/32)];
        if (which.getImage().equals(Tiles.ground1.image)){
            return 0;
        }
        if (which.getImage().equals(Tiles.ground2.image)){
            return 5;
        }
        if (which.getLayoutX()==xFarPlayer && which.getImage().equals(Tiles.water.image)){
            return 2;
        }
        if (which.getLayoutX()+32==xFarPlayer+1 && which.getImage().equals(Tiles.water.image)){
            return 4;
        }
        if ( which.getLayoutY()==yFarPlayer && which.getImage().equals(Tiles.water.image)){
            return 1;
        }
        if ( which.getLayoutY()+32==yFarPlayer+1 && which.getImage().equals(Tiles.water.image)){
            return 3;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Board{" +
                " tiles=" + tiles +
                '}';
    }

    public void read(int height, int width) {

        int vertical = height/32;
        int horizontal = width/32;
        int count = 0;


        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal  ; j++) {
                if (groundjson.charAt(count) == '1'){
                    Board board = new Board(Tiles.ground1.image);
                    groundg[j][i] = board;
                    ground[j][i]= Tiles.ground1;
                    Game.rootboard.add(board,j,i);
                }
                if (groundjson.charAt(count)== '2'){
                    Board board = new Board(Tiles.ground2.image);
                    groundg[j][i] = board;
                    ground[j][i]= Tiles.ground2;
                    Game.rootboard.add(board,j,i);
                }
                if (groundjson.charAt(count)== '3'){
                    Board board = new Board(Tiles.water.image);
                    groundg[j][i] = board;
                    ground[j][i]= Tiles.water;
                    Game.rootboard.add(board,j,i);
                }
                count++;

            }
        }

//        for (int i = 0; i < horizontal; i++) {
//            for (int j = 0; j < vertical; j++) {
//                int g = (int) (Math.random()*1000);
//                if (g==10){
//                    Board board = new Board(ground[i][j].image);
//                    ground[i][j] = tiles;
//                    Game.rootboard.add(board,i,j);
//                }
//
//            }
//        }
    }
//    public void move(){
//         timeline = new Timeline(new KeyFrame(Duration.millis(5),event -> {
//            if (Game.up) {
//            Game.rootboard.setLayoutY(Game.rootboard.getLayoutY()+0.05);
//            }
//            if (Game.down) {
//                Game.rootboard.setLayoutY(Game.rootboard.getLayoutY()-0.05);
//            }
//            if (Game.right) {
//               Game.rootboard.setLayoutX(Game.rootboard.getLayoutX()-0.05);
//            }
//            if (Game.left) {
//                Game.rootboard.setLayoutX(Game.rootboard.getLayoutX()+0.05);
//            }
//        }));
//         timeline.setCycleCount(Animation.INDEFINITE);
//         timeline.play();
//
//    }
//    public void starttomove() {
//        for (int i = 0; i < Game.Mapwidth/32; i++) {
//            for (int j = 0; j < Game.Mapheight/32; j++) {
//                ground[j][i].move();
//            }
//        }
//    }
}
