<script setup lang="ts">
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {MapResultDetailVO as VO} from "./map-result.http.utils.ts";

type Props = {
  data: VO | null,
};

defineProps<Props>();

const emits = defineEmits(["cancel"]);

async function handleCancel() {
  emits("cancel");
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view">
    <div class="beicroon-dialog-main beicroon-dialog-input" v-if="form">
      <beicroon-input disabled class="column" label="原地址" v-model="form.originAddress"></beicroon-input>
      <beicroon-input disabled class="column" label="原地址CRC32值" v-model="form.originAddressHash"></beicroon-input>
      <beicroon-input disabled class="column" label="高德省份" v-model="form.gaodeProvince"></beicroon-input>
      <beicroon-input disabled class="column" label="高德城市" v-model="form.gaodeCity"></beicroon-input>
      <beicroon-input disabled class="column" label="高德区划" v-model="form.gaodeDistrict"></beicroon-input>
      <beicroon-input disabled class="column" label="高德街道" v-model="form.gaodeStreet"></beicroon-input>
      <beicroon-input disabled class="column" label="高德地址" v-model="form.gaodeAddress"></beicroon-input>
      <beicroon-input disabled class="column" label="高德地址经度" v-model="form.gaodeAddressLng"></beicroon-input>
      <beicroon-input disabled class="column" label="高德地址纬度" v-model="form.gaodeAddressLat"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯省份" v-model="form.tengxunProvince"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯城市" v-model="form.tengxunCity"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯区划" v-model="form.tengxunDistrict"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯街道" v-model="form.tengxunStreet"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯地址" v-model="form.tengxunAddress"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯地址经度" v-model="form.tengxunAddressLng"></beicroon-input>
      <beicroon-input disabled class="column" label="腾讯地址纬度" v-model="form.tengxunAddressLat"></beicroon-input>
      <beicroon-input disabled class="column" label="百度省份" v-model="form.baiduProvince"></beicroon-input>
      <beicroon-input disabled class="column" label="百度城市" v-model="form.baiduCity"></beicroon-input>
      <beicroon-input disabled class="column" label="百度区划" v-model="form.baiduDistrict"></beicroon-input>
      <beicroon-input disabled class="column" label="百度街道" v-model="form.baiduStreet"></beicroon-input>
      <beicroon-input disabled class="column" label="百度地址" v-model="form.baiduAddress"></beicroon-input>
      <beicroon-input disabled class="column" label="百度地址经度" v-model="form.baiduAddressLng"></beicroon-input>
      <beicroon-input disabled class="column" label="百度地址纬度" v-model="form.baiduAddressLat"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
