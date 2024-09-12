<script setup lang="ts">
import {reactive, ref} from "vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {AccountAdminCreateDTO as DTO, create} from "./account-admin.http.ts";

const loading = ref(false);

const form = reactive<DTO>({});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.value = true;

  await create(form).finally(() => loading.value = false);

  emits("confirm");
}
</script>

<template>
  <form class="beicroon-dialog-view">
    <div class="beicroon-dialog-input">
      <beicroon-input class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input class="column" label="账号" placeholder="账号" v-model="form.username"></beicroon-input>
      <beicroon-input class="column" label="密码" placeholder="密码" v-model="form.password"></beicroon-input>
      <beicroon-input class="column" label="昵称" placeholder="昵称" v-model="form.nickname"></beicroon-input>
      <beicroon-input class="column" label="电话" placeholder="电话" v-model="form.phone"></beicroon-input>
      <beicroon-input class="column" label="邮箱" placeholder="邮箱" v-model="form.email"></beicroon-input>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block warning" label="取消" @click="handleCancel"></beicroon-button>
      <beicroon-button class="block primary" label="保存" @click="handleConfirm" :loading="loading"></beicroon-button>
    </div>
  </form>
</template>

<style scoped lang="less">
</style>
