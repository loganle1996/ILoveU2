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
    
    public void loadCache(GameModel gameModel){
        Wall1 wall1 = new Wall1(40, 40, 40, 40, true, Id.wall,tileHandler , "wall1");
        //Wall1 wall1 = new Wall1(true, Id.wall);
        wall1.setType("wall1");
        tileTable.put(wall1.getType(), wall1);
        
        Wall2 wall2 = new Wall2(40, 40, 40, 40, true, Id.wall,tileHandler, "wall2");
        //Wall2 wall2 = new Wall2(true, Id.wall);
        wall2.setType("wall2");
        tileTable.put(wall2.getType(), wall2);
        
        Wall3 wall3 = new Wall3(40, 40, 40, 40, true, Id.wall, tileHandler, "wall3");
        //Wall3 wall3 = new Wall3(true, Id.wall);
        wall3.setType("wall3");
        tileTable.put(wall3.getType(), wall3);
    }
    public static TileCache getInstance(){
        return tileCache;
    }
}
