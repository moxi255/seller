import Vue from 'vue'
import prompt from './prompt.vue'
import ElementUI from 'element-ui'
import VueResource from 'vue-resource'
Vue.use(ElementUI)
Vue.use(VueResource)

/* eslint-disable no-new */
new Vue({
  el: '#prompt',
  render: h => h(prompt)
})
