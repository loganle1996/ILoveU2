/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;


import Entity.Id;
import Entity.Player;
import Goomba.AIenemy;
import MVCpattern.GameModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author apple
 */
public class GameMap 
{
    public int map[][];
    public ImageView image;
    public static Image character;
    static int xCoordinate = 0, yCoordinate = 0;
    static int squareSize = 40;
    public Tile clonedTile;
    public Tile clonedTile1,clonedTile2,clonedTile3;
    public Player player1;
    public AIenemy enemy1;
    
    //contructor
    public GameMap(){
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
                {0,1,10,1,1,1,11,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,2,2,2,0},
                {0,2,2,2,2,2,2,2,2,2,2,6,6,6,6,6,2,2,2,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    }
    
    public void addAllObjectsToGameModel(GameModel gameModel, TileCache tileCache){
        int count = 1;
        for (int i = 0; i< 400; i++) {

            int location =  map[i/20][i%20];
            switch (location) {
                case 0:
                    clonedTile1 = (Tile) tileCache.getTile("wall1");
                    clonedTile1.setX((i%20) * clonedTile1.getWidth());
                    clonedTile1.setY((i/20) * clonedTile1.getWidth());
                    gameModel.addTile(clonedTile1);
                    break;
                case 1:
                    break;
                case 2:
                    clonedTile2 = (Tile) tileCache.getTile("wall2");
                    clonedTile2.setX((i%20) * clonedTile2.getWidth());
                    clonedTile2.setY((i/20) * clonedTile2.getWidth());
                    gameModel.addTile(clonedTile2);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    clonedTile2 = (Tile) tileCache.getTile("wall2");
                    clonedTile2.setX((i%20) * clonedTile2.getWidth());
                    clonedTile2.setY((i/20) * clonedTile2.getWidth());
                    gameModel.addTile(clonedTile2);
                    break;
                case 6:
                    break;
                case 9:
                    clonedTile3 = (Tile) tileCache.getTile("wall3");
                    clonedTile3.setX((i%20) * clonedTile3.getWidth());
                    clonedTile3.setY((i/20) * clonedTile3.getWidth());
                    gameModel.addTile(clonedTile3);
                    break;
                case 10:
                    player1 = new Player((i% 20)*40,(i/20)*40, 40, 40, true, Id.player, gameModel);
                    gameModel.addEntity(player1);
                    break;
                case 11:
                    enemy1 = new AIenemy((i% 20)*40,(i/20)*40, 40, 40, true, Id.Goomba, gameModel);
                    gameModel.addEntity(enemy1);
                default:
                
            }
      }
        
   }

}
