<script setup lang="ts">
import {onMounted, ref} from "vue";
import Create from "./ResourceMenuCreate.vue";
import Detail from "./ResourceMenuDetail.vue";
import Update from "./ResourceMenuUpdate.vue";
import createBeicroonList from "@/utils/beicroon/list.utils.ts";
import BeicroonList from "@/components/beicroon/BeicroonList.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonListRow from "@/components/beicroon/BeicroonListRow.vue";
import BeicroonListCell from "@/components/beicroon/BeicroonListCell.vue";
import BeicroonDatetime from "@/components/beicroon/BeicroonDatetime.vue";
import ResourceMenuSelect from "@/modules/beicroon/menu/ResourceMenuSelect.vue";
import BeicroonListCellButton from "@/components/beicroon/BeicroonListCellButton.vue";
import {page, remove, ResourceMenuPageVO, ResourceMenuQueryDTO} from "@/request/beicroon/resource-menu.http.ts";

const list = createBeicroonList<ResourceMenuQueryDTO, ResourceMenuPageVO>("菜单", page);

onMounted(list.resetSearch);

const parentName = ref();
</script>

<template>
  <beicroon-list :list="list">
    <template #head-search>
      <resource-menu-select label="父级" v-model="list.params.parentId" v-model:show-value="parentName"></resource-menu-select>
      <beicroon-input label="编码" v-model="list.params.code"></beicroon-input>
      <beicroon-input label="名称" v-model="list.params.name"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="180">父级编码</beicroon-list-cell>
      <beicroon-list-cell width="180">父级名称</beicroon-list-cell>
      <beicroon-list-cell width="180">编码</beicroon-list-cell>
      <beicroon-list-cell width="180">名称</beicroon-list-cell>
      <beicroon-list-cell width="180">路由</beicroon-list-cell>
      <beicroon-list-cell width="180">排序</beicroon-list-cell>
      <beicroon-list-cell-button>操作</beicroon-list-cell-button>
    </template>
    <template #table-body>
      <beicroon-list-row v-for="item in list.data">
        <beicroon-list-cell>{{ item.parentCode }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.parentName }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.code }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.name }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.path }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.sorting }}</beicroon-list-cell>
        <beicroon-list-cell-button>
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
        </beicroon-list-cell-button>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-datetime class="column" label="创建时间" v-model="list.params.createdAt"></beicroon-datetime>
      <resource-menu-select class="column" label="父级" v-model="list.params.parentId" v-model:show-value="parentName"></resource-menu-select>
      <beicroon-input class="column" label="编码" v-model="list.params.code"></beicroon-input>
      <beicroon-input class="column" label="名称" v-model="list.params.name"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>
