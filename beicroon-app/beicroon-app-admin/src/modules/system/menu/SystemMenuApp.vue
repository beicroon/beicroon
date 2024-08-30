<script setup lang="ts">
import {PageInfo} from "@/http.ts";
import {onMounted, ref, unref, watch} from "vue";
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

onMounted(handleSearch);

function watchPage() {
  return {
    page: pageInfo.value.page,
    size: pageInfo.value.size,
  };
}

watch(watchPage, handleSearch);

function handleReset() {
  params.value = {};
}

async function handleSearch() {
  if (unref(loading)) {
    return;
  }

  loading.value = true;

  data.value = [];

  const res = await page(params.value, pageInfo.value).finally(() => {
    loading.value = false;
  });

  data.value = res.data;

  pageInfo.value.size = res.page.size;
}

function handleCreate() {

}

function handleDetail(item: VO) {

}

function handleUpdate(item: VO) {

}

function handleRemove(item: VO) {

}
</script>

<template>
  <beicroon-list v-model:page-info="pageInfo">
    <template #head-search>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="loading" @click="handleSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="handleCreate"></beicroon-button>
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
          <beicroon-button class="primary" label="查看" @click="handleDetail(item)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="handleUpdate(item)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="handleRemove(item)"></beicroon-button>
        </beicroon-list-cell>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input class="column-three" label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input class="column-two" label="路径1" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>