<script setup lang="ts">
import {computed, ref} from "vue";
import {http, toast} from "beicroon-app-vue";
import config, {ContainerCreateDTO, ContainerSearchVO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import {BButton, BForm, BFormGroup, BGroup, BInput, BTable, BTableGroup} from "beicroon-app-vue/components";

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

  const res = await http<ContainerSearchVO>(config.containerSearch, {packingListInvoiceId: props.id, code: val})
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

  await http(config.containerAppend, data.value).finally(() => loading.value = false);

  toast.success("保存成功");

  resetData({code: data.value.code});
};

const handleRemove = async () => {
  if (loading.value) {
    return;
  }

  loading.value = true;

  await http(config.containerRemove, {id: data.value.id}).finally(() => loading.value = false);

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
  <b-form @submit="handleSubmit">
    <b-form-group label="箱单信息">
      <b-input required search label="箱号" v-model="data.code" @search="handleSearch"/>
      <b-input label="总净重" v-model="data.netWeight"/>
      <b-input label="总毛重" v-model="data.grossWeight"/>
    </b-form-group>
    <b-form-group label="码单信息" type="block">
      <b-group>
        <b-input label="长度比率" v-model="lengthRatio"/>
        <b-input label="体积比率" v-model="volumeRatio"/>
      </b-group>
      <b-table :data="data.records" height="520rem">
        <b-table-group label="等级">
          <template v-slot="{item, index}">
            <b-input required size="small" v-model="item.level" @paste="handlePaste($event, index, 'level')"/>
          </template>
        </b-table-group>
        <b-table-group label="厚度(mm)">
          <template v-slot="{item, index}">
            <b-input required size="small" v-model="item.thickness" @paste="handlePaste($event, index, 'thickness')"/>
          </template>
        </b-table-group>
        <b-table-group label="宽度(mm)">
          <template v-slot="{item, index}">
            <b-input required size="small" v-model="item.width" @paste="handlePaste($event, index, 'width')"/>
          </template>
        </b-table-group>
        <b-table-group label="长度(mm)">
          <template v-slot="{item, index}">
            <b-input required size="small" v-model="item.length" @paste="handlePaste($event, index, 'length')"/>
          </template>
        </b-table-group>
        <b-table-group label="商品体积(m³)">
          <template v-slot="{item, index}">
            <b-input size="small" v-model="item.volume" @paste="handlePaste($event, index, 'volume')"/>
          </template>
        </b-table-group>
        <b-table-group label="商品件数">
          <template v-slot="{item, index}">
            <b-input size="small" v-model="item.count" @paste="handlePaste($event, index, 'count')"/>
          </template>
        </b-table-group>
        <b-table-group label="单价">
          <template v-slot="{item, index}">
            <b-input size="small" v-model="item.unitPrice" @paste="handlePaste($event, index, 'unitPrice')"/>
          </template>
        </b-table-group>
        <b-table-group label="操作" direction="row" frozen-right>
          <template v-slot="{index}">
            <b-button size="small" label="复制" @click="handleCopy(index)"/>
            <b-button size="small" label="删除" @click="handleDelete(index)"/>
          </template>
        </b-table-group>
      </b-table>
    </b-form-group>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="清空" @click="handleClear"/>
      <b-button size="larger" :label="saveLabel" level="warning" type="submit" :loading="loading"/>
      <b-button size="larger" label="删除" level="danger" :disabled="!data.id" @click="handleRemove" :loading="loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>