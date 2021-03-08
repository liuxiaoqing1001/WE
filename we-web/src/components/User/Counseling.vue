<template>
  <div style="margin-top: 100px">




    <div style="border: 1px solid gray">
      <shrink-view v-model="open">

        <img src="../../assets/1.jpeg" height="640" width="1024"/>
      </shrink-view>
      <input type="checkbox" v-model="open">
    </div>

  </div>
</template>

<script>
  import ShrinkView from '../../components/User/ShrinkView';
    export default {
        name: "Counseling",


      components: {
        ShrinkView
      },
      data () {
        return {
          open: false
        }
      }


    }
</script>

<style scoped>

</style>
