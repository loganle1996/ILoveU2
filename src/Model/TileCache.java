/*

import java.util.Hashtable;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.GameView;

import java.util.Hashtable;

/**
 *
 * @author owne
 */
public class TileCache {
    private static Hashtable<String,Tile> tileTable = new Hashtable<String,Tile>();
    public static Tile getTile(String type){
        Tile cachedTile = tileTable.get(type);
        return(Tile) cachedTile.clone();
        //return cachedTile;
    }

    public static void loadCache(){
        Wall1 wall1 = new Wall1(40, 40, 40, 40, true, Id.wall, GameView.gameModel, "wall1");
        wall1.setType("wall1");
        //GameView.gameModel.addTile(wall1);
        tileTable.put(wall1.getType(), wall1);

        Wall2 wall2 = new Wall2(40, 40, 40, 40, true, Id.wall, GameView.gameModel, "wall2");
        wall2.setType("wall2");
        //GameView.gameModel.addTile(wall2);
        tileTable.put(wall2.getType(), wall2);


        Wall3 wall3 = new Wall3(40, 40, 40, 40, true, Id.wall, GameView.gameModel, "wall3");
        wall3.setType("wall3");
        //GameView.gameModel.addTile(wall3);
        tileTable.put(wall3.getType(), wall3);
    }
}
