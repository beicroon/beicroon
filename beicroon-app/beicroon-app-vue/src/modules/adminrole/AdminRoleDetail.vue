<script setup lang="ts">
import {computed, onMounted} from "vue";
import newModule from "@u/module-detail.utils";
import BeicroonButton from "@c/BeicroonButton.vue";
import requestUtils from "@u/module-request.utils";
import DetailView from "@m/adminrole/component/DetailView.vue";
import config, {DetailVO} from "@m/adminrole/script/module";
import resourceConfig, {TreeVO} from "@m/adminresource/script/module";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<DetailVO>(config, props.id);

const resource = requestUtils<Array<TreeVO>>(resourceConfig.tree);

const loading = computed(() => module.loading || resource.loading);

onMounted(async () => {
  await module.getData();

  await resource.request();

  if (module.data) {
    resource.setChecked(module.data.resourceIds, "id", "children");
  }
});
</script>

<template>
  <detail-view :data="module.data" :resources="resource.data" :loading="loading">
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
    </template>
  </detail-view>
</template>

<style lang="less">

</style>
