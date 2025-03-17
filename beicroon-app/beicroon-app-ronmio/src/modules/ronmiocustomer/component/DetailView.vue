<script setup lang="ts">
import {DetailVO} from "@m/ronmiocustomer/script/module.ts";
import {BForm, BFormGroup, BInput} from "beicroon-app-vue/components";

interface Props {
  data?: DetailVO | null,
}

defineProps<Props>();
</script>

<template>
  <b-form :loading="!data" disabled>
    <template v-if="data">
      <b-form-group label="基础信息">
        <b-input disabled label="客户名称" v-model="data.name"/>
        <b-input disabled label="联系电话" v-model="data.phone"/>
      </b-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
