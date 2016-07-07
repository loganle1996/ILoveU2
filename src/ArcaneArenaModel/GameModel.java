/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArenaModel;

import ArcaneArenaModel.Id;
import ArcaneArenaModel.Player;
import ArcaneArenaModel.Entity;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author owne
 */
public class GameModel {
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();
    
    public GameModel(){

    }
    
    public void addEntity(Entity en){
        entity.add(en);
    }
    public void removeEntity(Entity en){
        entity.remove();
    }
    public void addTile(Tile ti){
        tile.add(ti);
    }

    public LinkedList<Entity> getEntity() {
        return entity;
    }

    public void setEntity(LinkedList<Entity> entity) {
        this.entity = entity;
    }

    public LinkedList<Tile> getTile() {
        return tile;
    }

    public void setTile(LinkedList<Tile> tile) {
        this.tile = tile;
    }

    public void removeTile(Tile ti) {
        tile.remove(ti);
    }
    public LinkedList<Tile> getTileList(){
        return tile;
    }
    public void updateModelGame(){
        for(Entity en: entity){
            en.tick();
        }
        for(Tile ti: tile){
            ti.tick();
        }
    }
    
    public void renderModelGame(GraphicsContext g,Image image){
        for(Entity en: entity){
            en.render(g,image);
        }
        for(Tile ti: tile){
            ti.render(g,image);
        }
    }
}
