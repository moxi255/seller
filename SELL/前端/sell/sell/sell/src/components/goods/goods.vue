<template>
  <div>
  <div class="goods">
      <div class="menu-wrapper">
      <ul>
        <li v-for="(item,index) in goods" :key="index" class="menu-item" :class="{'current':currentIndex===index}" @click="selectMenu(index)">
          <span class="text border-1px">
            <span v-show="item.type>0" class="icon" :class="classMap[item.type]"></span>{{item.foodTypename}}
          </span>
        </li>
      </ul>
    </div>
    <div class="foods-wrapper">
      <ul>
        <li v-for="(item,index) in goods" :key="index" class="food-list food-list-hook" ref="foodList">
          <h1 class="title">{{item.name}}</h1>
          <ul>
            <li @click="selectFood(food,$event)" v-for="(food,index) in item.foods" :key="index" class="food-item  border-1px">
              <div class="icon">
                <img width="57" height="57" :src="food.icon">
              </div>
              <div class="content">
                <h2 class="name">{{food.name}}</h2>
                <p class="desc">{{food.description}}</p>
                <div class="extra">
                  <span class="count">月售{{food.sellCount}}份</span>
                  <span>好评率{{food.rating}}</span>
                </div>
                <div class="price">
                  <span class="now">￥{{food.price}}</span>
                  <span class="old" v-show="food.oldPrice">￥{{food.oldPrice}}</span>
                </div>
                <div class="cartControl-wrapper">
                  <cart-control @add="addFood" :food="food"></cart-control>
                </div>
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <shop-cart ref="shopCart" :select-foods="selectFoods" :delivery-price="seller.deliveryPrice" :min-price="seller.minPrice"></shop-cart>
  </div>
  <food ref="food" @add="addFood" :food="selectOneFood"></food>
</div>
</template>

<script type="text/ecmascript-6">
import BScroll from 'better-scroll'
import shopCart from '../shopcart/shopcart'
import food from '../food/food'
import cartControl from '../cartcontrol/cartcontrol'
import {rootPath} from '../../apiConfig/api'
export default {
  props: {
    seller: {
      type: Object
    }
  },
  data () {
    return {
      goods: [],
      // 保存数据
      listHeight: [],
      scrollY: 0,
      index: -1,
      selectOneFood: {
        // 解决goods初次加载时报错
        ratings: []
      }
    }
  },
  created () {
    this.classMap = ['decrease', 'discount', 'special', 'invoice', 'guarantee']
    this.$http.get(rootPath + '/foodTypes').then(response => {
      console.log(response.status)
      if (response.status === 200) {
        this.goods = response.body
        console.log(this.goods)
        // 实时更新
        this.$nextTick(() => {
          this._initScroll()
          this._calculateHeight()
        })
      }
    }, response => {
      console.log(response.status)
    })
  },
  computed: {
    // 为了显示css样式
    currentIndex () {
      for (let i = 0; i < this.listHeight.length; i++) {
        let height1 = this.listHeight[i]
        let height2 = this.listHeight[i + 1]
        if (!height2 || (this.scrollY >= height1 && this.scrollY < height2)) {
          if (this.index !== -1) {
            i = this.index
          }
          return i
        }
      }
      return 0
    },
    selectFoods () {
      let foods = []
      this.goods.forEach((good) => {
        good.foods.forEach((food) => {
          if (food.count) {
            foods.push(food)
          }
        })
      })
      return foods
    }
  },
  methods: {
  // 选择食物
    selectFood (food, event) {
      if (!event._constructed) {
        return
      }
      this.selectOneFood = food
      this.$refs.food.show()
    },
    // 父组件监听到事件调用drop方法
    addFood (target) {
      this._drop(target)
    },
    // 调用子组件drop方法
    _drop (target) {
      // 体验优化异步执行
      this.$nextTick(() => {
        this.$refs.shopCart.drop(target)
      })
    },
    selectMenu (index) {
      let foodList = this.$refs.foodList
      let el = foodList[index]
      this.foodScroll.scrollToElement(el, 300)
      this.index = index
    },
    // 滚动
    _initScroll () {
      this.menuScroll = new BScroll('.menu-wrapper', {click: true})
      this.foodScroll = new BScroll('.foods-wrapper', {probeType: 3, click: true})
      this.foodScroll.on('scroll', (pos) => {
        this.scrollY = Math.abs(Math.round(pos.y))
      })
    },
    // 滚动计算
    _calculateHeight () {
      let foodList = this.$refs.foodList
      let height = 0
      this.listHeight.push(height)
      for (let i = 0; i < foodList.length; i++) {
        let item = foodList[i]
        height += item.clientHeight
        this.listHeight.push(height)
      }
    }
  },
  components: {
    shopCart,
    cartControl,
    food
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/styles/mixin.styl"
  .goods
    display :flex
    position :absolute
    top:174px
    bottom:64px
    width :100%
    overflow :hidden
    .menu-wrapper
      flex:0 0 80px
      width :80px
      background :#f3f5f7
      .menu-item
        display :table
        height :54px
        width:56px
        line-height:14px
        padding:0 12px
        .icon
          display: inline-block
          width: 12px
          height: 12px
          margin-right: 2px
          background-size: 12px 12px
          background-repeat: no-repeat
          &.decrease
            bg-image('decrease_3')
          &.discount
            bg-image('discount_3')
          &.guarantee
            bg-image('guarantee_3')
          &.invoice
            bg-image('invoice_3')
          &.special
            bg-image('special_3')
        .text
          display :table-cell
          width:56px
          vertical-align :middle
          border-1px(rgba(7,17,27,0.1))
          font-size :12px
    .foods-wrapper
      flex: 1
      .title
        padding-left:14px
        height:26px
        line-height :26px
        border-left:2px solid #d9dde1
        font-size :12px
        color :rgb(147,153,159)
        background :#f3f5f7
      .food-item
        display :flex
        margin:18px
        padding-bottom :18px
        border-1px(rgba(7,17,27,0.1))
        &:last-child
          border-none()
          margin-bottom :0
        .icon
          flex :0 0 57px
          margin-right :10px
        .content
          flex:1
          .name
            margin :2px 0 8px 0
            height:14px
            line-height :14px
            font-size:14px
            color :rgb(7,17,27)
          .desc,.extra
            line-height :10px
            font-size :10px
            color :rgb(147,153,159)
          .desc
            margin-bottom :8px
          .extra
            &.count
              margin-right :12px
          .price
             font-weight :700
             line-height:24px
             .now
               margin-right:8px
               font-size:14px
               color :rgb(240,20,20)
             .old
               text-decoration :line-through
               font-size:10px
               color :rgb(147,153,159)
          .cartControl-wrapper
            position :absolute
            right : 0
            bottom: 12px
</style>
