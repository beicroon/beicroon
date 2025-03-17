<script setup lang="ts">
import {onMounted} from "vue";
import {moduleUpdate} from "beicroon-app-vue";
import config, {DetailVO, UpdateDTO} from "@m/ronmiocustomer/script/module.ts";

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
  <beicroon-form @submit="handleSubmit" :loading="module.getting">
    <beicroon-form-group label="基础信息">
      <beicroon-input label="客户名称" v-model="module.data.name"/>
      <beicroon-input label="联系电话" v-model="module.data.phone"/>
    </beicroon-form-group>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
