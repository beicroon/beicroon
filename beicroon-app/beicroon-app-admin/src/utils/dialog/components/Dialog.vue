<script setup lang="ts">
import ElButton from "@/components/elements/ElButton.vue";

const props = defineProps({
  title: {
    type: String,
    required: true,
  },
  message: {
    type: String,
    required: true,
  },
  confirm: {
    type: Function,
    required: true,
  },
  cancel: {
    type: Function,
    default: null,
    required: false,
  },
  complete: {
    type: Function,
    default: null,
    required: false,
  },
});

async function doCancel() {
  props.cancel && await props.cancel();

  await props.complete();
}

async function doConfirm() {
  await props.confirm();

  await props.complete();
}
</script>

<template>
  <div class="dialog">
    <div class="title">{{title}}</div>
    <div class="message">{{message}}</div>
    <div class="message">
      <el-button @click="doCancel">取消</el-button>
      <el-button @click="doConfirm">确认</el-button>
    </div>
  </div>
</template>

<style scoped lang="less">

</style>