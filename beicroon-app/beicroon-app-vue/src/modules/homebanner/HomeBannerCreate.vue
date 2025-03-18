<script setup lang="ts">
import {moduleCreate} from "@/index";
import config, {CreateDTO} from "@m/homebanner/script/module";
import {BButton, BForm, BFormGroup, BInput} from "@/components";

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
      <b-input label="地址" v-model="module.data.url"/>
      <b-input label="描述" v-model="module.data.description"/>
      <b-input label="生效时间" v-model="module.data.validAt"/>
      <b-input label="过期时间" v-model="module.data.expiredAt"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
