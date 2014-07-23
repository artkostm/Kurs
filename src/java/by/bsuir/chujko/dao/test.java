/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

import by.bsuir.chujko.model.entities.Dialog;
import by.bsuir.chujko.model.entities.Message;
import by.bsuir.chujko.model.entities.Tag;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Artyom
 */
public class test {
    
    static ExecutorService es = Executors.newFixedThreadPool(15);
    private final static ScheduledExecutorService scheduler 
            = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread th = new Thread(r);
                th.setDaemon(true);
                return th;
            }
        });
    
    public static void main(String [] args){
        
        final DAOManager factory = DAOManager.getInstance();
        Dialog d = factory.getDialogDAO().getEntityById(6);
        for(Message m : d.getMessages()){
            System.out.println(m);
        }
        System.out.println(d.getNote());
//        Security s = DAOManager.getInstance().getSecurityDAO()
//                .getSecurityByEP("ar@tut.by", "1234567");
//        System.out.println(s);
//        List<Developer> list = factory.getDeveloperDAO().getAll();
//            System.out.println(list.size());
//            for(Developer d : list){
//                System.out.println(d);
//            }
//            Developer dev = factory.getDeveloperDAO().getEntityById(2);
//            System.err.println(dev);
//            dev = factory.getDeveloperDAO().getEntityById(2);
//            dev.setScill(Developer.Scill.JUNIOR);
//            factory.getDeveloperDAO().update(dev);
//            System.err.println(dev);
//
//            list = factory.getDeveloperDAO().getAll();
//            System.out.println(list.size());
//            for(Developer d : list){
//                System.out.println(d);
//            }
//        for(int i =0; i<15; i++){
//            es.execute(new TestR(factory));
//        }
//        
//        scheduler.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                es.shutdown();
//                if(es.isTerminated()){
//                    factory.close();
//                }
//            }
//        }, 1, 400, TimeUnit.MILLISECONDS);
    }
    
    private static class TestR implements Runnable{
        DAOManager factory;

        public TestR(DAOManager factory) {
            this.factory = factory;
        }

        
        @Override
        public void run() {
//            List<Developer> list = factory.getDeveloperDAO().getAll();
//            System.out.println(list.size());
//            for(Developer d : list){
//                System.out.println(d);
//            }
//            Developer dev = factory.getDeveloperDAO().getEntityById(3);
//            System.err.println(dev);
//            dev = factory.getDeveloperDAO().getEntityById(2);
//            dev.setScill(Developer.Scill.JUNIOR);
//            factory.getDeveloperDAO().update(dev);
//            System.err.println(dev);
//
//            list = factory.getDeveloperDAO().getAll();
//            System.out.println(list.size());
//            for(Developer d : list){
//                System.out.println(d);
//            }
        }
        
    }
}
