<script setup lang="ts">
import {reactive, ref} from "vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import ResourceMenuSelect from "@/modules/beicroon/menu/ResourceMenuSelect.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {create, ResourceMenuCreateDTO as DTO} from "@/request/beicroon/resource-menu.http.ts";

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
      <resource-menu-select class="column" label="父级" placeholder="父级" v-model="form.parentId"></resource-menu-select>
      <beicroon-input required class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input required class="column" label="名称" placeholder="名称" v-model="form.name"></beicroon-input>
      <beicroon-input required class="column" label="路由" placeholder="路由" v-model="form.path"></beicroon-input>
      <beicroon-input class="column" label="排序" placeholder="排序" v-model="form.sorting"></beicroon-input>
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
