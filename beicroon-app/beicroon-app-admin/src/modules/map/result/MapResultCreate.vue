<script setup lang="ts">
import {reactive, ref} from "vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {create, MapResultCreateDTO as DTO} from "./map-result.http.utils.ts";

const loading = ref(false);

const form = reactive<DTO>({});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.value = true;

  await create(form).finally(() => loading.value = false);

  emits("confirm");
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main beicroon-dialog-input">
      <beicroon-input class="column" label="原地址" placeholder="原地址" v-model="form.originAddress"></beicroon-input>
      <beicroon-input class="column" label="原地址CRC32值" placeholder="原地址CRC32值" v-model="form.originAddressHash"></beicroon-input>
      <beicroon-input class="column" label="高德省份" placeholder="高德省份" v-model="form.gaodeProvince"></beicroon-input>
      <beicroon-input class="column" label="高德城市" placeholder="高德城市" v-model="form.gaodeCity"></beicroon-input>
      <beicroon-input class="column" label="高德区划" placeholder="高德区划" v-model="form.gaodeDistrict"></beicroon-input>
      <beicroon-input class="column" label="高德街道" placeholder="高德街道" v-model="form.gaodeStreet"></beicroon-input>
      <beicroon-input class="column" label="高德地址" placeholder="高德地址" v-model="form.gaodeAddress"></beicroon-input>
      <beicroon-input class="column" label="高德地址经度" placeholder="高德地址经度" v-model="form.gaodeAddressLng"></beicroon-input>
      <beicroon-input class="column" label="高德地址纬度" placeholder="高德地址纬度" v-model="form.gaodeAddressLat"></beicroon-input>
      <beicroon-input class="column" label="腾讯省份" placeholder="腾讯省份" v-model="form.tengxunProvince"></beicroon-input>
      <beicroon-input class="column" label="腾讯城市" placeholder="腾讯城市" v-model="form.tengxunCity"></beicroon-input>
      <beicroon-input class="column" label="腾讯区划" placeholder="腾讯区划" v-model="form.tengxunDistrict"></beicroon-input>
      <beicroon-input class="column" label="腾讯街道" placeholder="腾讯街道" v-model="form.tengxunStreet"></beicroon-input>
      <beicroon-input class="column" label="腾讯地址" placeholder="腾讯地址" v-model="form.tengxunAddress"></beicroon-input>
      <beicroon-input class="column" label="腾讯地址经度" placeholder="腾讯地址经度" v-model="form.tengxunAddressLng"></beicroon-input>
      <beicroon-input class="column" label="腾讯地址纬度" placeholder="腾讯地址纬度" v-model="form.tengxunAddressLat"></beicroon-input>
      <beicroon-input class="column" label="百度省份" placeholder="百度省份" v-model="form.baiduProvince"></beicroon-input>
      <beicroon-input class="column" label="百度城市" placeholder="百度城市" v-model="form.baiduCity"></beicroon-input>
      <beicroon-input class="column" label="百度区划" placeholder="百度区划" v-model="form.baiduDistrict"></beicroon-input>
      <beicroon-input class="column" label="百度街道" placeholder="百度街道" v-model="form.baiduStreet"></beicroon-input>
      <beicroon-input class="column" label="百度地址" placeholder="百度地址" v-model="form.baiduAddress"></beicroon-input>
      <beicroon-input class="column" label="百度地址经度" placeholder="百度地址经度" v-model="form.baiduAddressLng"></beicroon-input>
      <beicroon-input class="column" label="百度地址纬度" placeholder="百度地址纬度" v-model="form.baiduAddressLat"></beicroon-input>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block warning" label="取消" @click="handleCancel"></beicroon-button>
      <beicroon-button type="submit" class="block primary" label="保存" :loading="loading"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
