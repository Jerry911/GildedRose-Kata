package com.gildedrose.item;

import com.gildedrose.Item;

public class AgedBrieItem extends AbstractItem {

    public AgedBrieItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        increaseQuality(1);
        item.sellIn--;
        if (item.sellIn < 0) {
            increaseQuality(1); // Increases faster after sellIn
        }
    }
}
