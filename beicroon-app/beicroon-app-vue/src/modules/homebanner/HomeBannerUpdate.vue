<script setup lang="ts">
import {onMounted} from "vue";
import {moduleUpdate} from "@/index";
import {BButton, BForm, BFormGroup, BInput} from "@/components";
import config, {DetailVO, UpdateDTO} from "@m/homebanner/script/module";

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
    module.data.url = res.url;  // 地址
    module.data.description = res.description;  // 描述
    module.data.validAt = res.validAt;  // 生效时间
    module.data.expiredAt = res.expiredAt;  // 过期时间
  });
});
</script>

<template>
  <b-form @submit="handleSubmit" :loading="module.getting">
    <b-form-group label="基础信息">
      <b-input label="地址" v-model="module.data.url"/>
      <b-input label="描述" size="huge" v-model="module.data.description"/>
    </b-form-group>
    <b-form-group label="有效期">
      <b-input label="生效时间" v-model="module.data.validAt"/>
      <b-input label="过期时间" v-model="module.data.expiredAt"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
