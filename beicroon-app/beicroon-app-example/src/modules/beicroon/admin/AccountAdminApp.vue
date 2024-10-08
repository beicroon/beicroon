<script setup lang="ts">
import {onMounted} from "vue";
import Create from "./AccountAdminCreate.vue";
import Detail from "./AccountAdminDetail.vue";
import Update from "./AccountAdminUpdate.vue";
import Assign from "./AccountAdminRoleAssign.vue";
import toast from "@/utils/beicroon/toast.utils.ts";
import dialog from "@/utils/beicroon/dialog.utils.ts";
import createBeicroonList from "@/utils/beicroon/list.utils.ts";
import BeicroonList from "@/components/beicroon/BeicroonList.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonListRow from "@/components/beicroon/BeicroonListRow.vue";
import BeicroonListCell from "@/components/beicroon/BeicroonListCell.vue";
import BeicroonDatetime from "@/components/beicroon/BeicroonDatetime.vue";
import BeicroonListCellButton from "@/components/beicroon/BeicroonListCellButton.vue";
import {AccountAdminPageVO, AccountAdminQueryDTO, page, remove} from "@/request/beicroon/account-admin.http.ts";

const list = createBeicroonList<AccountAdminQueryDTO, AccountAdminPageVO>("后台账号", page);

onMounted(list.resetSearch);

function adminRoleAssign(item: AccountAdminPageVO) {
  dialog({
    title: "角色分配",
    width: 520,
    message: Assign,
    props: {adminId: item.id},
    confirm: async () => {
      await toast("保存成功");
    },
  });
}
</script>

<template>
  <beicroon-list :list="list">
    <template #head-search>
      <beicroon-input label="编码" v-model="list.params.code"></beicroon-input>
      <beicroon-input label="昵称" v-model="list.params.name"></beicroon-input>
      <beicroon-input label="账号" v-model="list.params.username"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="180">编码</beicroon-list-cell>
      <beicroon-list-cell width="180">昵称</beicroon-list-cell>
      <beicroon-list-cell width="180">账号</beicroon-list-cell>
      <beicroon-list-cell width="180">电话</beicroon-list-cell>
      <beicroon-list-cell width="180">邮箱</beicroon-list-cell>
      <beicroon-list-cell-button>操作</beicroon-list-cell-button>
    </template>
    <template #table-body>
      <beicroon-list-row v-for="item in list.data">
        <beicroon-list-cell>{{ item.code }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.name }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.username }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.phone }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.email }}</beicroon-list-cell>
        <beicroon-list-cell-button>
          <beicroon-button class="primary" label="角色分配" @click="adminRoleAssign(item)"></beicroon-button>
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
        </beicroon-list-cell-button>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-datetime class="column" label="创建时间" v-model="list.params.createdAt"></beicroon-datetime>
      <beicroon-input class="column" label="编码" v-model="list.params.code"></beicroon-input>
      <beicroon-input class="column" label="昵称" v-model="list.params.name"></beicroon-input>
      <beicroon-input class="column" label="账号" v-model="list.params.username"></beicroon-input>
      <beicroon-input class="column" label="电话" v-model="list.params.phone"></beicroon-input>
      <beicroon-input class="column" label="邮箱" v-model="list.params.email"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>
