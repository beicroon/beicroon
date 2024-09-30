<script setup lang="ts">
import {reactive, ref} from "vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {AccountAdminCreateDTO as DTO, create} from "@/request/account-admin.http.ts";

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
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main beicroon-dialog-input">
      <beicroon-input required class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input required class="column" label="昵称" placeholder="昵称" v-model="form.name"></beicroon-input>
      <beicroon-input required class="column" label="账号" placeholder="账号" v-model="form.username"></beicroon-input>
      <beicroon-input required class="column" label="密码" placeholder="密码" v-model="form.password"></beicroon-input>
      <beicroon-input class="column" label="电话" placeholder="电话" v-model="form.phone"></beicroon-input>
      <beicroon-input class="column" label="邮箱" placeholder="邮箱" v-model="form.email"></beicroon-input>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block warning" label="取消" @click="handleCancel"></beicroon-button>
      <beicroon-button type="submit" class="block primary" label="保存" :loading="loading"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
