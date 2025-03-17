<script setup lang="ts">
import {BTableCell, BTableView} from "beicroon-app-vue/components";
import {ContainerDetailVO} from "@m/ronmiopackinglistinvoice/script/module.ts";

interface Props {
  data?: Array<ContainerDetailVO>,
}

defineProps<Props>();
</script>

<template>
  <b-table-view :loading="!data" height="520rem">
    <template v-slot:head>
      <b-table-cell class="frozen" label="箱号"/>
      <b-table-cell label="等级"/>
      <b-table-cell label="规格(mm*mm*mm)"/>
      <b-table-cell label="单价"/>
      <b-table-cell label="总价"/>
      <b-table-cell label="总金额"/>
      <b-table-cell label="商品件数"/>
      <b-table-cell label="商品总件数"/>
      <b-table-cell label="商品体积(m³)"/>
      <b-table-cell label="商品总体积(m³)"/>
      <b-table-cell label="净重(kg)"/>
      <b-table-cell label="总净重(kg)"/>
      <b-table-cell label="毛重(kg)"/>
      <b-table-cell label="总毛重(kg)"/>
      <b-table-cell label="商品名称"/>
      <b-table-cell label="商品编码"/>
      <b-table-cell label="收货人"/>
      <b-table-cell label="目的地"/>
      <b-table-cell label="备注"/>
    </template>
    <template v-slot:body>
      <template v-for="item in data">
        <template v-for="(record, index) in item.records">
          <tr>
            <b-table-cell v-if="index === 0" column-class="frozen" :label="record.containerCode" :rowspan="item.records.length"/>
            <b-table-cell :label="record.level"/>
            <b-table-cell :label="record.specification"/>
            <b-table-cell :label="record.unitPrice"/>
            <b-table-cell :label="record.totalAmount"/>
            <b-table-cell v-if="index === 0" :label="item.totalAmount" :rowspan="item.records.length"/>
            <b-table-cell :label="record.count"/>
            <b-table-cell v-if="index === 0" :label="item.count" :rowspan="item.records.length"/>
            <b-table-cell :label="record.volume"/>
            <b-table-cell v-if="index === 0" :label="item.volume" :rowspan="item.records.length"/>
            <b-table-cell :label="record.netWeight"/>
            <b-table-cell v-if="index === 0" :label="item.netWeight" :rowspan="item.records.length"/>
            <b-table-cell :label="record.grossWeight"/>
            <b-table-cell v-if="index === 0" :label="item.grossWeight" :rowspan="item.records.length"/>
            <b-table-cell :label="record.commodityName"/>
            <b-table-cell :label="record.commodityCode"/>
            <b-table-cell :label="record.receiveName"/>
            <b-table-cell :label="record.destination"/>
            <b-table-cell :label="record.remark"/>
          </tr>
        </template>
      </template>
    </template>
  </b-table-view>
</template>

<style lang="less">
</style>