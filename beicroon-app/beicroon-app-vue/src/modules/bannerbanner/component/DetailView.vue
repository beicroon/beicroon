<script setup lang="ts">
import {computed} from "vue";
import {DetailVO} from "@m/bannerbanner/script/module";
import {BFile, BForm, BFormGroup, BInput} from "@/components";

interface Props {
  data?: DetailVO | null,
}

const props = defineProps<Props>();

const image = computed(() => {
  if (!props.data) {
    return null;
  }

  return {
    name: props.data.fileName as string,
    url: props.data.fileUrl as string,
  };
});
</script>

<template>
  <b-form :loading="!data" disabled>
    <template v-if="data">
      <b-form-group label="基础信息">
        <b-file disabled label="图片" placeholder="请选择图片" v-model="image"/>
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
