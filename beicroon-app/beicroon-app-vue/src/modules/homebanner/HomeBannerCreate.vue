<script setup lang="ts">
import {ref} from "vue";
import {moduleCreate, uploadImage} from "@/index";
import config, {CreateDTO} from "@m/homebanner/script/module";
import {BButton, BFile, BForm, BFormGroup, BInput} from "@/components";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleCreate<CreateDTO>(config);

const handleSubmit = async () => {
  if (!fileValue.value) {
    return;
  }

  const fileRes = await uploadImage(fileValue.value);

  module.data.url = fileRes.data.url;
  module.data.fileId = fileRes.data.id;

  await module.submit();

  emits("hide", true);
};

const fileValue = ref<File | null>(null);

const handleChange = (file: File) => {
  if (!file) {
    module.data.url = "";

    return;
  }

  fileValue.value = file;
};
</script>

<template>
  <b-form @submit="handleSubmit">
    <b-form-group label="基础信息">
      <b-file required label="图片" placeholder="请选择图片" @change="handleChange" v-model="module.data.url"/>
      <b-input label="描述" size="huge" v-model="module.data.description"/>
    </b-form-group>
    <b-form-group label="有效期">
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
