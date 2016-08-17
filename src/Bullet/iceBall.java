/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullet;

import Entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author khoinguyen
 */
public class iceBall extends Bullet{

    public iceBall(double x, double y, BulletHandler bulletHandler, boolean flyingLeft) {
        super(x, y, bulletHandler, flyingLeft);
    }

    @Override
    public void renderBullet(GraphicsContext gc, Entity en, Image imageLeft, Image imageRight) {
        
    }

    @Override
    public void tick() {
        
    }
    
}
