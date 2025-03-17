<script setup lang="ts">
import {computed, onMounted} from "vue";
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import newModule from "@u/module-create.utils";
import BeicroonButton from "@c/BeicroonButton.vue";
import requestUtils from "@u/module-request.utils";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import BeicroonCheckboxTree from "@c/BeicroonCheckboxTree.vue";
import config, {CreateDTO} from "@m/adminrole/script/module";
import resourceConfig, {TreeVO} from "@m/adminresource/script/module";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<CreateDTO>(config);

const handleSubmit = async () => {
  module.data.resourceIds = resource.getCheckedField("id", "children");

  await module.submit();

  emits("hide", true);
};

const loading = computed(() => module.loading || resource.loading);

const resource = requestUtils<Array<TreeVO>>(resourceConfig.tree);

onMounted(resource.request);
</script>

<template>
  <beicroon-form @submit="handleSubmit" :loading="resource.loading">
    <beicroon-form-group label="基础信息">
      <beicroon-input required label="编码" v-model="module.data.code"/>
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
