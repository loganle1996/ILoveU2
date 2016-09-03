/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import Entity.Id;
import MVCpattern.GameModel;
import MVCpattern.GameView;
import java.util.Hashtable;
//import tile.WoodBridge;
/**
 *
 * @author owne
 */
public class TileCache {
    private static Hashtable<String,Tile> tileTable = new Hashtable<String,Tile>();
    private static TileCache tileCache = new TileCache();
    private TileHandler tileHandler = TileHandler.getInstance();
    
    //Make this contructor private, so it wont be able to be instatiated 
    private TileCache(){
        
    }
    public Tile getTile(String type){
        Tile cachedTile = tileTable.get(type);
        return(Tile) cachedTile.clone();
    }
    
    public void loadCache(){
        Wall1 wall1 = new Wall1(40, 40, 40, 40, true, Id.wall,tileHandler , "wall1");
        wall1.setType("wall1");
        tileTable.put(wall1.getType(), wall1);
        
        Wall2 wall2 = new Wall2(40, 40, 40, 40, true, Id.wall,tileHandler, "wall2");
        wall2.setType("wall2");
        tileTable.put(wall2.getType(), wall2);
        
        Wall3 wall3 = new Wall3(40, 40, 40, 40, true, Id.wall, tileHandler, "wall3");
        wall3.setType("wall3");
        tileTable.put(wall3.getType(), wall3);
        
        Trap1 trap1 = new Trap1(40, 40, 40, 40, true, Id.fireTrap, tileHandler, "trap1");
        tileTable.put(trap1.getType(),trap1);
        
        InvisibleWall invisibleWall = new InvisibleWall(40, 40, 40, 40, false, Id.wall, tileHandler, "invisibleWall");
        tileTable.put(invisibleWall.getType(), invisibleWall);
        
        WoodBridge woodBridge = new WoodBridge(true, 40, 40, 40, 40, true, Id.wall, tileHandler, "woodBridge");
        tileTable.put(woodBridge.getType(),woodBridge);
        
        Portal portal = new Portal(40, 40, 40, 40, true, Id.Portal, tileHandler, "portal");
        tileTable.put(portal.getType(), portal);

        Water water = new Water(40, 40, 40, 40, false, Id.wall, tileHandler, "water");
        tileTable.put(water.getType(),water);

        Dwall2 dwall2 = new Dwall2(40, 40, 40, 40, true, Id.wall, tileHandler, "dWall2");
        tileTable.put(dwall2.getType(),dwall2);

    }
    public static TileCache getInstance(){
        return tileCache;
    }
}
