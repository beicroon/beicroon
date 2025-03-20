<script setup lang="ts">
import {moduleCreate} from "@/utils";
import config, {CreateDTO} from "@m/rbacresource/script/module";
import {BButton, BForm, BFormGroup, BInput, BSearch} from "@/components";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleCreate<CreateDTO>(config);

const handleSubmit = async () => {
  await module.submit();

  emits("hide", true);
};
</script>

<template>
  <b-form @submit="handleSubmit">
    <b-form-group label="基础信息">
      <b-input required label="名称" v-model="module.data.name"/>
      <b-input label="编码" v-model="module.data.code"/>
      <b-input label="路由" v-model="module.data.path"/>
      <b-search label="父级" v-model="module.data.parentId" :request="config.list"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
