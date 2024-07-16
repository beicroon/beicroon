<script setup lang="ts">
import {onMounted, ref} from 'vue';
import {PageInfo} from '@/https';
import {AdminPageVO, AdminQueryDTO, page} from '@/https/account/admin.http.ts';

const pageInfo = ref<PageInfo>();
const data = ref<Array<AdminPageVO>>();

onMounted(async () => {
  const res = await page({} as AdminQueryDTO);

  data.value = res.data;

  pageInfo.value = res.page;
})
</script>

<template>
  <div class="list">
    <div class="list-function">
      <button>新增</button>
      <button>刷新</button>
      <button>重置</button>
    </div>
    <table class="list-table">
      <thead class="list-head">
      <tr class="list-row">
        <th>
          <div class="list-cell">ID</div>
        </th>
        <th>
          <div class="list-cell">编号</div>
        </th>
        <th>
          <div class="list-cell">昵称</div>
        </th>
        <th>
          <div class="list-cell">账号</div>
        </th>
        <th>
          <div class="list-cell">电话</div>
        </th>
        <th>
          <div class="list-cell">邮箱</div>
        </th>
        <th>
          <div class="list-cell">创建时间</div>
        </th>
        <th class="list-action">
          <div class="list-cell list-button">操作</div>
        </th>
      </tr>
      </thead>
      <tbody class="list-body">
      <tr class="list-row" v-for="item in data">
        <td>
          <div class="list-cell">{{ item.id }}</div>
        </td>
        <td>
          <div class="list-cell">{{ item.code }}</div>
        </td>
        <td>
          <div class="list-cell">{{ item.nickname }}</div>
        </td>
        <td>
          <div class="list-cell">{{ item.account }}</div>
        </td>
        <td>
          <div class="list-cell">{{ item.phone }}</div>
        </td>
        <td>
          <div class="list-cell">{{ item.email }}</div>
        </td>
        <td>
          <div class="list-cell">{{ item.createdAt }}</div>
        </td>
        <td class="list-action">
          <div class="list-cell list-button">
            <button>查看</button>
            <button>编辑</button>
            <button>删除</button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped lang="less">

</style>