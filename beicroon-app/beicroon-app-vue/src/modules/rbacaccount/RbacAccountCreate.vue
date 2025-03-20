<script setup lang="ts">
import {computed, onMounted} from "vue";
import {moduleCreate, moduleRequest, sha256} from "@/index";
import roleConfig, {BaseVO} from "@m/rbacrole/script/module";
import config, {CreateDTO} from "@m/rbacaccount/script/module";
import {BButton, BCheckboxTree, BForm, BFormGroup, BInput} from "@/components";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleCreate<CreateDTO>(config);

const role = moduleRequest<Array<BaseVO>>(roleConfig.list);

const loading = computed(() => module.loading || role.loading);

const handleSubmit = async () => {
  await module.submit({
    password: sha256(module.data.password),
    roleIds: role.getCheckedField("id"),
  });

  emits("hide", true);
};

onMounted(role.request);
</script>

<template>
  <b-form @submit="handleSubmit" :loading="loading">
    <b-form-group label="基础信息">
      <b-input required label="编码" v-model="module.data.code"/>
      <b-input required label="名称" v-model="module.data.name"/>
    </b-form-group>
    <b-form-group label="登录信息">
      <b-input required label="登录账号" v-model="module.data.username"/>
      <b-input required label="登录密码" v-model="module.data.password" type="password"/>
    </b-form-group>
    <b-form-group label="附加信息">
      <b-input label="联系电话" v-model="module.data.phone"/>
      <b-input label="邮箱地址" v-model="module.data.email"/>
    </b-form-group>
    <b-form-group label="角色权限">
      <b-checkbox-tree
        label-field="name"
        child-field="children"
        check-field="id"
        :data="role.data"
      />
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
