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
      <el-input disabled class="form-input" v-model="data.parentId">父级主键</el-input>
      <el-input disabled class="form-input" v-model="data.parentCode">父级编码</el-input>
      <el-input disabled class="form-input" v-model="data.parentName">父级名称</el-input>
      <el-input disabled class="form-input" v-model="data.code">编码</el-input>
      <el-input disabled class="form-input" v-model="data.name">名称</el-input>
      <el-input disabled class="form-input" v-model="data.path">路径</el-input>
      <el-input disabled class="form-input" v-model="data.sorting">排序</el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel">关闭</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
