package com.gildedrose.strategy;

import com.gildedrose.Item;

class BackstageItemUpdateStrategy implements UpdateItemStrategy {

    @Override
    public int updateQuality(Item item) {
        if (item.sellIn <= 0) {
            return 0;
        }
        int quality = item.quality;
        if (item.sellIn <= 5)
            quality += 3;
        else if (item.sellIn <= 10)
            quality += 2;
        else
            quality += 1;
        return Math.min(quality, 50);
    }

}
