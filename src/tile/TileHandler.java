/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author khoinguyen
 */
public class TileHandler implements Cloneable{
    public LinkedList<Tile> tile = new LinkedList<Tile>();
    public LinkedList<Tile> copiedTile;

    private static TileHandler tileHandler = new TileHandler();
    //clone
    @Override
    public Object clone(){
      Object clone = null;

      try {
         clone = super.clone();

      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }

      return clone;
    }

    //private constructor
    private TileHandler()
    {

    }

    //add and remove tiles
    public void addTile(Tile ti){
        tile.add(ti);
    }
    public void removeTile(Tile ti){
        tile.remove(ti);
    }
    // Accessors and mutators
    public LinkedList<Tile> getTile() {
        return tile;
    }

    public void setTile(LinkedList<Tile> tile) {
        this.tile = tile;
    }
    //tick
    public void tickTiles(){
        copiedTile = new LinkedList<>(tile);
        for(Tile ti: copiedTile){
            ti.tick();
        }
    }
    //render
    public void renderTiles(GraphicsContext g){
        copiedTile = new LinkedList<>(tile);
        for(Tile ti: copiedTile){
            ti.render(g);
        }
    }
    //edit instance
    public void editInstance(TileHandler tileHandler){
        this.tileHandler = tileHandler;
    }
    //get instance
    public static TileHandler getInstance(){
        return tileHandler;
    }
}
