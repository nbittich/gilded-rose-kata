package com.gildedrose.strategy;

import com.gildedrose.Item;

class LegendaryItemUpdateStrategy implements UpdateItemStrategy {

    @Override
    public int updateQuality(Item item) {
        return 80;
    }

    @Override
    public int updateSellIn(Item item) {
        return item.sellIn;
    }
}
