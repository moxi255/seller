<template>
  <div id="app">
    <!--传递数据对象-->
    <v-header :seller="seller"></v-header>
    <div class="tab">
      <div class="tab-item">
        <router-link to="/goods">商品</router-link>
      </div>
      <div class="tab-item">
        <router-link to="/ratings">评论</router-link>
      </div>
      <div class="tab-item">
        <router-link to="/seller">商家</router-link>
      </div>
    </div>
    <router-view :seller="seller"></router-view>
  </div>
</template>

<script>
import vHeader from '../../components/header/header'
export default {
  // 数据对象
  data () {
    return {
      seller: {}
    }
  },
  // AJAX请求
  created () {
    this.$http.get('/api/seller').then(response => {
      response = response.body
      console.log(response)
      if (response.errno === 0) {
        this.seller = response.seller
        console.log(response)
      }
    }, response => {
      // error callback
    })
  },
  components: {
    'v-header': vHeader
  }
}
</script>
<style lang="stylus" rel="stylesheet/stylus">
  @import '../../common/style.css'
  @import '../../common/styles/base.styl'
  #app
    .tab
      display:flex
      width: 100%
      height: 40px
      line-height 40px
      .tab-item
        flex :1
        text-align :center
</style>
