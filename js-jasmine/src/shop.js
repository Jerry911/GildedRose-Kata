var backstage_pass = require('./backstage_pass_update.js')
var aged_brie = require('./aged_brie_update.js')
var sulfuras = require('./sulfuras_update.js')

class Shop {
  constructor(items = []) {
    this.items = items;
    this.MAX_QUALITY = 50;
    this.MIN_QUALITY = 0;
  }
  updateQuality() {
    this.items.forEach(item =>
      this._updateItem(item)
    )
    return this.items;
  }

  _updateItem(item) {
    this._updateItemQuality(item);
    this._checkMaxQuality(item);
    this._checkMinQuality(item);
    this._updateItemSellIn(item);
  }

  _updateItemQuality(item) {
    item.quality += this._getQualityChange(item);
  }

  _getQualityChange(item) {
    if (backstage_pass.is(item)) {
      return backstage_pass.getQualityChange(item);
    } else if (aged_brie.is(item)) {
      return aged_brie.getQualityChange(item);
    } else if (sulfuras.is(item)) {
      return sulfuras.getQualityChange(item);
    } else {
      return this._getQualityChangeStandard(item);
    }
  }

  _getQualityChangeStandard(item) {
    if (item.sellIn <= 0) {
      return -2;
    } else {
      return -1;
    }
  }

  _updateItemSellIn(item) {
    if (item.name != 'Sulfuras, Hand of Ragnaros') {
      item.sellIn = item.sellIn - 1;
    }
  }

  _checkMaxQuality(item) {
    if (item.quality > this.MAX_QUALITY) {
      item.quality = this.MAX_QUALITY;
    }
  }

  _checkMinQuality(item) {
    if (item.quality < this.MIN_QUALITY) {
      item.quality = this.MIN_QUALITY;
    }
  }
}
module.exports = {
  Shop
}
