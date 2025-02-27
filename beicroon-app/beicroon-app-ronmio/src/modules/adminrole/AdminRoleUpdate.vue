<script setup lang="ts">
import {computed, onMounted} from "vue";
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import newModule from "@u/module-update.utils.ts";
import BeicroonButton from "@c/BeicroonButton.vue";
import requestUtils from "@u/module-request.utils.ts";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import BeicroonCheckboxTree from "@c/BeicroonCheckboxTree.vue";
import resourceConfig, {TreeVO} from "@m/adminresource/script/module.ts";
import config, {DetailVO, UpdateDTO} from "@m/adminrole/script/module.ts";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<UpdateDTO, DetailVO>(config, props.id);

const handleSubmit = async () => {
  module.data.resourceIds = resource.getCheckedField("id", "children");

  await module.submit();

  emits("hide", true);
};

const resource = requestUtils<Array<TreeVO>>(resourceConfig.tree);

const loading = computed(() => module.getting || resource.loading);

onMounted(async () => {
  await module.getData(res => {
    module.data.code = res.code;  // 编码
    module.data.name = res.name;  // 名称
    module.data.resourceIds = res.resourceIds;  // 资源主键列表
  });

  await resource.request();

  resource.setChecked(module.data.resourceIds, "id", "children");
});
</script>

<template>
  <beicroon-form @submit="handleSubmit" :loading="loading">
    <beicroon-form-group label="基础信息">
      <beicroon-input disabled required label="编码" v-model="module.data.code"/>
      <beicroon-input required label="名称" v-model="module.data.name"/>
    </beicroon-form-group>
    <beicroon-form-group label="资源权限">
      <beicroon-checkbox-tree
        label-field="name"
        child-field="children"
        check-field="id"
        :data="resource.data"
      />
    </beicroon-form-group>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="loading"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
