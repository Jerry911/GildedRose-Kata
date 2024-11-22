package com.gildedrose.item;

import com.gildedrose.Item;

public class NormalItem extends AbstractItem {

    public NormalItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality(1);
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(1); // Degrades twice as fast after sellIn
        }
    }
}
