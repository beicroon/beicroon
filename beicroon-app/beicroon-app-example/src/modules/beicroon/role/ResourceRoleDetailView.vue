<script setup lang="ts">
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {ResourceRoleDetailVO as VO} from "@/request/beicroon/resource-role.http.ts";

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
      <beicroon-input disabled class="column" label="编码" v-model="data.code"></beicroon-input>
      <beicroon-input disabled class="column" label="名称" v-model="data.name"></beicroon-input>
      <beicroon-input disabled class="column" label="备注" v-model="data.remark"></beicroon-input>
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
