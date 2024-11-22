package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("fixme", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    /**
     * NORMAL ITEMS TESTS
     */
    @Test
    public void testNormalItemQualityDecreasesByOne() {
        Item[] items = new Item[]{new Item("Normal Item", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    public void testNormalItemBeforeSellDate() {
        Item[] items = new Item[]{new Item("Normal Item", 5, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    public void testNormalItemOnSellDate() {
        Item[] items = new Item[]{new Item("Normal Item", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    public void testNormalItemAfterSellDate() {
        Item[] items = new Item[]{new Item("Normal Item", -1, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    public void testNormalItemQualityNeverNegative() {
        Item[] items = new Item[]{new Item("Normal Item", 5, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(0, items[0].quality); // Quality should not go below 0
    }

    /**
     * AGED BRIE TESTS
     */
    @Test
    public void testAgedBrieBeforeSellDate() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    public void testAgedBrieOnSellDate() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality); // Quality increases twice as fast after sell date
    }

    @Test
    public void testAgedBrieAfterSellDate() {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    public void testAgedBrieQualityCapAtFifty() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality); // Quality should not exceed 50
    }

    /**
     * SULFURAS TESTS
     */
    @Test
    public void testSulfurasNeverChanges() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testSulfurasQualityAlwaysEighty() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(80, items[0].quality); // Quality should remain constant
    }

    /**
     * CONJURED ITEMS TEST
     */
    @Test
    public void testConjuredItemBeforeSellDate() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 5, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(8, items[0].quality); // Decreases by 2
    }

    @Test
    public void testConjuredItemAfterSellDate() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(6, items[0].quality); // Decreases by 4 after sell date
    }

    @Test
    public void testConjuredItemQualityNeverNegative() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 5, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(0, items[0].quality); // Quality cannot go below 0
    }

    /**
     * Backstage passes to a TAFKAL80ETC concert TESTS
     */
    @Test
    void testBackstagePassesIncreaseByOneWhenMoreThanTenDaysLeft() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality); // Quality increases by 1
    }

    @Test
    void testBackstagePassesIncreaseByTwoWhenTenDaysOrLessLeft() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality); // Quality increases by 2
    }

    @Test
    void testBackstagePassesIncreaseByThreeWhenFiveDaysOrLessLeft() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality); // Quality increases by 3
    }

    @Test
    void testBackstagePassesDropToZeroAfterConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality); // Quality drops to 0
    }

    @Test
    void testBackstagePassesQualityNeverExceedsFifty() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality); // Max quality is 50
    }

    @Test
    void testBackstagePassesQualityNeverExceedsFiftyWhenFiveDaysLeft() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality); // Max quality is 50
    }
}
