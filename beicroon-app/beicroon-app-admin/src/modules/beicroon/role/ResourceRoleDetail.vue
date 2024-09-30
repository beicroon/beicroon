<script setup lang="ts">
import {onMounted, ref} from "vue";
import ResourceRoleDetailView from "./ResourceRoleDetailView.vue";
import {detail, ResourceRoleDetailVO as VO} from "@/request/beicroon/resource-role.http.ts";

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
  <resource-role-detail-view :data="form" @cancel="handleCancel"></resource-role-detail-view>
</template>

<style scoped lang="less">
</style>
