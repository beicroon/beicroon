<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {AdminDetailVO, detail} from "./account.admin.http.ts";

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
});

const data = ref<AdminDetailVO>({
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
      <el-input disabled class="form-input" label="账号" v-model="data.username"></el-input>
      <el-input disabled class="form-input" label="昵称" v-model="data.nickname"></el-input>
      <el-input disabled class="form-input" label="电话" v-model="data.phone"></el-input>
      <el-input disabled class="form-input" label="邮箱" v-model="data.email"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel">关闭</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>