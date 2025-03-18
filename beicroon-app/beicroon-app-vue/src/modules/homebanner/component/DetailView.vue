<script setup lang="ts">
import {DetailVO} from "@m/homebanner/script/module";
import {BForm, BFormGroup, BInput} from "@/components";

interface Props {
  data?: DetailVO | null,
}

defineProps<Props>();
</script>

<template>
  <b-form :loading="!data" disabled>
    <template v-if="data">
      <b-form-group label="基础信息">
        <b-input disabled label="地址" v-model="data.url"/>
        <b-input disabled label="描述" size="huge" v-model="data.description"/>
      </b-form-group>
      <b-form-group label="有效期">
        <b-input disabled label="生效时间" v-model="data.validAt"/>
        <b-input disabled label="过期时间" v-model="data.expiredAt"/>
      </b-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
