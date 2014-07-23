/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities.security;

/**
 *
 * @author Artyom
 */
public final class Key {
    
    private final int size = 8;
    private int count = 0;
    private final byte[] bytes = new byte[size];

    public Key() {
    }
    
    public void add(int i){
        if(count < size){
            bytes[count] = (byte)i;
            count++;
        }
    }
    
    public byte[] getBytes(){
        return bytes;
    }
}
