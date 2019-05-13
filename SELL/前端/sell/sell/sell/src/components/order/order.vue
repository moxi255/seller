<template>
  <div class="order">
    <div class="title">查看订单</div>
    <input type="text" placeholder="请输入订单状态" v-model="status">
    <button v-on:click="inquiry">查询</button>
    <ul class="slider">
      <li v-for="(item,index) in orders" :key="index"  class="content">
          <mt-cell-swipe :title="item.id + item.status + item.orderTime" :right="[{
              content: '删除',
              style:{
              background :'#0098ff',
              color:'white'},
              handler: () => deleteItem(item.id)
            }]">
          </mt-cell-swipe>
      </li>
    </ul>
    <router-link :to="{ path: '/manage'}" replace>
      <button>返回主页</button>
    </router-link>
  </div>
</template>

<script type="text/ecmascript-6">
import Vue from 'vue'
import {CellSwipe} from 'mint-ui'
import 'mint-ui/lib/style.css'
import {rootPath} from '../../apiConfig/api'
Vue.component(CellSwipe.name, CellSwipe)
export default {
  created () {
    this.getData()
  },
  data () {
    return {
      status: '',
      orders: [],
      startX: 0, // 触摸位置
      endX: 0, // 结束位置
      moveX: 0, // 滑动时的位置
      disX: 0, // 移动距离
      deleteSlider: '' // 滑动时的效果,使用v-bind:style="deleteSlider"
    }
  },
  methods: {
    getData () {
      this.$http.get(rootPath + '/orderes').then(response => {
        if (response.status === 200) {
          this.orders = response.body
          console.log(this.orders)
        }
      }, response => {
        console.log(response.status)
      })
    },
    inquiry () {
      // alert('status=' + this.status)
      this.$http.post(rootPath + '/orderes1', {status: this.status}, {emulateJSON: true}).then(response => {
        this.orders = response.body
        console.log(response)
        /** if (response.status === 200) {
          this.orders = response.body
          console.log(this.orders)
        } */
      }, response => {
        console.log(response.status)
      })
    },
    deleteItem (index) {
      this.$confirm('此操作将永久删除该行是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.delete(rootPath + '/orderes/' + index)
          .then(function (response) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getData()
          })
          .catch(function (response) { console.error(response) })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped lang="less">
  input{
  display :block;
  width :250px;
  height :40px;
  margin :0 auto;
  border-style: solid;
  border-bottom-width: 1px;
  border-bottom-color: #030000;}
  mt-cell-swipe{
    width: 100%;
    height:50px;
  }
  .slider{
    width: 100%;
    height:auto;
    position: relative;
    user-select: none;
    .content{
      height:50px;
      position: relative;
      left: 0;
      right: 0;
      top: 0;
      bottom: 0;
      background:white;
      z-index: 100;
      //    设置过渡动画
      transition: 0.3s;
    }
  }
  .title{
    text-align :center;
    font-size :20px;
    padding-bottom :10px;
    font-weight :700;}
</style>
