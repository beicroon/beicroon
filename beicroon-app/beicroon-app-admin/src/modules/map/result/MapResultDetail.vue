<script setup lang="ts">
import {onMounted, ref} from "vue";
import {detail, MapResultDetailVO as VO} from "./map-result.http.utils.ts";

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
  <map-result-detail-view :data="form" @cancel="handleCancel"></resource-role-detail-view>
</template>

<style scoped lang="less">
</style>
