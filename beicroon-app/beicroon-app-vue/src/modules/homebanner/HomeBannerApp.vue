<script setup lang="ts">
import {onMounted} from "vue";
import {dialog, moduleApp, overlay} from "@/index";
import HomeBannerCreate from "@m/homebanner/HomeBannerCreate.vue";
import HomeBannerUpdate from "@m/homebanner/HomeBannerUpdate.vue";
import HomeBannerDetail from "@m/homebanner/HomeBannerDetail.vue";
import config, {PageVO, QueryDTO} from "@m/homebanner/script/module";
import {BButton, BDatetime, BImage, BInput, BModule, BTableColumn, BTableField, BTableGroup} from "@/components";

const module = moduleApp<QueryDTO, PageVO>(config);

const handleCreate = () => {
  overlay({
    title: "新增轮播图",
    component: HomeBannerCreate,
    confirm: module.page.request,
  });
};

const handleUpdate = (item: any) => {
  overlay({
    title: "编辑轮播图",
    component: HomeBannerUpdate,
    props: {id: item.id},
    confirm: module.page.request,
  });
};

const handleDetail = (item: any) => {
  overlay({
    title: "轮播图详情",
    component: HomeBannerDetail,
    props: {id: item.id},
  });
};

const handleRemove = (item: any) => {
  dialog({
    title: "删除确认",
    message: `确定删除该轮播图吗？`,
    confirm: async () => await module.remove.request(item.id),
    succeed: module.page.request,
  });
};

onMounted(module.page.request);
</script>

<template>
  <b-module :module="module">
    <template v-slot:search>
      <b-input label="描述" v-model="module.params.description"/>
      <b-datetime label="生效时间" time="start" v-model="module.params.validAt"/>
      <b-datetime label="过期时间" time="end" v-model="module.params.expiredAt"/>
    </template>
    <template v-slot:action-left>
      <b-button level="warning" label="新增" @click="handleCreate"/>
    </template>
    <template v-slot:action-right>
      <b-button label="重置" @click="module.clear" level="warning"/>
      <b-button label="查询" :loading="module.page.loading" @click="module.page.request()"/>
    </template>
    <b-table-group label="图片">
      <template v-slot="{item}">
        <b-image :src="item.fileUrl" :alt="item.fileName" size="small" />
      </template>
    </b-table-group>
    <b-table-column label="描述" field="description"/>
    <b-table-column label="生效时间" field="validAt"/>
    <b-table-column label="过期时间" field="expiredAt"/>
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
