package com.gildedrose.strategy;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateItemStrategyTest {
    @Test
    void updateSellIn() {
        Item item = new Item("", 1, 1);
        UpdateItemStrategy strategy = (it) -> 0;
        assertEquals(0, strategy.updateSellIn(item));
    }
}