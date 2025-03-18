<script setup lang="ts">
import {BaseVO} from "@m/adminrole/script/module";
import {DetailVO} from "@m/adminaccount/script/module";
import {BCheckboxTree, BForm, BFormGroup, BInput} from "@/components";

interface Props {
  data?: DetailVO | null,
  roles?: Array<BaseVO> | null,
  loading?: boolean,
}

defineProps<Props>();
</script>

<template>
  <b-form :loading="loading" disabled>
    <template v-if="data">
      <b-form-group label="基础信息">
        <b-input disabled required label="编码" v-model="data.code"/>
        <b-input disabled required label="名称" v-model="data.name"/>
      </b-form-group>
      <b-form-group label="登录信息">
        <b-input disabled required label="登录账号" v-model="data.username"/>
      </b-form-group>
      <b-form-group label="附加信息">
        <b-input disabled label="联系电话" v-model="data.phone"/>
        <b-input disabled label="邮箱地址" v-model="data.email"/>
      </b-form-group>
      <b-form-group label="角色权限">
        <b-checkbox-tree
          disabled
          label-field="name"
          child-field="children"
          check-field="id"
          :data="roles"
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
