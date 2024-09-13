<script setup lang="ts">
import {onMounted, reactive} from "vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {detail, ResourceRoleUpdateDTO as DTO, update} from "./resource-role.http.ts";

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
  form.remark = data.remark;
  form.superAdminFlag = data.superAdminFlag;
});
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-input" v-if="!loading.get">
      <beicroon-input class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input class="column" label="名称" placeholder="名称" v-model="form.name"></beicroon-input>
      <beicroon-input class="column" label="备注" placeholder="备注" v-model="form.remark"></beicroon-input>
      <beicroon-input class="column" label="是否超级管理员" placeholder="是否超级管理员" v-model="form.superAdminFlag"></beicroon-input>
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
