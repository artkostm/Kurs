/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.model.entities.security;

/**
 *
 * @author Development
 */
public class test {
    public static void main(String[] args){
        StringCrypter sc = new StringCrypter();
        String target = "061994artemkossovo";
        String encript = sc.encrypt(target);
        String decript = sc.decrypt(encript);
        
        System.out.println("Target: "+target);
        System.out.println("Encript: "+encript);
        System.out.println("Decript: "+decript);
    }
}
