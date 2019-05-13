<template>
  <div>
    <div class="shopcart">
      <div class="content" @click="toggleList">
        <div class="content-left">
          <div class="logo-wrapper">
            <div class="logo" :class="{'highlight' :totalCount>0}">
              <i class="icon-shopping_cart" :class="{'highlight' :totalCount>0}"></i>
            </div>
            <div class="num" v-show="totalCount>0">{{totalCount}}</div>
          </div>
          <div class="price" :class="{'highlight' :totalCount>0}">￥{{totalPrice}}</div>
          <div class="desc">另需配送费￥{{deliveryPrice}}元</div>
        </div>
        <div class="content-right">
          <div class="pay" :class="payClass" @click.stop.prevent="payMoney">{{payDesc}}
        </div>
      </div>
    </div>
      <div class="ball-container">
        <div v-for="(ball,index) in balls" :key="index">
          <transition name="drop" v-on:before-enter="beforeEnter" v-on:enter="enter" v-on:afterenter="afterEnter">
            <div class="ball" v-show="ball.show">
              <div class="inner inner-hook"></div>
            </div>
          </transition>
        </div>
      </div>
      <transition name="fold">
        <div class="shopcart-list" v-show="listShow">
          <div class="list-header">
            <h1 class="title">购物车</h1>
            <span class="empty" @click="empty">清空</span>
          </div>
          <div class="list-content">
            <ul>
              <li class="food" v-for="(food,index) in selectFoods" :key="index">
                <span class="name">{{food.name}}</span>
                <div class="price">
                  <span>￥{{food.price*food.count}}</span>
                </div>
                <div class="cartcontrol-wrapper">
                  <cart-control :food="food"></cart-control>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </transition>
  </div>
    <transition name="fade">
      <div class="list-mask" @click="hideList" v-show="listShow"></div>
    </transition>
  </div>
</template>

<script type="text/ecmascript-6">
import BScroll from 'better-scroll'
import cartControl from '../cartcontrol/cartcontrol'
import {getCookie} from '../../common/js/cookieUtil'
import BMap from 'BMap'
import {rootPath} from '../../apiConfig/api'
export default {
  name: 'shopcart',
  data () {
    return {
      balls: [
        {show: false
        },
        {show: false
        },
        {show: false
        },
        {show: false
        },
        {show: false
        }
      ],
      dropBalls: [],
      // 是否折叠，默认折叠
      fold: true,
      province: 0,
      city: 0,
      district: 0,
      street: 0,
      orderId: 0
    }
  },
  props: {
    selectFoods: {
      type: Array,
      default () {
        return []
      }
    },
    deliveryPrice: {
      type: Number,
      default: 0
    },
    minPrice: {
      type: Number,
      default: 0
    }
  },
  computed: {
    totalPrice () {
      let total = 0
      this.selectFoods.forEach((food) => {
        console.log(food.price)
        total += food.price * food.count + 4
      })
      return total
    },
    totalCount () {
      let count = 0
      this.selectFoods.forEach((food) => {
        count += food.count
      })
      return count
    },
    payDesc () {
      if (this.totalPrice === 0) {
        return `￥${this.minPrice}元起送`
      } else if (this.totalPrice < this.minPrice) {
        let diff = this.minPrice - this.totalPrice
        return `还差￥${diff}元起送`
      } else {
        return '去结算'
      }
    },
    payClass () {
      if (this.totalPrice < this.minPrice) {
        return 'not-enough'
      } else {
        return 'enough'
      }
    },
    listShow () {
      if (!this.totalCount) {
        return false
      }
      let show = !this.fold
      return show
    }
  },
  watch: {
    selectFoods (newFoods, oldFoods) {
      if (newFoods.length === 0) {
        this.fold = true
      }
      console.log(newFoods.length)
    },
    fold (totalCount) {
      let show = this.fold
      if (show) {
        this.$nextTick(() => {
          if (!this.scroll) {
            this.scroll = new BScroll('.list-content', {
              click: true
            })
          } else {
            this.scroll.refresh()
          }
        })
      }
      return show
    }
  },
  mounted: function () {
    if (navigator.geolocation) { // 调用导航器geolocation函数
      navigator.geolocation.getCurrentPosition(this.loand) // 进入总显示函数loand，函数名由自己定
    } else {
      alert('您的浏览器不支持地理定位')// 不支持
    }
  },
  methods: {
    loand (position) {
      var lat = position.coords.latitude// y，纬度，通过上面的getCurrentPosition函数定位浏览器位置，从而获取地址
      var lon = position.coords.longitude// x，经度
      console.log(this.lat)
      var gc = new BMap.Geocoder() // 初始化，Geocoder类
      var point = new BMap.Point(lon, lat) // 这里设置刚开始的点所在处
      gc.getLocation(point, function (rs) { // getLocation函数用来解析地址信息，分别返回省市区街等
        var addComp = rs.addressComponents
        this.province = addComp.province// 获取省份
        this.city = addComp.city// 获取城市
        this.district = addComp.district// 区
        this.street = addComp.street// 街
        console.log(this.province)
      })
    },
    payMoney () {
      // 结算
      if (this.totalPrice < this.minPrice) {
        return
      }
      window.alert(`支付${this.totalPrice}元`)
      // 获取当前时间
      var date = new Date()
      var seperator1 = '-'
      var year = date.getFullYear()
      var month = date.getMonth() + 1
      var strDate = date.getDate()
      if (month >= 1 && month <= 9) {
        month = '0' + month
        if (strDate >= 0 && strDate <= 9) {
          strDate = '0' + strDate
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate
        console.log(getCookie('userid'))
        this.$http.get(rootPath + '/useres/' + getCookie('userid')).then(response => {
          this.address = response.body.address
          this.$http.post(rootPath + '/orderes',
            {
              userId: getCookie('userid'),
              orderTime: currentdate,
              orderPrice: this.totalPrice,
              address: this.address,
              status: '已下单'
            },
            {
              emulateJson: true
            }).then(function (res) {
            this.orderId = res.body.id
            this.selectFoods.forEach((food) => {
              var foodid = food.id
              this.$http.post(rootPath + '/orderlists',
                {
                  orderid: this.orderId,
                  foodid: foodid,
                  amount: this.totalCount
                },
                {
                  emulateJson: true
                }).then(response => {
                alert('下单成功！')
                window.location.href = 'prompt.html?orderId=' + this.orderId
              }, response => {
                console.log(response.status)
              })
            })
            console.log(res)
          })
        }, response => {
          console.log(response.status)
        })
      }
    },
    empty () {
      // 清空购物车
      this.selectFoods.forEach((food) => {
        food.count = 0
      })
    },
    hideList () {
      this.fold = true
    },
    toggleList () {
      // 购物车弹窗
      if (!this.totalCount) {
        return
      }
      this.fold = !this.fold
      return this.fold
    },
    drop (el) {
      console.log(el)
      // 找到隐藏的小球添加到数组中
      for (let i = 0; i < this.balls.length; i++) {
        let ball = this.balls[i]
        if (!ball.show) {
          ball.show = true
          ball.el = el
          this.dropBalls.push(ball)
          return
        }
      }
    },
    beforeEnter (el) {
      // 开始进入事件
      console.log(el)
      let count = this.balls.length
      while (count--) {
        let ball = this.balls[count]
        if (ball.show) {
          let rect = ball.el.getBoundingClientRect()
          let x = rect.left - 32
          let y = -(window.innerHeight - rect.top - 22)
          el.style.display = ''
          el.style.webkitTransform = `translate3d(0,${y}px,0`
          el.style.transform = `translate3d(0,${y}px,0`
          let inner = el.getElementsByClassName('inner-hook')[0]
          inner.style.webkitTransform = `translate3d(${x}px,0,0`
          inner.style.transform = `translate3d(${x}px,0,0`
        }
      }
    },
    enter (el, done) {
      // 进入事件
      console.log(el)
      /* eslint-disable no-unused-vars */
      let rf = el.offsetHeight
      this.$nextTick(() => {
        el.style.webkitTransform = 'translate3d(0,0,0)'
        el.style.transform = 'translate3d(0,0,0)'
        let inner = el.getElementsByClassName('inner-hook')[0]
        inner.style.webkitTransform = 'translate3d(0,0,0)'
        inner.style.transform = 'translate3d(0,0,0)'
        el.addEventListener('transitionend', done)
      })
    },
    afterEnter (el) {
      console.log(el)
      // 进入后事件
      let ball = this.dropBalls.shift()
      if (ball) {
        ball.show = false
        el.style.display = 'none'
      }
    }
  },
  components: {
    cartControl
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/styles/mixin.styl"
  .shopcart
    position fixed
    z-index 50
    left 0
    bottom 0
    width 100%
    height 48px
    .content
      display flex
      background #141d27
      font-size 0
      .content-left
        flex 1
        .logo-wrapper
          display inline-block
          position relative
          top -10px
          margin 0 12px
          padding 6px
          width 56px
          height 56px
          box-sizing border-box
          vertical-align top
          border-radius 50%
          background #141d27
          .logo
            width 100%
            height 100%
            border-radius 50%
            background rgb(43,52,60)
            text-align center
            &.highlight
              background rgb(0,160,220)
            .icon-shopping_cart
              line-height 44px
              font-size 24px
              color #80858a
              &.highlight
                color white
          .num{
            position absolute
            top 0
            right 0
            width 24px
            height 16px
            line-height 16px
            text-align center
            border-radius 16px
            font-size 9px
            font-weight 700
            color white
            background rgb(240,20,20)
            box-shadow 0 4px 8px 0 rgba(0,0,0,0.4)
          }
        .price
          display inline-block
          vertical-align top
          line-height 24px
          margin-top 12px
          box-sizing border-box
          padding-right 12px
          border-right 1px solid rgba(255,255,255,0.1)
          font-size 16px
          font-weight 700
          color rgba(255,255,255,0.4)
          &.highlight
            color  white
        .desc
          display inline-block
          vertical-align top
          line-height 24px
          margin 12px 0 0 12px
          font-size 10px
          color rgba(255,255,255,0.4)
      .content-right
        flex 0 0 105px
        width 105px
        .pay
          height 48px
          line-height 48px
          text-align center
          font-size 12px
          background #2b333b
          font-weight 700
          color rgba(255,255,255,0.4)
          &.not-enough
            background #2b333b
          &.enough
            background #00b43c
            color white
    .ball-container
      .ball
        position flex
        left 32px
        bottom 22px
        z-index: 200
        transition: all 0.4s cubic-bezier(0.49, -0.29, 0.75, 0.41)
        .inner
          width 16px
          height 16px
          border-radius 50%
          background rgb(0,160,220)
          transition: all 0.4s linear
    .shopcart-list
      position absolute
      top 0
      left 0
      z-index -1
      width 100%
      transform: translate3d(0, -100%, 0)
      &.fold-enter-active, &.fold-leave-active
        transition all 0.5s
      &.fold-enter, &.fold-leave-to
        transform translate3d(0,0,0)
      .list-header
        height 40px
        line-height 40px
        padding 0 18px
        background #f3f5f7
        border-bottom 1px solid rgba(7,17,27,0.1)
        .title
          float left
          font-size 14px
          color rgb(7,17,27)
        .empty
          float right
          font-size 12px
          color rgb(0,160,220)
      .list-content
        padding 0 18px
        max-height 217px
        background #fff
        overflow hidden
        .food
          position relative
          padding 12px 0
          box-sizing border-box
          border-1px(rgba(7,17,27,0.1))
          .name
            line-height 24px
            font-size 14px
            color rgb(7,17,27)
          .price
            position absolute
            right 90px
            bottom 12px
            line-height 24px
            font-size 14px
            font-weight 700
            color rgb(240,20,20)
          .cartcontrol-wrapper
            position absolute
            right 0
            bottom 6px
  .list-mask
    position: fixed
    top: 0
    left: 0
    width: 100%
    height: 100%
    z-index: 40
    backdrop-filter: blur(10px)
    opacity: 1
    background: rgba(7, 17, 27, 0.6)
    &.fade-enter-active, &.fade-leave-active
      transition: all 0.5s
    &.fade-enter, &.fade-leave-active
      opacity: 0
      background: rgba(7, 17, 27, 0)

</style>
