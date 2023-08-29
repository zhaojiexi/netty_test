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
public class Replies {

    private long rpid;
    private long oid;
    private int type;
    private long mid;
    private int root;
    private int parent;
    private int dialog;
    private int count;
    private int rcount;
    private int state;
    private int fansgrade;
    private int attr;
    private long ctime;
    private String rpid_str;
    private String root_str;
    private String parent_str;
    private int like;
    private int action;
    private Member member;
    private Content content;
    private List<Replies> replies;
    private int assist;
    private Up_action up_action;
    private boolean invisible;
    private List<Card_label> card_label;
    private Reply_control reply_control;
    private Folder folder;
    private String dynamic_id_str;
    public void setRpid(long rpid) {
         this.rpid = rpid;
     }
     public long getRpid() {
         return rpid;
     }

    public void setOid(long oid) {
         this.oid = oid;
     }
     public long getOid() {
         return oid;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setMid(long mid) {
         this.mid = mid;
     }
     public long getMid() {
         return mid;
     }

    public void setRoot(int root) {
         this.root = root;
     }
     public int getRoot() {
         return root;
     }

    public void setParent(int parent) {
         this.parent = parent;
     }
     public int getParent() {
         return parent;
     }

    public void setDialog(int dialog) {
         this.dialog = dialog;
     }
     public int getDialog() {
         return dialog;
     }

    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setRcount(int rcount) {
         this.rcount = rcount;
     }
     public int getRcount() {
         return rcount;
     }

    public void setState(int state) {
         this.state = state;
     }
     public int getState() {
         return state;
     }

    public void setFansgrade(int fansgrade) {
         this.fansgrade = fansgrade;
     }
     public int getFansgrade() {
         return fansgrade;
     }

    public void setAttr(int attr) {
         this.attr = attr;
     }
     public int getAttr() {
         return attr;
     }

    public void setCtime(long ctime) {
         this.ctime = ctime;
     }
     public long getCtime() {
         return ctime;
     }

    public void setRpid_str(String rpid_str) {
         this.rpid_str = rpid_str;
     }
     public String getRpid_str() {
         return rpid_str;
     }

    public void setRoot_str(String root_str) {
         this.root_str = root_str;
     }
     public String getRoot_str() {
         return root_str;
     }

    public void setParent_str(String parent_str) {
         this.parent_str = parent_str;
     }
     public String getParent_str() {
         return parent_str;
     }

    public void setLike(int like) {
         this.like = like;
     }
     public int getLike() {
         return like;
     }

    public void setAction(int action) {
         this.action = action;
     }
     public int getAction() {
         return action;
     }

    public void setMember(Member member) {
         this.member = member;
     }
     public Member getMember() {
         return member;
     }

    public void setContent(Content content) {
         this.content = content;
     }
     public Content getContent() {
         return content;
     }

    public void setReplies(List<Replies> replies) {
         this.replies = replies;
     }
     public List<Replies> getReplies() {
         return replies;
     }

    public void setAssist(int assist) {
         this.assist = assist;
     }
     public int getAssist() {
         return assist;
     }

    public void setUp_action(Up_action up_action) {
         this.up_action = up_action;
     }
     public Up_action getUp_action() {
         return up_action;
     }

    public void setInvisible(boolean invisible) {
         this.invisible = invisible;
     }
     public boolean getInvisible() {
         return invisible;
     }

    public void setCard_label(List<Card_label> card_label) {
         this.card_label = card_label;
     }
     public List<Card_label> getCard_label() {
         return card_label;
     }

    public void setReply_control(Reply_control reply_control) {
         this.reply_control = reply_control;
     }
     public Reply_control getReply_control() {
         return reply_control;
     }

    public void setFolder(Folder folder) {
         this.folder = folder;
     }
     public Folder getFolder() {
         return folder;
     }

    public void setDynamic_id_str(String dynamic_id_str) {
         this.dynamic_id_str = dynamic_id_str;
     }
     public String getDynamic_id_str() {
         return dynamic_id_str;
     }

}