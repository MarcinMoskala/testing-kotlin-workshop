class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items = items.map { it.let(::toItem).updateQuality().toDTO() }
    }

    private fun toItem(item: Item): ShopItem = when (item.name) {
        "Sulfuras, Hand of Ragnaros" -> ImmutableItem(item)
        "Aged Brie" -> AgedBrie(item)
        "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses(item)
        else -> ShopItem(item)
    }
}

data class Item(val name: String, val sellIn: Int, val quality: Int)

private open class ShopItem(var item: Item) {

    open fun updateQuality(): ShopItem {
        decreaseQuality()
        if (isExpired()) {
            decreaseQuality()
        }
        decreaseSellIn()
        if (isExpired()) {
            decreaseQuality()
        }
        return this
    }

    fun toDTO(): Item = Item(item.name, item.sellIn, item.quality)

    protected fun decreaseSellIn() {
        item = item.copy(sellIn = item.sellIn - 1)
    }

    protected fun decreaseQuality() {
        if (item.quality > 0) {
            item = item.copy(quality = item.quality - 1)
        }
    }

    protected fun increaseQuality() {
        if (item.quality < 50) {
            item = item.copy(quality = item.quality + 1)
        }
    }

    protected fun isExpired() = item.sellIn < 0
}

private class BackstagePasses(name: Item) : ShopItem(name) {
    override fun updateQuality(): ShopItem {
        increaseQuality()

        if (item.sellIn < 11) {
            increaseQuality()
        }

        if (item.sellIn < 6) {
            increaseQuality()
        }
        if (isExpired()) {
            item = item.copy(quality = 0)
        }

        decreaseSellIn()

        if (isExpired()) {
            item = item.copy(quality = 0)
        }

        return this
    }
}

private class ImmutableItem(item: Item) : ShopItem(item) {
    override fun updateQuality(): ShopItem = this
}

private class AgedBrie(item: Item) : ShopItem(item) {
    override fun updateQuality(): ShopItem {
        increaseQuality()

        if (isExpired()) {
            increaseQuality()
        }

        decreaseSellIn()

        if (isExpired()) {
            increaseQuality()
        }
        return this
    }
}