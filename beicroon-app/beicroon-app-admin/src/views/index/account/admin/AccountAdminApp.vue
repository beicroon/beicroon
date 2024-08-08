<script setup lang="ts">
import {PageInfo} from '@/https';
import {onMounted, ref} from 'vue';
import ElButton from '@/components/elements/ElButton.vue';
import {AdminPageVO, AdminQueryDTO, page} from '@/https/account/admin.http.ts';

const query = ref<AdminQueryDTO>({} as AdminQueryDTO);

const paginator = ref<PageInfo>();

const data = ref<Array<AdminPageVO>>();

onMounted(load);

const loading = ref(false);

async function load() {
  loading.value = true;

  data.value = [];

  const res = await page(query.value);

  data.value = res.data;

  paginator.value = res.page;

  loading.value = false;
}

async function reset() {
  query.value = {} as AdminQueryDTO;
}

async function create() {
}

async function update(item: AdminPageVO) {
  console.info(item);
}

async function detail(item: AdminPageVO) {
  console.info(item);
}

async function remove(item: AdminPageVO) {
  console.info(item);
}
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
        <el-button class="head-button" @click="load" :loading="loading">查询</el-button>
        <el-button class="head-button" @click="reset">重置</el-button>
        <el-button class="head-button" @click="create">新增</el-button>
      </div>
    </div>
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
        <td class="table-action">
          <div class="table-cell table-button">
            <el-button class="primary" @click="detail(item)">查看</el-button>
            <el-button class="warning" @click="update(item)">编辑</el-button>
            <el-button class="dangerous" @click="remove(item)">删除</el-button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped lang="less">

</style>