<script setup lang="ts">
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import newModule from "@u/module-create.utils.ts";
import BeicroonButton from "@c/BeicroonButton.vue";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import config, {CreateDTO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import WeighFractionalSelect from "@m/ronmiopackinglistinvoice/component/WeighFractionalSelect.vue";
import TotalAmountCalculationSelect from "@m/ronmiopackinglistinvoice/component/TotalAmountCalculationSelect.vue";

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = newModule<CreateDTO>(config);

const handleSubmit = async () => {
  await module.submit();

  emits("hide", true);
};
</script>

<template>
  <beicroon-form @submit="handleSubmit">
    <beicroon-form-group label="基础信息">
      <beicroon-input required label="客户名称" v-model="module.data.customerName"/>
      <beicroon-input label="班列号" v-model="module.data.trainCode"/>
      <beicroon-input label="货柜号" v-model="module.data.containerCode"/>
      <beicroon-input label="备注" size="huge" v-model="module.data.remark"/>
    </beicroon-form-group>
    <beicroon-form-group label="计算方式">
      <total-amount-calculation-select v-model="module.data.totalAmountCalculation"/>
      <weigh-fractional-select label="净重小数位数" v-model="module.data.netWeightFractional"/>
      <weigh-fractional-select label="毛重小数位数" v-model="module.data.grossWeightFractional"/>
    </beicroon-form-group>
    <beicroon-form-group label="其他信息">
      <beicroon-input label="合同号" v-model="module.data.contractCode"/>
      <beicroon-input label="原产国" v-model="module.data.originCountry"/>
      <beicroon-input label="价格条款" v-model="module.data.priceTerm"/>
      <beicroon-input label="箱型" v-model="module.data.boxType"/>
    </beicroon-form-group>
    <beicroon-form-group label="卖方信息">
      <beicroon-input label="卖方名称" size="large" v-model="module.data.sellerName"/>
      <beicroon-input label="卖方地址" size="huge" v-model="module.data.sellerAddr"/>
    </beicroon-form-group>
    <beicroon-form-group label="买方信息">
      <beicroon-input label="买方名称" size="large" v-model="module.data.buyerName"/>
      <beicroon-input label="买方地址" size="huge" v-model="module.data.buyerAddr"/>
    </beicroon-form-group>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>
