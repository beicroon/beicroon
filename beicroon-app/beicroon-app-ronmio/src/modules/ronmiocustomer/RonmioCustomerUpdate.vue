<script setup lang="ts">
import {onMounted} from "vue";
import {moduleUpdate} from "beicroon-app-vue";
import config, {DetailVO, UpdateDTO} from "@m/ronmiocustomer/script/module.ts";
import {BButton, BForm, BFormGroup, BInput} from "beicroon-app-vue/components";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleUpdate<UpdateDTO, DetailVO>(config, props.id);

const handleSubmit = async () => {
  await module.submit();

  emits("hide", true);
};

onMounted(async () => {
  await module.getData(res => {
    module.data.name = res.name;  // 客户名称
    module.data.phone = res.phone;  // 联系电话
  });
});
</script>

<template>
  <b-form @submit="handleSubmit" :loading="module.getting">
    <b-form-group label="基础信息">
      <b-input label="客户名称" v-model="module.data.name"/>
      <b-input label="联系电话" v-model="module.data.phone"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
