<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
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
      <el-input class="form-input" label="编码" placeholder="请输入编码" v-model="data.code"></el-input>
      <el-input class="form-input" label="账号" placeholder="请输入账号" v-model="data.username"></el-input>
      <el-input class="form-input" label="密码" placeholder="请输入密码" v-model="data.password"></el-input>
      <el-input class="form-input" label="昵称" placeholder="请输入昵称" v-model="data.nickname"></el-input>
      <el-input class="form-input" label="电话" placeholder="请输入电话" v-model="data.phone"></el-input>
      <el-input class="form-input" label="邮箱" placeholder="请输入邮箱" v-model="data.email"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel">关闭</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
