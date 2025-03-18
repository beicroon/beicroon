<script setup lang="ts">
import {onMounted} from "vue";
import {dialog, moduleApp, overlay} from "@/index";
import config, {PageVO, QueryDTO} from "@m/adminaccount/script/module";
import AdminAccountCreate from "@m/adminaccount/AdminAccountCreate.vue";
import AdminAccountUpdate from "@m/adminaccount/AdminAccountUpdate.vue";
import AdminAccountDetail from "@m/adminaccount/AdminAccountDetail.vue";
import {BButton, BInput, BModule, BTableColumn, BTableField, BTableGroup} from "@/components";

const module = moduleApp<QueryDTO, PageVO>(config);

const handleCreate = () => {
  overlay({
    title: "新增账号",
    width: "800rem",
    component: AdminAccountCreate,
    confirm: module.page.request,
  });
};

const handleUpdate = (item: any) => {
  overlay({
    title: "编辑账号",
    width: "800rem",
    component: AdminAccountUpdate,
    props: {id: item.id},
    confirm: module.page.request,
  });
};

const handleDetail = (item: any) => {
  overlay({
    title: "账号详情",
    width: "800rem",
    component: AdminAccountDetail,
    props: {id: item.id},
  });
};

const handleRemove = (item: any) => {
  dialog({
    title: "删除确认",
    message: `确定删除该账号吗？`,
    confirm: async () => await module.remove.request(item.id),
    succeed: module.page.request,
  });
};

onMounted(module.page.request);
</script>

<template>
  <b-module :module="module">
    <template v-slot:search>
      <b-input label="名称" v-model="module.params.name"/>
      <b-input label="登录账号" v-model="module.params.username"/>
      <b-input label="联系电话" v-model="module.params.phone"/>
      <b-input label="邮箱地址" v-model="module.params.email"/>
    </template>
    <template v-slot:action-left>
      <b-button level="warning" label="新增" @click="handleCreate"/>
    </template>
    <template v-slot:action-right>
      <b-button label="重置" @click="module.clear" level="warning"/>
      <b-button label="查询" :loading="module.page.loading" @click="module.page.request()"/>
    </template>
    <b-table-column label="编码" field="code"/>
    <b-table-column label="名称" field="name"/>
    <b-table-column label="登录账号" field="username"/>
    <b-table-column label="联系电话" field="phone"/>
    <b-table-column label="邮箱地址" field="email"/>
    <b-table-group label="创建/更新" width="320rem">
      <template v-slot="{item}">
        <b-table-field label="创建人" :content="item.creatorName"/>
        <b-table-field label="创建时间" :content="item.createdAt"/>
        <b-table-field label="更新人" :content="item.modifierName"/>
        <b-table-field label="更新时间" :content="item.modifiedAt"/>
      </template>
    </b-table-group>
    <b-table-group label="操作" direction="row" frozen-right>
      <template v-slot="{item}">
        <b-button size="small" level="primary" label="详情" @click="handleDetail(item)"/>
        <b-button size="small" level="warning" label="编辑" @click="handleUpdate(item)"/>
        <b-button size="small" level="danger" label="删除" @click="handleRemove(item)"/>
      </template>
    </b-table-group>
  </b-module>
</template>

<style lang="less">

</style>
