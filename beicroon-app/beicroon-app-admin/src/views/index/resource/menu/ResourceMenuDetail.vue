<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import ElButton from "@/components/elements/ElButton.vue";
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

const emits = defineEmits(["hide"]);

async function cancel() {
  emits("hide");
}
</script>

<template>
  <form class="detail">
    <div class="view"></div>
    <div class="button">
      <el-button class="cancel" @click="cancel">取消</el-button>
      <el-button class="confirm">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>