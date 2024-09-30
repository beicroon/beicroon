<script setup lang="ts">
import {computed, onMounted, ref, watch} from "vue";
import createBeicroonSelect from "@/utils/select.utils.ts";
import BeicroonSelect from "@/components/beicroon/BeicroonSelect.vue";
import {AccountAdminPageVO as VO, AccountAdminQueryDTO as DTO, page} from "@/request/account-admin.http.ts";

type Props = {
  label: string,
  showValue?: any,
  modelValue?: any,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:showValue", "update:modelValue"]);

const select = createBeicroonSelect<DTO, VO>({
  request: page,
  optionValue: "id",
  optionLabel: "name",
});

const showValue = ref(props.showValue);

onMounted(() => {
  watch(() => props.showValue, (val?: any) => {
    showValue.value = val;
  });

  watch(() => showValue.value, (val?: any) => {
    emits("update:showValue", val);
  });
});

const modelValue = computed({
  get: () => props.modelValue,
  set: (val?: any) => {
    emits("update:modelValue", val);
  },
});
</script>

<template>
<beicroon-select
  :label="label"
  :select="select"
  v-model:show-value="showValue"
  v-model:model-value="modelValue"
  v-bind="$attrs"
></beicroon-select>
</template>

<style scoped lang="less">

</style>
