<script setup lang="ts">
import {onMounted} from "vue";
import {dialog, moduleApp, overlay} from "beicroon-app-vue";
import config, {PageVO, QueryDTO} from "@m/ronmiocustomer/script/module.ts";
import RonmioCustomerCreate from "@m/ronmiocustomer/RonmioCustomerCreate.vue";
import RonmioCustomerUpdate from "@m/ronmiocustomer/RonmioCustomerUpdate.vue";
import RonmioCustomerDetail from "@m/ronmiocustomer/RonmioCustomerDetail.vue";
import {BButton, BInput, BModule, BTableColumn, BTableField, BTableGroup} from "beicroon-app-vue/components";

const module = moduleApp<QueryDTO, PageVO>(config);

const handleCreate = () => {
  overlay({
    title: "新增客户",
    component: RonmioCustomerCreate,
    confirm: module.page.request,
  });
};

const handleUpdate = (item: any) => {
  overlay({
    title: "编辑客户",
    component: RonmioCustomerUpdate,
    props: {id: item.id},
    confirm: module.page.request,
  });
};

const handleDetail = (item: any) => {
  overlay({
    title: "客户详情",
    component: RonmioCustomerDetail,
    props: {id: item.id},
  });
};

const handleRemove = (item: any) => {
  dialog({
    title: "删除确认",
    message: `确定删除该客户吗？`,
    confirm: async () => await module.remove.request(item.id),
    succeed: module.page.request,
  });
};

onMounted(module.page.request);
</script>

<template>
  <b-module :module="module">
    <template v-slot:search>
      <b-input label="客户名称" v-model="module.params.name"/>
      <b-input label="联系电话" v-model="module.params.phone"/>
    </template>
    <template v-slot:action-left>
      <b-button level="warning" label="新增" @click="handleCreate"/>
    </template>
    <template v-slot:action-right>
      <b-button label="重置" @click="module.clear" level="warning"/>
      <b-button label="查询" :loading="module.page.loading" @click="module.page.request()"/>
    </template>
    <b-table-column label="客户名称" field="name"/>
    <b-table-column label="联系电话" field="phone"/>
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
