<script setup lang="ts">
import {computed, onMounted} from "vue";
import {moduleRequest, moduleUpdate} from "@/index";
import resourceConfig, {TreeVO} from "@m/rbacresource/script/module";
import config, {DetailVO, UpdateDTO} from "@m/rbacrole/script/module";
import {BButton, BCheckboxTree, BForm, BFormGroup, BInput} from "@/components";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleUpdate<UpdateDTO, DetailVO>(config, props.id);

const handleSubmit = async () => {
  module.data.resourceIds = resource.getCheckedField("id", "children");

  await module.submit();

  emits("hide", true);
};

const resource = moduleRequest<Array<TreeVO>>(resourceConfig.tree);

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
  <b-form @submit="handleSubmit" :loading="loading">
    <b-form-group label="基础信息">
      <b-input disabled required label="编码" v-model="module.data.code"/>
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
