<template>
  <main class="ui-page-body">
    <div class="ui-page-container" style="max-width: 1000px;">
      <div class="simple-answer-card">
        <ul class="answer-list">
          <li class="item">
            <button class="ui-raised-button round-btn unknown current" type="button" tabindex="0" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
              <div class="ui-raised-button-wrapper">
                <div class="mu-ripple-wrapper"></div>
                <span class="ui-raised-button-label">1</span>
              </div>
            </button>
          </li>
        </ul>
      </div>
      <div class="exam-box">
        <h3 class="title">所有的苹果都是水果</h3>
        <ul class="options">
          <li class="item">正确</li>
          <li class="item">错误</li>
        </ul>
        <ul class="options"></ul>
        <div class="op">
          <button class="ui-raised-button btn disabled" disabled="" type="button" style="outline: none; appearance: none;">
            <div class="ui-raised-button-wrapper">
              <span class="ui-raised-button-label">上一题</span>
            </div>
          </button>
          <button class="ui-raised-button btn ui-raised-button-primary ui-raised-button-inverse" type="button" tabindex="0" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
            <div class="ui-raised-button-wrapper">
              <div class="mu-ripple-wrapper"></div>
              <span class="ui-raised-button-label">下一题</span>
            </div>
          </button>
          <button class="ui-raised-button btn" type="button" tabindex="0" style="user-select: none; outline: none; cursor: pointer; appearance: none;">
            <div class="ui-raised-button-wrapper">
              <div class="mu-ripple-wrapper"></div>
              <span class="ui-raised-button-label">提交</span>
            </div>
          </button>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
    export default {
      name: "MentalTest",

      data(){
          return{

          }
      },

    }
</script>

<style scoped>
  .ui-page-body {
    position: absolute;
    top: 64px;
    left: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    padding-top: 0;
    overflow: auto;
  }

  .ui-page-container {
    margin: 0 auto;
    padding: 16px;
  }

  /*.meta-list {*/
  /*  margin-bottom: 16px;*/
  /*  color: #999;*/
  /*}*/

  /*.meta-list .item {*/
  /*  float: left;*/
  /*  margin-right: 16px;*/
  /*}*/

  .mu-ripple-wrapper {
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
  }

  li, ol, ul {
    list-style: none;
  }

  h1, h2, h3, h4, h5, h6, li, ol, ul {
    padding: 0;
    margin: 0;
    font-size: inherit;
    font-weight: inherit;
  }

  li {
    display: list-item;
    text-align: -webkit-match-parent;
  }

  /*.meta-list:after {*/
  /*  clear: both;*/
  /*  content: ".";*/
  /*  display: block;*/
  /*  height: 0;*/
  /*  line-height: 0;*/
  /*  overflow: hidden;*/
  /*}*/

  .simple-answer-card {
    padding: 16px;
    margin-bottom: 16px;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  /*.simple-answer-card .card-title {*/
  /*  font-size: 18px;*/
  /*  margin-bottom: 16px;*/
  /*}*/

  /*h2 {*/
  /*  display: block;*/
  /*  font-size: 1.5em;*/
  /*  margin-block-start: 0.83em;*/
  /*  margin-block-end: 0.83em;*/
  /*  margin-inline-start: 0px;*/
  /*  margin-inline-end: 0px;*/
  /*  font-weight: bold;*/
  /*}*/

  .simple-answer-card .answer-list .item {
    float: left;
    margin-right: 16px;
    margin-bottom: 16px;
  }

  .simple-answer-card .answer-list:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    line-height: 0;
    overflow: hidden;
  }

  .exam-box {
    padding: 16px;
    margin-bottom: 24px;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  .exam-box .index, .exam-box .title, .exam-box .type {
    margin-bottom: 16px;
  }

  .exam-box .title {
    font-size: 30px;
  }

  h3 {
    display: block;
    font-size: 1.17em;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
  }

  .exam-box .options .item {
    padding: 16px;
    margin-bottom: 4px;
    cursor: pointer;
    border: 1px solid #f1f1f1;
  }

  .exam-box .op .btn {
    margin-right: 8px;
  }

  .ui-raised-button.disabled {
    color: rgba(0,0,0,.3);
    cursor: not-allowed;
    background-color: #e6e6e6;
    box-shadow: none;
  }

  .ui-raised-button {
    display: inline-block;
    overflow: hidden;
    position: relative;
    border-radius: 2px;
    height: 36px;
    line-height: 36px;
    min-width: 88px;
    transition-duration: .3s;
    transition-timing-function: cubic-bezier(.23,1,.32,1);
    -webkit-transform: translateZ(0);
    transform: translateZ(0);
    text-decoration: none;
    text-transform: uppercase;
    border: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    background-color: #fff;
    color: rgba(0,0,0,.87);
    -webkit-box-flex: 0;
    -webkit-flex-shrink: 0;
    -ms-flex: 0 0 auto;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    margin: 0;
    outline: 0;
    padding: 0;
    cursor: pointer;
    box-shadow: 0 1px 6px rgba(0,0,0,.117647), 0 1px 4px rgba(0,0,0,.117647);
  }

  /*.ui-raised-button.ui-raised-button-inverse {*/
  /*  color: #fff;*/
  /*}*/
</style>










