<script setup lang="ts">
import {computed, onMounted} from "vue";
import {moduleRequest, moduleUpdate, sha256} from "@/index";
import roleConfig, {BaseVO} from "@m/rbacrole/script/module";
import config, {DetailVO, UpdateDTO} from "@m/rbacaccount/script/module";
import {BButton, BCheckboxTree, BForm, BFormGroup, BInput} from "@/components";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleUpdate<UpdateDTO, DetailVO>(config, props.id);

const role = moduleRequest<Array<BaseVO>>(roleConfig.list);

const loading = computed(() => module.getting || role.loading);

const handleSubmit = async () => {
  await module.submit({
    password: sha256(module.data.password),
    roleIds: role.getCheckedField("id"),
  });

  emits("hide", true);
};

onMounted(async () => {
  await module.getData(res => {
    module.data.code = res.code;  // 编码
    module.data.name = res.name;  // 名称
    module.data.username = res.username;  // 登录账号
    module.data.phone = res.phone;  // 联系电话
    module.data.email = res.email;  // 邮箱地址
    module.data.roleIds = res.roleIds;  // 角色主键列表
  });

  await role.request();

  role.setChecked(module.data.roleIds, "id");
});
</script>

<template>
  <b-form @submit="handleSubmit" :loading="loading">
    <b-form-group label="基础信息">
      <b-input disabled required label="编码" v-model="module.data.code"/>
      <b-input required label="名称" v-model="module.data.name"/>
    </b-form-group>
    <b-form-group label="登录信息">
      <b-input disabled required label="登录账号" v-model="module.data.username"/>
      <b-input label="登录密码" v-model="module.data.password" type="password"/>
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
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
