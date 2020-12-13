package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LegendaryItemUpdateStrategyTest {

    @Test
    void updateQuality() {
        Item item = new Item("", -1, 80);
        LegendaryItemUpdateStrategy strategy = new LegendaryItemUpdateStrategy();
        assertEquals(80, strategy.updateQuality(item));
        assertEquals(-1, strategy.updateSellIn(item));
    }
}