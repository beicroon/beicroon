<script setup lang="ts">
import {onMounted} from "vue";
import Create from "./MapResultCreate.vue";
import Detail from "./MapResultDetail.vue";
import Update from "./MapResultUpdate.vue";
import createBeicroonList from "@/utils/beicroon/list.utils.ts";
import BeicroonList from "@/components/beicroon/BeicroonList.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonListRow from "@/components/beicroon/BeicroonListRow.vue";
import BeicroonListCell from "@/components/beicroon/BeicroonListCell.vue";
import BeicroonDatetime from "@/components/beicroon/BeicroonDatetime.vue";
import BeicroonListCellButton from "@/components/beicroon/BeicroonListCellButton.vue";
import {MapResultPageVO, MapResultQueryDTO, page, remove} from "./map-result.http.utils.ts";

const list = createBeicroonList<MapResultQueryDTO, MapResultPageVO>("图商解析结果", page);

onMounted(list.resetSearch);
</script>

<template>
  <beicroon-list :list="list">
    <template #head-search>
      <beicroon-input label="原地址" v-model="list.params.originAddress"></beicroon-input>
      <beicroon-input label="原地址CRC32值" v-model="list.params.originAddressHash"></beicroon-input>
      <beicroon-input label="高德省份" v-model="list.params.gaodeProvince"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="180">原地址</beicroon-list-cell>
      <beicroon-list-cell width="180">原地址CRC32值</beicroon-list-cell>
      <beicroon-list-cell width="180">高德省份</beicroon-list-cell>
      <beicroon-list-cell width="180">高德城市</beicroon-list-cell>
      <beicroon-list-cell width="180">高德区划</beicroon-list-cell>
      <beicroon-list-cell width="180">高德街道</beicroon-list-cell>
      <beicroon-list-cell width="180">高德地址</beicroon-list-cell>
      <beicroon-list-cell width="180">高德地址经度</beicroon-list-cell>
      <beicroon-list-cell width="180">高德地址纬度</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯省份</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯城市</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯区划</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯街道</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯地址</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯地址经度</beicroon-list-cell>
      <beicroon-list-cell width="180">腾讯地址纬度</beicroon-list-cell>
      <beicroon-list-cell width="180">百度省份</beicroon-list-cell>
      <beicroon-list-cell width="180">百度城市</beicroon-list-cell>
      <beicroon-list-cell width="180">百度区划</beicroon-list-cell>
      <beicroon-list-cell width="180">百度街道</beicroon-list-cell>
      <beicroon-list-cell width="180">百度地址</beicroon-list-cell>
      <beicroon-list-cell width="180">百度地址经度</beicroon-list-cell>
      <beicroon-list-cell width="180">百度地址纬度</beicroon-list-cell>
      <beicroon-list-cell-button>操作</beicroon-list-cell-button>
    </template>
    <template #table-body>
      <beicroon-list-row v-for="item in list.data">
        <beicroon-list-cell>{{ item.originAddress }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.originAddressHash }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeProvince }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeCity }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeDistrict }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeStreet }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeAddress }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeAddressLng }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.gaodeAddressLat }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunProvince }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunCity }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunDistrict }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunStreet }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunAddress }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunAddressLng }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.tengxunAddressLat }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduProvince }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduCity }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduDistrict }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduStreet }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduAddress }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduAddressLng }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.baiduAddressLat }}</beicroon-list-cell>
        <beicroon-list-cell-button>
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
        </beicroon-list-cell-button>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-datetime class="column" label="创建时间" v-model="list.params.createdAt"></beicroon-datetime>
      <beicroon-input class="column" label="原地址" v-model="list.params.originAddress"></beicroon-input>
      <beicroon-input class="column" label="原地址CRC32值" v-model="list.params.originAddressHash"></beicroon-input>
      <beicroon-input class="column" label="高德省份" v-model="list.params.gaodeProvince"></beicroon-input>
      <beicroon-input class="column" label="高德城市" v-model="list.params.gaodeCity"></beicroon-input>
      <beicroon-input class="column" label="高德区划" v-model="list.params.gaodeDistrict"></beicroon-input>
      <beicroon-input class="column" label="高德街道" v-model="list.params.gaodeStreet"></beicroon-input>
      <beicroon-input class="column" label="高德地址" v-model="list.params.gaodeAddress"></beicroon-input>
      <beicroon-input class="column" label="高德地址经度" v-model="list.params.gaodeAddressLng"></beicroon-input>
      <beicroon-input class="column" label="高德地址纬度" v-model="list.params.gaodeAddressLat"></beicroon-input>
      <beicroon-input class="column" label="腾讯省份" v-model="list.params.tengxunProvince"></beicroon-input>
      <beicroon-input class="column" label="腾讯城市" v-model="list.params.tengxunCity"></beicroon-input>
      <beicroon-input class="column" label="腾讯区划" v-model="list.params.tengxunDistrict"></beicroon-input>
      <beicroon-input class="column" label="腾讯街道" v-model="list.params.tengxunStreet"></beicroon-input>
      <beicroon-input class="column" label="腾讯地址" v-model="list.params.tengxunAddress"></beicroon-input>
      <beicroon-input class="column" label="腾讯地址经度" v-model="list.params.tengxunAddressLng"></beicroon-input>
      <beicroon-input class="column" label="腾讯地址纬度" v-model="list.params.tengxunAddressLat"></beicroon-input>
      <beicroon-input class="column" label="百度省份" v-model="list.params.baiduProvince"></beicroon-input>
      <beicroon-input class="column" label="百度城市" v-model="list.params.baiduCity"></beicroon-input>
      <beicroon-input class="column" label="百度区划" v-model="list.params.baiduDistrict"></beicroon-input>
      <beicroon-input class="column" label="百度街道" v-model="list.params.baiduStreet"></beicroon-input>
      <beicroon-input class="column" label="百度地址" v-model="list.params.baiduAddress"></beicroon-input>
      <beicroon-input class="column" label="百度地址经度" v-model="list.params.baiduAddressLng"></beicroon-input>
      <beicroon-input class="column" label="百度地址纬度" v-model="list.params.baiduAddressLat"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>
