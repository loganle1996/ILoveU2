/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import View.GameView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author apple
 */
public class TiledMap 
{
    public int map[][];
    public ImageView image;
    public static Image character;
    static int xCoordinate = 0, yCoordinate = 0;
    static int squareSize = 40;
    public Tile clonedTile;
    public Tile clonedTile1,clonedTile2,clonedTile3;
    
    //contructor
    public TiledMap(){
    }
    public void mapData()
    {
        map = new int [][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,9,9,9,9,9,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,9,9,9,9,9,1,1,1,1,1,1,1,1,0},
                {0,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,0},
                {0,3,1,1,1,1,1,1,1,1,9,9,9,9,9,1,1,1,4,0},
                {0,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,0},
                {0,3,1,1,1,1,9,9,1,1,1,1,1,1,1,1,1,1,4,0},
                {0,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,0},
                {0,1,1,9,9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,9,9,9,9,9,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,2,2,2,0},
                {0,2,2,2,2,2,2,2,2,2,2,6,6,6,6,6,2,2,2,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    }
    
        public void renderMapFromData(GraphicsContext gc){
        int count = 1;
        for (int i = 0; i< 400; i++) {

            int location =  map[i/20][i%20];
            switch (location) {
                case 0:
                    clonedTile1 = (Wall1) TileCache.getTile("wall1");
                    clonedTile1.render(gc,(i%20) * clonedTile1.getWidth(), (i / 20) * clonedTile1.getHeight());
//                    clonedTile1.setX((i%20) * clonedTile1.getWidth());
//                    clonedTile1.setY((i/20) * clonedTile1.getHeight());
////                    clonedTile.getBoundary();
//                    //clonedTile1.setGameModel(GameView.gameModel);
//                    GameView.gameModel.addTile(clonedTile1);
                    break;
                case 1:
                    gc.setFill(Color.WHITE);
                    gc.fillRect((i%20) * squareSize, (i / 20) * squareSize, 40, 40);
                    break;
                case 2:
                    clonedTile2 = (Wall2) TileCache.getTile("wall2");
                    clonedTile2.render(gc,(i%20) * clonedTile2.getWidth(), (i / 20) * clonedTile2.getHeight());
//                    clonedTile2.setX((i%20) * clonedTile2.getWidth());
//                    clonedTile2.setY((i/20) * clonedTile2.getHeight());
////                    clonedTile.getBoundary();
//                    //clonedTile2.setGameModel(GameView.gameModel);
//                    GameView.gameModel.addTile(clonedTile2);

                    break;
                case 3:
                    gc.setFill(Color.BLACK);
                    gc.fillRect((i%20) * squareSize, (i / 20) * squareSize, 40, 40);
                    break;
                case 4:
                    gc.setFill(Color.BLACK);
                    gc.fillRect((i%20) * squareSize, (i / 20) * squareSize, 40, 40);
                    break;
                case 5:
                    clonedTile2 = (Wall2) TileCache.getTile("wall2");
                    clonedTile2.render(gc,(i%20) * clonedTile2.getWidth(), (i / 20) * clonedTile2.getHeight());
//                    clonedTile2.setX((i%20) * clonedTile2.getWidth());
//                    clonedTile2.setY((i/20) * clonedTile2.getHeight());
                    //clonedTile2.setGameModel(GameView.gameModel);
//                    GameView.gameModel.addTile(clonedTile2);
//                    clonedTile.getBoundary();
                    break;
                case 6:
                    gc.setFill(Color.BLACK);
                    gc.fillRect((i%20) * squareSize, (i / 20) * squareSize, 40, 40);
                    break;
                case 9:
                    clonedTile3 = (Wall3) TileCache.getTile("wall3");
                    clonedTile3.render(gc,(i%20) * clonedTile3.getWidth(), (i / 20) * clonedTile3.getHeight());
//                    clonedTile3.setX((i%20) * clonedTile3.getWidth());
//                    clonedTile3.setY((i/20) * clonedTile3.getHeight());
////                    clonedTile.getBoundary();
//                    //clonedTile3.setGameModel(GameView.gameModel);
//                    GameView.gameModel.addTile(clonedTile3);
                    break;
                case 10:
                    //gc.drawImage(character, (i%20) * squareSize, (i / 20) * squareSize,squareSize, squareSize);
                    break;
                default:
                
            }
        }
        
    }
        public void createBoundaryForMap(){
        int count = 1;
        for (int i = 0; i< 400; i++) {

            int location =  map[i/20][i%20];
            switch (location) {
                case 0:
                    clonedTile = (Tile) TileCache.getTile("wall1");
                    clonedTile.setX((i%20) * clonedTile.getWidth());
                    clonedTile.setY((i/20) * clonedTile.getWidth());
                    GameView.gameModel.addTile(new Wall1((i%20) * clonedTile.getWidth(), (i/20) * clonedTile.getHeight(), clonedTile.getWidth(), clonedTile.getHeight(), true, Id.wall,GameView.gameModel,"wall1"));
                    //GameView.gameModel.addTile(clonedTile);
                    break;
                case 1:
                    break;
                case 2:
                    clonedTile = (Tile) TileCache.getTile("wall2");
                    clonedTile.setX((i%20) * clonedTile.getWidth());
                    clonedTile.setY((i/20) * clonedTile.getWidth());
                    GameView.gameModel.addTile(new Wall2((i%20) * clonedTile.getWidth(), (i/20) * clonedTile.getHeight(), clonedTile.getWidth(), clonedTile.getHeight(), true, Id.wall,GameView.gameModel,"wall2"));
                    //GameView.gameModel.addTile();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    clonedTile = (Tile) TileCache.getTile("wall2");
                    clonedTile.setX((i%20) * clonedTile.getWidth());
                    clonedTile.setY((i/20) * clonedTile.getWidth());
                    GameView.gameModel.addTile(new Wall2((i%20) * clonedTile.getWidth(), (i/20) * clonedTile.getHeight(), clonedTile.getWidth(), clonedTile.getHeight(), true, Id.wall,GameView.gameModel,"wall2"));
                   // GameView.gameModel.addTile(clonedTile);
                    break;
                case 6:
                    break;
                case 9:
                    clonedTile = (Tile) TileCache.getTile("wall3");
                    clonedTile.setX((i%20) * clonedTile.getWidth());
                    clonedTile.setY((i/20) * clonedTile.getWidth());
                    GameView.gameModel.addTile(new Wall3((i%20) * clonedTile.getWidth(), (i/20) * clonedTile.getHeight(), clonedTile.getWidth(), clonedTile.getHeight(), true, Id.wall,GameView.gameModel,"wall3"));
                    //GameView.gameModel.addTile(clonedTile);
                    break;
                case 10:
                    //gc.drawImage(character, (i%20) * squareSize, (i / 20) * squareSize,squareSize, squareSize);
                    break;
                default:
                
            }
      }
        
   }


}
