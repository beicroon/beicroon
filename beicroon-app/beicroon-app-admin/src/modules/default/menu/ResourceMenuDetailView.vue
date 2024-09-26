<script setup lang="ts">
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {ResourceMenuDetailVO as VO} from "@/request/resource-menu.http.ts";

type Props = {
  data: VO | null,
};

defineProps<Props>();

const emits = defineEmits(["cancel"]);

async function handleCancel() {
  emits("cancel");
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view">
    <div class="beicroon-dialog-main beicroon-dialog-input" v-if="data">
      <beicroon-input disabled class="column" label="父级编码" v-model="data.parentCode"></beicroon-input>
      <beicroon-input disabled class="column" label="父级名称" v-model="data.parentName"></beicroon-input>
      <beicroon-input disabled class="column" label="编码" v-model="data.code"></beicroon-input>
      <beicroon-input disabled class="column" label="名称" v-model="data.name"></beicroon-input>
      <beicroon-input disabled class="column" label="路由" v-model="data.path"></beicroon-input>
      <beicroon-input disabled class="column" label="排序" v-model="data.sorting"></beicroon-input>
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
