<script setup lang="ts">
import {onMounted, ref} from "vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {AccountAdminDetailVO as VO, detail} from "./account-admin.http.ts";

type Props = {
  id: string,
};

const props = defineProps<Props>();

const form = ref<VO | null>(null);

const emits = defineEmits(["cancel"]);

async function handleCancel() {
  emits("cancel");
}

onMounted(async () => {
  const {data} = await detail(props.id);

  form.value = data;
});
</script>

<template>
  <beicroon-form class="beicroon-dialog-view">
    <div class="beicroon-dialog-main beicroon-dialog-input" v-if="form">
      <beicroon-input disabled class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input disabled class="column" label="账号" placeholder="账号" v-model="form.username"></beicroon-input>
      <beicroon-input disabled class="column" label="昵称" placeholder="昵称" v-model="form.nickname"></beicroon-input>
      <beicroon-input disabled class="column" label="电话" placeholder="电话" v-model="form.phone"></beicroon-input>
      <beicroon-input disabled class="column" label="邮箱" placeholder="邮箱" v-model="form.email"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
