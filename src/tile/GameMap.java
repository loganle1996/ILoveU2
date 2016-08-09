/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;


import Entity.AIenemy;
import Target.AiHouse;
import Entity.Entity;
import Entity.Id;
import Entity.Player;
import MVCpattern.GameModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
    public Trap1 clonedTile4;
    public Player player1 = Player.getInstance();
    public AIenemy aienemy;
    public AiHouse aiHouse;
    public WoodBridge woodBridge;
    Image aiHouseImage = new Image("AiHouse.png");
    
    //contructor
    public GameMap(){
    }
    public void getMapData1()
    {
        map = new int [][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,4,1,1,1,1,1,1,6,6,6,6,6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,3,3,3,3,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,2,2,2,2,1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,11,1,1,1,1,4,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,11,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,6,6,6,6,6,6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,11,1,1,1,1,1,3,3,3,3,3,3,3,3,3,0},
                {0,2,2,2,1,1,1,2,2,2,1,1,1,2,2,2,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,6,6,6,6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,3,3,3,3,1,1,3,3,1,1,3,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,2,2,2,2,1,1,2,2,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,1,1,1,2,2,2,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,0},
                {0,2,2,2,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,0},
                {0,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,2,2,2,1,1,1,2,2,2,1,1,1,2,2,2,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
                {0,1,1,1,3,3,3,1,1,3,3,3,3,1,1,1,3,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}


        };
    }
    
    public void addAllObjectsToGameModel(GameModel gameModel, TileCache tileCache,GraphicsContext gc){
        int count = 1;
        for (int i = 0; i< 1600; i++) {
            int location =  map[i/40][i%40];
            switch (location) {
                case 0:
                    clonedTile1 = (Tile) tileCache.getTile("wall1");
                    clonedTile1.setX((i%40) * clonedTile1.getWidth());
                    clonedTile1.setY((i/40) * clonedTile1.getWidth());
                    gameModel.addTile(clonedTile1);
                    break;
                case 1:
                    gc.setFill(Color.BLACK);
                    gc.fillRect((i%40)*40,(i/40)*40, 40, 40);
                    break;
                case 2:
                    clonedTile2 = (Tile) tileCache.getTile("wall2");
                    clonedTile2.setX((i%40) * clonedTile2.getWidth());
                    clonedTile2.setY((i/40) * clonedTile2.getWidth());
                    gameModel.addTile(clonedTile2);
                    break;
                case 3:
                    clonedTile4 = (Trap1) tileCache.getTile("trap1");
                    clonedTile4.setX((i%40) * clonedTile4.getWidth());
                    clonedTile4.setY((i/40) * clonedTile1.getHeight());
                    clonedTile4.setTrapDirection("up");
                    gameModel.addTile(clonedTile4);
                    break;
                case 4:
                    clonedTile3 = (Tile) tileCache.getTile("invisibleWall");
                    clonedTile3.setX((i%40) * clonedTile3.getWidth());
                    clonedTile3.setY((i/40) * clonedTile3.getHeight());
                    gameModel.addTile(clonedTile3);
                    break;
                case 5:
                    clonedTile2 = (Tile) tileCache.getTile("wall2");
                    clonedTile2.setX((i%40) * clonedTile2.getWidth());
                    clonedTile2.setY((i/40) * clonedTile2.getWidth());
                    gameModel.addTile(clonedTile2);
                    break;
                case 6:
                    woodBridge = (WoodBridge) tileCache.getTile("woodBridge");
                    woodBridge.setX((i%40)*woodBridge.getWidth());
                    woodBridge.setY((i/40)* woodBridge.getHeight());
                    gameModel.addTile(woodBridge);
                    break;
                case 9:
                    clonedTile3 = (Tile) tileCache.getTile("wall3");
                    clonedTile3.setX((i%40) * clonedTile3.getWidth());
                    clonedTile3.setY((i/40) * clonedTile3.getWidth());
                    gameModel.addTile(clonedTile3);
                    break;
                case 10:
                    player1.setX((i% 20)*40);
                    player1.setY((i/40)*40);
                    gameModel.getEntityHandler().addEntity(player1);
                    break;
                case 11:
                    aienemy = new AIenemy((i%40) * 40, (i/40) * 40, 40, 40, true, Id.Goomba, gameModel.getEntityHandler());
                    gameModel.getEntityHandler().addEntity(aienemy);
                    break;
                case 12:
                    aiHouse = new AiHouse((i%40)* 40,(i/40)*40,aiHouseImage);
//                    int size = gameModel.getHouseHandler().getAiHouses().size();
//                    aiHouse.setId(size+1);
                    gameModel.getHouseHandler().addHouse(aiHouse);
                    break;
                default:
                
            }
      }
        
   }


    public Entity returnPlayer1(){
        return player1;
    }

}
