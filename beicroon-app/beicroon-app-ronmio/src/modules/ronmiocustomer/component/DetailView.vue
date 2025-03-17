<script setup lang="ts">
import {DetailVO} from "@m/ronmiocustomer/script/module.ts";

interface Props {
  data?: DetailVO | null,
}

defineProps<Props>();
</script>

<template>
  <beicroon-form :loading="!data" disabled>
    <template v-if="data">
      <beicroon-form-group label="基础信息">
        <beicroon-input disabled label="客户名称" v-model="data.name"/>
        <beicroon-input disabled label="联系电话" v-model="data.phone"/>
      </beicroon-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
