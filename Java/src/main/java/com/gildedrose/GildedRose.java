package com.gildedrose;

import com.gildedrose.item.AbstractItem;
import com.gildedrose.factory.ItemFactory;

import java.util.Arrays;

class GildedRose {
    AbstractItem[] items;

    public GildedRose(Item[] items) {
        this.items = Arrays.stream(items)
            .map(ItemFactory::createItem)
            .toArray(AbstractItem[]::new);
    }

    public void updateQuality() {
        for (AbstractItem item : items) {
            item.update();
        }
    }
}
