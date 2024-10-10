<script setup lang="ts">
import {onMounted, reactive} from "vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {AccountAdminUpdateDTO as DTO, detail, update} from "@/request/beicroon/account-admin.http.ts";

type Props = {
  id: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const form = reactive<DTO>({
  id: props.id,
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.set = true;

  await update(form).finally(() => loading.set = false);

  emits("confirm");
}

onMounted(async () => {
  loading.get = true;

  const {data} = await detail(props.id).finally(() => loading.get = false);

  form.code = data.code;
  form.name = data.name;
  form.username = data.username;
  form.phone = data.phone;
  form.email = data.email;
});
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main beicroon-dialog-input" v-if="!loading.get">
      <beicroon-input disabled class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input required class="column" label="昵称" placeholder="昵称" v-model="form.name"></beicroon-input>
      <beicroon-input disabled class="column" label="账号" placeholder="账号" v-model="form.username"></beicroon-input>
      <beicroon-input class="column" label="密码" placeholder="密码" v-model="form.password"></beicroon-input>
      <beicroon-input class="column" label="电话" placeholder="电话" v-model="form.phone"></beicroon-input>
      <beicroon-input class="column" label="邮箱" placeholder="邮箱" v-model="form.email"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
      <beicroon-button type="submit" class="block warning" label="保存" :loading="loading.set"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
