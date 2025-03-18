<script setup lang="ts">
import {DetailVO} from "@m/adminrole/script/module";
import {BCheckboxTree, BForm, BFormGroup, BInput} from "@/components";

interface Props {
  data?: DetailVO | null,
  resources?: Array<any> | null,
  loading: boolean,
}

defineProps<Props>();
</script>

<template>
  <b-form :loading="loading" disabled>
    <template v-if="data">
      <b-form-group label="基础信息">
        <b-input disabled label="编码" v-model="data.code"/>
        <b-input disabled label="名称" v-model="data.name"/>
      </b-form-group>
      <b-form-group label="资源权限">
        <b-checkbox-tree
          disabled
          label-field="name"
          child-field="children"
          check-field="id"
          :data="resources"
        />
      </b-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
