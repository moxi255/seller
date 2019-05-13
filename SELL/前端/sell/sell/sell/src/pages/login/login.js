import Vue from 'vue'
import login from './login.vue'
import VueResource from 'vue-resource'
Vue.use(VueResource)
/* eslint-disable no-new */
new Vue({
  el: '#login',
  render: h => h(login)
})
