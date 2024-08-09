<script setup lang="ts">
import {ref} from "vue";
import toast from "@/utils/toast";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {AdminCreateDTO as DTO, create} from "@/https/account/admin.http.ts";

const form = ref<DTO>({});

const loading = ref(false);

const emits = defineEmits(["hide", 'reload']);

function cancel() {
  emits("hide");
}

async function confirm() {
  loading.value = true;

  await create(form.value);

  await toast("添加成功");

  emits("hide");

  emits("reload");
}
</script>

<template>
  <form class="create">
    <div class="view">
      <el-input class="form-input" label="账号" placeholder="请输入账号" v-model="form.username"></el-input>
      <el-input class="form-input" label="昵称" placeholder="请输入昵称" v-model="form.nickname"></el-input>
      <el-input class="form-input" label="电话" placeholder="请输入电话" v-model="form.phone"></el-input>
      <el-input class="form-input" label="邮箱" placeholder="请输入邮箱" v-model="form.email"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel" :loading="loading">取消</el-button>
      <el-button class="confirm" @click="confirm" :loading="loading">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>