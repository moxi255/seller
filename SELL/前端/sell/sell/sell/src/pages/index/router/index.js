import Vue from 'vue'
import Router from 'vue-router'
import goods from '../../../components/goods/goods'
import ratings from '../../../components/ratings/ratings'
import seller from '../../../components/seller/seller'
import add from '../../../components/add/add'
import update from '../../../components/update/update'
import deletes from '../../../components/deletes/deletes'
import manage from '../../../components/manage/manage'
import order from '../../../components/order/order'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: 'login.html'
    },
    {
      path: '/index.html',
      redirect: '/goods'
    },
    {
      path: '/goods',
      name: 'goods',
      component: goods
    },
    {
      path: '/ratings',
      name: 'ratings',
      component: ratings
    },
    {
      path: '/seller',
      name: 'seller',
      component: seller
    },
    {
      path: '/add',
      name: 'add',
      component: add
    },
    {
      path: '/update',
      name: 'update',
      component: update
    },
    {
      path: '/deletes',
      name: 'deletes',
      component: deletes
    },
    {
      path: '/manage',
      name: 'manage',
      component: manage
    },
    {
      path: '/order',
      name: 'order',
      component: order
    }
  ]
})
