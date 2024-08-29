<script setup lang="ts">
import {ref} from "vue";
import BeicroonList from "@/components/BeicroonList.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import {SystemMenuQueryDTO as DTO} from "@/modules/system/menu/system.menu.http.ts";

const params = ref<DTO>({});
</script>

<template>
  <beicroon-list>
    <template #search>
      <beicroon-input label="名称" v-model="params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="params.sorting"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
</style>