package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackstageItemUpdateStrategyTest {

    @Test
    void updateQuality() {
        Item item = new Item("", 0, 32);
        BackstageItemUpdateStrategy strategy = new BackstageItemUpdateStrategy();
        assertEquals(0, strategy.updateQuality(item));
        assertEquals(-1, strategy.updateSellIn(item));
        item = new Item("", 5, 32);
        assertEquals(35, strategy.updateQuality(item));
        assertEquals(4, strategy.updateSellIn(item));
        item = new Item("", 10, 32);
        assertEquals(34, strategy.updateQuality(item));
        assertEquals(9, strategy.updateSellIn(item));
        item = new Item("", 11, 32);
        assertEquals(33, strategy.updateQuality(item));
        assertEquals(10, strategy.updateSellIn(item));

    }
}