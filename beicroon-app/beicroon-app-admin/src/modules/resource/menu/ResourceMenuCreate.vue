<script setup lang="ts">
import {ref} from "vue";
import toast from "@/utils/toast";
import {validateForm} from "@/utils/function.ts";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {create as submit, ResourceMenuCreateDTO as DTO} from "./ResourceMenu.http.ts";

const form = ref();

const data = ref<DTO>({});

const loading = ref(false);

const emits = defineEmits(["hide", "reload"]);

function cancel() {
  emits("hide");
}

async function confirm() {
  loading.value = true;

  if (!validateForm(form)) {
    await toast("请填写必填项！", true);

    loading.value = false;

    return;
  }

  await submit(data.value).finally(() => loading.value = false);

  await toast("添加成功");

  emits("hide");

  emits("reload");
}
</script>

<template>
  <form class="create" ref="form">
    <div class="view">
      <el-input class="form-input" placeholder="请输入父级主键" v-model="data.parentId">父级主键</el-input>
      <el-input class="form-input" placeholder="请输入父级编码" v-model="data.parentCode">父级编码</el-input>
      <el-input class="form-input" placeholder="请输入父级名称" v-model="data.parentName">父级名称</el-input>
      <el-input class="form-input" placeholder="请输入编码" v-model="data.code">编码</el-input>
      <el-input class="form-input" placeholder="请输入名称" v-model="data.name">名称</el-input>
      <el-input class="form-input" placeholder="请输入路径" v-model="data.path">路径</el-input>
      <el-input class="form-input" placeholder="请输入排序" v-model="data.sorting">排序</el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel" :loading="loading">取消</el-button>
      <el-button class="confirm" @click="confirm" :loading="loading">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
