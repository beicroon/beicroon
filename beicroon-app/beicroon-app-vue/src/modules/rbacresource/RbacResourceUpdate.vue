<script setup lang="ts">
import {onMounted} from "vue";
import {moduleUpdate} from "@/utils";
import {BButton, BForm, BFormGroup, BInput, BSearch} from "@/components";
import config, {DetailVO, UpdateDTO} from "@m/rbacresource/script/module";

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
    module.data.parentId = res.parentId;  // 父级主键
    module.data.code = res.code;  // 编码
    module.data.name = res.name;  // 名称
    module.data.path = res.path;  // 路由
  });
});
</script>

<template>
  <b-form @submit="handleSubmit" :loading="module.getting">
    <b-form-group label="基础信息">
      <b-input required label="名称" v-model="module.data.name"/>
      <b-input label="编码" v-model="module.data.code"/>
      <b-input label="路由" v-model="module.data.path"/>
      <b-search label="父级" v-model="module.data.parentId" :request="config.list"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
