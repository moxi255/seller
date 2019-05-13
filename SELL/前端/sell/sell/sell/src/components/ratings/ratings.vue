<template>
  <div class="ratings">
    <div class="ratings-content">
      <div class="overview">
        <div class="overview-left">
          <h1 class="score">{{seller.score}}</h1>
          <div class="title">综合评价</div>
          <div class="rank">高于周边商家{{seller.rankRate}}%</div>
        </div>
        <div class="overview-right">
          <div class="score-wrapper">
            <span class="title">服务态度</span>
            <star :size="36" :score="seller.serviceScore"></star>
            <span class="score">{{seller.serviceScore}}</span>
          </div>
          <div class="score-wrapper">
            <span class="title">商品评分</span>
            <star :size="36" :score="seller.foodScore"></star>
            <span class="score">{{seller.foodScore}}</span>
          </div>
          <div class="delivery-wrapper">
            <span class="title">送达时间</span>
            <span class="delivery">{{seller.deliveryTime}}分钟</span>
          </div>
        </div>
      </div>
      <split></split>
      <rating-select @select="selectRating" @toggle="toggleContent" :selectType="selectType" :onlyContent="onlyContent" :ratings="ratings"></rating-select>
      <div class="rating-wrapper">
        <ul>
          <li v-for="(ratings,index) in ratings" :key="index" v-show="needShow(ratings.rateType, ratings.text)" class="rating-item">
            <div class="avatar">
              <img width="28" height="28" :src="ratings.avatar">
            </div>
            <div class="content">
              <h1 class="name">{{ratings.username}}</h1>
              <div class="star-wrapper">
                <star :size="24" :score="ratings.score"></star>
                <span class="delivery" v-show="ratings.deliveryTime">{{ratings.deliveryTime}}</span>
              </div>
              <p class="text">{{ratings.text}}</p>
              <div class="recommend" v-show="ratings.recommend && ratings.recommend.length">
                <span class="icon-thumb_up"></span>
                <span class="item" v-for="(item,index) in ratings.recommend" :key="index">{{item}}</span>
              </div>
              <div class="time">
                {{ratings.rateTime | formatDate}}
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import star from '../star/star'
import Split from '../split/split'
import ratingSelect from '../ratingselect/ratingselect'
import BScroll from 'better-scroll'
// 导入日期转换
import {formatDate} from '../../common/js/data'
// const ERR_OK = 0
import {rootPath} from '../../apiConfig/api'
const ALL = 0
export default {
  name: 'ratings',
  props: {
    seller: {
      type: Object
    }
  },
  created () {
    this.classMap = ['decrease', 'discount', 'special', 'invoice', 'guarantee']
    this.$http.get(rootPath + '/foodRatingses').then(response => {
      console.log(response.status)
      if (response.status === 200) {
        this.ratings = response.body
        console.log(this.ratings)
        // 实时更新
        this.$nextTick(() => {
          this._initScroll()
        })
      }
    }, response => {
      console.log(response.status)
    })
  },
  /** this.$http.get('/api/rating').then(response => {
      response = response.body
      console.log(response)
      if (response.errno === ERR_OK) {
        this.ratings = response.seller
        // 实时更新
        this.$nextTick(() => {
          this._initScroll()
        })
      }
    }, response => {
    })
  }, */
  data () {
    return {
      ratings: [],
      selectType: ALL,
      onlyContent: true
    }
  },
  components: {
    star,
    Split,
    ratingSelect
  },
  methods: {
    needShow (type, text) {
      if (this.onlyContent && !text) {
        return false
      }
      if (this.selectType === ALL) {
        return true
      } else {
        return type === this.selectType
      }
    },
    // 按钮选择由rating-select组建触发
    selectRating (type) {
      this.selectType = type
      console.log(type)
      this.$nextTick(() => {
        this.scroll.refresh()
      })
    },
    // 是否显示内容选择由rating-select组建触发
    toggleContent () {
      this.onlyContent = !this.onlyContent
      this.$nextTick(() => {
        this.scroll.refresh()
      })
    },
    // 滚动
    _initScroll () {
      this.scroll = new BScroll('.ratings', {click: true})
    }
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
  },
  filters: {
    formatDate (time) {
      let date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd ')
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/styles/mixin.styl"
  .ratings
    position :absolute
    top:174px
    bottom:0
    left :0
    width :100%
    overflow :hidden
    .overview
      display :flex
      padding:18px 0
      .overview-left
        flex:0 0 137px
        padding:6px 0
        width :137px
        border-right :1px solid rgba(7,17,27,0.1)
        text-align :center
        @media only screen and (max-width:320px)
          flex:0 0 120px
          width:120px
        .score
          margin-bottom :6px
          line-height :28px
          font-size :24px
          color :rgb(255, 153, 0)
        .title
          margin-bottom :8px
          line-height :12px
          font-size :12px
          color :rgb(7,17,27)
        .rank
          line-height :10px
          font-size :10px
          color :rgb(147, 153, 159)
      .overview-right
        flex :1
        padding :6px 0 6px 24px
        @media only screen and (max-width:320px)
          padding-left:6px
        .score-wrapper
          margin-bottom :8px
          font-size :0
          .title
            display :inline-block
            line-height :18px
            vertical-align :top
            font-size :12px
            color :rgb(7,17,27)
          .star
            display :inline-block
            margin : 0 12px
            vertical-align :top
          .score
            display: inline-block
            line-height :18px
            vertical-align :top
            font-size :12px
            color:rgb(255,153,0)
        .delivery-wrapper
          font-size :0
          .title
            line-height :18px
            font-size :12px
            color :rgb(7,17,27)
          .delivery
            margin-left:12px
            font-size :12px
            color :rgb(147,153,159)
    .rating-wrapper
      padding:0 13px
      .rating-item
        display :flex
        padding : 18px 0
        border-1px(rgba(7,17,27,0.1))
        .avatar
          flex :0 0 28px
          width :28px
          margin-right :12px
          img
            border-radius :50%
        .content
          position :relative
          flex :1
          .name
            margin-bottom :4px
            line-height :12px
            font-size :10px
            color :rgb(7,17,27)
          .star-wrapper
            margin-bottom: 6px
            font-size: 0
            .star
              display: inline-block
              margin-right: 6px
              vertical-align: top
            .delivery
              display: inline-block
              vertical-align: top
              line-height: 12px
              font-size: 10px
              color: rgb(147, 153, 159)
          .text
            margin-bottom: 8px
            line-height: 18px
            color: rgb(7, 17, 27)
            font-size: 12px
          .recommend
            line-height: 16px
            font-size: 0
            .icon-thumb_up, .item
              display: inline-block
              margin: 0 8px 4px 0
              font-size: 9px
            .icon-thumb_up
              color: rgb(0, 160, 220)
            .item
              padding: 0 6px
              border: 1px solid rgba(7, 17, 27, 0.1)
              border-radius: 1px
              color: rgb(147, 153, 159)
              background: #fff
          .time
            position: absolute
            top: 0
            right: 0
            line-height: 12px
            font-size: 10px
            color: rgb(147, 153, 159)
</style>
