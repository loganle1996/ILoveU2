/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import MVCpattern.GameModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author owne
 */
public class AIenemy extends Entity {

    public AIenemy(int x, int y, int width, int height, boolean solid, Id id, GameModel gameModel) {
        super(x, y, width, height, solid, id, gameModel);
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(this.getX(), this.getY(), width, height);
    }

    @Override
    public void tick() {
        
    }
    
}
