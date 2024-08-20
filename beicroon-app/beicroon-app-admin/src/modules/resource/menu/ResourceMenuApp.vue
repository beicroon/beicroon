<script setup lang="ts">
import {PageInfo} from "@/https";
import toast from "@/utils/toast";
import {onMounted, ref} from "vue";
import dialog from "@/utils/dialog";
import Create from "./ResourceMenuCreate.vue";
import Detail from "./ResourceMenuDetail.vue";
import Update from "./ResourceMenuUpdate.vue";
import dialogWindow from "@/utils/dialogWindow";
import ElButton from "@/components/elements/ElButton.vue";
import {page, remove, ResourceMenuPageVO as VO, ResourceMenuQueryDTO as DTO} from "./ResourceMenu.http.ts";

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
  await dialogWindow("新增资源菜单", Create, {}, {
    reload: doLoad,
  });
}

async function showDetail(item: VO) {
  await dialogWindow("资源菜单详情", Detail, {id: item.id}, {
    reload: doLoad,
  });
}

async function showUpdate(item: VO) {
  await dialogWindow("编辑资源菜单", Update, {id: item.id}, {
    reload: doLoad,
  });
}

async function showRemove(item: VO) {
  await dialog(
      "是否删除该资源菜单数据？删除后数据不可恢复，请谨慎操作！",
      () => remove(item.id),
      async () => toast("删除成功").then(doLoad)
  );
}

onMounted(doLoad);
</script>

<template>
  <div class="list">
    <div class="list-head flex-row">
      <div class="head-search">
        <label class="head-input">
          <span><i>父</i><i>级</i><i>主</i><i>键</i></span>
          <input type="text" placeholder="父级主键筛选" v-model="query.parentId"/>
        </label>
        <label class="head-input">
          <span><i>父</i><i>级</i><i>编</i><i>码</i></span>
          <input type="text" placeholder="父级编码筛选" v-model="query.parentCode"/>
        </label>
        <label class="head-input">
          <span><i>父</i><i>级</i><i>名</i><i>称</i></span>
          <input type="text" placeholder="父级名称筛选" v-model="query.parentName"/>
        </label>
        <label class="head-input">
          <span><i>编</i><i>码</i></span>
          <input type="text" placeholder="编码筛选" v-model="query.code"/>
        </label>
        <label class="head-input">
          <span><i>名</i><i>称</i></span>
          <input type="text" placeholder="名称筛选" v-model="query.name"/>
        </label>
        <label class="head-input">
          <span><i>路</i><i>径</i></span>
          <input type="text" placeholder="路径筛选" v-model="query.path"/>
        </label>
        <label class="head-input">
          <span><i>排</i><i>序</i></span>
          <input type="text" placeholder="排序筛选" v-model="query.sorting"/>
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
            <div class="table-cell">父级主键</div>
          </th>
          <th>
            <div class="table-cell">父级编码</div>
          </th>
          <th>
            <div class="table-cell">父级名称</div>
          </th>
          <th>
            <div class="table-cell">编码</div>
          </th>
          <th>
            <div class="table-cell">名称</div>
          </th>
          <th>
            <div class="table-cell">路径</div>
          </th>
          <th>
            <div class="table-cell">排序</div>
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
            <div class="table-cell">{{ item.parentId }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.parentCode }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.parentName }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.code }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.name }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.path }}</div>
          </td>
          <td>
            <div class="table-cell">{{ item.sorting }}</div>
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
