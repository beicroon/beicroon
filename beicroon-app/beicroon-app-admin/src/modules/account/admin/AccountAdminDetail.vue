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
      <el-input class="form-input" placeholder="请输入编码" v-model="data.code">编码</el-input>
      <el-input class="form-input" placeholder="请输入账号" v-model="data.username">账号</el-input>
      <el-input class="form-input" placeholder="请输入昵称" v-model="data.nickname">昵称</el-input>
      <el-input class="form-input" placeholder="请输入电话" v-model="data.phone">电话</el-input>
      <el-input class="form-input" placeholder="请输入邮箱" v-model="data.email">邮箱</el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel">关闭</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
