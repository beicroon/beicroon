<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import FormInput from "@/components/form/FormInput.vue";
import FormButton from "@/components/form/FormButton.vue";
import {AccountAdminDetailVO as VO, detail} from "./AccountAdmin.http.ts";

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
});

const data = ref<VO>({
  id: props.id,
});

onBeforeMount(async () => {
  const res = await detail(props.id);

  if (res.data) {
    data.value = res.data;
  }
});

const emits = defineEmits(["hide"]);

async function cancel() {
  emits("hide");
}
</script>

<template>
  <form class="detail disabled">
    <div class="view">
      <form-input disabled class="form-input" v-model="data.code">编码</form-input>
      <form-input disabled class="form-input" v-model="data.username">账号</form-input>
      <form-input disabled class="form-input" v-model="data.nickname">昵称</form-input>
      <form-input disabled class="form-input" v-model="data.phone">电话</form-input>
      <form-input disabled class="form-input" v-model="data.email">邮箱</form-input>
    </div>
    <div class="button">
      <form-button class="cancel" @click="cancel">关闭</form-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
