package com.app.aptap.model;

/**
 * Created by ke41342 on 22-09-2017.
 */

public class SpinnerItemData {

    private int itemId;
    private String itemTitle;
    private String itemImage;

    public SpinnerItemData(int itemId, String itemTitle, String itemImage) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
