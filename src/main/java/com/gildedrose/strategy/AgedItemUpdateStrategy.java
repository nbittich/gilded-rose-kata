package com.gildedrose.strategy;

import com.gildedrose.Item;

class AgedItemUpdateStrategy implements UpdateItemStrategy {
    @Override
    public int updateQuality(Item item) {
        int quality = item.quality;
        return Math.min(item.sellIn > 0 ? quality + 1 : quality + 2, 50);
    }
}
