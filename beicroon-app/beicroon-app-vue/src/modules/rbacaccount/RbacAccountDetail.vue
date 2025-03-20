<script setup lang="ts">
import {BButton} from "@/components";
import {computed, onMounted} from "vue";
import {moduleDetail, moduleRequest} from "@/utils";
import roleConfig, {BaseVO} from "@m/rbacrole/script/module";
import config, {DetailVO} from "@m/rbacaccount/script/module";
import DetailView from "@m/rbacaccount/component/DetailView.vue";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleDetail<DetailVO>(config, props.id);

const role = moduleRequest<Array<BaseVO>>(roleConfig.list);

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
      <b-button size="larger" label="关闭" @click="handleHide"/>
    </template>
  </detail-view>
</template>

<style lang="less">

</style>
