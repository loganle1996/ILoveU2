/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.util.Hashtable;

/**
 *
 * @author LoganLe
 */
public class ItemCache {
    private static Hashtable<String,Item> itemTable = new Hashtable<String,Item>();
    private static ItemCache itemCache = new ItemCache();
    private ItemHandler itemHandler = ItemHandler.getInstance();

    private ItemCache(){

    }

    public Item getItem(String type){
        Item cachedItem = itemTable.get(type);
        return(Item) cachedItem.clone();
    }
    public void loadCache(){
        FireRune fireRune = new FireRune(itemHandler);
        AirRune airRune = new AirRune(itemHandler);
        WaterRune waterRune = new WaterRune(itemHandler);
        EarthRune earthRune = new EarthRune(itemHandler);

        itemTable.put(fireRune.getItemType(), fireRune);
        itemTable.put(waterRune.getItemType(), waterRune);
        itemTable.put(airRune.getItemType(), airRune);
        itemTable.put(earthRune.getItemType(), earthRune);
    }
    public static ItemCache getInstance(){
        return  itemCache;
    }
}
