<script setup lang="ts">
import {onMounted, ref} from "vue";
import ResourceMenuDetailView from "./ResourceMenuDetailView.vue";
import {detail, ResourceMenuDetailVO as VO} from "@/request/beicroon/resource-menu.http.ts";

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
  <resource-menu-detail-view :data="form" @cancel="handleCancel"></resource-menu-detail-view>
</template>

<style scoped lang="less">
</style>
