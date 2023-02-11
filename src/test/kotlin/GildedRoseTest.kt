import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GildedRoseTest {

    @Test
    fun `should Lower SellIn and Quality of item at the end of each day`() {
        val result = updateQualityTo(listOf(anItem(3, 5)))

        assertEquals(anItem(2, 4), result[0])
    }

    @Test
    fun `should lower SellIn and Quality of all items at the end of each day`() {
        val items = updateQualityTo(
            listOf(
                anItem(3, 5),
                anItem(3, 5)
            )
        )

        for (item in items) {
            assertEquals(anItem(2, 4), item)
        }
    }

    @Test
    fun `should Quality be never negative`() {
        val items = updateQualityTo(listOf(anItem(10, 0)))

        assertEquals(anItem(9, 0), items[0])
    }

    @Test
    fun `should Quality decrease twice if the item is expired`() {
        val items = updateQualityTo(listOf(anItem(0, 2)))

        assertEquals(anItem(-1, 0), items[0])
    }

    @Test
    fun `aged brie actually increases when a day passes by`() {
        val items = updateQualityTo(listOf(agedBrie(1, 2)))

        assertEquals(3, items[0].quality)
    }

    @Test
    fun `aged brie actually increases double fast when a day passes by and the product is expired`() {
        val items = updateQualityTo(listOf(agedBrie(0, 2)))

        assertEquals(agedBrie(-1, 4), items[0])
    }

    @Test
    fun `Quality should never be larger than 50`() {
        val items = updateQualityTo(listOf(agedBrie(0, 50)))

        assertEquals(agedBrie(-1, 50), items[0])
    }

    @Test
    fun `sulfuras never should be sold or decrased in Quality case Expired`() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(0, 5)
    }

    @Test
    fun `sulfuras never should be sold or decrased in Quality case Not Expired`() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(1, 2)
    }

    @Test
    fun `sulfuras never should be sold or decrased in Quality case Maximum Normal Quality`() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(1, 50)
    }

    @Test
    fun `sulfuras never should be sold or decrased in Quality case Maximum Quality`() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(1, 80)
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Case Tier 3`() {
        val x = 30
        val items = updateQualityTo(listOf(backstagePass(11, x)))

        assertEquals(backstagePass(10, x + 1), items[0])
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Case Tier 2`() {
        val x = 30
        val items = updateQualityTo(listOf(backstagePass(10, x)))

        assertEquals(backstagePass(9, x + 2), items[0])
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Case Tier 1`() {
        val x = 30
        val items = updateQualityTo(listOf(backstagePass(5, x)))

        assertEquals(backstagePass(4, x + 3), items[0])
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Expired`() {
        val items = updateQualityTo(listOf(backstagePass(0, 30)))

        assertEquals(backstagePass(-1, 0), items[0])
    }

    private fun `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(sellIn: Int, quality: Int) {
        val items = updateQualityTo(listOf(sulfuras(sellIn, quality)))
        assertEquals(sulfuras(sellIn, quality), items[0])
    }

    private fun anItem(sellIn: Int, quality: Int) = Item("anItem", sellIn, quality)

    private fun agedBrie(sellIn: Int, quality: Int) = Item("Aged Brie", sellIn, quality)

    private fun sulfuras(sellIn: Int, quality: Int) = Item("Sulfuras, Hand of Ragnaros", sellIn, quality)

    private fun backstagePass(sellIn: Int, quality: Int) = Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)

    private fun updateQualityTo(items: List<Item>): List<Item> {
        val app = GildedRose(items)
        app.updateQuality()
        return app.items
    }
}