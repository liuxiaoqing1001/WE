<template>
  <div class="container">
    <form id="LoginForm">
      <div class="form-group">
        <label for="userName">用户名</label>
        <input type="text" class="input-sm" id="userName" name="loginName" placeholder="请输入用户名" size="43" autocomplete="off">
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <label class="col-md-offset-8"><a href="#">忘记密码</a></label>
        <input type="password" class="input-sm" id="password" name="loginPass" placeholder="请输入密码" size="43">
      </div>
      <div class="form-group">
        <button type="button" class="btn btn-info" id="btnLogin">登录</button>
      </div>
      <div class="form-group">
        <label>不存在账号？<a href="#">注册</a></label>
      </div>
    </form>
  </div>
</template>

<script>
    export default {
        name: "Login"
    }
</script>

<style scoped>

</style>
