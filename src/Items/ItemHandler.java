/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author LoganLe
 */
public class ItemHandler {
    public LinkedList<Item> items = new LinkedList<Item>();
    public LinkedList<Item> copiedItems;
    private static ItemHandler itemHandler = new ItemHandler();

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }


    public static void setItemHandler(ItemHandler itemHandler) {
        ItemHandler.itemHandler = itemHandler;
    }
    
    public void tickItems(long currentime){
        copiedItems = new LinkedList<Item>(items);
        for(Item item: copiedItems){
            item.tick(currentime);
        }
    }
    public void renderItems(GraphicsContext gc){
        copiedItems = new LinkedList<Item>(items);
        for(Item item: copiedItems){
            item.render(gc);
        }
    }
    
    //add and remove objects
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }
    public static ItemHandler getInstance(){
        return itemHandler;
    }
}
