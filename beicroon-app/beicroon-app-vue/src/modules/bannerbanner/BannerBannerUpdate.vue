<script setup lang="ts">
import {onMounted, ref} from "vue";
import {BeicroonFile, moduleUpdate, uploadImage} from "@/utils";
import config, {DetailVO, UpdateDTO} from "@m/bannerbanner/script/module";
import {BButton, BDatetime, BFile, BForm, BFormGroup, BInput} from "@/components";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleUpdate<UpdateDTO, DetailVO>(config, props.id);

const handleSubmit = async () => {
  if (fileValue.value) {
    const fileRes = await uploadImage(fileValue.value);

    module.data.fileId = fileRes.data.id;
    module.data.fileName = fileRes.data.name;
    module.data.fileUrl = fileRes.data.url;
  }

  await module.submit();

  emits("hide", true);
};

const image = ref<BeicroonFile | null>(null);

onMounted(async () => {
  await module.getData(res => {
    module.data.fileId = res.fileId;  // 文件主键
    module.data.fileName = res.fileName;  // 文件名称
    module.data.fileUrl = res.fileUrl;  // 文件地址
    module.data.description = res.description;  // 描述
    module.data.validAt = res.validAt;  // 生效时间
    module.data.expiredAt = res.expiredAt;  // 过期时间

    image.value = {
      name: res.fileName as string,
      url: res.fileUrl as string,
    };
  });
});

const fileValue = ref<File | null>(null);

const handleChange = (file: File) => {
  if (!file) {
    module.data.fileId = "0";
    module.data.fileName = "";
    module.data.fileUrl = "";

    return;
  }

  image.value = null;
  fileValue.value = file;
};
</script>

<template>
  <b-form @submit="handleSubmit" :loading="module.getting">
    <b-form-group label="基础信息">
      <b-file required label="图片" placeholder="请选择图片" @change="handleChange" v-model="image"/>
      <b-input label="描述" size="huge" v-model="module.data.description"/>
    </b-form-group>
    <b-form-group label="有效期">
      <b-datetime label="生效时间" time="current" v-model="module.data.validAt"/>
      <b-datetime label="过期时间" time="end" v-model="module.data.expiredAt"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
