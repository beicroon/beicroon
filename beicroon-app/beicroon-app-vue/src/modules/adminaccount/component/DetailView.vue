<script setup lang="ts">
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import {BaseVO} from "@m/adminrole/script/module";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import {DetailVO} from "@m/adminaccount/script/module";
import BeicroonCheckboxTree from "@c/BeicroonCheckboxTree.vue";

interface Props {
  data?: DetailVO | null,
  roles?: Array<BaseVO> | null,
  loading?: boolean,
}

defineProps<Props>();
</script>

<template>
  <beicroon-form :loading="loading" disabled>
    <template v-if="data">
      <beicroon-form-group label="基础信息">
        <beicroon-input disabled required label="编码" v-model="data.code"/>
        <beicroon-input disabled required label="名称" v-model="data.name"/>
      </beicroon-form-group>
      <beicroon-form-group label="登录信息">
        <beicroon-input disabled required label="登录账号" v-model="data.username"/>
      </beicroon-form-group>
      <beicroon-form-group label="附加信息">
        <beicroon-input disabled label="联系电话" v-model="data.phone"/>
        <beicroon-input disabled label="邮箱地址" v-model="data.email"/>
      </beicroon-form-group>
      <beicroon-form-group label="角色权限">
        <beicroon-checkbox-tree
          disabled
          label-field="name"
          child-field="children"
          check-field="id"
          :data="roles"
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
