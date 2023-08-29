/**
  * Copyright 2023 bejson.com 
  */
package com.game.bilibili.reply.pojo;
import java.util.Date;

/**
 * Auto-generated: 2023-08-22 16:16:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Cursor {

    private boolean is_begin;
    private int prev;
    private int next;
    private boolean is_end;
    private int mode;
    private String mode_text;
    private int all_count;
    private Date support_mode;
    private String name;
    private Pagination_reply pagination_reply;
    private String session_id;
    public void setIs_begin(boolean is_begin) {
         this.is_begin = is_begin;
     }
     public boolean getIs_begin() {
         return is_begin;
     }

    public void setPrev(int prev) {
         this.prev = prev;
     }
     public int getPrev() {
         return prev;
     }

    public void setNext(int next) {
         this.next = next;
     }
     public int getNext() {
         return next;
     }

    public void setIs_end(boolean is_end) {
         this.is_end = is_end;
     }
     public boolean getIs_end() {
         return is_end;
     }

    public void setMode(int mode) {
         this.mode = mode;
     }
     public int getMode() {
         return mode;
     }

    public void setMode_text(String mode_text) {
         this.mode_text = mode_text;
     }
     public String getMode_text() {
         return mode_text;
     }

    public void setAll_count(int all_count) {
         this.all_count = all_count;
     }
     public int getAll_count() {
         return all_count;
     }

    public void setSupport_mode(Date support_mode) {
         this.support_mode = support_mode;
     }
     public Date getSupport_mode() {
         return support_mode;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPagination_reply(Pagination_reply pagination_reply) {
         this.pagination_reply = pagination_reply;
     }
     public Pagination_reply getPagination_reply() {
         return pagination_reply;
     }

    public void setSession_id(String session_id) {
         this.session_id = session_id;
     }
     public String getSession_id() {
         return session_id;
     }

}