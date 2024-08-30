<script setup lang="ts">
import {onMounted, ref, unref, watch} from "vue";
import {PageInfo} from "@/http.ts";
import BeicroonList from "@/components/BeicroonList.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonListRow from "@/components/BeicroonListRow.vue";
import BeicroonListCell from "@/components/BeicroonListCell.vue";
import {page, SystemMenuPageVO as VO, SystemMenuQueryDTO as DTO} from "@/modules/system/menu/system.menu.http.ts";

const loading = ref(false);

const params = ref<DTO>({});

const data = ref<Array<VO>>([]);

const pageInfo = ref<PageInfo>({
  page: 1,
  size: 15,
  total: 0,
});

async function getPage() {
  if (unref(loading)) {
    return;
  }

  loading.value = true;

  data.value = [];

  const res = await page(params.value, pageInfo.value);

  data.value = res.data;

  pageInfo.value.size = res.page.size;

  loading.value = false;
}

onMounted(getPage);

function watchPage() {
  return {
    page: pageInfo.value.page,
    size: pageInfo.value.size,
  };
}

watch(watchPage, getPage);
</script>

<template>
  <beicroon-list v-model:page-info="pageInfo">
    <template #head-search>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置"></beicroon-button>
      <beicroon-button class="block primary" label="查询"></beicroon-button>
      <beicroon-button class="block warning" label="新增"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="500">名称</beicroon-list-cell>
      <beicroon-list-cell width="500">路径</beicroon-list-cell>
      <beicroon-list-cell width="500">排序</beicroon-list-cell>
      <beicroon-list-cell class="beicroon-list-button">操作</beicroon-list-cell>
    </template>
    <template #table-body>
      <beicroon-list-row label="名称" field="name" width="500" v-for="item in data">
        <beicroon-list-cell>{{item.name}}</beicroon-list-cell>
        <beicroon-list-cell>{{item.path}}</beicroon-list-cell>
        <beicroon-list-cell>{{item.sorting}}</beicroon-list-cell>
        <beicroon-list-cell class="beicroon-list-button">
          <beicroon-button class="primary" label="查看"></beicroon-button>
          <beicroon-button class="warning" label="编辑"></beicroon-button>
          <beicroon-button class="danger" label="删除"></beicroon-button>
        </beicroon-list-cell>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>