<template>
  <div class="title">
    <h1>欢迎进入商家管理</h1>
    <div class="manage-list">
      <router-link :to="{ path: '/add'}" replace>
      <button class="add" >菜品上架</button>
      </router-link>
      <router-link :to="{ path: '/deletes'}" replace>
      <button class="delete">菜品下架</button>
      </router-link>
       <router-link :to="{ path: '/deletes'}" replace>
      <button class="update">查看菜品</button>
       </router-link>
       <router-link :to="{ path: '/order'}" replace>
      <button class="order">查看订单</button>
       </router-link>
   </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'manage'
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .title
    text-align center
  h1
    color :#0098ff
    font-size: 2em
    padding-bottom :100px
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

</style>
