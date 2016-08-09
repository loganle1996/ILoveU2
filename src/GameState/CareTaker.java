/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoinguyen
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();
    
    public void add(Memento memento){
        mementoList.add(memento);
    }
//    public Memento get(int index){
//        return mementoList.get(index);
//    }
    public Memento get(){
        System.out.println("memmoList size: "+ mementoList.size());
        return mementoList.get(mementoList.size()-1);
    }
}
