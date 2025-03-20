<script setup lang="ts">
import {computed, onMounted} from "vue";
import {moduleCreate, moduleRequest} from "@/utils";
import config, {CreateDTO} from "@m/rbacrole/script/module";
import resourceConfig, {TreeVO} from "@m/rbacresource/script/module";
import {BButton, BCheckboxTree, BForm, BFormGroup, BInput} from "@/components";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleCreate<CreateDTO>(config);

const handleSubmit = async () => {
  module.data.resourceIds = resource.getCheckedField("id", "children");

  await module.submit();

  emits("hide", true);
};

const loading = computed(() => module.loading || resource.loading);

const resource = moduleRequest<Array<TreeVO>>(resourceConfig.tree);

onMounted(resource.request);
</script>

<template>
  <b-form @submit="handleSubmit" :loading="resource.loading">
    <b-form-group label="基础信息">
      <b-input required label="编码" v-model="module.data.code"/>
      <b-input required label="名称" v-model="module.data.name"/>
    </b-form-group>
    <b-form-group label="资源权限">
      <b-checkbox-tree
        label-field="name"
        child-field="children"
        check-field="id"
        :data="resource.data"
      />
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
