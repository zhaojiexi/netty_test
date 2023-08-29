/**
  * Copyright 2023 bejson.com 
  */
package com.game.bilibili.reply.pojo;

/**
 * Auto-generated: 2023-08-22 16:16:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Layer_config {

    private Tags tags;
    private boolean is_critical;
    private Layer_mask layer_mask;
    public void setTags(Tags tags) {
         this.tags = tags;
     }
     public Tags getTags() {
         return tags;
     }

    public void setIs_critical(boolean is_critical) {
         this.is_critical = is_critical;
     }
     public boolean getIs_critical() {
         return is_critical;
     }

    public void setLayer_mask(Layer_mask layer_mask) {
         this.layer_mask = layer_mask;
     }
     public Layer_mask getLayer_mask() {
         return layer_mask;
     }

}