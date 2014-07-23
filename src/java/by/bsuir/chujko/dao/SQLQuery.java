/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao;

/**
 *
 * @author Artyom
 * 
 * This class is for storing database queries (from: by/bsuir/chujko/properties/sql.properties ).
 */
public final class SQLQuery {

    /**
     * Not to create instances of this class.
     */
    private SQLQuery() {}
    
    /**
     * For User table
     */
    public static final String U_GET_ALL 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.user.get_all");
    
    public static final String U_ADD 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.user.add");
    
    public static final String U_GET_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.user.get_by_id");
    
    public static final String U_GET_BY_SEC_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.user.get_by_sec_id");
    
    public static final String U_DELETE 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.user.delete");
    
    public static final String U_UPDATE
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.user.update");
    
    /**
     * For Dialog table
     */
    public static final String D_GET_ALL 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.get_all");

    public static final String D_GET_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.get_by_id");
    
    public static final String D_GET_BY_NOTE_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.get_by_note_id");
    
    public static final String D_ADD 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.add");
    
    public static final String D_DELETE 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.delete");
    
    public static final String D_UPDATE
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.update");
    
    public static final String D_ALL_MS_BY_DIA_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.get_all_mes_by_dia_id");
    
    public static final String D_ALL_MS_BY_NOTE_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.get_all_mes_by_note_id");
    
    public static final String D_GET_NOTE_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.dia.get_note");
    
    /**
     * For Note table
     */
    public static final String N_GET_ALL 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.get_all");
    
    public static final String N_GET_TAG_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.get_tag_by_id");
    
    public static final String N_ADD 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.add");
    
    public static final String N_GET_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.get_by_id");
    
    public static final String N_DELETE 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.delete");
    
    public static final String N_UPDATE
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.update");
    
    public static final String N_GET_ONE_LAST
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.get_one_last");
    
    public static final String N_GET_ONE_LAST_NEW
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.note.get_one_last_id");
    
    /**
     * For Tag table
     */
    public static final String T_GET_ALL 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.get_all");
    
    public static final String T_ADD 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.add");
    
    public static final String T_GET_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.get_by_id");
    
    public static final String T_DELETE 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.delete");
    
    public static final String T_UPDATE
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.update");
    
    public static final String T_GET_LAST
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.get_last20");
    
    public static final String T_GET_BY_VAL
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.tag.get_by_val");
    
    /**
     * For Security table
     */
    public static final String S_GET_ALL 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.get_all");
    
    public static final String S_ADD 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.add");
    
    public static final String S_GET_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.get_by_id");
    
    public static final String S_GET_BY_EP 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.get_by_ep");
    
    public static final String S_DELETE 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.delete");
    
    public static final String S_UPDATE
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.update");
    
    public static final String S_GET_KEY
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.sec.get_key");
    
    /**
     * For Message table
     */
    public static final String MS_GET_ALL 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_all");
    
    public static final String MS_ADD 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.add");
    
    public static final String MS_GET_BY_ID 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_by_id");
    
    public static final String MS_DELETE 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.delete");
    
    public static final String MS_UPDATE
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.update");
    
    public static final String MS_GET_LAST_BY_D 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_unread_by_d");
    
    public static final String MS_GET_LAST_BY_N
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_unread_by_n");
    
    public static final String MS_GET_COUNT_LAST_BY_D 
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_count_unread_by_d");
    
    public static final String MS_GET_COUNT_LAST_BY_N
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_count_unread_by_n");
    
    public static final String MS_GET_UNREAD
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_unread");
    
    public static final String MS_GET_COUNT
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_count_unread");
    
    public static final String MS_GET_LAST_UNREAD
            = java.util.ResourceBundle
                  .getBundle("by/bsuir/chujko/properties/sql")
                  .getString("sql.ms.get_last_unread");
}
