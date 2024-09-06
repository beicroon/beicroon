<script setup lang="ts">
import {reactive, ref} from "vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {create, ResourceMenuCreateDTO as DTO} from "./resource-menu.http.ts";

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
  <form class="beicroon-dialog-view">
    <div class="beicroon-dialog-input">
      <beicroon-input class="column" label="父级主键" placeholder="父级主键" v-model="form.parentId"></beicroon-input>
      <beicroon-input class="column" label="父级编码" placeholder="父级编码" v-model="form.parentCode"></beicroon-input>
      <beicroon-input class="column" label="父级名称" placeholder="父级名称" v-model="form.parentName"></beicroon-input>
      <beicroon-input class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input class="column" label="名称" placeholder="名称" v-model="form.name"></beicroon-input>
      <beicroon-input class="column" label="路径" placeholder="路径" v-model="form.path"></beicroon-input>
      <beicroon-input class="column" label="排序" placeholder="排序" v-model="form.sorting"></beicroon-input>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block warning" label="取消" @click="handleCancel"></beicroon-button>
      <beicroon-button class="block primary" label="保存" @click="handleConfirm" :loading="loading"></beicroon-button>
    </div>
  </form>
</template>

<style lang="less">
</style>
