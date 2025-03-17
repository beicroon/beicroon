<script setup lang="ts">
import {onMounted, ref} from "vue";
import {http, toast} from "beicroon-app-vue";
import config, {RecordGroupDTO, RecordGroupVO} from "@m/ronmiopackinglistinvoice/script/module.ts";
import {BButton, BButtonGroup, BForm, BInput, BTableCell, BTableView} from "beicroon-app-vue/components";

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
  <b-form @submit="handleSubmit">
    <template v-slot:default>
      <b-table-view :loading="loading">
        <template v-slot:head>
          <b-table-cell label="操作"/>
          <b-table-cell label="厚度(mm)*宽度(mm)"/>
          <b-table-cell label="最小长度"/>
          <b-table-cell label="最大长度"/>
          <b-table-cell label="等级"/>
          <b-table-cell label="单价(元)"/>
        </template>
        <template v-slot:body>
          <tr v-for="(item, index) in data">
            <b-table-cell>
              <b-button-group align="center">
                <b-button label="复制" size="small" @click="handleCopy(item, index)"/>
                <b-button label="删除" size="small" @click="handleDelete(index)"/>
              </b-button-group>
            </b-table-cell>
            <b-table-cell>
              <b-input disabled size="small" :model-value="`${item.thickness}*${item.width}`"/>
            </b-table-cell>
            <b-table-cell>
              <b-input required size="small" v-model="item.minLength"/>
            </b-table-cell>
            <b-table-cell>
              <b-input size="small" v-model="item.maxLength"/>
            </b-table-cell>
            <b-table-cell>
              <b-input disabled size="small" :model-value="item.level"/>
            </b-table-cell>
            <b-table-cell>
              <b-input size="small" v-model="item.unitPrice"/>
            </b-table-cell>
          </tr>
        </template>
      </b-table-view>
    </template>
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
      <b-button size="larger" label="保存" level="warning" type="submit" :loading="loading"/>
    </template>
  </b-form>
</template>

<style lang="less">

</style>