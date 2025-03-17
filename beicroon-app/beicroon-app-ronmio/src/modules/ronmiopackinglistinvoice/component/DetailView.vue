<script setup lang="ts">
import {DetailVO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import RecordTable from "@m/ronmiopackinglistinvoice/component/RecordTable.vue";
import WeighFractionalSelect from "@m/ronmiopackinglistinvoice/component/WeighFractionalSelect.vue";
import TotalAmountCalculationSelect from "@m/ronmiopackinglistinvoice/component/TotalAmountCalculationSelect.vue";

interface Props {
  data?: DetailVO | null,
}

defineProps<Props>();
</script>

<template>
  <beicroon-form :loading="!data" disabled>
    <template v-if="data">
      <beicroon-form-group label="基础信息">
        <beicroon-input disabled label="客户名称" v-model="data.customerName"/>
        <beicroon-input disabled label="班列号" v-model="data.trainCode"/>
        <beicroon-input disabled label="货柜号" v-model="data.containerCode"/>
        <beicroon-input disabled label="备注" size="huge" v-model="data.remark"/>
      </beicroon-form-group>
      <beicroon-form-group label="计算方式">
        <total-amount-calculation-select disabled v-model="data.totalAmountCalculation"/>
        <weigh-fractional-select disabled label="净重小数位数" v-model="data.netWeightFractional"/>
        <weigh-fractional-select disabled label="毛重小数位数" v-model="data.grossWeightFractional"/>
      </beicroon-form-group>
      <beicroon-form-group label="其他信息">
        <beicroon-input disabled label="合同号" v-model="data.contractCode"/>
        <beicroon-input disabled label="原产国" v-model="data.originCountry"/>
        <beicroon-input disabled label="价格条款" v-model="data.priceTerm"/>
        <beicroon-input disabled label="箱型" v-model="data.boxType"/>
      </beicroon-form-group>
      <beicroon-form-group label="卖方信息">
        <beicroon-input disabled label="卖方名称" size="large" v-model="data.sellerName"/>
        <beicroon-input disabled label="卖方地址" size="huge" v-model="data.sellerAddr"/>
      </beicroon-form-group>
      <beicroon-form-group label="买方信息">
        <beicroon-input disabled label="买方名称" size="large" v-model="data.buyerName"/>
        <beicroon-input disabled label="买方地址" size="huge" v-model="data.buyerAddr"/>
      </beicroon-form-group>
      <beicroon-form-group label="计费信息">
        <beicroon-input disabled label="总金额" v-model="data.totalAmount"/>
        <beicroon-input disabled label="总体积" v-model="data.totalVolume"/>
        <beicroon-input disabled label="总净重" v-model="data.totalNetWeight"/>
        <beicroon-input disabled label="总毛重" v-model="data.totalGrossWeight"/>
      </beicroon-form-group>
      <beicroon-form-group label="码单信息" type="block">
        <record-table :data="data.containers" />
      </beicroon-form-group>
    </template>
    <template v-slot:button>
      <slot name="button"></slot>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
