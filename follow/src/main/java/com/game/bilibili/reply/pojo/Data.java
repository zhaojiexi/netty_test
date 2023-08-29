/**
  * Copyright 2023 bejson.com 
  */
package com.game.bilibili.reply.pojo;
import java.util.List;

/**
 * Auto-generated: 2023-08-22 16:16:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private Cursor cursor;
    private List<Replies> replies;
    private Top top;
    private String top_replies;
    private Up_selection up_selection;
    private Effects effects;
    private int assist;
    private int blacklist;
    private int vote;
    private Config config;
    private Upper upper;
    private Control control;
    private int note;
    private String callbacks;
    public void setCursor(Cursor cursor) {
         this.cursor = cursor;
     }
     public Cursor getCursor() {
         return cursor;
     }

    public void setReplies(List<Replies> replies) {
         this.replies = replies;
     }
     public List<Replies> getReplies() {
         return replies;
     }

    public void setTop(Top top) {
         this.top = top;
     }
     public Top getTop() {
         return top;
     }

    public void setTop_replies(String top_replies) {
         this.top_replies = top_replies;
     }
     public String getTop_replies() {
         return top_replies;
     }

    public void setUp_selection(Up_selection up_selection) {
         this.up_selection = up_selection;
     }
     public Up_selection getUp_selection() {
         return up_selection;
     }

    public void setEffects(Effects effects) {
         this.effects = effects;
     }
     public Effects getEffects() {
         return effects;
     }

    public void setAssist(int assist) {
         this.assist = assist;
     }
     public int getAssist() {
         return assist;
     }

    public void setBlacklist(int blacklist) {
         this.blacklist = blacklist;
     }
     public int getBlacklist() {
         return blacklist;
     }

    public void setVote(int vote) {
         this.vote = vote;
     }
     public int getVote() {
         return vote;
     }

    public void setConfig(Config config) {
         this.config = config;
     }
     public Config getConfig() {
         return config;
     }

    public void setUpper(Upper upper) {
         this.upper = upper;
     }
     public Upper getUpper() {
         return upper;
     }

    public void setControl(Control control) {
         this.control = control;
     }
     public Control getControl() {
         return control;
     }

    public void setNote(int note) {
         this.note = note;
     }
     public int getNote() {
         return note;
     }

    public void setCallbacks(String callbacks) {
         this.callbacks = callbacks;
     }
     public String getCallbacks() {
         return callbacks;
     }

}