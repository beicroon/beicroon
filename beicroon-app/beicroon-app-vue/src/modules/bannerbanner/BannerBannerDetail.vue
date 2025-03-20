<script setup lang="ts">
import {onMounted} from "vue";
import {moduleDetail} from "@/index";
import {BButton} from "@/components";
import config, {DetailVO} from "@m/bannerbanner/script/module";
import DetailView from "@m/bannerbanner/component/DetailView.vue";

interface Props {
  id: string,
}

const props = defineProps<Props>();

const emits = defineEmits(["hide"]);

const handleHide = () => emits("hide", false);

const module = moduleDetail<DetailVO>(config, props.id);

onMounted(module.getData);
</script>

<template>
  <detail-view :data="module.data">
    <template v-slot:button>
      <b-button size="larger" label="关闭" @click="handleHide"/>
    </template>
  </detail-view>
</template>

<style lang="less">

</style>
