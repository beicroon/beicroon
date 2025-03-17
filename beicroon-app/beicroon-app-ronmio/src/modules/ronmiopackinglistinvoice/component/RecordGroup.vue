<script setup lang="ts">
import {onMounted, ref} from "vue";
import {http, toast} from "beicroon-app-vue";
import config, {RecordGroupDTO, RecordGroupVO} from "@m/ronmiopackinglistinvoice/script/module.ts";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const data = ref<Array<RecordGroupDTO>>([]);

const loading = ref(false);

const handleSubmit = async () => {
  if (loading.value) {
    return;
  }

  loading.value = true;

  await http(config.fillUnitPrice, {packingListInvoiceId: props.id, groups: data.value})
    .finally(() => loading.value = false);

  toast.success("保存成功");

  handleHide();
};

const handleHide = () => emits("hide");

onMounted(async () => {
  if (loading.value) {
    return;
  }

  loading.value = true;

  const res = await http<Array<RecordGroupVO>>(config.recordGroupList, {packingListInvoiceId: props.id})
    .finally(() => loading.value = false);

  data.value = res.data.map(item => {
    return {
      thickness: item.thickness,
      width: item.width,
      level: item.level,
      minLength: item.minLength,
      maxLength: item.maxLength,
      unitPrice: item.unitPrice,
    };
  });
});

const handleCopy = (item: RecordGroupDTO, index: number) => {
  data.value.splice(index + 1, 0, {...item});
};

const handleDelete = (index: number) => {
  data.value.splice(index, 1);
};
</script>

<template>
  <beicroon-form @submit="handleSubmit">
    <template v-slot:default>
      <beicroon-table-view :loading="loading">
        <template v-slot:head>
          <beicroon-table-cell label="操作"/>
          <beicroon-table-cell label="厚度(mm)*宽度(mm)"/>
          <beicroon-table-cell label="最小长度"/>
          <beicroon-table-cell label="最大长度"/>
          <beicroon-table-cell label="等级"/>
          <beicroon-table-cell label="单价(元)"/>
        </template>
        <template v-slot:body>
          <tr v-for="(item, index) in data">
            <beicroon-table-cell>
              <beicroon-button-group align="center">
                <beicroon-button label="复制" size="small" @click="handleCopy(item, index)"/>
                <beicroon-button label="删除" size="small" @click="handleDelete(index)"/>
              </beicroon-button-group>
            </beicroon-table-cell>
            <beicroon-table-cell>
              <beicroon-input disabled size="small" :model-value="`${item.thickness}*${item.width}`"/>
            </beicroon-table-cell>
            <beicroon-table-cell>
              <beicroon-input required size="small" v-model="item.minLength"/>
            </beicroon-table-cell>
            <beicroon-table-cell>
              <beicroon-input size="small" v-model="item.maxLength"/>
            </beicroon-table-cell>
            <beicroon-table-cell>
              <beicroon-input disabled size="small" :model-value="item.level"/>
            </beicroon-table-cell>
            <beicroon-table-cell>
              <beicroon-input size="small" v-model="item.unitPrice"/>
            </beicroon-table-cell>
          </tr>
        </template>
      </beicroon-table-view>
    </template>
    <template v-slot:button>
      <beicroon-button size="larger" label="关闭" @click="handleHide"/>
      <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="loading"/>
    </template>
  </beicroon-form>
</template>

<style lang="less">

</style>