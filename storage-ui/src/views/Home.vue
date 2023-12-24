<template>
  <el-row>
    <el-col :span="8">
      <el-card class="box-card">
        <div class="user">
          <img src="../assets/images/user-default.png" alt="">
          <div class="userinfo">
            <p class="name">Admin</p>
            <p class="access">Super Admin</p>
          </div>
        </div>
        <div class="login-info">
          <p>Last login time: <span></span></p>
          <p>Last login location: <span></span></p>
        </div>
      </el-card>
    </el-col>
    <el-col :span="16"><div class="grid-content bg-purple-light"></div></el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {}
  }
}
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }
  .userinfo {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: #999999;
    }
  }
}
.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;
    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
</style>
