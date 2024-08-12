<script setup lang="ts">
import {PageInfo} from "@/https";
import toast from "@/utils/toast";
import {onMounted, ref} from "vue";
import dialog from "@/utils/dialog";
import dialogWindow from "@/utils/dialogWindow";
import ElButton from "@/components/elements/ElButton.vue";
import Create from "@/views/index/account/admin/AccountAdminCreate.vue";
import Detail from "@/views/index/account/admin/AccountAdminDetail.vue";
import Update from "@/views/index/account/admin/AccountAdminUpdate.vue";
import {AdminPageVO, AdminQueryDTO, page, remove} from "@/https/account/admin.http.ts";

const query = ref<AdminQueryDTO>({} as AdminQueryDTO);

const paginator = ref<PageInfo>();

const data = ref<Array<AdminPageVO>>();

const loading = ref(false);

async function doLoad() {
  loading.value = true;

  data.value = [];

  const res = await page(query.value);

  data.value = res.data;

  paginator.value = res.page;

  loading.value = false;
}

async function doReset() {
  query.value = {} as AdminQueryDTO;
}

async function showCreate() {
  await dialogWindow("新增账号", Create, {}, {
    reload: doLoad,
  });
}

async function showDetail(item: AdminPageVO) {
  await dialogWindow("账号详情", Detail, {id: item.id}, {
    reload: doLoad,
  });
}

async function showUpdate(item: AdminPageVO) {
  await dialogWindow("编辑账号", Update, {id: item.id}, {
    reload: doLoad,
  });
}

async function showRemove(item: AdminPageVO) {
  await dialog(
      "是否删除该账号数据？删除后数据不可恢复，请谨慎操作！",
      async () => await remove(item.id),
      async () => await toast("删除成功").then(doLoad)
  );
}

onMounted(doLoad);
</script>

<template>
  <div class="list">
    <div class="list-head flex-row">
      <div class="head-search">
        <label class="head-input">
          <span><i>昵</i><i>称</i></span>
          <input type="text" placeholder="昵称筛选" v-model="query.nickname"/>
        </label>
        <label class="head-input">
          <span><i>电</i><i>话</i></span>
          <input type="text" placeholder="电话筛选" v-model="query.phone"/>
        </label>
        <label class="head-input">
          <span><i>邮</i><i>箱</i></span>
          <input type="text" placeholder="邮箱筛选" v-model="query.email"/>
        </label>
      </div>
      <div class="head-action">
        <el-button class="head-button" @click="doLoad" :loading="loading">查询</el-button>
        <el-button class="head-button" @click="doReset">重置</el-button>
        <el-button class="head-button" @click="showCreate">新增</el-button>
      </div>
    </div>
    <div class="list-view">
      <table class="list-table">
        <thead class="table-head">
        <tr class="table-row">
          <th>
            <div class="table-cell">ID</div>
          </th>
          <th>
            <div class="table-cell">编号</div>
          </th>
          <th>
            <div class="table-cell">账号</div>
          </th>
          <th>
            <div class="table-cell">昵称</div>
          </th>
          <th>
            <div class="table-cell">电话</div>
          </th>
          <th>
            <div class="table-cell">邮箱</div>
          </th>
          <th>
            <div class="table-cell">创建时间</div>
          </th>
          <th>
            <div class="table-cell">创建人昵称</div>
          </th>
          <th>
            <div class="table-cell">更新时间</div>
          </th>
          <th>
            <div class="table-cell">更新人昵称</div>
          </th>
          <th class="table-action">
            <div class="table-cell table-button">操作</div>
          </th>
        </tr>
        </thead>
        <tbody class="table-body">
        <tr class="table-row" v-for="item in data">
          <td>
            <div class="table-cell">{{ item.id }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.code }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.username }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.nickname }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.phone }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.email }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.createdAt }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.creatorName }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.modifiedAt }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.modifierName }}</div>
          </td>
          <td class="table-action">
            <div class="table-cell table-button">
              <el-button class="primary" @click="showDetail(item)">查看</el-button>
              <el-button class="warning" @click="showUpdate(item)">编辑</el-button>
              <el-button class="dangerous" @click="showRemove(item)">删除</el-button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="list-foot"></div>
  </div>
</template>

<style scoped lang="less">
</style>