<template>
  <!-- view -->
  <div id="app">
    <!-- 导航栏 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-success mb-5">
      <a class="navbar-brand" href="#">{{title}}</a>
      <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse"
              data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="collapsibleNavId">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <!-- <li class="nav-item active">
          <a class="nav-link" href="#">Home</span></a>
      </li> -->
          <!-- 菜单 -->
          <li v-for="(menu, index) in  menus" :key="index" class="nav-item">
            <a class="nav-link" :class="{active:index==0}" href="#">{{menu}}</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">关于</a>
            <div class="dropdown-menu" aria-labelledby="dropdownId">
              <a v-for="(about, index) in abouts" :key="index" class="dropdown-item"
                 href="#">{{about}}</a>

            </div>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="搜索题目.用户">
          <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">搜索</button>
        </form>
      </div>
    </nav>
    <!-- 答题区域 -->

    <div v-if="!isFinish" class="container ">
      <!-- 进度条 -->
      <div class="progress mb-3">
        <div class="progress-bar progress-bar-striped progress-bar-animated bg-primary" role="progressbar"
             :style="progressStyle" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">{{progressBar}}
        </div>
      </div>
      <div class="jumbotron">

        <h1 class="display-4"><span class="badge badge-dark mr-3">{{question.id}}</span>{{question.title}}</h1>

        <hr class="my-2">
        <!-- 选项 -->
        <div v-for="(item, i) in question.items" :key="i" class="form-check my-3">
          <label class="form-check-label">
            <input @change="setAnswer(question.id)" v-model="answer" type="radio" class="form-check-input"
                   name="item" id="" value="checkedValue" :value="itemIndexs[i]">
            <span class="badge badge-pill badge-danger mr-2">{{itemIndexs[i]}}</span>
            {{item}}
          </label>
        </div>

        <!-- 按钮 -->
        <!-- h行 -->
        <div class="row">
          <div class="col-lg-6 col-sm-12">
            <button v-if="progress==0" disabled type="button" class="btn btn-danger btn-block disabled">上一题
            </button>
            <button v-else @click="prev()" type="button" class="btn btn-danger btn-block">上一题</button>
          </div>
          <div class="col-lg-6 col-sm-12">
            <button v-if="isLast"
                    @click="submit()"
                    type="button" class="btn btn-primary btn-block mt-sm-3 mt-lg-0 ">提交</button>
            <button v-else @click="next()" type="button"
                    class="btn btn-success btn-block mt-sm-3 mt-lg-0">下一题</button>
          </div>
        </div>



      </div>
      <!-- 题目的按钮 -->
      <span v-for="(item, index) in questionList" :key="index">
               <button @click="skip(index)" v-if="answers.has(index+1)" type="button" class="btn btn-primary">{{index+1}}</button>
               <button @click="skip(index)" v-else type="button" class="btn btn-danger">{{index+1}}</button>
           </span>
    </div>



    <!-- 显示答案 -->
    <div v-else class="container">
      <div class="jumbotron">
        <h1 class="display-3 text-center">答案与成绩</h1>

        <hr class="my-2">
        <table class="table table-bordered table-striped">
          <thead class="thead-dark text-center">
          <tr>
            <th>序号</th>
            <th>答案</th>
            <th>正确答案</th>
            <th>得分</th>
          </tr>
          </thead>
          <tbody>
          <tr  v-for="([k,v], index) in answers" :key="index">
            <td>{{k}}</td>
            <td><span v-if="v===correct(k)" class="badge badge-pill badge-danger">{{v}}</span>
              <span v-else class="badge badge-pill badge-primary">{{v}}</span>


            </td>
            <!-- 根据id获取正确答案 -->
            <td><span class="badge badge-pill badge-danger">{{correct(k)}}</span></td>

            <!-- 根据索引获取正确答案 -->
            <!-- <td><span class="badge badge-pill badge-danger">{{questionList[index].answer}}</span></td> -->
            <td>
              {{v===correct(k)?20:0}}
            </td>
          </tr>

          <!-- 总计 -->
          <tr
            class="text-center h3 ">
            <td colspan="3">总成绩</td>
            <td > {{score}}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "MentalTest",
      data(){
          return{
            score:0,
            title: '在线测试',
            menus: ['首页', '单选题', '多选题', '模拟测试'],
            abouts: ['联系我们', '加入我们', '其他信息'],
            isFinish: false,
            progress: 0,
            size: 0,

            answer: '',
            answers: new Map(),

            itemIndexs: ['A', 'B', 'C', 'D', 'E', 'F'],

            questionList: [
              {
                id: 1,
                title: '最好的编程语言?',
                items: ['PHP', 'C', 'JS', 'Java', 'GO'],
                answer: 'A'
              },
              {
                id: 2,
                title: '最高级的数据库？',
                items: ['Redit', 'MySQL', 'postgreSQL', 'MongDB'],
                answer: 'C'
              },
              {
                id: 3,
                title: '开源数据是？',
                items: ['DB2', 'Oracle', 'SQL Server', 'MySQL'],
                answer: 'A'
              },
              {
                id: 4,
                title: '市值最高的公司？',
                items: ['微软', '亚马逊', '苹果', '中国建行'],
                answer: 'C'
              },
              {
                id: 5,
                title: '最好的前端框架？',
                items: ['Angular', 'Vue', 'React', 'Java'],
                answer: 'B'
              }
            ]
          }

      },
      methods: {

        submit:function(){
          // 答题完毕，提交答案
          this.isFinish=true;
          for (const q of this.questionList) {
            if(q.answer===this.answers.get(q.id)){
              this.score+=20;
            }

          }

        },


        skip:function(i){
          this.progress=i;
          this.answer='';

        },

        setAnswer: function (id) {
          //判断是否重复的一道题
          if (!this.answers.has(id)) {
            // 答题的数量
            this.size++;
          }

          this.answers.set(id, this.answer);
          // console.log(this.answers);


        },
        next: function () {
          this.progress++;
          if (this.answers.has(this.progress)) {
            this.answer = this.answers.get(this.progress + 1);
          } else {
            this.answer = '';
          }


        },
        prev: function () {
          //获得上一题的答案并且复位
          this.answer = this.answers.get(this.progress);
          this.progress--;

        }

      },
      computed: {
        // 根据id去获取题目的正确答案
        correct(){
          return function (id) {
            for(const i of this.questionList){
              if(i.id==id){
                return i.answer;
              }
            }

          }
        },


        // isFinish:function(){
        //     return this.size===this.questionList.length;
        // },
        question: function () {
          return this.questionList[this.progress];
        },
        isLast: function () {
          return this.progress == this.questionList.length - 1;
        },
        // progressStyle: function () {
        //     return `width:${this.progressChange}%;`;
        // },

        progressBar: function () {
          return Math.round(this.size / this.questionList.length * 100);
        },

        progressStyle: function () {
          return `width:${this.progressBar}%;`;
        }

      },
      mounted() {

      }
    }
</script>

<style scoped>

</style>








