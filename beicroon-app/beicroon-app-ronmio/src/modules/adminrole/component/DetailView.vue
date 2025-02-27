<script setup lang="ts">
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import {DetailVO} from "@m/adminrole/script/module.ts";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import BeicroonCheckboxTree from "@c/BeicroonCheckboxTree.vue";

interface Props {
  data?: DetailVO | null,
  resources?: Array<any> | null,
  loading: boolean,
}

defineProps<Props>();
</script>

<template>
  <beicroon-form :loading="loading" disabled>
    <template v-if="data">
      <beicroon-form-group label="基础信息">
        <beicroon-input disabled label="编码" v-model="data.code"/>
        <beicroon-input disabled label="名称" v-model="data.name"/>
      </beicroon-form-group>
      <beicroon-form-group label="资源权限">
        <beicroon-checkbox-tree
          disabled
          label-field="name"
          child-field="children"
          check-field="id"
          :data="resources"
        />
      </beicroon-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
