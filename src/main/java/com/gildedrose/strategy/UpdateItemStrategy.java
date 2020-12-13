package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * @author nordine bittich
 * Strategy interface to be implemented depending on the item & how it should be updated based on the sellIn property
 */
public interface UpdateItemStrategy {

    /**
     * Give the next value for sellIn. Default method
     * @param item
     * @return new value for sellIn
     */
    default int updateSellIn(Item item) {
        return item.sellIn - 1;
    }

    /**
     * Give the next value for quality.
     * @param item
     * @return new value for quality
     */
    int updateQuality(Item item);

}
