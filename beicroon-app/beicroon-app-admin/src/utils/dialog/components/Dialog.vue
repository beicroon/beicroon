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
    <div class="title">{{ title }}</div>
    <div class="message">{{ message }}</div>
    <div class="button">
      <el-button class="cancel" @click="doCancel">取消</el-button>
      <el-button class="confirm" @click="doConfirm">确认</el-button>
    </div>
  </div>
</template>

<style scoped lang="less">
.dialog {
  display: flex;
  width: 520rem;
  height: 240rem;
  border-radius: 8rem;
  flex-direction: column;
  border: 1rem solid #e0e4ed;

  .title {
    height: 60rem;
    line-height: 60rem;
    text-align: center;
    border-bottom: 1rem solid #e0e4ed;
  }

  .message {
    flex: 1;
    display: flex;
    padding: 8rem;
    line-height: 24rem;
    align-items: center;
    justify-content: center;
  }

  .button {
    height: 60rem;
    display: flex;
    align-items: center;
    justify-content: space-around;
    border-top: 1rem solid #e0e4ed;

    button {
      color: #ffffff;
      border-radius: 6rem;
      padding: 8rem 24rem;
    }

    .cancel {
      background-color: #f85e5e;
    }

    .confirm {
      background-color: #287bf1;
    }
  }
}
</style>