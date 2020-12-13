package com.gildedrose.strategy;

import com.gildedrose.Item;

class NormalItemUpdateStrategy implements UpdateItemStrategy {

    @Override
    public int updateQuality(Item item) {
        int quality = item.quality;
        return Math.max(item.sellIn > 0 ? quality - 1 : quality - 2, 0);
    }

}
