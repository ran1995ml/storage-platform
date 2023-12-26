<template>
  <el-form ref="form" class="login_container" :model="form">
    <h3 class="login_title">Storage Platform</h3>
    <el-form-item label="Account" prop="username">
      <el-input v-model="form.username" placeholder="please input account"></el-input>
    </el-form-item>
    <el-form-item label="Password" prop="password">
      <el-input type="password" v-model="form.password" placeholder="please input password"></el-input>
    </el-form-item>
    <el-form-item style="text-align: center;margin-top: 40px">
      <el-button @click="submit" style="width: 100%" type="primary">Login</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import Mock from "mockjs"
import Cookie from "js-cookie"
import { getMenu } from "@/api";
export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    submit() {
      this.$refs.form.validate((valid)=>{
        if (valid) {
          getMenu(this.form).then(({data})=>{
            console.log(data)
            if (data.code === 20000) {
              Cookie.set('token', data.data.token)
              this.$router.push('/home')
            } else {
              this.$message.error(data.data.message)
            }
          })
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
.login_container {
  width: 500px;
  border: 1px solid #eaeaea;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
  box-sizing: border-box;
  .login_title {
    text-align: center;
    margin-bottom: 40px;
  }
}
</style>
