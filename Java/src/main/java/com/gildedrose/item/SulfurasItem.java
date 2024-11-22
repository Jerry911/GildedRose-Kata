package com.gildedrose.item;

import com.gildedrose.Item;

public class SulfurasItem extends AbstractItem {
    public SulfurasItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        // Sulfuras never changes in quality or sellIn
    }
}
