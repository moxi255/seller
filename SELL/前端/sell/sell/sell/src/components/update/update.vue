<template>
  <!-- 修改商品 -->
  <div class="addprod">
    <div class="title">修改商品</div>
    <el-form ref="addprod" :rules="prodrules" :model="addprod" label-width="80px">
      <el-form-item label="商品名" prop="name">
        <el-input v-model="addprod.name" ></el-input>
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input v-model.number="addprod.price"></el-input>
      </el-form-item>
      <el-form-item label="原价" prop="oldPrice">
        <el-input v-model="addprod.oldPrice"></el-input>
      </el-form-item>
      <el-form-item label="月售" prop="sellCount">
        <el-input v-model.number="addprod.sellCount"></el-input>
      </el-form-item>
      <el-form-item label="好评率" prop="rating">
        <el-input v-model.number="addprod.rating"></el-input>
      </el-form-item>
      <el-form-item label="商品副图" prop="image"  v-model="addprod.icon">
        <el-upload
          name="picture"
          :limit="1"
          class="prod-image"
          action="http://127.0.0.1:8080/upload/icon"
          :on-success="handleIconSuccess"
          :before-upload="beforeUp"
        >
          <img v-if="addprod.iconUrl" :src="addprod.iconUrl" class="cur-image">
          <i v-else class="el-icon-plus prod-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="商品主图" prop="image"  v-model="addprod.image">
        <el-upload
          name="picture"
          :limit="1"
          class="prod-image"
          action="http://127.0.0.1:8080/upload/image"
          :on-success="handleImageSuccess"
          :before-upload="beforeUp"
          :show-file-list="false">
          <img v-if="addprod.imageUrl" :src="addprod.imageUrl" class="cur-image">
          <i v-else class="el-icon-plus prod-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="商品类别" prop="type">
        <el-select v-model="addprod.type" placeholder="请选择商品类别" @change="choice">
          <el-option v-for="item in option" :key="item.foodTypeId" :label="item.foodTypename" :value="item.foodTypeId">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否上架">
        <el-switch v-model="addprod.selling"></el-switch>
      </el-form-item>
      <el-form-item label="商品简介" prop="desc">
        <el-input type="textarea" v-model="addprod.desc" placeholder="请输入商品简介"></el-input>
      </el-form-item>

      <el-form-item label="商品详情" prop="info">
        <mavon-editor :toolbars="markdownOption"  :subfield="false" ref="md"  v-model="addprod.info"></mavon-editor>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="newprod">立即修改</el-button>
        <router-link :to="{ path: '/manage'}" replace>
          <el-button type="primary">返回主页</el-button>
        </router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script type="text/ecmascript-6">
import {rootPath} from '../../apiConfig/api'
export default {
  name: 'update',
  data () {
    return {
      option: [],
      markdownOption: {
        bold: true // 粗体
      },

      addprod: {
        id: '',
        name: '',
        price: '',
        oldPrice: '',
        sellCount: '',
        rating: '',
        type: '',
        desc: '',
        info: '',
        iconUrl: '',
        imageUrl: ''
      },
      prodrules: {
        name: [
          {
            required: true,
            message: '请输入商品名',
            trigger: 'blur'
          },
          {
            min: 3,
            max: 15,
            message: '长度在 3 到 15 个字',
            trigger: 'blur'
          }
        ],
        image: [
          {
            required: true,
            message: '请上传图片',
            trigger: 'blur'
          }
        ],
        price: [
          {
            required: true,
            message: '请输入商品价格',
            trigger: 'blur'
          },
          {
            type: 'number',
            message: '价格必须是数字',
            trigger: 'blur'
          }
        ],
        type: [
          {
            required: true,
            message: '商品必须选择一个类别',
            trigger: 'change'
          }
        ],
        desc: [
          {
            required: true,
            message: '请输入商品简介',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created () {
    this.getData()
    this.$http.get(rootPath + '/foodTypenames').then(response => {
      if (response.status === 200) {
        this.option = response.body
        console.log(this.option)
      }
    }, response => {
      console.log(response.status)
    })
  },
  methods: {
    getData () {
      var id = this.$route.query.name
      this.$http.get(rootPath + '/foods/' + id).then(response => {
        console.log(response.status)
        if (response.status === 200) {
          this.addprod.id = id
          this.addprod.name = response.body.name
          this.addprod.price = response.body.price
          this.addprod.oldPrice = response.body.oldPrice
          this.addprod.sellCount = response.body.sellCount
          this.addprod.rating = response.body.rating
          this.addprod.type = response.body.foodTypeid
          this.addprod.info = response.body.info
          this.addprod.desc = response.body.description
          this.addprod.iconUrl = response.body.icon
          this.addprod.imageUrl = response.body.image
          console.log(response.body)
        }
      }, response => {
        console.log(response.status)
      })
    },
    handleIconSuccess (res, file) {
      console.log(file)
      this.addprod.iconUrl = file.response.src
    },
    handleImageSuccess (res, file) {
      console.log(file)
      this.addprod.imageUrl = file.response.src
    },
    beforeUp (file) {
    },
    choice (selectValue) {
      this.addprod.type = selectValue
      console.log(selectValue)
    },
    newprod () {
      this.$http.put(rootPath + '/foods',
        {
          id: this.addprod.id,
          name: this.addprod.name,
          price: this.addprod.price,
          oldPrice: this.addprod.oldPrice,
          sellCount: this.addprod.sellCount,
          rating: this.addprod.rating,
          foodTypeid: this.addprod.type,
          info: this.addprod.info,
          description: this.addprod.desc,
          icon: this.addprod.iconUrl,
          image: this.addprod.imageUrl
        },
        {emulateJSON: false}).then(function (res) {
        if (res.body >= 0) {
          alert('修改成功！')
          window.location.href = '/manage'
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
  .title
    text-align :center
    font-size :20px
    padding-bottom :10px
    font-weight :700
  .addprod
    padding-right :10px
  .learncontent
  .el-form
    text-align: left
    .el-select
      width: 100%
    .el-switch
      margin: 10px 0 0 0
    .prod-image
      width: 200px
      height: 200px
      border: 1px dashed #d9d9d9
      border-radius: 6px
      cursor: pointer
      position: relative
      overflow: hidden
      .cur-image
        width: 100%
      .prod-uploader-icon
        font-size: 45px
        color: #8c939d
        width: 200px
        height: 200px
        line-height: 200px
        text-align: center;
</style>
