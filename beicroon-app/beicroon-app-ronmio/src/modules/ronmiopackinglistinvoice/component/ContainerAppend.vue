<script setup lang="ts">
import {computed, ref} from "vue";
import httpUtils from "@u/http.utils.ts";
import toastUtils from "@u/toast.utils.ts";
import BeicroonForm from "@c/BeicroonForm.vue";
import BeicroonInput from "@c/BeicroonInput.vue";
import BeicroonTable from "@c/BeicroonTable.vue";
import BeicroonGroup from "@c/BeicroonGroup.vue";
import BeicroonButton from "@c/BeicroonButton.vue";
import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
import BeicroonTableGroup from "@c/BeicroonTableGroup.vue";
import config, {ContainerCreateDTO, ContainerSearchVO} from "@m/ronmiopackinglistinvoice/script/module.ts";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const loading = ref(false);

const data = ref<ContainerCreateDTO>({packingListInvoiceId: props.id, records: [{}]});

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide");

const resetData = (init?: Record<string, any>) => data.value = {...init, packingListInvoiceId: props.id, records: [{}]};

const handlePaste = (event: ClipboardEvent, index: number, key: string) => {
  event.preventDefault();

  const value = event.clipboardData?.getData("text").trim();

  if (!value) {
    return;
  }

  const values = value.trim().split("\r\n");

  if (values.length == 1) {
    setVal(data.value.records[index], key, value);

    return;
  }

  for (let i = 0; i < values.length; i++) {
    const val = values[i].trim();

    const item: any = data.value.records[index + i];

    if (item) {
      setVal(item, key, val);

      continue;
    }

    const newItem: any = {...data.value.records[index]};

    setVal(newItem, key, val);

    data.value.records.push(newItem);
  }
};

const handleCopy = (index: number) => data.value.records.push({...data.value.records[index]});

const handleDelete = (index: number) => data.value.records.splice(index, 1);

const handleClear = () => resetData({
  code: data.value.code,
  netWeight: data.value.netWeight,
  grossWeight: data.value.grossWeight,
});

const lengthRatio = ref(null);
const volumeRatio = ref(null);

const setVal = (item: any, key: string, val: any) => {
  if (key === "level") {
    val = val.replaceAll(/\s+/g, " ");
  }

  if (key === "length") {
    val = val.replaceAll(",", "");

    if (lengthRatio.value) {
      val = (val * lengthRatio.value).toFixed(0);
    }
  }

  if (key === "volume") {
    val = val.replaceAll(",", "");

    if (volumeRatio.value) {
      val = (val * volumeRatio.value).toFixed(2);
    }
  }

  item[key] = val;
};

const handleSearch = async (val: string) => {
  if (loading.value) {
    return;
  }

  loading.value = true;

  const res = await httpUtils<ContainerSearchVO>(config.containerSearch, {packingListInvoiceId: props.id, code: val})
    .finally(() => loading.value = false);

  if (res.data) {
    data.value.id = res.data.id;
    data.value.code = res.data.code;
    data.value.netWeight = res.data.netWeight;
    data.value.grossWeight = res.data.grossWeight;
    data.value.records = res.data.records.map(item => {
      return {
        level: item.level,
        thickness: item.thickness,
        width: item.width,
        length: item.length,
        count: item.count,
        volume: item.volume,
        netWeight: item.netWeight,
        grossWeight: item.grossWeight,
        unitPrice: item.unitPrice,
      };
    });
  }
};

const handleSubmit = async () => {
  if (loading.value) {
    return;
  }

  loading.value = true;

  await httpUtils(config.containerAppend, data.value).finally(() => loading.value = false);

  toastUtils.success("保存成功");

  resetData({code: data.value.code});
};

const handleRemove = async () => {
  if (loading.value) {
    return;
  }

  loading.value = true;

  await httpUtils(config.containerRemove, {id: data.value.id}).finally(() => loading.value = false);

  resetData();
};

const saveLabel = computed(() => {
  const size = data.value.records.length;

  if (size <= 0) {
    return "保存";
  }

  return "保存" + `(${size})`;
});
</script>

<template>
  <beicroon-form @submit="handleSubmit">
    <beicroon-form-group label="箱单信息">
      <beicroon-input required search label="箱号" v-model="data.code" @search="handleSearch"/>
      <beicroon-input label="总净重" v-model="data.netWeight"/>
      <beicroon-input label="总毛重" v-model="data.grossWeight"/>
    </beicroon-form-group>
    <beicroon-form-group label="码单信息" type="block">
      <beicroon-group>
        <beicroon-input label="长度比率" v-model="lengthRatio"/>
        <beicroon-input label="体积比率" v-model="volumeRatio"/>
      </beicroon-group>
      <beicroon-table :data="data.records" height="520rem">
        <beicroon-table-group label="等级">
          <template v-slot="{item, index}">
            <beicroon-input required size="small" v-model="item.level" @paste="handlePaste($event, index, 'level')"/>
          </template>
        </beicroon-table-group>
        <beicroon-table-group label="厚度(mm)">
          <template v-slot="{item, index}">
            <beicroon-input required size="small" v-model="item.thickness" @paste="handlePaste($event, index, 'thickness')"/>
          </template>
        </beicroon-table-group>
        <beicroon-table-group label="宽度(mm)">
          <template v-slot="{item, index}">
            <beicroon-input required size="small" v-model="item.width" @paste="handlePaste($event, index, 'width')"/>
          </template>
        </beicroon-table-group>
        <beicroon-table-group label="长度(mm)">
          <template v-slot="{item, index}">
            <beicroon-input required size="small" v-model="item.length" @paste="handlePaste($event, index, 'length')"/>
          </template>
        </beicroon-table-group>
        <beicroon-table-group label="商品体积(m³)">
          <template v-slot="{item, index}">
            <beicroon-input size="small" v-model="item.volume" @paste="handlePaste($event, index, 'volume')"/>
          </template>
        </beicroon-table-group>
        <beicroon-table-group label="商品件数">
          <template v-slot="{item, index}">
            <beicroon-input size="small" v-model="item.count" @paste="handlePaste($event, index, 'count')"/>
          </template>
        </beicroon-table-group>
        <beicroon-table-group label="操作" direction="row" frozen-right>
          <template v-slot="{index}">
            <beicroon-button size="small" label="复制" @click="handleCopy(index)"/>
            <beicroon-button size="small" label="删除" @click="handleDelete(index)"/>
          </template>
        </beicroon-table-group>
      </beicroon-table>
    </beicroon-form-group>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="清空" @click="handleClear"/>
      <beicroon-button size="larger" :label="saveLabel" level="warning" type="submit" :loading="loading"/>
      <beicroon-button size="larger" label="删除" level="danger" :disabled="!data.id" @click="handleRemove" :loading="loading"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>