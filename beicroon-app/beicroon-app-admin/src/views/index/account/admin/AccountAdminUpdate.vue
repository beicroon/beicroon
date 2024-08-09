<script setup lang="ts">
import {ref} from "vue";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {AdminUpdateDTO as DTO} from "@/https/account/admin.http.ts";

const emits = defineEmits(["hide"]);

async function cancel() {
  emits("hide");
}

const form = ref<DTO>({});
</script>

<template>
  <form class="update">
    <div class="view">
      <el-input class="form-input" label="昵称" placeholder="请输入昵称" v-model="form.nickname"></el-input>
      <el-input class="form-input" label="电话" placeholder="请输入电话" v-model="form.phone"></el-input>
      <el-input class="form-input" label="邮箱" placeholder="请输入邮箱" v-model="form.email"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel">取消</el-button>
      <el-button class="confirm">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">
</style>