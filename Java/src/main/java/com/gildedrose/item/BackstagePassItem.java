package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePassItem extends AbstractItem {

    public BackstagePassItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.sellIn > 10) {
            increaseQuality(1);
        } else if (item.sellIn > 5) {
            increaseQuality(2); // 10 days or less
        } else if (item.sellIn > 0) {
            increaseQuality(3); // 5 days or less
        } else {
            item.quality = 0; // After the concert
        }

        item.sellIn--;
    }
}
