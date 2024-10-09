<script setup lang="ts">
import {onMounted, ref} from "vue";
import AccountAdminDetailView from "./AccountAdminDetailView.vue";
import {AccountAdminDetailVO as VO, detail} from "@/request/beicroon/account-admin.http.ts";

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
  <account-admin-detail-view :data="form" @cancel="handleCancel"></account-admin-detail-view>
</template>

<style scoped lang="less">
</style>
