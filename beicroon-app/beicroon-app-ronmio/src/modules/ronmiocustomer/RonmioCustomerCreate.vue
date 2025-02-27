<script setup lang="ts">
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import newModule from "@u/module-create.utils.ts";
import BeicroonButton from "@c/BeicroonButton.vue";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import config, {CreateDTO} from "@m/ronmiocustomer/script/module.ts";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<CreateDTO>(config);

const handleSubmit = async () => {
  await module.submit();

  emits("hide", true);
};
</script>

<template>
  <beicroon-form @submit="handleSubmit">
    <beicroon-form-group label="基础信息">
      <beicroon-input label="客户名称" v-model="module.data.name"/>
      <beicroon-input label="联系电话" v-model="module.data.phone"/>
    </beicroon-form-group>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
