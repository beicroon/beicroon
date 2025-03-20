<script setup lang="ts">
import {BButton} from "@/components";
import {computed, onMounted} from "vue";
import {moduleDetail, moduleRequest} from "@/utils";
import config, {DetailVO} from "@m/rbacrole/script/module";
import DetailView from "@m/rbacrole/component/DetailView.vue";
import resourceConfig, {TreeVO} from "@m/rbacresource/script/module";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleDetail<DetailVO>(config, props.id);

const resource = moduleRequest<Array<TreeVO>>(resourceConfig.tree);

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
      <b-button size="larger" label="关闭" @click="handleHide"/>
    </template>
  </detail-view>
</template>

<style lang="less">

</style>
