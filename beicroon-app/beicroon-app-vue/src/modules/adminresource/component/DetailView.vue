<script setup lang="ts">
import {BForm, BFormGroup, BInput} from "@/components";
import {DetailVO} from "@m/adminresource/script/module";

interface Props {
  data?: DetailVO | null,
}

defineProps<Props>();
</script>

<template>
  <b-form :loading="!data" disabled>
    <template v-if="data">
      <b-form-group label="基础信息">
        <b-input disabled required label="名称" v-model="data.name"/>
        <b-input disabled label="编码" v-model="data.code"/>
        <b-input disabled label="路由" v-model="data.path"/>
        <b-input disabled label="父级" v-model="data.parentName"/>
      </b-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
