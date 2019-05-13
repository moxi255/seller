<template>
  <div>
    <div class="login-wrap" v-show="showLogin">
      <div class="logo">
        <img src="../../pages/login/logo.jpg" width="150" height="150">
      </div>
      <h3>登录</h3>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="password" placeholder="请输入密码" v-model="password">
      <button v-on:click="login">登录</button>
      <span v-on:click="ToRegister">没有账号？马上注册</span>
    </div>

    <div class="login-wrap" v-show="showRegister">
      <div class="logo">
        <img width="150" height="150" src="../../pages/login/logo.jpg">
      </div>
      <h3>注册</h3>
      <input type="text" placeholder="请输入用户名" v-model="newUsername">
      <input type="password" placeholder="请输入密码" v-model="newPassword">
      <input type="tele" placeholder="请输入电话" v-model="newTele">
      <input type="address" placeholder="请输入地址" v-model="newAddress">
      <button v-on:click="register">注册</button>
      <span  v-on:click="ToLogin">已有账号？马上登录</span>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
import {setCookie} from '../../common/js/cookieUtil'
import {rootPath} from '../../apiConfig/api'
export default {
  data () {
    return {
      showLogin: true,
      showRegister: false,
      username: '',
      password: '',
      newUsername: '',
      newPassword: '',
      newTele: '',
      newAddress: ''
    }
  },
  mounted () {
  },
  methods: {
    login () {
      if (this.username === '' || this.password === '') {
        alert('请输入用户名或密码')
      } else {
        if (this.username === 'admin' && this.password === 'admin') {
          window.location.href = '/manage'
        } else {
          this.$http.post(rootPath + '/useres/login',
            { username: this.username,
              password: this.password
            },
            {emulateJSON: true}).then(function (res) {
            if (res.body > -1) {
              console.log(res.body + '登陆成功！')
              setCookie('userid', res.body)
              alert('登陆成功！')
              window.location.href = 'index.html'
            } else {
              alert('用户名或密码错误，请重新登陆！')
            }
          }, function (res) {
            console.log(res.status)
          })
        }

      /**  /!**
        var ajax = new XMLHttpRequest()
        ajax.open('get', 'http://127.0.0.1:8080/useres/login?username=' + this.username + '&password=' + this.password)
        ajax.send()
        ajax.onreadystatechange = function () {
          if (ajax.status === 200) {
            alert(ajax.responseText)
            console.log(ajax.response)
            alert('ddd')
          }
        }
        this.toGet('http://127.0.0.1:8080/useres/login', {username: this.username, password: this.password})**/
      }
    },
    ToRegister () {
      this.showRegister = true
      this.showLogin = false
    },
    ToLogin () {
      this.showRegister = false
      this.showLogin = true
    },
    register () {
      this.$http.post(rootPath + '/useres/add',
        { username: this.newUsername,
          password: this.newPassword,
          tele: this.newTele,
          address: this.newAddress
        },
        {emulateJSON: false}).then(function (res) {
        if (res.body.userId >= 0) {
          alert('注册成功！')
          window.location.href = 'login.html'
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
  .logo
    padding-top :120px
  .login-wrap
    text-align :center
    vertical-align :middle
    width 100%
  input
    display :block
    width :250px
    height :40px
    margin :0 auto
    margin-bottom: 10px
    border-style: solid;
    border-bottom-width: 1px
    border-bottom-color #030000
  p
    color :red
  button
    display:block
    width:250px
    line-height: 40px
    margin:0 auto
    background-color: #0098ff
    color:#fff
    font-size:18px
    margin-bottom:5px
    padding :5px
  span
     text-align :center
     cursor :pointer
     span:hover
       color : #0098ff
  .left
    padding-left :300px
  h3
    text-align :center
    font-size :20px
    padding-top :20px
    padding-bottom :30px
  input:focus
    outline :none
</style>
