package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalItemUpdateStrategyTest {

    @Test
    void updateQuality() {
        Item item1 = new Item("", 1, 1);
        Item item2 = new Item("", 0, 1);

        NormalItemUpdateStrategy strategy = new NormalItemUpdateStrategy();
        assertEquals(0, strategy.updateQuality(item1));
        assertEquals(0, strategy.updateQuality(item2));
    }
}