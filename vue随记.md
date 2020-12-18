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

### 将bootstrap  bootstrap-vue添加到项目中
npm install bootstrap-vue bootstrap axios


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

~~~ 修饰符  . 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定

Vue.js 通过由点 . 表示的指令后缀来调用修饰符。

v-on 修饰符
.stop - 阻止冒泡
.prevent - 阻止默认事件
.capture - 阻止捕获
.self - 只监听触发该元素的事件
.once - 只触发一次
.left - 左键事件
.right - 右键事件
.middle - 中间滚轮事件

<!-- 阻止单击事件冒泡 -->
<a v-on:click.stop="doThis"></a>
<!-- 提交事件不再重载页面 -->
<form v-on:submit.prevent="onSubmit"></form>
<!-- 修饰符可以串联  -->
<a v-on:click.stop.prevent="doThat"></a>
<!-- 只有修饰符 -->
<form v-on:submit.prevent></form>
<!-- 添加事件侦听器时使用事件捕获模式 -->
<div v-on:click.capture="doThis">...</div>
<!-- 只当事件在该元素本身（而不是子元素）触发时触发回调 -->
<div v-on:click.self="doThat">...</div>

<!-- click 事件只能点击一次，2.1.4版本新增 -->
<a v-on:click.once="doThis"></a>
~~~

~~~ 按键修饰符 Vue 允许为 v-on 在监听键盘事件时添加按键修饰符：
<!-- 只有在 keyCode 是 13 时调用 vm.submit() -->
<input v-on:keyup.13="submit">

<!-- 同上 -->
<input v-on:keyup.enter="submit">
<!-- 缩写语法 -->
<input @keyup.enter="submit">

全部的按键别名：
.enter
.tab
.delete (捕获 "删除" 和 "退格" 键)
.esc
.space
.up
.down
.left
.right
.ctrl
.alt
.shift
.meta

<!-- Alt + C -->
<input @keyup.alt.67="clear">
<!-- Ctrl + Click -->
<div @click.ctrl="doSomething">Do something</div>
~~~

~~~ 修饰符 v-model
.lazy
v-model 在 input 事件中默认同步输入框的值与数据，修饰符 lazy ，转为在 change 事件中同步：
<!-- 在 "change" 而不是 "input" 事件中更新 -->
<input v-model.lazy="msg" >

.number
自动将用户的输入值转为 Number 类型（如果原值的转换结果为 NaN 则返回原值）
修饰符 number 给 v-model 来处理输入值：
<input v-model.number="age" type="number">
这通常很有用，因为在 type="number" 时 HTML 中输入的值也总是会返回字符串类型。

.trim
自动过滤用户输入的首尾空格，可以添加 trim 修饰符到 v-model 上过滤输入：
<input v-model.trim="msg">
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

~~~ v-on 设置事件监听 接收一个定义的方法来调用
<div id="app">
   <!-- `greet` 是在下面定义的方法名 -->
  <button v-on:click="greet">Greet</button>
</div>
 
<script>
var app = new Vue({
  el: '#app',
  data: {
    name: 'Vue.js'
  },
  // 在 `methods` 对象中定义方法
  methods: {
    greet: function (event) {
      // `this` 在方法里指当前 Vue 实例
      alert('Hello ' + this.name + '!')
      // `event` 是原生 DOM 事件
      if (event) {
          alert(event.target.tagName)
      }
    }
  }
})
// 也可以用 JavaScript 直接调用方法
app.greet() // -> 'Hello Vue.js!'
</script>
~~~

~~~ 内联 JavaScript 语句绑定方法
<div id="app">
  <button v-on:click="say('hi')">Say hi</button>
  <button v-on:click="say('what')">Say what</button>
</div>

<script>
new Vue({
  el: '#app',
  methods: {
    say: function (message) {
      alert(message)
    }
  }
})
</script>
~~~

~~~ 监听属性：watch 实现计数器
<div id = "app">
    <p style = "font-size:25px;">计数器: {{ counter }}</p>
    <button @click = "counter++" style = "font-size:25px;">点我</button>
</div>
<script type = "text/javascript">
var vm = new Vue({
    el: '#app',
    data: {
        counter: 1
    }
});
vm.$watch('counter', function(nval, oval) {
    alert('计数器值的变化 :' + oval + ' 变为 ' + nval + '!');
});
</script>
~~~

~~~ 千米和米之间的换算
<div id = "computed_props">
    千米 : <input type = "text" v-model = "kilometers">
    米 : <input type = "text" v-model = "meters">
</div>
<p id="info"></p>
<script type = "text/javascript">
    var vm = new Vue({
    el: '#computed_props',
    data: {
        kilometers : 0,
        meters:0
    },
    methods: {
    },
    computed :{
    },
    watch : {
        kilometers:function(val) {
            this.kilometers = val;
            this.meters = this.kilometers * 1000
        },
        meters : function (val) {
            this.kilometers = val/ 1000;
            this.meters = val;
        }
    }
    });
    // $watch 是一个实例方法
    vm.$watch('kilometers', function (newValue, oldValue) {
    // 这个回调将在 vm.kilometers 改变后调用
    document.getElementById ("info").innerHTML = "修改前值为: " + oldValue + "，修改后值为: " + newValue;
})
</script>
~~~

~~~ 样式绑定 v-bind:class 设置一个对象，从而动态的切换 class
// div class
<div class="active"></div>

// vue
<div id="app">
  <div v-bind:class="{ 'active': isActive }"></div>
</div>

<script>
new Vue({
  el: '#app',
  data: {
    isActive: true
  }
})
</script>

<style>
.active {
	width: 100px;
	height: 100px;
	background: green;
}
</style>

~~~

~~~ 样式绑定 在对象中传入更多属性用来动态切换多个 class 
// div class
<div class="static active text-danger"></div>

// vue
<div id="app">
  <div class="static"
     v-bind:class="{ 'active': isActive, 'text-danger': hasError }">
  </div>
</div>

<script>
new Vue({
  el: '#app',
  data: {
    isActive: true,
	hasError: true
  }
})
</script>

<style>
.active {
	width: 100px;
	height: 100px;
	background: green;
}
.text-danger {
	background: red;
}
</style>
~~~

~~~ 绑定返回对象的计算属性。一个常用且强大的模式
<div id="app">
  <div v-bind:class="classObject"></div>
</div>
<script>

new Vue({
  el: '#app',
  data: {
    isActive: true,
    error: {
      value: true,
      type: 'fatal'
    }
  },
  computed: {
    classObject: function () {
      return {
  base: true,
        active: this.isActive && !this.error.value,
        'text-danger': this.error.value && this.error.type === 'fatal',
      }
    }
  }
})
</script>

<style>
.base {
  width: 100px;
  height: 100px;
}

.active {
  background: green;
}

.text-danger {
  background: red;
}
</style>
~~~

~~~ 内联样式
// div class
<div style="color: green; font-size: 30px;">菜鸟教程</div>

// vue
<div id="app">
	<div v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }">菜鸟教程</div>
</div>

<script>
new Vue({
  el: '#app',
  data: {
    activeColor: 'green',
	fontSize: 30
  }
})
</script>

~~~

~~~ 直接绑定到一个样式对象，让模板更清晰
<div id="app">
  <div v-bind:style="styleObject">菜鸟教程</div>
</div>

<script>
new Vue({
  el: '#app',
  data: {
    styleObject: {
      color: 'green',
      fontSize: '30px'
    }
  }
})
</script>

~~~

~~~ 表单 input 和 textarea 元素中使用 v-model 实现双向数据绑定
<div id="app">
  <p>input 元素：</p>
  <input v-model="message" placeholder="编辑我……">
  <p>消息是: {{ message }}</p>
    
  <p>textarea 元素：</p>
  <p style="white-space: pre">{{ message2 }}</p>
  <textarea v-model="message2" placeholder="多行文本输入……"></textarea>
</div>
 
<script>
new Vue({
  el: '#app',
  data: {
    message: 'Runoob',
    message2: '菜鸟教程\r\nhttp://www.runoob.com'
  }
})
</script>
~~~

~~~ 表单 checkbox 复选框
<div id="app">
  <p>单个复选框：</p>
  <input type="checkbox" id="checkbox" v-model="checked">
  <label for="checkbox">{{ checked }}</label>
    
  <p>多个复选框：</p>
  <input type="checkbox" id="runoob" value="Runoob" v-model="checkedNames">
  <label for="runoob">Runoob</label>
  <input type="checkbox" id="google" value="Google" v-model="checkedNames">
  <label for="google">Google</label>
  <input type="checkbox" id="taobao" value="Taobao" v-model="checkedNames">
  <label for="taobao">taobao</label>
  <br>
  <span>选择的值为: {{ checkedNames }}</span>
</div>
 
<script>
new Vue({
  el: '#app',
  data: {
    checked : false,
    checkedNames: []
  }
})
</script>
~~~

~~~ 表单 radio 单选按钮
<div id="app">
  <input type="radio" id="runoob" value="Runoob" v-model="picked">
  <label for="runoob">Runoob</label>
  <br>
  <input type="radio" id="google" value="Google" v-model="picked">
  <label for="google">Google</label>
  <br>
  <span>选中值为: {{ picked }}</span>
</div>
 
<script>
new Vue({
  el: '#app',
  data: {
    picked : 'Runoob'
  }
})
</script>

~~~

~~~ 表单 select 下拉列表
<div id="app">
  <select v-model="selected" name="fruit">
    <option value="">选择一个网站</option>
    <option value="www.runoob.com">Runoob</option>
    <option value="www.google.com">Google</option>
  </select>
 
  <div id="output">
      选择的网站是: {{selected}}
  </div>
</div>
 
<script>
new Vue({
  el: '#app',
  data: {
    selected: '' 
  }
})
</script>
~~~

~~~ 组件 扩展 HTML 元素，封装可重用的代码
注册一个全局组件语法格式如下：
Vue.component(tagName, options)
tagName 为组件名，options 为配置选项。

注册后，我们可以使用以下方式来调用组件：
<tagName></tagName>
~~~

~~~ 组件 全局组件
<div id="app">
    <runoob></runoob>
</div>
 
<script>
// 注册
Vue.component('runoob', {
  template: '<h1>自定义组件!</h1>'
})
// 创建根实例
new Vue({
  el: '#app'
})
</script>
~~~

~~~ 组件 局部组件
<div id="app">
    <runoob></runoob>
</div>
 
<script>
var Child = {
  template: '<h1>自定义组件!</h1>'
}
 
// 创建根实例
new Vue({
  el: '#app',
  components: {
    // <runoob> 将只在父模板可用
    'runoob': Child
  }
})
</script>
~~~

~~~ 组件 prop 是子组件用来接受父组件传递过来的数据的一个自定义属性
父组件的数据需要通过 props 把数据传给子组件，子组件需要显式地用 props 选项声明 "prop"
<div id="app">
    <child message="hello!"></child>
</div>
 
<script>
// 注册
Vue.component('child', {
  // 声明 props
  props: ['message'],
  // 同样也可以在 vm 实例中像 "this.message" 这样使用
  template: '<span>{{ message }}</span>'
})
// 创建根实例
new Vue({
  el: '#app'
})
</script>
~~~

~~~ 动态 Prop
类似于用 v-bind 绑定 HTML 特性到一个表达式，也可以用 v-bind 动态绑定 props 的值到父组件的数据中。每当父组件的数据变化时，该变化也会传导给子组件：

<div id="app">
    <div>
      <input v-model="parentMsg">
      <br>
      <child v-bind:message="parentMsg"></child>
    </div>
</div>
 
<script>
// 注册
Vue.component('child', {
  // 声明 props
  props: ['message'],
  // 同样也可以在 vm 实例中像 "this.message" 这样使用
  template: '<span>子：{{ message }}</span>'
})
// 创建根实例
new Vue({
  el: '#app',
  data: {
    parentMsg: '父组件内容'
  }
})
</script>

~~~

~~~ 使用 v-bind 指令将 todo 传到每一个重复的组件中
<div id="app">
    <ol>
    <todo-item v-for="item in sites" v-bind:todo="item"></todo-item>
      </ol>
</div>
 
<script>
Vue.component('todo-item', {
  props: ['todo'],
  template: '<li>{{ todo.text }}</li>'
})
new Vue({
  el: '#app',
  data: {
    sites: [
      { text: 'Runoob' },
      { text: 'Google' },
      { text: 'Taobao' }
    ]
  }
})
</script>

prop 是单向绑定的：当父组件的属性变化时，将传导给子组件，但是不会反过来
~~~

~~~ Prop 验证
组件可以为 props 指定验证要求。
为 props 中的值提供一个带有验证需求的对象，而不是一个字符串数组

Vue.component('my-component', {
  props: {
    // 基础的类型检查 (`null` 和 `undefined` 会通过任何类型验证)
    propA: Number,
    // 多个可能的类型
    propB: [String, Number],
    // 必填的字符串
    propC: {
      type: String,
      required: true
    },
    // 带有默认值的数字
    propD: {
      type: Number,
      default: 100
    },
    // 带有默认值的对象
    propE: {
      type: Object,
      // 对象或数组默认值必须从一个工厂函数获取
      default: function () {
        return { message: 'hello' }
      }
    },
    // 自定义验证函数
    propF: {
      validator: function (value) {
        // 这个值必须匹配下列字符串中的一个
        return ['success', 'warning', 'danger'].indexOf(value) !== -1
      }
    }
  }
})
当 prop 验证失败的时候，(开发环境构建版本的) Vue 将会产生一个控制台的警告。

type 可以是下面原生构造器：
String
Number
Boolean
Array
Object
Date
Function
Symbol

type 也可以是一个自定义构造器，使用 instanceof 检测。
~~~

~~~ 自定义事件：父组件是使用 props 传递数据给子组件，但如果子组件要把数据传递回去需要自定义事件
<div id="app">
    <div id="counter-event-example">
      <p>{{ total }}</p>
      <button-counter v-on:increment="incrementTotal"></button-counter>
      <button-counter v-on:increment="incrementTotal"></button-counter>
    </div>
</div>
 
<script>
Vue.component('button-counter', {
  template: '<button v-on:click="incrementHandler">{{ counter }}</button>',
  data: function () {	//data 必须是一个函数（每个实例可以维护一份被返回对象的独立的拷贝）
    return {
      counter: 0
    }
  },
  methods: {
    incrementHandler: function () {
      this.counter += 1
      this.$emit('increment')
    }
  },
})
new Vue({
  el: '#counter-event-example',
  data: {
    total: 0
  },
  methods: {
    incrementTotal: function () {
      this.total += 1
    }
  }
})
</script>
~~~

~~~ 在某个组件的根元素上监听一个原生事件。可以使用 .native 修饰 v-on 。如：
<my-component v-on:click.native="doTheThing"></my-component>

~~~

~~~ 
<div id="components-demo3" class="demo">
    <button-counter2></button-counter2>
    <button-counter2></button-counter2>
    <button-counter2></button-counter2>
</div>
 
<script>
var buttonCounter2Data = {
  count: 0
}
Vue.component('button-counter2', {
    /*
    data: function () {
        // data 选项是一个函数，组件不相互影响
        return {
            count: 0
        }
    },
    */
    data: function () {
        // data 选项是一个对象，会影响到其他实例
        return buttonCounter2Data
    },
    template: '<button v-on:click="count++">点击了 {{ count }} 次。</button>'
})
new Vue({ el: '#components-demo3' })
</script>
~~~

~~~ 组件上的 v-model 默认会利用名为 value 的 prop 和名为 input 的事件。
<input v-model="parentData">
等价于：
<input 
    :value="parentData"
    @input="parentData = $event.target.value"
>

~~~

~~~ 自定义组件 runoob-input，父组件的 num 的初始值 100，更改子组件的值能实时更新父组件的 num：

<div id="app">
    <runoob-input v-model="num"></runoob-input>
    <p>输入的数字为:{{num}}</p>
</div>
<script>
Vue.component('runoob-input', {
    template: `
    <p>   <!-- 包含了名为 input 的事件 -->
      <input
       ref="input"
       :value="value" 
       @input="$emit('input', $event.target.value)"
      >
    </p>
    `,
    props: ['value'], // 名为 value 的 prop
})
   
new Vue({
    el: '#app',
    data: {
        num: 100,
    }
})
</script>

~~~

~~~ 由于 v-model 默认传的是 value，不是 checked，所以对于对于复选框或者单选框的组件时，我们需要使用 model 选项，model 选项可以指定当前的事件类型和传入的 props。

<div id="app">
    <base-checkbox v-model="lovingVue"></base-checkbox> 
     <div v-show="lovingVue"> 
        如果选择框打勾我就会显示。 
    </div>
</div> 
<script>
// 注册
Vue.component('base-checkbox', {
 
  model: {
    prop: 'checked',
    event: 'change'  // onchange 事件
  },
  props: {
    checked: Boolean
  },
   
  template: `
    <input
      type="checkbox"
      v-bind:checked="checked"
      v-on:change="$emit('change', $event.target.checked)"
    >
  `
})
// 创建根实例
new Vue({
  el: '#app',
  data: {
    lovingVue: true
  }
})
</script>


实例中 lovingVue 的值会传给 checked 的 prop，同时当 <base-checkbox> 触发 change 事件时， lovingVue 的值也会更新。

~~~

~~~ 全局指令 v-focus, 该指令的功能是在页面加载时，元素获得焦点：
<div id="app">
    <p>页面载入时，input 元素自动获取焦点：</p>
    <input v-focus>
</div>
 
<script>
// 注册一个全局自定义指令 v-focus
Vue.directive('focus', {
  // 当绑定元素插入到 DOM 中。
  inserted: function (el) {
    // 聚焦元素
    el.focus()
  }
})
// 创建根实例
new Vue({
  el: '#app'
})
</script>

~~~

~~~ 使用 directives 选项来注册局部指令，这样指令只能在这个实例中使用：
<div id="app">
  <p>页面载入时，input 元素自动获取焦点：</p>
  <input v-focus>
</div>
 
<script>
// 创建根实例
new Vue({
  el: '#app',
  directives: {
    // 注册一个局部的自定义指令 v-focus
    focus: {
      // 指令的定义
      inserted: function (el) {
        // 聚焦元素
        el.focus()
      }
    }
  }
})
</script>

~~~

~~~ 钩子函数
bind：只调用一次，指令第一次绑定到元素时调用（定义一个在绑定时执行一次的初始化动作）
inserted：被绑定元素插入父节点时调用
update：被绑定元素所在的模板更新时调用，不管绑定值是否改变
componentUpdated：被绑定元素所在模板完成一次更新周期时调用
unbind：只调用一次，指令与元素解绑时调用

~~~

~~~ 钩子函数的参数
el: 指令所绑定的元素，可以用来直接操作 DOM 。
binding: 一个对象，包含以下属性：
	name: 指令名，不包括 v- 前缀。
	value: 指令的绑定值， 
		例如： v-my-directive="1 + 1", value 的值是 2。
	oldValue: 指令绑定的前一个值，仅在 update 和 componentUpdated 钩子中可用。无论值是否改变都可用。
	expression: 绑定值的表达式或变量名。 
		例如 v-my-directive="1 + 1" ， expression 的值是 "1 + 1"。
	arg: 传给指令的参数。
		例如 v-my-directive:foo， arg 的值是 "foo"。
	modifiers: 一个包含修饰符的对象。 
		例如： v-my-directive.foo.bar, 修饰符对象 modifiers 的值是 { foo: true, bar: true }。
vnode: Vue 编译生成的虚拟节点。
oldVnode: 上一个虚拟节点，仅在 update 和 componentUpdated 钩子中可用。

~~~

~~~ 钩子函数的参数的使用
<div id="app"  v-runoob:hello.a.b="message">
</div>
 
<script>
Vue.directive('runoob', {
  bind: function (el, binding, vnode) {
    var s = JSON.stringify
    el.innerHTML =
      'name: '       + s(binding.name) + '<br>' +
      'value: '      + s(binding.value) + '<br>' +
      'expression: ' + s(binding.expression) + '<br>' +
      'argument: '   + s(binding.arg) + '<br>' +
      'modifiers: '  + s(binding.modifiers) + '<br>' +
      'vnode keys: ' + Object.keys(vnode).join(', ')
  }
})
new Vue({
  el: '#app',
  data: {
    message: '菜鸟教程!'
  }
})
</script>

~~~

~~~ 指令函数可接受所有合法的 JavaScript 表达式，以下实例传入了 JavaScript 对象
<div id="app">
    <div v-runoob="{ color: 'green', text: '菜鸟教程!' }"></div>
</div>
 
<script>
Vue.directive('runoob', function (el, binding) {
    // 简写方式设置文本及背景颜色
    el.innerHTML = binding.value.text
    el.style.backgroundColor = binding.value.color
})
new Vue({
  el: '#app'
})
</script>

~~~

~~~ vue-router 路由允许我们通过不同的 URL 访问不同的内容。
可以实现多视图的单页Web应用（single page web application，SPA）

<router-link to=""> 
一个组件，用于设置一个导航链接，切换不同 HTML 内容。 
to 属性为目标地址， 即要显示的内容。

配置组件和路由映射，再告诉 vue-router 在哪里渲染它们
~~~

~~~ 点击过的导航链接都会加上样式 class ="router-link-exact-active router-link-active"
<div id="app">
  <h1>Hello App!</h1>
  <p>
    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
    <router-link to="/foo">Go to Foo</router-link>
    <router-link to="/bar">Go to Bar</router-link>
  </p>
  <!-- 路由出口 -->
  <!-- 路由匹配到的组件将渲染在这里 -->
  <router-view></router-view>
</div>

<script>
// 0. 如果使用模块化机制编程，導入Vue和VueRouter，要调用 Vue.use(VueRouter)

// 1. 定义（路由）组件。
// 可以从其他文件 import 进来
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
  { path: '/foo', component: Foo },
  { path: '/bar', component: Bar }
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})

// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
const app = new Vue({
  router
}).$mount('#app')

// 现在，应用已经启动了！
</script>
~~~

~~~ <router-link> 属性 to
表示目标路由的链接。 
当被点击后，内部会立刻把 to 的值传到 router.push()，值可以是一个字符串或者是描述目标位置的对象

<!-- 字符串 -->
<router-link to="home">Home</router-link>
<!-- 渲染结果 -->
<a href="home">Home</a>

<!-- 使用 v-bind 的 JS 表达式 -->
<router-link v-bind:to="'home'">Home</router-link>

<!-- 不写 v-bind 也可以，就像绑定别的属性一样 -->
<router-link :to="'home'">Home</router-link>

<!-- 同上 -->
<router-link :to="{ path: 'home' }">Home</router-link>

<!-- 命名的路由 -->
<router-link :to="{ name: 'user', params: { userId: 123 }}">User</router-link>

<!-- 带查询参数，下面的结果为 /register?plan=private -->
<router-link :to="{ path: 'register', query: { plan: 'private' }}">Register</router-link>
~~~

~~~ <router-link> 属性 replace
当点击时，会调用 router.replace() 而不是 router.push()，导航后不会留下 history 记录。

<router-link :to="{ path: '/abc'}" replace></router-link>
~~~

~~~ <router-link> 属性 append
在当前 (相对) 路径前添加其路径。
例如，我们从 /a 导航到一个相对路径 b，如果没有配置 append，则路径为 /b，如果配了，则为 /a/b

<router-link :to="{ path: 'relative/path'}" append></router-link>
~~~

~~~ <router-link> 属性 tag
有时候想要 <router-link> 渲染成某种标签，例如 <li>。 
使用 tag prop 类指定何种标签，同样它还是会监听点击，触发导航。

<router-link to="/foo" tag="li">foo</router-link>
<!-- 渲染结果 -->
<li>foo</li>

~~~

~~~ <router-link> 属性 active-class
设置 链接激活时使用的 CSS 类名。可以通过以下代码来替代。

<style>
   ._active{
      background-color : red;
   }
</style>
<p>
   <router-link v-bind:to = "{ path: '/route1'}" active-class = "_active">
   Router Link 1
   </router-link>
   <router-link v-bind:to = "{ path: '/route2'}" tag = "span">
   Router Link 2
   </router-link>
</p>

~~~

~~~ <router-link> 属性 exact-active-class
配置当链接被精确匹配的时候应该激活的 class。可以通过以下代码来替代。

<p>
   <router-link v-bind:to = "{ path: '/route1'}" exact-active-class = "_active">
   Router Link 1
   </router-link>
   <router-link v-bind:to = "{ path: '/route2'}" tag = "span">
   Router Link 2
   </router-link>
</p>

~~~

~~~ <router-link> 属性 event
声明可以用来触发导航的事件。可以是一个字符串或是一个包含字符串的数组。

<router-link v-bind:to = "{ path: '/route1'}" event = "mouseover">Router Link 1</router-link>

~~~

~~~ 过渡
<transition name = "nameoftransition">
   <div></div>
</transition>

过渡其实就是一个淡入淡出的效果
（v- 是这些类名的默认前缀）
1. v-enter：定义进入过渡的开始状态。在元素被插入之前生效，在元素被插入之后的下一帧移除。

2. v-enter-active：定义进入过渡生效时的状态。在整个进入过渡的阶段中应用，在元素被插入之前生效，在过渡/动画完成之后移除。这个类可以被用来定义进入过渡的过程时间，延迟和曲线函数。

3. v-enter-to: 2.1.8版及以上 定义进入过渡的结束状态。在元素被插入之后下一帧生效 (与此同时 v-enter 被移除)，在过渡/动画完成之后移除。

4. v-leave: 定义离开过渡的开始状态。在离开过渡被触发时立刻生效，下一帧被移除。

5. v-leave-active：定义离开过渡生效时的状态。在整个离开过渡的阶段中应用，在离开过渡被触发时立刻生效，在过渡/动画完成之后移除。这个类可以被用来定义离开过渡的过程时间，延迟和曲线函数。

6. v-leave-to: 2.1.8版及以上 定义离开过渡的结束状态。在离开过渡被触发之后下一帧生效 (与此同时 v-leave 被删除)，在过渡/动画完成之后移除。

~~~

~~~ 过渡 实现
<div id = "databinding">
<button v-on:click = "show = !show">点我</button>
<transition name = "fade">
    <p v-show = "show" v-bind:style = "styleobj">动画实例</p>
</transition>
</div>
<script type = "text/javascript">
var vm = new Vue({
el: '#databinding',
    data: {
        show:true,
        styleobj :{
            fontSize:'30px',
            color:'red'
        }
    },
    methods : {
    }
});
</script>
~~~

~~~ CSS 过渡
<div id = "databinding">
<button v-on:click = "show = !show">点我</button>
<transition name="slide-fade">
    <p v-if="show">hello</p>
</transition>
</div>
<script type = "text/javascript">
new Vue({
    el: '#databinding',
    data: {
        show: true
    }
})
</script>

~~~

~~~ CSS 动画
CSS 动画用法类似 CSS 过渡，但是在动画中 v-enter 类名在节点插入 DOM 后不会立即删除，而是在 animationend 事件触发时删除。

<div id = "databinding">
<button v-on:click = "show = !show">点我</button>
<transition name="bounce">
    <p v-if="show">菜鸟教程 -- 学的不仅是技术，更是梦想！！！</p>
</transition>
</div>
<script type = "text/javascript">
new Vue({
    el: '#databinding',
    data: {
        show: true
    }
})
</script>

~~~

~~~ 自定义过渡的类名优先级高于普通的类名，与第三方（如：animate.css）的动画库结合使用方便

<div id = "databinding">
<button v-on:click = "show = !show">点我</button>
<transition
    name="custom-classes-transition"
    enter-active-class="animated tada"
    leave-active-class="animated bounceOutRight"
>
    <p v-if="show">菜鸟教程 -- 学的不仅是技术，更是梦想！！！</p>
</transition>
</div>
<script type = "text/javascript">
new Vue({
    el: '#databinding',
    data: {
        show: true
    }
})
</script>

~~~

~~~ 显性的过渡持续时间
可以用 <transition> 组件上的 duration 属性定制一个显性的过渡持续时间 (以毫秒计)：

<transition :duration="1000">...</transition>

可以定制进入和移出的持续时间：
<transition :duration="{ enter: 500, leave: 800 }">...</transition>

~~~

~~~ 使用 Velocity.js 
<div id = "databinding">
<button v-on:click = "show = !show">点我</button>
<transition
    v-on:before-enter="beforeEnter"
    v-on:enter="enter"
    v-on:leave="leave"
    v-bind:css="false"
  >
    <p v-if="show">菜鸟教程 -- 学的不仅是技术，更是梦想！！！</p>
</transition>
</div>
<script type = "text/javascript">
new Vue({
  el: '#databinding',
  data: {
    show: false
  },
  methods: {
    beforeEnter: function (el) {
      el.style.opacity = 0
      el.style.transformOrigin = 'left'
    },
    enter: function (el, done) {
      Velocity(el, { opacity: 1, fontSize: '1.4em' }, { duration: 300 })
      Velocity(el, { fontSize: '1em' }, { complete: done })
    },
    leave: function (el, done) {
      Velocity(el, { translateX: '15px', rotateZ: '50deg' }, { duration: 600 })
      Velocity(el, { rotateZ: '100deg' }, { loop: 2 })
      Velocity(el, {
        rotateZ: '45deg',
        translateY: '30px',
        translateX: '30px',
        opacity: 0
      }, { complete: done })
    }
  }
})
</script>

~~~

~~~ Vue.js 混入
混入 (mixins)定义了一部分可复用的方法或者计算属性。混入对象可以包含任意组件选项。当组件使用混入对象时，所有混入对象的选项将被混入该组件本身的选项。

<div id = "databinding"></div>
<script type = "text/javascript">
var vm = new Vue({
	el: '#databinding',
	data: {
	},
	methods : {
	},
});
// 定义一个混入对象
var myMixin = {
	created: function () {
		this.startmixin()
	},
	methods: {
		startmixin: function () {
			document.write("欢迎来到混入实例");
		}
	}
};
var Component = Vue.extend({
	mixins: [myMixin]
})
var component = new Component();
</script>

~~~

~~~ 选项合并
当组件和混入对象含有同名选项时，这些选项将以恰当的方式混合。
比如，数据对象在内部会进行浅合并 (一层属性深度)，在和组件的数据发生冲突时以组件数据优先
<div id = "databinding"></div>
<script type = "text/javascript">
var mixin = {
	created: function () {
		document.write('混入调用' + '<br>')
	}
}
new Vue({
	mixins: [mixin],
		created: function () {
		document.write('组件调用' + '<br>')
	}
});
</script>
~~~

~~~ methods 选项中有相同的函数名，则 Vue 实例优先级会较高
<div id = "databinding"></div>
<script type = "text/javascript">
var mixin = {
	methods: {
		hellworld: function () {
			document.write('HelloWorld 方法' + '<br>');
		},
		samemethod: function () {
			document.write('Mixin：相同方法名' + '<br>');
		}
	}
};
var vm = new Vue({
	mixins: [mixin],
	methods: {
		start: function () {
			document.write('start 方法' + '<br>');
		},
		samemethod: function () {
			document.write('Main：相同方法名' + '<br>');
		}
	}
});
vm.hellworld();
vm.start();
vm.samemethod();
</script>
~~~

~~~ 全局混入
注意使用！ 一旦使用全局混入对象，将会影响到 所有 之后创建的 Vue 实例。使用恰当时，可以为自定义对象注入处理逻辑。

<script type = "text/javascript">
// 为自定义的选项 'myOption' 注入一个处理器。
Vue.mixin({
  created: function () {
    var myOption = this.$options.myOption
    if (myOption) {
      document.write(myOption)
    }
  }
})

new Vue({
  myOption: 'hello!'
})
// => "hello!"
</script>
~~~

~~~ vue.js 建议使用 axios 完成 ajax 请求
Axios 是一个基于 Promise 的 HTTP 库，可以用在浏览器和 node.js 中

安装方法：
使用 cdn:
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
或
<script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>

使用 npm:
$ npm install axios

使用 bower:
$ bower install axios

使用 yarn:
$ yarn add axios
~~~

~~~ GET 方法 
简单的读取 JSON 数据：
<div id="app">
  {{ info }}
</div>
<script type = "text/javascript">
new Vue({
  el: '#app',
  data () {
    return {
      info: null
    }
  },
  mounted () {
    axios
      .get('https://www.runoob.com/try/ajax/json_demo.json')
      .then(response => (this.info = response))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      });
  }
})
</script>

~~~

~~~ 使用 response.data 读取 JSON 数据：
<div id="app">
  <h1>网站列表</h1>
  <div
    v-for="site in info"
  >
    {{ site.name }}
  </div>
</div>
<script type = "text/javascript">
new Vue({
  el: '#app',
  data () {
    return {
      info: null
    }
  },
  mounted () {
    axios
      .get('https://www.runoob.com/try/ajax/json_demo.json')
      .then(response => (this.info = response.data.sites))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      });
  }
})
</script>

~~~

~~~ GET 方法传递参数格式如下：
// 直接在 URL 上添加参数 ID=12345
axios.get('/user?ID=12345')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
 
// 也可以通过 params 设置参数：
axios.get('/user', {
    params: {
      ID: 12345
    }
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });

~~~

~~~ POST  方法
<div id="app">
  {{ info }}
</div>
<script type = "text/javascript">
new Vue({
  el: '#app',
  data () {
    return {
      info: null
    }
  },
  mounted () {
    axios
      .post('https://www.runoob.com/try/ajax/demo_axios_post.php')
      .then(response => (this.info = response))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      });
  }
})
</script>
~~~

~~~ POST 方法传递参数格式如下：
axios.post('/user', {
    firstName: 'Fred',        // 参数 firstName
    lastName: 'Flintstone'    // 参数 lastName
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });

~~~

~~~ axios API
可以通过向 axios 传递相关配置来创建请求。

axios(config)
// 发送 POST 请求
axios({
  method: 'post',
  url: '/user/12345',
  data: {
    firstName: 'Fred',
    lastName: 'Flintstone'
  }
});
//  GET 请求远程图片
axios({
  method:'get',
  url:'http://bit.ly/2mTM3nY',
  responseType:'stream'
})
  .then(function(response) {
  response.data.pipe(fs.createWriteStream('ada_lovelace.jpg'))
});
axios(url[, config])
// 发送 GET 请求（默认的方法）
axios('/user/12345');

~~~

~~~ 请求方法的别名
axios.request(config)
axios.get(url[, config])
axios.delete(url[, config])
axios.head(url[, config])
axios.post(url[, data[, config]])
axios.put(url[, data[, config]])
axios.patch(url[, data[, config]])

~~~

~~~ 并发
处理并发请求的助手函数：

axios.all(iterable)
axios.spread(callback)

~~~

~~~ 创建实例
可以使用自定义配置新建一个 axios 实例：

axios.create([config])
const instance = axios.create({
  baseURL: 'https://some-domain.com/api/',
  timeout: 1000,
  headers: {'X-Custom-Header': 'foobar'}
});

~~~

~~~ 实例方法
以下是可用的实例方法。指定的配置将与实例的配置合并：

axios#request(config)
axios#get(url[, config])
axios#delete(url[, config])
axios#head(url[, config])
axios#post(url[, data[, config]])
axios#put(url[, data[, config]])
axios#patch(url[, data[, config]])

~~~

~~~ 响应结构
axios请求的响应包含以下信息：

{
  // `data` 由服务器提供的响应
  data: {},

  // `status`  HTTP 状态码
  status: 200,

  // `statusText` 来自服务器响应的 HTTP 状态信息
  statusText: "OK",

  // `headers` 服务器响应的头
  headers: {},

  // `config` 是为请求提供的配置信息
  config: {}
}

使用 then 时，会接收下面这样的响应：

axios.get("/user/12345")
  .then(function(response) {
    console.log(response.data);
    console.log(response.status);
    console.log(response.statusText);
    console.log(response.headers);
    console.log(response.config);
  });

~~~

~~~ 配置的默认值
全局的 axios 默认值：

axios.defaults.baseURL = 'https://api.example.com';
axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

~~~

~~~ 拦截器
在请求或响应被 then 或 catch 处理前拦截它们。

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
  }, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  });

~~~

~~~ 移除拦截器，可以这样：
var myInterceptor = axios.interceptors.request.use(function () {/*...*/});
axios.interceptors.request.eject(myInterceptor);

~~~

~~~ 可以为自定义 axios 实例添加拦截器。
var instance = axios.create();
instance.interceptors.request.use(function () {/*...*/});

~~~

~~~ 错误处理：

axios.get('/user/12345')
  .catch(function (error) {
    if (error.response) {
      // 请求已发出，但服务器响应的状态码不在 2xx 范围内
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    console.log(error.config);
  });

~~~

~~~ Get 请求实例，请求地址是一个简单的 txt 文本
<div id="box">
	<input type="button" @click="get()" value="点我异步获取数据(Get)">
</div>
<script type = "text/javascript">
window.onload = function(){
var vm = new Vue({
    el:'#box',
    data:{
        msg:'Hello World!',
    },
    methods:{
        get:function(){
            //发送get请求
            this.$http.get('/try/ajax/ajax_info.txt').then(function(res){
                document.write(res.body);    
            },function(){
                console.log('请求失败处理');
            });
        }
    }
});
}
</script>
~~~

~~~ post 发送数据到后端，需要第三个参数 {emulateJSON:true}。
emulateJSON 的作用： 如果Web服务器无法处理编码为 application/json 的请求，你可以启用 emulateJSON 选项。
<div id="box">
	<input type="button" @click="post()" value="点我异步获取数据(Post)">
</div>
<script type = "text/javascript">
window.onload = function(){
	var vm = new Vue({
	    el:'#box',
	    data:{
	        msg:'Hello World!',
	    },
	    methods:{
	        post:function(){
	            //发送 post 请求
	            this.$http.post('/try/ajax/demo_test_post.php',{name:"菜鸟教程",url:"http://www.runoob.com"},{emulateJSON:true}).then(function(res){
                    document.write(res.body);    
                },function(res){
                    console.log(res.status);
                });
	        }
	    }
	});
}
</script>
~~~

~~~ vue-resource 提供了 7 种请求 API(REST 风格)：
get(url, [options])
head(url, [options])
delete(url, [options])
jsonp(url, [options])
post(url, [body], [options])
put(url, [body], [options])
patch(url, [body], [options])

~~~

~~~ 添加数据动态响应接口
使用 $watch 属性来实现数据的监听，$watch 必须添加在 Vue 实例之外才能实现正确的响应
通过点击按钮计数器会加 1。setTimeout 设置 10 秒后计算器的值加上 20 
<div id = "app">
    <p style = "font-size:25px;">计数器: {{ counter }}</p>
    <button @click = "counter++" style = "font-size:25px;">点我</button>
</div>
<script type = "text/javascript">
var vm = new Vue({
    el: '#app',
    data: {
        counter: 1
    }
});
vm.$watch('counter', function(nval, oval) {
    alert('计数器值的变化 :' + oval + ' 变为 ' + nval + '!');
});
setTimeout(
    function(){
        vm.counter += 20;
    },10000
);
</script>
~~~

~~~ Vue.set 方法用于设置对象的属性，它可以解决 Vue 无法检测添加属性的限制，语法格式如下：

Vue.set( target, key, value )
参数说明：

target: 可以是对象或数组
key : 可以是字符串或数字
value: 可以是任何类型

~~~

~~~ Vue.set前
<div id = "app">
   <p style = "font-size:25px;">计数器: {{ products.id }}</p>
   <button @click = "products.id++" style = "font-size:25px;">点我</button>
</div>
<script type = "text/javascript">
var myproduct = {"id":1, name:"book", "price":"20.00"};
var vm = new Vue({
   el: '#app',
   data: {
      products: myproduct
   }
});
vm.products.qty = "1";
console.log(vm);
vm.$watch('products.id', function(nval, oval) {
   alert('计数器值的变化 :' + oval + ' 变为 ' + nval + '!');
});
</script>
~~~

~~~ Vue.set后
<div id = "app">
<p style = "font-size:25px;">计数器: {{ products.id }}</p>
<button @click = "products.id++" style = "font-size:25px;">点我</button>
</div>
<script type = "text/javascript">
var myproduct = {"id":1, name:"book", "price":"20.00"};
var vm = new Vue({
   el: '#app',
   data: {
      products: myproduct
   }
});
Vue.set(myproduct, 'qty', 1);
console.log(vm);
vm.$watch('products.id', function(nval, oval) {
   alert('计数器值的变化 :' + oval + ' 变为 ' + nval + '!');
});
</script>
~~~

~~~ Vue.delete 用于删除动态添加的属性 语法格式：

Vue.delete( target, key )
参数说明：

target: 可以是对象或数组
key : 可以是字符串或数字

~~~

~~~ 
<div id = "app">
   <p style = "font-size:25px;">计数器: {{ products.id }}</p>
   <button @click = "products.id++" style = "font-size:25px;">点我</button>
</div>
<script type = "text/javascript">
var myproduct = {"id":1, name:"book", "price":"20.00"};
var vm = new Vue({
   el: '#app',
   data: {
      products: myproduct
   }
});
Vue.delete(myproduct, 'price');
console.log(vm);
vm.$watch('products.id', function(nval, oval) {
   alert('计数器值的变化 :' + oval + ' 变为 ' + nval + '!');
});
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

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~

~~~ 

~~~






