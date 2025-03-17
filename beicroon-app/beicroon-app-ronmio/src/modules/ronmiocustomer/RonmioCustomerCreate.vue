<script setup lang="ts">
import {moduleCreate} from "beicroon-app-vue";
import config, {CreateDTO} from "@m/ronmiocustomer/script/module.ts";
import {BButton, BForm, BFormGroup, BInput} from "beicroon-app-vue/components";

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
      <b-input label="客户名称" v-model="module.data.name"/>
      <b-input label="联系电话" v-model="module.data.phone"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
