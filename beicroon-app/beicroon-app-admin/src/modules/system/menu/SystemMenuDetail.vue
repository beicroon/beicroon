<script setup lang="ts">
import {onMounted, ref} from "vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {detail, SystemMenuDetailVO as VO} from "./system-menu.http.ts";

type Props = {
  id: string,
};

const props = defineProps<Props>();

const vo = ref<VO | null>(null);

const emits = defineEmits(["cancel"]);

async function handleCancel() {
  emits("cancel");
}

onMounted(async () => {
  const {data} = await detail(props.id);

  vo.value = data;
});
</script>

<template>
  <form class="beicroon-dialog-view">
    <div class="beicroon-dialog-input" v-if="vo">
      <beicroon-input disabled class="column" label="名称" v-model="vo.name"></beicroon-input>
      <beicroon-input disabled class="column" label="路径" v-model="vo.path"></beicroon-input>
      <beicroon-input disabled class="column" type="number" label="排序" v-model="vo.sorting"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
    </div>
  </form>
</template>

<style lang="less">
</style>