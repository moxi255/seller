<template>
  <div class="prompt">
    <div class="tishi">下单成功，请等待送达</div>
    <img src="../../pages/prompt/logo.jpg" height="500" width="600">
    <button v-on:click="sure">确认送达</button>
  </div>
</template>

<script type="text/ecmascript-6">
import {rootPath} from '../../apiConfig/api'
function getQueryString (name) {
  var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i')
  var r = window.location.search.substr(1).match(reg)
  if (r != null) {
    return unescape(r[2])
  }
  return null
}
export default {
  name: 'prompt',
  methods: {
    sure () {
      var orderId = getQueryString('orderId')
      alert(orderId)
      this.$http.put(rootPath + '/orderes',
        {
          id: orderId,
          status: '已送达'
        },
        {emulateJSON: false}).then(function (res) {
        if (res.body >= 0) {
        } else {

        }
      }, function (res) {
        console.log(res.status)
      })
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .tishi
    padding-top :20px
    font-size :50px
    text-align :center
  button
    display:block
    width :300px
    height :50px
    margin:0 auto
    padding :10px
    margin-top :10px
    background-color :#0098ff
    font-size :24px
    color:white
  img
    margin: 0 auto
</style>
