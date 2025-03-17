<script setup lang="ts">
import {computed, onMounted} from "vue";
import {dialog, http, moduleApp, overlay, toast} from "beicroon-app-vue";
import RecordGroup from "@m/ronmiopackinglistinvoice/component/RecordGroup.vue";
import config, {PageVO, QueryDTO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import ContainerAppend from "@m/ronmiopackinglistinvoice/component/ContainerAppend.vue";
import RonmioPackingListInvoiceCreate from "@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceCreate.vue";
import RonmioPackingListInvoiceUpdate from "@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceUpdate.vue";
import RonmioPackingListInvoiceDetail from "@m/ronmiopackinglistinvoice/RonmioPackingListInvoiceDetail.vue";

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
  <beicroon-module :module="module" tab-enable>
    <template v-slot:search>
      <beicroon-input label="箱单单号" v-model="module.params.code"/>
      <beicroon-input label="客户名称" v-model="module.params.customerName"/>
      <beicroon-input label="班列号" v-model="module.params.trainCode"/>
      <beicroon-input label="货柜号" v-model="module.params.containerCode"/>
    </template>
    <template v-slot:action-left>
      <beicroon-button label="新增" @click="handleCreate" level="warning"/>
      <beicroon-button label="刷新" :loading="loading" @click="handleReload"/>
    </template>
    <template v-slot:action-right>
      <beicroon-button label="重置" @click="module.clear" level="warning"/>
      <beicroon-button label="查询" :loading="loading" @click="module.page.request()"/>
    </template>
    <beicroon-table-column label="箱单单号" field="code"/>
    <beicroon-table-column label="客户名称" field="customerName"/>
    <beicroon-table-column label="班列号" field="trainCode"/>
    <beicroon-table-column label="货柜号" field="containerCode"/>
    <beicroon-table-column label="备注" field="remark" width="220rem"/>
    <beicroon-table-group label="卖方信息" width="420rem">
      <template v-slot="{item}">
        <beicroon-table-field label="卖方名称" :content="item.sellerName"/>
        <beicroon-table-field label="卖方地址" :content="item.sellerAddr"/>
      </template>
    </beicroon-table-group>
    <beicroon-table-group label="买方信息" width="420rem">
      <template v-slot="{item}">
        <beicroon-table-field label="买方名称" :content="item.buyerName"/>
        <beicroon-table-field label="买方地址" :content="item.buyerAddr"/>
      </template>
    </beicroon-table-group>
    <beicroon-table-group label="其他信息" width="320rem">
      <template v-slot="{item}">
        <beicroon-table-field label="合同号" :content="item.contractCode"/>
        <beicroon-table-field label="原产国" :content="item.originCountry"/>
        <beicroon-table-field label="价格条款" :content="item.priceTerm"/>
        <beicroon-table-field label="箱型" :content="item.boxType"/>
      </template>
    </beicroon-table-group>
    <beicroon-table-group label="创建/更新" width="320rem">
      <template v-slot="{item}">
        <beicroon-table-field label="创建人" :content="item.creatorName"/>
        <beicroon-table-field label="创建时间" :content="item.createdAt"/>
        <beicroon-table-field label="更新人" :content="item.modifierName"/>
        <beicroon-table-field label="更新时间" :content="item.modifiedAt"/>
      </template>
    </beicroon-table-group>
    <beicroon-table-group label="操作" direction="column" frozen-right>
      <template v-slot="{item}">
        <beicroon-button-group>
          <beicroon-button size="small" level="primary" label="详情" @click="handleDetail(item)"/>
          <beicroon-button size="small" level="warning" label="编辑" @click="handleUpdate(item)"/>
          <beicroon-button size="small" level="danger" label="删除" @click="handleRemove(item)"/>
        </beicroon-button-group>
        <beicroon-button-group>
          <beicroon-button size="small" level="primary" label="下载" @click="handleDownload(item)"/>
          <beicroon-button size="small" level="primary" label="箱单" @click="handleUpdateContainer(item)"/>
          <beicroon-button size="small" level="primary" label="单价" @click="handleFillUnitPrice(item)"/>
        </beicroon-button-group>
      </template>
    </beicroon-table-group>
  </beicroon-module>
</template>

<style lang="less">

</style>
