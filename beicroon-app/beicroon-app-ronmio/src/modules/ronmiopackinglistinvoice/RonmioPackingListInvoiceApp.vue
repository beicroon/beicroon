<script setup lang="ts">
import {computed, onMounted} from "vue";
import {dialog, http, moduleApp, overlay, toast} from "beicroon-app-vue";
import RecordGroup from "@m/ronmiopackinglistinvoice/component/RecordGroup.vue";
import config, {PageVO, QueryDTO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import ContainerAppend from "@m/ronmiopackinglistinvoice/component/ContainerAppend.vue";
import RonmioPackingListInvoiceCreate from "@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceCreate.vue";
import RonmioPackingListInvoiceUpdate from "@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceUpdate.vue";
import RonmioPackingListInvoiceDetail from "@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceDetail.vue";
import {BButton, BButtonGroup, BInput, BModule, BTableColumn, BTableField, BTableGroup} from "beicroon-app-vue/components";

const module = moduleApp<QueryDTO, PageVO>(config);

const handleCreate = () => {
  overlay({
    title: "新增箱单发票",
    component: RonmioPackingListInvoiceCreate,
    confirm: module.page.request,
  });
};

const handleUpdate = (item: any) => {
  overlay({
    title: `编辑箱单发票[${item.code}]`,
    component: RonmioPackingListInvoiceUpdate,
    props: {id: item.id},
    confirm: module.page.request,
  });
};

const handleDetail = (item: any) => {
  overlay({
    title: "箱单发票详情",
    component: RonmioPackingListInvoiceDetail,
    props: {id: item.id},
  });
};

const handleRemove = (item: any) => {
  dialog({
    title: "删除确认",
    message: `确定删除该箱单发票吗？`,
    confirm: async () => await module.remove.request(item.id),
    succeed: module.page.request,
  });
};

const handleUpdateContainer = async (item: any) => {
  overlay({
    title: `添加箱单[${item.code}]`,
    component: ContainerAppend,
    props: {id: item.id},
  });
};

const handleFillUnitPrice = async (item: any) => {
  overlay({
    title: `录入单价[${item.code}]`,
    width: "1400rem",
    component: RecordGroup,
    props: {id: item.id},
  });
};

const handleDownload = async (item: any) => {
  const res = await http(config.download, {id: item.id});

  window.open(res.data as string, "_blank");

  toast.success("下载成功");
};

const loading = computed(() => module.page.loading || module.tab.loading);

const handleReload = () => {
  module.tab.request();

  module.page.request();
};

onMounted(handleReload);
</script>

<template>
  <b-module :module="module" tab-enable>
    <template v-slot:search>
      <b-input label="箱单单号" v-model="module.params.code"/>
      <b-input label="客户名称" v-model="module.params.customerName"/>
      <b-input label="班列号" v-model="module.params.trainCode"/>
      <b-input label="货柜号" v-model="module.params.containerCode"/>
    </template>
    <template v-slot:action-left>
      <b-button label="新增" @click="handleCreate" level="warning"/>
      <b-button label="刷新" :loading="loading" @click="handleReload"/>
    </template>
    <template v-slot:action-right>
      <b-button label="重置" @click="module.clear" level="warning"/>
      <b-button label="查询" :loading="loading" @click="module.page.request()"/>
    </template>
    <b-table-column label="箱单单号" field="code"/>
    <b-table-column label="客户名称" field="customerName"/>
    <b-table-column label="班列号" field="trainCode"/>
    <b-table-column label="货柜号" field="containerCode"/>
    <b-table-column label="备注" field="remark" width="220rem"/>
    <b-table-group label="卖方信息" width="420rem">
      <template v-slot="{item}">
        <b-table-field label="卖方名称" :content="item.sellerName"/>
        <b-table-field label="卖方地址" :content="item.sellerAddr"/>
      </template>
    </b-table-group>
    <b-table-group label="买方信息" width="420rem">
      <template v-slot="{item}">
        <b-table-field label="买方名称" :content="item.buyerName"/>
        <b-table-field label="买方地址" :content="item.buyerAddr"/>
      </template>
    </b-table-group>
    <b-table-group label="其他信息" width="320rem">
      <template v-slot="{item}">
        <b-table-field label="合同号" :content="item.contractCode"/>
        <b-table-field label="原产国" :content="item.originCountry"/>
        <b-table-field label="价格条款" :content="item.priceTerm"/>
        <b-table-field label="箱型" :content="item.boxType"/>
      </template>
    </b-table-group>
    <b-table-group label="创建/更新" width="320rem">
      <template v-slot="{item}">
        <b-table-field label="创建人" :content="item.creatorName"/>
        <b-table-field label="创建时间" :content="item.createdAt"/>
        <b-table-field label="更新人" :content="item.modifierName"/>
        <b-table-field label="更新时间" :content="item.modifiedAt"/>
      </template>
    </b-table-group>
    <b-table-group label="操作" direction="column" frozen-right>
      <template v-slot="{item}">
        <b-button-group>
          <b-button size="small" level="primary" label="详情" @click="handleDetail(item)"/>
          <b-button size="small" level="warning" label="编辑" @click="handleUpdate(item)"/>
          <b-button size="small" level="danger" label="删除" @click="handleRemove(item)"/>
        </b-button-group>
        <b-button-group>
          <b-button size="small" level="primary" label="下载" @click="handleDownload(item)"/>
          <b-button size="small" level="primary" label="箱单" @click="handleUpdateContainer(item)"/>
          <b-button size="small" level="primary" label="单价" @click="handleFillUnitPrice(item)"/>
        </b-button-group>
      </template>
    </b-table-group>
  </b-module>
</template>

<style lang="less">

</style>
