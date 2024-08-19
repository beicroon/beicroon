<script setup lang="ts">
import {ref} from "vue";
import toast from "@/utils/toast";
import {validateForm} from "@/utils/function.ts";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {AccountAdminCreateDTO as DTO, create as submit} from "./AccountAdmin.http.ts";

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

  await submit(data.value).finally(() => loading.value = false);;

  await toast("添加成功");

  emits("hide");

  emits("reload");
}
</script>

<template>
  <form class="create" ref="form">
    <div class="view">
      <el-input class="form-input" label="编码" placeholder="请输入编码" v-model="data.code"></el-input>
      <el-input class="form-input" label="账号" placeholder="请输入账号" v-model="data.username"></el-input>
      <el-input class="form-input" label="密码" placeholder="请输入密码" v-model="data.password"></el-input>
      <el-input class="form-input" label="昵称" placeholder="请输入昵称" v-model="data.nickname"></el-input>
      <el-input class="form-input" label="电话" placeholder="请输入电话" v-model="data.phone"></el-input>
      <el-input class="form-input" label="邮箱" placeholder="请输入邮箱" v-model="data.email"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel" :loading="loading">取消</el-button>
      <el-button class="confirm" @click="confirm" :loading="loading">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
