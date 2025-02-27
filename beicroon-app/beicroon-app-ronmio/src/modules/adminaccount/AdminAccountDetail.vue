<script setup lang="ts">
import {computed, onMounted} from "vue";
import newModule from "@u/module-detail.utils.ts";
import BeicroonButton from "@c/BeicroonButton.vue";
import requestUtils from "@u/module-request.utils.ts";
import roleConfig, {BaseVO} from "@m/adminrole/script/module.ts";
import config, {DetailVO} from "@m/adminaccount/script/module.ts";
import DetailView from "@m/adminaccount/component/DetailView.vue";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<DetailVO>(config, props.id);

const role = requestUtils<Array<BaseVO>>(roleConfig.list);

const loading = computed(() => module.loading || role.loading);

onMounted(async () => {
  await module.getData();

  await role.request();

  if (module.data) {
    role.setChecked(module.data.roleIds, "id");
  }
});
</script>

<template>
  <detail-view :data="module.data" :roles="role.data" :loading="loading">
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
    </template>
  </detail-view>
</template>

<style lang="less">

</style>
