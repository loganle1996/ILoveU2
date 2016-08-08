/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tile;

import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author khoinguyen
 */
public class TileHandler {
    public LinkedList<Tile> tile = new LinkedList<Tile>();
    private static TileHandler tileHandler = new TileHandler();
    //private constructor
    private TileHandler(){
        
    }
    //add and remove tiles
    public void addTile(Tile ti){
        tile.add(ti);
    }
    public void removeTile(Tile ti){
        tile.remove();
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
        for(Tile ti: tile){
            ti.tick();
        }
    }
    //render
    public void renderTiles(GraphicsContext g){
        for(Tile ti: tile){
            ti.render(g);
        }
    }
    //get instance
    public static TileHandler getInstance(){
        return tileHandler;
    }
}
