/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Bullet.Bullet;
import Bullet.BulletHandler;
import Entity.Entity;
import Entity.EntityHandler;
import java.util.LinkedList;
import tile.Tile;
import tile.TileHandler;

/**
 *
 * @author khoinguyen
 */
public class Memento{
    private TileHandler tileState;
    private BulletHandler bulletState;
    private EntityHandler entityState;

    //Constructor

    public Memento(TileHandler tileState, BulletHandler bulletState, EntityHandler entityState) {
        this.tileState = tileState;
        this.bulletState = bulletState;
        this.entityState = entityState;
    }

    //Accessors and mutators
    public TileHandler getTileState() {
        return tileState;
    }

    public void setTileState(TileHandler tileState) {
        this.tileState = tileState;
    }

    public BulletHandler getBulletState() {
        return bulletState;
    }

    public void setBulletState(BulletHandler bulletState) {
        this.bulletState = bulletState;
    }

    public EntityHandler getEntityState() {
        return entityState;
    }

    public void setEntityState(EntityHandler entityState) {
        this.entityState = entityState;
    }
    
    
    
}
