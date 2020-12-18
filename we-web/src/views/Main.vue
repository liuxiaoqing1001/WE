<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="name" label="名称" width="180"></el-table-column>
    <el-table-column prop="password" label="密码" width="180"></el-table-column>
    <el-table-column prop="createTime" label="日期" width="180"></el-table-column>
  </el-table>
</template>

<script>
  export default {
    name: "Main",
    data() {
      return {
        tableData: []
      }
    },
    created() {
      this.findPage()
    },
    methods: {
      findPage() {
        this.axios.get('/user/list')
          .then((res) => {
            console.log(res.data);
            this.tableData = res.data
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  }
</script>

<style scoped>

</style>

<!--<template>-->
<!--  <div id="main">-->

<!--    &lt;!&ndash; 激活的菜单样式为  active 类 &ndash;&gt;-->
<!--    &lt;!&ndash; 为了阻止链接在点击时跳转，我们使用了 "prevent" 修饰符 (preventDefault 的简称)。 &ndash;&gt;-->

<!--    <nav v-bind:class="active" v-on:click.prevent>-->

<!--      &lt;!&ndash; 当菜单上的链接被点击时，我们调用了 makeActive 方法, 该方法在 Vue 实例中创建。 &ndash;&gt;-->

<!--      <a href="#" class="home" v-on:click="makeActive('home')">Home</a>-->
<!--      <a href="#" class="projects" v-on:click="makeActive('projects')">Projects</a>-->
<!--      <a href="#" class="services" v-on:click="makeActive('services')">Services</a>-->
<!--      <a href="#" class="contact" v-on:click="makeActive('contact')">Contact</a>-->
<!--    </nav>-->

<!--    &lt;!&ndash; 以下 "active" 变量会根据当前选中的值来自动变换 &ndash;&gt;-->

<!--    <p>您选择了 <b>{{active}} 菜单</b></p>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--    export default {-->
<!--      name: 'Main',-->
<!--      data () {-->
<!--        return {-->
<!--          active: 'home'-->
<!--        }-->
<!--      },-->
<!--      // 点击菜单使用的函数-->
<!--      methods: {-->
<!--        makeActive: function(item){-->
<!--          // 模型改变，视图会自动更新-->
<!--          this.active = item;-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--</script>-->

<!--<style scoped>-->
<!--  *{-->
<!--    margin:0;-->
<!--    padding:0;-->
<!--  }-->

<!--  body{-->
<!--    font:15px/1.3 'Open Sans', sans-serif;-->
<!--    color: #5e5b64;-->
<!--    text-align:center;-->
<!--  }-->

<!--  a, a:visited {-->
<!--    outline:none;-->
<!--    color: mediumseagreen;-->
<!--  }-->

<!--  a:hover{-->
<!--    text-decoration:none;-->
<!--  }-->

<!--  section, footer, header, aside, nav{-->
<!--    display: block;-->
<!--  }-->

<!--  /*-&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;-->
<!--    菜鸟-->
<!--  &#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;*/-->

<!--  nav{-->
<!--    display:inline-block;-->
<!--    margin:0 auto 45px;-->
<!--    background-color: silver;-->
<!--    box-shadow:0 1px 1px #ccc;-->
<!--    border-radius:2px;-->
<!--  }-->

<!--  nav a{-->
<!--    display:inline-block;-->
<!--    padding: 18px 30px;-->
<!--    color:#fff !important;-->
<!--    font-weight:bold;-->
<!--    font-size:16px;-->
<!--    text-decoration:none !important;-->
<!--    line-height:1;-->
<!--    text-transform: uppercase;-->
<!--    background-color:transparent;-->

<!--    -webkit-transition:background-color 0.25s;-->
<!--    -moz-transition:background-color 0.25s;-->
<!--    transition:background-color 0.25s;-->
<!--  }-->

<!--  nav a:first-child{-->
<!--    border-radius:2px 0 0 2px;-->
<!--  }-->

<!--  nav a:last-child{-->
<!--    border-radius:0 2px 2px 0;-->
<!--  }-->

<!--  nav.home .home,-->
<!--  nav.projects .projects,-->
<!--  nav.services .services,-->
<!--  nav.contact .contact{-->
<!--    background-color: forestgreen;-->
<!--  }-->

<!--  p{-->
<!--    font-size:22px;-->
<!--    font-weight:bold;-->
<!--    color:#7d9098;-->
<!--  }-->

<!--  p b{-->
<!--    color:#ffffff;-->
<!--    display:inline-block;-->
<!--    padding:5px 10px;-->
<!--    background-color: mediumseagreen;-->
<!--    border-radius:2px;-->
<!--    text-transform:uppercase;-->
<!--    font-size:18px;-->
<!--  }-->
<!--</style>-->
