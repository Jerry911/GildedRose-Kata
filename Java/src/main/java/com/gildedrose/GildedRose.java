package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                // Sulfuras does not change in quality or sellIn
                continue;
            }

            // Reduce SellIn value
            item.sellIn--;

            if (item.name.equals("Aged Brie")) {
                updateAgedBrie(item);
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                updateBackstagePasses(item);
            } else if (item.name.startsWith("Conjured")) {
                updateConjuredItem(item);
            } else {
                updateNormalItem(item);
            }
        }
    }

    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality++; // Increases twice as fast after sell-by date
        }
    }

    private void updateBackstagePasses(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0; // Quality drops to 0 after the concert
        } else if (item.sellIn < 5) {
            increaseQuality(item, 3); // Increase by 3 when 5 days or less
        } else if (item.sellIn < 10) {
            increaseQuality(item, 2); // Increase by 2 when 10 days or less
        } else {
            increaseQuality(item, 1); // Increase by 1 otherwise
        }
    }

    private void updateConjuredItem(Item item) {
        decreaseQuality(item, 2); // Degrades twice as fast
        if (item.sellIn < 0) {
            decreaseQuality(item, 2); // Degrades even faster after sell-by date
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item, 1); // Decrease by 1
        if (item.sellIn < 0) {
            decreaseQuality(item, 1); // Decreases twice as fast after sell-by date
        }
    }

    private void increaseQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, 50); // Max quality is 50
    }

    private void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, 0); // Quality cannot go below 0
    }
}
