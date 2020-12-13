package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConjuredUpdateStrategyTest {

    @Test
    void updateQuality() {
        ConjuredUpdateStrategy strategy = new ConjuredUpdateStrategy();
        Item item = new Item("", -1, 80);
        assertEquals(76, strategy.updateQuality(item));
        assertEquals(-2, strategy.updateSellIn(item));
        item = new Item("", 1, 80);
        assertEquals(78, strategy.updateQuality(item));
        assertEquals(0, strategy.updateSellIn(item));
    }
}