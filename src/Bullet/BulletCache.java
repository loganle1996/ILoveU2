/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullet;

import java.util.Hashtable;
import tile.Tile;

/**
 *
 * @author khoinguyen
 */
public class BulletCache {
    private static Hashtable<bulletType,Bullet> bulletHashtable = new Hashtable<bulletType,Bullet>();
    private static BulletCache bulletCache = new BulletCache();
    private BulletHandler bulletHandler = BulletHandler.getInstance();
    
    private BulletCache(){
        
    }
    
    public Bullet getBullet(bulletType id){
        Bullet cachedBullet = bulletHashtable.get(id);
        return (Bullet) cachedBullet.clone();
    }
    public void loadBulletCache(){
        FireBall fireBall = new FireBall(bulletHandler, bulletType.fireBall);
        bulletHashtable.put(fireBall.getId(),fireBall);
        
        IceBall iceBall = new IceBall(bulletHandler, bulletType.snowBall);
        bulletHashtable.put(iceBall.getId(), iceBall);
        
        SlowSpirit slowSpirit = new SlowSpirit(bulletHandler, bulletType.SlowSpirit);
        bulletHashtable.put(slowSpirit.getId(), slowSpirit);
        
    }
    //get instance
    public static BulletCache getInstance(){
        return bulletCache;
    }
}
