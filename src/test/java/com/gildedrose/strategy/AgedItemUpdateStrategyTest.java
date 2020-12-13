package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgedItemUpdateStrategyTest {

    @Test
    void updateQuality() {
        Item item = new Item("", 0, 32);
        AgedItemUpdateStrategy strategy = new AgedItemUpdateStrategy();
        assertEquals(34, strategy.updateQuality(item));
        assertEquals(-1, strategy.updateSellIn(item));
        item = new Item("", 5, 32);
        assertEquals(33, strategy.updateQuality(item));
        assertEquals(4, strategy.updateSellIn(item));
        item = new Item("", 10, 50);
        assertEquals(50, strategy.updateQuality(item));
        assertEquals(9, strategy.updateSellIn(item));
    }
}