<script setup lang="ts">
import {onMounted, reactive} from "vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {detail, MapResultUpdateDTO as DTO, update} from "./map-result.http.utils.ts";

type Props = {
  id: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const form = reactive<DTO>({
  id: props.id,
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.set = true;

  await update(form).finally(() => loading.set = false);

  emits("confirm");
}

onMounted(async () => {
  loading.get = true;

  const {data} = await detail(props.id).finally(() => loading.get = false);

  form.originAddress = data.originAddress;
  form.originAddressHash = data.originAddressHash;
  form.gaodeProvince = data.gaodeProvince;
  form.gaodeCity = data.gaodeCity;
  form.gaodeDistrict = data.gaodeDistrict;
  form.gaodeStreet = data.gaodeStreet;
  form.gaodeAddress = data.gaodeAddress;
  form.gaodeAddressLng = data.gaodeAddressLng;
  form.gaodeAddressLat = data.gaodeAddressLat;
  form.tengxunProvince = data.tengxunProvince;
  form.tengxunCity = data.tengxunCity;
  form.tengxunDistrict = data.tengxunDistrict;
  form.tengxunStreet = data.tengxunStreet;
  form.tengxunAddress = data.tengxunAddress;
  form.tengxunAddressLng = data.tengxunAddressLng;
  form.tengxunAddressLat = data.tengxunAddressLat;
  form.baiduProvince = data.baiduProvince;
  form.baiduCity = data.baiduCity;
  form.baiduDistrict = data.baiduDistrict;
  form.baiduStreet = data.baiduStreet;
  form.baiduAddress = data.baiduAddress;
  form.baiduAddressLng = data.baiduAddressLng;
  form.baiduAddressLat = data.baiduAddressLat;
});
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main beicroon-dialog-input" v-if="!loading.get">
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
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
      <beicroon-button type="submit" class="block warning" label="保存" :loading="loading.set"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
