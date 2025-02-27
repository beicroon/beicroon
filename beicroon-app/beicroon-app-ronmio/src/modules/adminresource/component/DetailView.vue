<script setup lang="ts">
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import {DetailVO} from "@m/adminresource/script/module.ts";

interface Props {
  data?: DetailVO | null,
}

defineProps<Props>();
</script>

<template>
  <beicroon-form :loading="!data" disabled>
    <template v-if="data">
      <beicroon-form-group label="基础信息">
        <beicroon-input disabled required label="名称" v-model="data.name"/>
        <beicroon-input disabled label="编码" v-model="data.code"/>
        <beicroon-input disabled label="路由" v-model="data.path"/>
        <beicroon-input disabled label="父级" v-model="data.parentName"/>
      </beicroon-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
