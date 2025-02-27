<script setup lang="ts">
import {computed, onMounted} from "vue";
import {sha256} from "@u/function.utils.ts";
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import newModule from "@u/module-create.utils.ts";
import BeicroonButton from "@c/BeicroonButton.vue";
import requestUtils from "@u/module-request.utils.ts";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import BeicroonCheckboxTree from "@c/BeicroonCheckboxTree.vue";
import roleConfig, {BaseVO} from "@m/adminrole/script/module.ts";
import config, {CreateDTO} from "@m/adminaccount/script/module.ts";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<CreateDTO>(config);

const role = requestUtils<Array<BaseVO>>(roleConfig.list);

const loading = computed(() => module.loading || role.loading);

const handleSubmit = async () => {
  await module.submit({
    password: await sha256(module.data.password),
    roleIds: role.getCheckedField("id"),
  });

  emits("hide", true);
};

onMounted(role.request);
</script>

<template>
  <beicroon-form @submit="handleSubmit" :loading="loading">
    <beicroon-form-group label="基础信息">
      <beicroon-input required label="编码" v-model="module.data.code"/>
      <beicroon-input required label="名称" v-model="module.data.name"/>
    </beicroon-form-group>
    <beicroon-form-group label="登录信息">
      <beicroon-input required label="登录账号" v-model="module.data.username"/>
      <beicroon-input required label="登录密码" v-model="module.data.password" type="password"/>
    </beicroon-form-group>
    <beicroon-form-group label="附加信息">
      <beicroon-input label="联系电话" v-model="module.data.phone"/>
      <beicroon-input label="邮箱地址" v-model="module.data.email"/>
    </beicroon-form-group>
    <beicroon-form-group label="角色权限">
      <beicroon-checkbox-tree
        label-field="name"
        child-field="children"
        check-field="id"
        :data="role.data"
      />
    </beicroon-form-group>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="loading"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
