package com.gildedrose.item;

import com.gildedrose.Item;

public class ConjuredItem extends AbstractItem {

    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality(2); // Degrades twice as fast
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(2); // Degrades even faster after sellIn
        }
    }
}
