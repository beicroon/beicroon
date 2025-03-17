<script setup lang="ts">
import {onMounted} from "vue";
import {moduleUpdate} from "beicroon-app-vue";
import {BButton, BForm, BFormGroup, BInput} from "beicroon-app-vue/components";
import config, {DetailVO, UpdateDTO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import WeighFractionalSelect from "@m/ronmiopackinglistinvoice/component/WeighFractionalSelect.vue";
import TotalAmountCalculationSelect from "@m/ronmiopackinglistinvoice/component/TotalAmountCalculationSelect.vue";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleUpdate<UpdateDTO, DetailVO>(config, props.id);

const handleSubmit = async () => {
  await module.submit();

  emits("hide", true);
};

onMounted(async () => {
  await module.getData(res => {
    module.data.code = res.code;  // 箱单单号
    module.data.customerId = res.customerId;  // 客户主键
    module.data.customerName = res.customerName;  // 客户名称
    module.data.trainCode = res.trainCode;  // 班列号
    module.data.containerCode = res.containerCode;  // 货柜号
    module.data.sellerName = res.sellerName;  // 卖方名称
    module.data.sellerAddr = res.sellerAddr;  // 卖方地址
    module.data.buyerName = res.buyerName;  // 买方名称
    module.data.buyerAddr = res.buyerAddr;  // 买方地址
    module.data.contractCode = res.contractCode;  // 合同号
    module.data.originCountry = res.originCountry;  // 原产国
    module.data.priceTerm = res.priceTerm;  // 价格条款
    module.data.boxType = res.boxType;  // 箱型
    module.data.totalAmount = res.totalAmount;  // 总金额
    module.data.totalVolume = res.totalVolume;  // 总体积
    module.data.totalNetWeight = res.totalNetWeight;  // 总净重
    module.data.totalGrossWeight = res.totalGrossWeight;  // 总毛重
    module.data.remark = res.remark;  // 备注
    module.data.totalAmountCalculation = res.totalAmountCalculation;  // 总价计算方式
    module.data.netWeightFractional = res.netWeightFractional;  // 净重小数位数
    module.data.grossWeightFractional = res.grossWeightFractional;  // 毛重小数位数
  });
});
</script>

<template>
  <b-form @submit="handleSubmit" :loading="module.getting">
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
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>
