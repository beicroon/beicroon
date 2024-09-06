<script setup lang="ts">
import {onMounted} from "vue";
import createBeicroonList from "@/list.ts";
import Create from "./AccountAdminCreate.vue";
import Detail from "./AccountAdminDetail.vue";
import Update from "./AccountAdminUpdate.vue";
import BeicroonList from "@/components/BeicroonList.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonListRow from "@/components/BeicroonListRow.vue";
import BeicroonListCell from "@/components/BeicroonListCell.vue";
import BeicroonListCellButton from "@/components/BeicroonListCellButton.vue";
import {AccountAdminPageVO as VO, AccountAdminQueryDTO as DTO, page, remove} from "./account-admin.http.ts";

const list = createBeicroonList<DTO, VO>("后台账号", page);

onMounted(list.resetSearch);
</script>

<template>
  <beicroon-list :list="list">
    <template #head-search>
      <beicroon-input label="编码" v-model="list.params.code"></beicroon-input>
      <beicroon-input label="账号" v-model="list.params.username"></beicroon-input>
      <beicroon-input label="密码" v-model="list.params.password"></beicroon-input>
      <beicroon-input label="昵称" v-model="list.params.nickname"></beicroon-input>
      <beicroon-input label="电话" v-model="list.params.phone"></beicroon-input>
      <beicroon-input label="邮箱" v-model="list.params.email"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="180">编码</beicroon-list-cell>
      <beicroon-list-cell width="180">账号</beicroon-list-cell>
      <beicroon-list-cell width="180">密码</beicroon-list-cell>
      <beicroon-list-cell width="180">昵称</beicroon-list-cell>
      <beicroon-list-cell width="180">电话</beicroon-list-cell>
      <beicroon-list-cell width="180">邮箱</beicroon-list-cell>
      <beicroon-list-cell-button>操作</beicroon-list-cell-button>
    </template>
    <template #table-body>
      <beicroon-list-row v-for="item in list.data">
        <beicroon-list-cell>{{ item.code }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.username }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.password }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.nickname }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.phone }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.email }}</beicroon-list-cell>
        <beicroon-list-cell-button>
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
        </beicroon-list-cell-button>
      </beicroon-list-row>
    </template>
    <template #more-search>
      
    </template>
  </beicroon-list>
</template>

<style lang="less">
</style>
