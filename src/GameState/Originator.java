/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Bullet.BulletHandler;
import Entity.EntityHandler;
import tile.TileHandler;

/**
 *
 * @author khoinguyen
 */
public class Originator {
    private TileHandler tileState;
    private BulletHandler bulletState;
    private EntityHandler entityState;
    
    public TileHandler getTileState(){
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
    public Memento saveStateMemento(){
        return new Memento(tileState, bulletState, entityState);
    }
    //Accessors and mutators
    public void setEntityState(EntityHandler entityState) {
        this.entityState = entityState;
    }

    public void getStateFromMemento(Memento memento) {
        tileState = memento.getTileState();
        bulletState = memento.getBulletState();
        entityState = memento.getEntityState();
    }
}
