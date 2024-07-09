<script setup>
import {defineComponent, onBeforeMount} from 'vue';

defineComponent({
  name: 'Index',
});

onBeforeMount(() => {

})
</script>

<template>
  <div class="index">
    <div class="head">
      <ul class="navigator">
        <li>账号中心</li>
        <li>系统设置</li>
      </ul>
    </div>
    <div class="menu">MENU</div>
    <div class="view">VIEW</div>
  </div>
</template>

<style scoped lang="less">
.head{
  top: 0;
  left: 0;
  width: 100%;
  height: 60rem;
  color: #ffffff;
  font-size: 16rem;
  padding: 0 38rem;
  position: absolute;
  background: linear-gradient(83deg, #0f71e2 0%, #0f71e2 100%);

  .navigator {
    height: 100%;
    display: flex;
    align-items: center;

    li {
      margin: 0 8rem;
      cursor: pointer;
      padding: 8rem 12rem;
      border-radius: 8rem;
      border: solid 1rem #65a5ff;

      &:hover{
        color: #65a5ff;
        font-weight: bold;
        border-color: #ffffff;
        background-color: #ffffff;
      }
    }
  }
}
.menu{
  left: 0;
  top: 60rem;
  width: 180rem;
  position: absolute;
  height: calc(100% - 60rem);

  .navigator, .linker {
    height: 60rem;
  }

  .linker.active{
    background: linear-gradient(90deg, #287bf1 0%, #65a5ff 100%);
  }
}
.view{
  right: 0;
  top: 60rem;
  overflow: auto;
  position: absolute;
  width: calc(100% - 180rem);
  height: calc(100% - 60rem);
}
</style>