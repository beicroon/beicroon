<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {detail, ResourceMenuDetailVO as VO} from "./ResourceMenu.http.ts";

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
      <el-input class="form-input" label="父级主键" placeholder="请输入父级主键" v-model="data.parentId"></el-input>
      <el-input class="form-input" label="父级编码" placeholder="请输入父级编码" v-model="data.parentCode"></el-input>
      <el-input class="form-input" label="父级名称" placeholder="请输入父级名称" v-model="data.parentName"></el-input>
      <el-input class="form-input" label="编码" placeholder="请输入编码" v-model="data.code"></el-input>
      <el-input class="form-input" label="名称" placeholder="请输入名称" v-model="data.name"></el-input>
      <el-input class="form-input" label="路径" placeholder="请输入路径" v-model="data.path"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel">关闭</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
