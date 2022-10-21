<template>
  <div>
    <van-area title="标题" :area-list="areaList" @confirm="showArea"/>
  </div>
</template>

<script>
import {areaList} from '@vant/area-data'

export default {
  data() {
    return {
      areaList

    };
  },
  methods: {
    showArea(area) {
      console.log(area);
    },
  }

}
</script>

<style scoped>

</style>