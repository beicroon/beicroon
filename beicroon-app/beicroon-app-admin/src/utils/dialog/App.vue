<script setup lang="ts">
import {ref} from "vue";
import FormButton from "@/components/form/FormButton.vue";

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

const loading = ref(false);

async function doCancel() {
  props.cancel && await props.cancel();

  await props.complete(false);
}

async function doConfirm() {
  loading.value = true;

  await props.confirm();

  loading.value = false;

  await props.complete(true);
}
</script>

<template>
  <div class="dialog">
    <div class="title">{{ title }}</div>
    <div class="message">{{ message }}</div>
    <div class="button">
      <form-button class="cancel" @click="doCancel" :loading="loading">取消</form-button>
      <form-button class="confirm" @click="doConfirm" :loading="loading">确认</form-button>
    </div>
  </div>
</template>

<style scoped lang="less">
@import "@/less/colors";

.dialog {
  display: flex;
  width: 520rem;
  height: 240rem;
  border-radius: 8rem;
  flex-direction: column;
  border: 1rem solid @grey;

  .title {
    height: 60rem;
    font-weight: bold;
    line-height: 60rem;
    text-align: center;
    letter-spacing: 8rem;
    border-bottom: 1rem solid @grey;
  }

  .message {
    flex: 1;
    display: flex;
    text-align: center;
    line-height: 24rem;
    align-items: center;
    padding: 8rem 18rem;
    letter-spacing: 2rem;
    justify-content: center;
  }

  .button {
    height: 60rem;
    display: flex;
    min-width: 80rem;
    align-items: center;
    border-top: 1rem solid @grey;
    justify-content: space-around;

    .form-button {
      color: @white;
      padding: 8rem 0;
      min-width: 80rem;
    }

    .cancel {
      background-color: @dangerous;
    }

    .confirm {
      background-color: @primary;
    }
  }
}
</style>