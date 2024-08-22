<script setup lang="ts">
import {PageInfo} from "@/https";
import toast from "@/utils/toast";
import {onMounted, ref} from "vue";
import dialog from "@/utils/dialog";
import Create from "./AccountAdminCreate.vue";
import Detail from "./AccountAdminDetail.vue";
import Update from "./AccountAdminUpdate.vue";
import dialogWindow from "@/utils/dialogWindow";
import FormInput from "@/components/form/FormInput.vue";
import FormButton from "@/components/form/FormButton.vue";
import {AccountAdminPageVO as VO, AccountAdminQueryDTO as DTO, page, remove} from "./AccountAdmin.http.ts";

const query = ref<DTO>({} as DTO);

const paginator = ref<PageInfo>();

const data = ref<Array<VO>>();

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
  query.value = {} as DTO;
}

async function showCreate() {
  await dialogWindow("新增后台账号", Create, {}, {
    reload: doLoad,
  });
}

async function showDetail(item: VO) {
  await dialogWindow("后台账号详情", Detail, {id: item.id}, {
    reload: doLoad,
  });
}

async function showUpdate(item: VO) {
  await dialogWindow("编辑后台账号", Update, {id: item.id}, {
    reload: doLoad,
  });
}

async function showRemove(item: VO) {
  await dialog(
      "是否删除该后台账号数据？删除后数据不可恢复，请谨慎操作！",
      () => remove(item.id),
      async () => toast("删除成功").then(doLoad)
  );
}

onMounted(doLoad);
</script>

<template>
  <div class="list">
    <div class="list-head">
      <div class="head-search">
        <form-input class="row" v-model="query.code"><i>编</i><i>码</i></form-input>
        <form-input class="row" v-model="query.username"><i>账</i><i>号</i></form-input>
        <form-input class="row" v-model="query.nickname"><i>昵</i><i>称</i></form-input>
        <form-input class="row" v-model="query.phone"><i>电</i><i>话</i></form-input>
        <form-input class="row" v-model="query.email"><i>邮</i><i>箱</i></form-input>
      </div>
      <div class="head-action">
        <form-button class="head-button" @click="doLoad" :loading="loading">查询</form-button>
        <form-button class="head-button" @click="doReset">重置</form-button>
        <form-button class="head-button" @click="showCreate">新增</form-button>
      </div>
    </div>
    <div class="list-view">
      <table class="list-table">
        <thead class="table-head">
        <tr class="table-row">
          <th><div class="table-cell">编码</div></th>
          <th><div class="table-cell">账号</div></th>
          <th><div class="table-cell">昵称</div></th>
          <th><div class="table-cell">电话</div></th>
          <th><div class="table-cell">邮箱</div></th>
          <th><div class="table-cell">创建信息</div></th>
          <th><div class="table-cell">更新信息</div></th>
          <th class="table-action">
            <div class="table-cell table-button">操作</div>
          </th>
        </tr>
        </thead>
        <tbody class="table-body">
        <tr class="table-row" v-for="item in data">
          <td><div class="table-cell">{{ item.code }}</div></td>
          <td><div class="table-cell">{{ item.username }}</div></td>
          <td><div class="table-cell">{{ item.nickname }}</div></td>
          <td><div class="table-cell">{{ item.phone }}</div></td>
          <td><div class="table-cell">{{ item.email }}</div></td>
          <td>
            <div class="table-cell table-datetime">
              <div class="line">
                <span class="label">昵称</span>
                <span class="value">{{ item.creatorName }}</span>
              </div>
              <div class="line">
                <span class="label">时间</span>
                <span class="value">{{ item.createdAt }}</span>
              </div>
            </div>
          </td>
          <td>
            <div class="table-cell table-datetime">
              <div class="line">
                <span class="label">昵称</span>
                <span class="value">{{ item.modifierName }}</span>
              </div>
              <div class="line">
                <span class="label">时间</span>
                <span class="value">{{ item.modifiedAt }}</span>
              </div>
            </div>
          </td>
          <td class="table-action">
            <div class="table-cell table-button">
              <form-button class="primary" @click="showDetail(item)">查看</form-button>
              <form-button class="warning" @click="showUpdate(item)">编辑</form-button>
              <form-button class="dangerous" @click="showRemove(item)">删除</form-button>
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
