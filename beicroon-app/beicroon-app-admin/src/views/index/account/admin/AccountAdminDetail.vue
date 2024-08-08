<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import {escUp} from "@/utils/function.ts";
import {AdminDetailVO, detail} from "@/https/account/admin.http.ts";

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
});

const data = ref<AdminDetailVO | null>(null);

onBeforeMount(async () => {
  const res = await detail(props.id);

  data.value = res.data;
});

const emits = defineEmits(["close"]);

async function close() {
  emits("close");
}

escUp(close);
</script>

<template>
  <div class="window">DETAIL</div>
</template>

<style scoped lang="less">

</style>