package com.gildedrose.item;

import com.gildedrose.Item;

public abstract class AbstractItem {
    public Item item;

    public AbstractItem(Item item) {
        this.item = item;
    }

    public abstract void update();

    protected void increaseQuality(int amount) {
        item.quality = Math.min(item.quality + amount, 50);
    }

    protected void decreaseQuality(int amount) {
        item.quality = Math.max(item.quality - amount, 0);
    }
}
