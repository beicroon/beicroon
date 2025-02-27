<script setup lang="ts">
import {onMounted} from "vue";
import dialogUtils from "@u/dialog.utils.ts";
import overlayUtils from "@u/overlay.utils.ts";
import newModule from "@u/module-app.utils.ts";
import BeicroonInput from "@c/BeicroonInput.vue";
import BeicroonSearch from "@c/BeicroonSearch.vue";
import BeicroonModule from "@c/BeicroonModule.vue";
import BeicroonButton from "@c/BeicroonButton.vue";
import BeicroonTableField from "@c/BeicroonTableField.vue";
import BeicroonTableGroup from "@c/BeicroonTableGroup.vue";
import BeicroonTableColumn from "@c/BeicroonTableColumn.vue";
import AdminResourceCreate from "@m/adminresource/AdminResourceCreate.vue";
import AdminResourceUpdate from "@m/adminresource/AdminResourceUpdate.vue";
import AdminResourceDetail from "@m/adminresource/AdminResourceDetail.vue";
import config, {PageVO, QueryDTO} from "@m/adminresource/script/module.ts";

const module = newModule<QueryDTO, PageVO>(config);

const handleCreate = () => {
  overlayUtils({
    title: "新增资源",
    width: "800rem",
    component: AdminResourceCreate,
    confirm: module.page.request,
  });
};

const handleUpdate = (item: any) => {
  overlayUtils({
    title: "编辑资源",
    width: "800rem",
    component: AdminResourceUpdate,
    props: {id: item.id},
    confirm: module.page.request,
  });
};

const handleDetail = (item: any) => {
  overlayUtils({
    title: "资源详情",
    width: "800rem",
    component: AdminResourceDetail,
    props: {id: item.id},
  });
};

const handleRemove = (item: any) => {
  dialogUtils({
    title: "删除确认",
    message: `确定删除该资源吗？`,
    confirm: async () => await module.remove.request(item.id),
    succeed: module.page.request,
  });
};

onMounted(module.page.request);
</script>

<template>
  <beicroon-module :module="module">
    <template v-slot:search>
      <beicroon-search label="父级" v-model="module.params.parentId" :request="config.list"/>
      <beicroon-input label="编码" v-model="module.params.code"/>
      <beicroon-input label="名称" v-model="module.params.name"/>
    </template>
    <template v-slot:action-left>
      <beicroon-button level="warning" label="新增" @click="handleCreate"/>
    </template>
    <template v-slot:action-right>
      <beicroon-button label="重置" @click="module.clear" level="warning"/>
      <beicroon-button label="查询" :loading="module.page.loading" @click="module.page.request()"/>
    </template>
    <beicroon-table-column label="编码" field="code"/>
    <beicroon-table-column label="名称" field="name"/>
    <beicroon-table-column label="路由" field="path"/>
    <beicroon-table-column label="父级" field="parentName"/>
    <beicroon-table-group label="创建/更新" width="320rem">
      <template v-slot="{item}">
        <beicroon-table-field label="创建人" :content="item.creatorName"/>
        <beicroon-table-field label="创建时间" :content="item.createdAt"/>
        <beicroon-table-field label="更新人" :content="item.modifierName"/>
        <beicroon-table-field label="更新时间" :content="item.modifiedAt"/>
      </template>
    </beicroon-table-group>
    <beicroon-table-group label="操作" direction="row" frozen-right>
      <template v-slot="{item}">
        <beicroon-button size="small" level="primary" label="详情" @click="handleDetail(item)"/>
        <beicroon-button size="small" level="warning" label="编辑" @click="handleUpdate(item)"/>
        <beicroon-button size="small" level="danger" label="删除" @click="handleRemove(item)"/>
      </template>
    </beicroon-table-group>
  </beicroon-module>
</template>

<style lang="less">

</style>
