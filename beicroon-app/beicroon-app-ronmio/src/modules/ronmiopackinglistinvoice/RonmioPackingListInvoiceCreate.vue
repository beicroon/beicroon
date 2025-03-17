<script setup lang="ts">
import {moduleCreate} from "beicroon-app-vue";
import config, {CreateDTO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import WeighFractionalSelect from "@m/ronmiopackinglistinvoice/component/WeighFractionalSelect.vue";
import TotalAmountCalculationSelect from "@m/ronmiopackinglistinvoice/component/TotalAmountCalculationSelect.vue";
import {BButton, BForm, BFormGroup, BInput} from "beicroon-app-vue/components";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleCreate<CreateDTO>(config);

const handleSubmit = async () => {
  await module.submit();

  emits("hide", true);
};
</script>

<template>
  <b-form @submit="handleSubmit">
    <b-form-group label="基础信息">
      <b-input required label="客户名称" v-model="module.data.customerName"/>
      <b-input label="班列号" v-model="module.data.trainCode"/>
      <b-input label="货柜号" v-model="module.data.containerCode"/>
      <b-input label="备注" size="huge" v-model="module.data.remark"/>
    </b-form-group>
    <b-form-group label="计算方式">
      <total-amount-calculation-select v-model="module.data.totalAmountCalculation"/>
      <weigh-fractional-select label="净重小数位数" v-model="module.data.netWeightFractional"/>
      <weigh-fractional-select label="毛重小数位数" v-model="module.data.grossWeightFractional"/>
    </b-form-group>
    <b-form-group label="其他信息">
      <b-input label="合同号" v-model="module.data.contractCode"/>
      <b-input label="原产国" v-model="module.data.originCountry"/>
      <b-input label="价格条款" v-model="module.data.priceTerm"/>
      <b-input label="箱型" v-model="module.data.boxType"/>
    </b-form-group>
    <b-form-group label="卖方信息">
      <b-input label="卖方名称" size="large" v-model="module.data.sellerName"/>
      <b-input label="卖方地址" size="huge" v-model="module.data.sellerAddr"/>
    </b-form-group>
    <b-form-group label="买方信息">
      <b-input label="买方名称" size="large" v-model="module.data.buyerName"/>
      <b-input label="买方地址" size="huge" v-model="module.data.buyerAddr"/>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
