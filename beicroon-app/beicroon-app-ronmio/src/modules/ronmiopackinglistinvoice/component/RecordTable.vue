<script setup lang="ts">
import {ContainerDetailVO} from "@m/ronmiopackinglistinvoice/script/module.ts";

interface Props {
  data?: Array<ContainerDetailVO>,
}

defineProps<Props>();
</script>

<template>
  <beicroon-table-view :loading="!data" height="520rem">
    <template v-slot:head>
      <beicroon-table-cell class="frozen" label="箱号"/>
      <beicroon-table-cell label="等级"/>
      <beicroon-table-cell label="规格(mm*mm*mm)"/>
      <beicroon-table-cell label="单价"/>
      <beicroon-table-cell label="总价"/>
      <beicroon-table-cell label="总金额"/>
      <beicroon-table-cell label="商品件数"/>
      <beicroon-table-cell label="商品总件数"/>
      <beicroon-table-cell label="商品体积(m³)"/>
      <beicroon-table-cell label="商品总体积(m³)"/>
      <beicroon-table-cell label="净重(kg)"/>
      <beicroon-table-cell label="总净重(kg)"/>
      <beicroon-table-cell label="毛重(kg)"/>
      <beicroon-table-cell label="总毛重(kg)"/>
      <beicroon-table-cell label="商品名称"/>
      <beicroon-table-cell label="商品编码"/>
      <beicroon-table-cell label="收货人"/>
      <beicroon-table-cell label="目的地"/>
      <beicroon-table-cell label="备注"/>
    </template>
    <template v-slot:body>
      <template v-for="item in data">
        <template v-for="(record, index) in item.records">
          <tr>
            <beicroon-table-cell v-if="index === 0" column-class="frozen" :label="record.containerCode" :rowspan="item.records.length"/>
            <beicroon-table-cell :label="record.level"/>
            <beicroon-table-cell :label="record.specification"/>
            <beicroon-table-cell :label="record.unitPrice"/>
            <beicroon-table-cell :label="record.totalAmount"/>
            <beicroon-table-cell v-if="index === 0" :label="item.totalAmount" :rowspan="item.records.length"/>
            <beicroon-table-cell :label="record.count"/>
            <beicroon-table-cell v-if="index === 0" :label="item.count" :rowspan="item.records.length"/>
            <beicroon-table-cell :label="record.volume"/>
            <beicroon-table-cell v-if="index === 0" :label="item.volume" :rowspan="item.records.length"/>
            <beicroon-table-cell :label="record.netWeight"/>
            <beicroon-table-cell v-if="index === 0" :label="item.netWeight" :rowspan="item.records.length"/>
            <beicroon-table-cell :label="record.grossWeight"/>
            <beicroon-table-cell v-if="index === 0" :label="item.grossWeight" :rowspan="item.records.length"/>
            <beicroon-table-cell :label="record.commodityName"/>
            <beicroon-table-cell :label="record.commodityCode"/>
            <beicroon-table-cell :label="record.receiveName"/>
            <beicroon-table-cell :label="record.destination"/>
            <beicroon-table-cell :label="record.remark"/>
          </tr>
        </template>
      </template>
    </template>
  </beicroon-table-view>
</template>

<style lang="less">
</style>