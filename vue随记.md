### ubuntu20.04 搭建vue开发环境
https://www.cnblogs.com/leemamas/p/13028988.html

注：
创建一个Vue项目：sudo vue init webpack vueDemo
? Project name <vuedemo>
? Project description <vue test>
? Author <lemamas>
? Vue build <standalone>
? Install vue-router? <No>
? Use ESLint to lint your code? <No>
? Set up unit tests <No>
? Setup e2e tests with Nightwatch? <No>
? Should we run `npm install` for you after the project has been created? (recom
mended) <npm>

standalone：独立
vue-router：vue路由
ESLint：开源的JavaScript验证工具（插件化）

### 搭建vue开发环境2
https://blog.csdn.net/qq_42306803/article/details/104126330#16__49

export NODEJS_HOME=/home/liu/java/node-v6.12.3-linux-x64
export PATH=${PATH}:${NODEJS_HOME}/bin



### Vue项目工程中npm run dev到底做了什么？
https://www.cnblogs.com/angel1254/p/10684491.html
总结：
执行了package.json中的script脚本
底层相当执行build/webpack.dev.conf.js
build/webpack.dev.conf.js查询了webpack.base.conf文件，并且合并入新的对象配置
webpack.base.conf文件中设置项目入口src/main.js

### 开启npm run dev，关闭Ctrl+C

### 
目录/文件	说明
build		项目构建(webpack)相关代码
config		配置目录，包括端口号等。我们初学可以使用默认的。
node_modules	npm 加载的项目依赖模块
src		我们要开发的目录，基本上要做的事情都在这个目录里。包含了几个目录及文件：
		assets: 放置一些图片，如logo等。
		components: 目录里面放了一个组件文件，可以不用。
		App.vue: 项目入口文件，我们也可以直接将组件写这里，而不用 components 目录
		main.js: 项目的核心文件。
static		静态资源目录，如图片、字体等。
test		初始测试目录，可删除
.xxxx文件	这些是一些配置文件，包括语法配置，git配置等。
index.html	首页入口文件，你可以添加一些 meta 信息或统计代码啥的。
package.json	项目配置文件。
README.md	项目的说明文档，markdown 格式

.vue文件：
<template></template>：放HTML代码
<script></script>：放JavaScript代码
<style scoped></style>：放CSS代码

------------------------------------------------------------------------------------
每个Vue应用都需要通过实例化Vue来实现
1. 当一个 Vue 实例被创建时，它向 Vue 的响应式系统中加入了其 data 对象中能找到的所有的属性。当这些属性的值发生改变时，html 视图将也会产生相应的变化。

~~~
var vm = new Vue({
//选项
})
~~~

~~~Vue构造器
<div id="vue_det">
	<h1>site : {{site}}</h1>	//{{}}用于输出对象属性和函数返回值
	<h1>url : {{url}}</h1>
	<h1>{{details()}}</h1>
</div>

<script type="text/javascript">
	var vm = new Vue({
		el: '#vue_det',	// DOM 元素中的 id
		data: {		// 定义属性
			site: "java",
			url: "www.xxx.com",
			alexa: "10000"	//alexa
		},
		methods: {	// 定义函数
			details: function() {
				return  this.site + " - 梦想！";
			}
		}
	})
</script>

~~~

2. 实例属性与方法，前缀$

3. Vue.js 的核心是一个允许采用简洁的模板语法来声明式的将数据渲染进 DOM 的系统
数据绑定最常见的形式就是使用 {{...}}（双大括号）的文本插值

~~~ 使用 v-html 指令用于输出 html 代码
<div id="app">
    <div v-html="message"></div>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: '<h1>菜鸟教程</h1>'
  }
})
</script>
~~~

~~~ 属性中的值用 v-bind 指令,判断 use 的值，如果为 true 使用 class1 类的样式，否则不使用该类
<div id="app">
  <label for="r1">修改颜色</label><input type="checkbox" v-model="use" id="r1">
  <br><br>
  <div v-bind:class="{'class1': use}">
    v-bind:class 指令
  </div>
</div>
    
<script>
new Vue({
  el: '#app',
  data:{
      use: false
  }
});
</script>
~~~

~~~ JavaScript表达式
<div id="app">
    {{5+5}}<br>
    {{ ok ? 'YES' : 'NO' }}<br>
    {{ message.split('').reverse().join('') }}
    <div v-bind:id="'list-' + id">菜鸟教程</div>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    ok: true,
    message: 'RUNOOB',
    id : 1
  }
})
</script>
~~~


~~~ 指令是带有 v- 前缀的特殊属性 如：v-if条件
<div id="app">
    <p v-if="seen">现在你看到我了</p>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    seen: true	//为false就不显示了
  }
})
</script>

~~~

~~~ 参数在指令后以冒号指明,如v-bind 指令被用来响应地更新 HTML 属性
<div id="app">
    <pre><a v-bind:href="url">菜鸟教程</a></pre>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    url: 'http://www.runoob.com'
  }
})
</script>
~~~

~~~ v-on 指令 如：反转字符串
<div id="app">
    <p>{{ message }}</p>
    <button v-on:click="reverseMessage">反转字符串</button>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: 'Runoob!'
  },
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
})
</script>
~~~

~~~ 修饰符是以半角句号 . 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定。例如，.prevent 修饰符告诉 v-on 指令对于触发的事件调用 event.preventDefault()：
<form v-on:submit.prevent="onSubmit"></form>
~~~

~~~  input 输入框中我们可以使用 v-model 指令来实现双向数据绑定
<div id="app">
    <p>{{ message }}</p>
    //v-model 指令用来在 input、select、textarea、checkbox、radio 等表单控件元素上创建双向数据绑定，根据表单上的值，自动更新绑定的元素的值。
    <input v-model="message">
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: 'Runoob!'
  }
})
</script>
~~~

~~~ 过滤器（管道符） 如：首字母大写
<div id="app">
  <!-- 在两个大括号中 -->
{{ message | capitalize }}

<!-- 在 v-bind 指令中 -->
<div v-bind:id="rawId | formatId"></div>

//过滤器可以串联：{{ message | filterA | filterB }}
//过滤器是 JavaScript 函数，可接受参数：{{ message | filterA('arg1', arg2) }}
//message 是第一个参数，字符串 'arg1' 将传给过滤器作为第二个参数， arg2 表达式的值将被求值然后传给过滤器作为第三个参数。

</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: 'runoob'
  },
  filters: {
    capitalize: function (value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    }
  }
})
</script>
~~~

~~~ 缩写
<!-- 完整语法 -->
<a v-bind:href="url"></a>
<!-- 缩写 -->
<a :href="url"></a>

<!-- 完整语法 -->
<a v-on:click="doSomething"></a>
<!-- 缩写 -->
<a @click="doSomething"></a>
~~~

~~~ v-if条件 v-else
<div id="app">
    <div v-if="Math.random() > 0.5">
      Sorry
    </div>
    <div v-else>
      Not sorry
    </div>
</div>
    
<script>
new Vue({
  el: '#app'
})
</script>
~~~
~~~ v-else-if
<div id="app">
    <div v-if="type === 'A'">
      A
    </div>
    <div v-else-if="type === 'B'">
      B
    </div>
    <div v-else-if="type === 'C'">
      C
    </div>
    <div v-else>
      Not A/B/C
    </div>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    type: 'C'
  }
})
</script>
~~~

~~~ v-show根据条件展示元素
<h1 v-show="ok">Hello!</h1>
~~~

~~~ v-for循环 如：绑定数据到数组来渲染一个列表
//v-for 指令需要以 site in sites 形式的特殊语法， sites 是源数据数组并且 site 是数组元素迭代的别名
<div id="app">
  <ol>
    <li v-for="site in sites">
      {{ site.name }}
    </li>
  </ol>
</div>
 
<script>
new Vue({
  el: '#app',
  data: {
    sites: [
      { name: 'Runoob' },
      { name: 'Google' },
      { name: 'Taobao' }
    ]
  }
})
</script>
~~~

~~~ 模板中使用 v-for
<ul>
  <template v-for="site in sites">
    <li>{{ site.name }}</li>
    <li>--------------</li>
  </template>
</ul>
~~~

~~~ v-for 迭代对象 可以通过一个对象的属性来迭代数据
<div id="app">
  <ul>
    <li v-for="value in object">
    {{ value }}
    </li>
  </ul>
</div>
 
<script>
new Vue({
  el: '#app',
  data: {
    object: {
      name: '菜鸟教程',
      url: 'http://www.runoob.com',
      slogan: '学的不仅是技术，更是梦想！'
    }
  }
})
</script>
~~~

~~~ v-for 迭代对象 提供第二个的参数为键名：
<div id="app">
  <ul>
    <li v-for="(value, key) in object">
    {{ key }} : {{ value }}
    </li>
  </ul>
</div>
~~~

~~~ v-for 迭代对象 第三个参数为索引：
<div id="app">
  <ul>
    <li v-for="(value, key, index) in object">
     {{ index }}. {{ key }} : {{ value }}
    </li>
  </ul>
</div>
~~~

~~~ v-for 迭代整数 循环整数：
<div id="app">
  <ul>
    <li v-for="n in 10">
     {{ n }}
    </li>
  </ul>
</div>
~~~

~~~ 反转字符串
<div id="app">
  <p>原始字符串: {{ message }}</p>
  <p>计算后反转字符串: {{ reversedMessage }}</p>
</div>
 
<script>
var vm = new Vue({
  el: '#app',
  data: {
    message: 'Runoob!'
  },
  computed: {
    // 计算属性的 getter
    reversedMessage: function () {
      // `this` 指向 vm 实例
      return this.message.split('').reverse().join('')
    }
  }
})

//声明了一个计算属性 reversedMessage 。
//提供的函数将用作属性 vm.reversedMessage 的 getter 。
//vm.reversedMessage 依赖于 vm.message，在 vm.message 发生改变时，vm.reversedMessage 也会更新
</script>
~~~

~~~
可以使用 methods 来替代 computed，效果一样，computed 性能更好，如果不希望缓存，用 methods
computed 是基于它的依赖缓存，只有相关依赖发生改变时才会重新取值
methods ，在重新渲染的时候，函数总会重新调用执行

methods: {
  reversedMessage2: function () {
    return this.message.split('').reverse().join('')
  }
}
~~~

~~~ computed 属性默认只有 getter ，不过在需要时你也可以提供一个 setter
<div id="app">
  <p>{{ site }}</p>
</div>

<script>
var vm = new Vue({
  el: '#app',
  data: {
	name: 'Google',
	url: 'http://www.google.com'
  },
  computed: {
    site: {
      // getter
      get: function () {
        return this.name + ' ' + this.url
      },
      // setter
      set: function (newValue) {
        var names = newValue.split(' ')
        this.name = names[0]
        this.url = names[names.length - 1]
      }
    }
  }
})
// 调用 setter， vm.name 和 vm.url 也会被对应更新
vm.site = '菜鸟教程 http://www.runoob.com';
document.write('name: ' + vm.name);
document.write('<br>');
document.write('url: ' + vm.url);
</script>
~~~

~~~

~~~

~~~

~~~

~~~

~~~

~~~

~~~


