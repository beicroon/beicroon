<script setup lang="ts">
import {onMounted} from "vue";
import {dialog, moduleApp, overlay} from "@/utils";
import config, {PageVO, QueryDTO} from "@m/rbacresource/script/module";
import RbacResourceCreate from "@m/rbacresource/RbacResourceCreate.vue";
import RbacResourceUpdate from "@m/rbacresource/RbacResourceUpdate.vue";
import RbacResourceDetail from "@m/rbacresource/RbacResourceDetail.vue";
import {BButton, BInput, BModule, BSearch, BTableColumn, BTableField, BTableGroup} from "@/components";

const module = moduleApp<QueryDTO, PageVO>(config);

const handleCreate = () => {
  overlay({
    title: "新增资源",
    width: "800rem",
    component: RbacResourceCreate,
    confirm: module.page.request,
  });
};

const handleUpdate = (item: any) => {
  overlay({
    title: "编辑资源",
    width: "800rem",
    component: RbacResourceUpdate,
    props: {id: item.id},
    confirm: module.page.request,
  });
};

const handleDetail = (item: any) => {
  overlay({
    title: "资源详情",
    width: "800rem",
    component: RbacResourceDetail,
    props: {id: item.id},
  });
};

const handleRemove = (item: any) => {
  dialog({
    title: "删除确认",
    message: `确定删除该资源吗？`,
    confirm: async () => await module.remove.request(item.id),
    succeed: module.page.request,
  });
};

onMounted(module.page.request);
</script>

<template>
  <b-module :module="module">
    <template v-slot:search>
      <b-search label="父级" v-model="module.params.parentId" :request="config.list"/>
      <b-input label="编码" v-model="module.params.code"/>
      <b-input label="名称" v-model="module.params.name"/>
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
    <b-table-column label="路由" field="path"/>
    <b-table-column label="父级" field="parentName"/>
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
