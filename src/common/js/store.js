/**
 * 存取数据到本地
 * @param id 商家 的id
 * @param key 字段
 * @param value 值
 */
export function saveToLocal (id, key, value) {
  let seller = window.localStorage.__seller__
  if (!seller) {
    seller = {}
    seller[id] = {}
  } else {
    seller = JSON.parse(seller)
    // 如果没有这个商家
    if (!seller[id]) {
      seller[id] = {}
    }
  }
  seller[id][key] = value
  window.localStorage.__seller__ = JSON.stringify(seller)
}

/**
 * 读取本地存取的数据
 * @param id
 * @param key
 * @param def 缺省变量
 * @returns {*}
 */
export function loadFromLocal (id, key, def) {
  let seller = window.localStorage.__seller__
  if (!seller) {
    return def
  }
  seller = JSON.parse(seller)[id]
  if (!seller) {
    return def
  }
  let ret = seller[key]
  return ret || def
}
