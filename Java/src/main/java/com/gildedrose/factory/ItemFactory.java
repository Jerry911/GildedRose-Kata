package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.item.AbstractItem;
import com.gildedrose.item.AgedBrieItem;
import com.gildedrose.item.BackstagePassItem;
import com.gildedrose.item.ConjuredItem;
import com.gildedrose.item.NormalItem;
import com.gildedrose.item.SulfurasItem;

public class ItemFactory {
    public static AbstractItem createItem(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrieItem(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassItem(item);
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasItem(item);
            default:
                if (item.name.startsWith("Conjured")) {
                    return new ConjuredItem(item);
                } else {
                    return new NormalItem(item);
                }
        }
    }
}
