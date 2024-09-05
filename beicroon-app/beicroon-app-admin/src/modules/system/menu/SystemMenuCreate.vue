<script setup lang="ts">
import {reactive, ref} from "vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import {create, SystemMenuCreateDTO as DTO} from "./system-menu.http.ts";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";

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
<form class="beicroon-dialog-view system-menu-create">
  <div class="beicroon-dialog-input">
    <beicroon-input class="column" label="名称" placeholder="菜单名称" v-model="form.name"></beicroon-input>
    <beicroon-input class="column" label="路径" placeholder="菜单路径" v-model="form.path"></beicroon-input>
    <beicroon-input class="column" type="number" label="排序" placeholder="菜单排序" v-model="form.sorting"></beicroon-input>
  </div>
  <beicroon-line-vertical></beicroon-line-vertical>
  <div class="beicroon-dialog-button">
    <beicroon-button class="block warning" label="取消" @click="handleCancel"></beicroon-button>
    <beicroon-button class="block primary" label="保存" @click="handleConfirm" :loading="loading"></beicroon-button>
  </div>
</form>
</template>

<style scoped lang="less">
.system-menu-create {

}
</style>