<script setup lang="ts">
import {onMounted} from "vue";
import toast from "@/utils/toast.utils.ts";
import dialog from "@/utils/dialog.utils.ts";
import Create from "./ResourceRoleCreate.vue";
import Detail from "./ResourceRoleDetail.vue";
import Update from "./ResourceRoleUpdate.vue";
import Assign from "./ResourceRoleMenuAssign.vue";
import createBeicroonList from "@/utils/list.utils.ts";
import BeicroonList from "@/components/beicroon/BeicroonList.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonListRow from "@/components/beicroon/BeicroonListRow.vue";
import BeicroonListCell from "@/components/beicroon/BeicroonListCell.vue";
import BeicroonListCellButton from "@/components/beicroon/BeicroonListCellButton.vue";
import {page, remove, ResourceRolePageVO as VO, ResourceRoleQueryDTO as DTO} from "@/request/resource-role.http.ts";

const list = createBeicroonList<DTO, VO>("角色", page);

onMounted(list.resetSearch);

function roleMenuAssign(item: VO) {
  dialog({
    title: "菜单分配",
    width: 520,
    message: Assign,
    props: {roleId: item.id},
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
      <beicroon-input label="名称" v-model="list.params.name"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="180">编码</beicroon-list-cell>
      <beicroon-list-cell width="180">名称</beicroon-list-cell>
      <beicroon-list-cell width="180">备注</beicroon-list-cell>
      <beicroon-list-cell-button>操作</beicroon-list-cell-button>
    </template>
    <template #table-body>
      <beicroon-list-row v-for="item in list.data">
        <beicroon-list-cell>{{ item.code }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.name }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.remark }}</beicroon-list-cell>
        <beicroon-list-cell-button>
          <beicroon-button class="primary" label="菜单分配" @click="roleMenuAssign(item)"></beicroon-button>
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
        </beicroon-list-cell-button>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-input class="column" label="编码" v-model="list.params.code"></beicroon-input>
      <beicroon-input class="column" label="名称" v-model="list.params.name"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>
