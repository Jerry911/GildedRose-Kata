package com.gildedrose

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import java.util.*

class GildedRoseTest {

    @Test
    fun givenRandomItem_afterRandomNumberOfDays_shouldDecreaseSellInByNumberOfDays() {
        val days = generateRandomNumber()
        val initialSellIn = generateRandomNumber()
        val item = Item(generateRandomName(), initialSellIn, 0)
        val app = GildedRose(arrayOf(item))

        app.advanceTimeBy(days)

        assertThat(item.sellIn).isEqualTo(initialSellIn-days)
    }

    @Test
    fun givenSulfuras_afterRandomNumberOfDays_shouldHaveSameSellIn() {
        val days = generateRandomNumber()
        val initialSellIn = generateRandomNumber()
        val item = Item("Sulfuras, Hand of Ragnaros", initialSellIn, 0)
        val app = GildedRose(arrayOf(item))

        app.advanceTimeBy(days)

        assertThat(item.sellIn).isEqualTo(initialSellIn)
    }

    private fun GildedRose.advanceTimeBy(days: Int) = repeat(days, { this.updateQuality() })

    private fun generateRandomName() = UUID.randomUUID().toString()

    private fun generateRandomNumber() = (0 until 9999).shuffled().last()
}
