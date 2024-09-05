<script setup lang="ts">
import {onMounted, reactive} from "vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {detail, SystemMenuUpdateDTO as DTO, update} from "./system-menu.http.ts";

type Props = {
  id: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const form = reactive<DTO>({
  id: props.id,
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.set = true;

  await update(form).finally(() => loading.set = false);

  emits("confirm");
}

onMounted(async () => {
  loading.get = true;

  const {data} = await detail(props.id).finally(() => loading.get = false);

  form.code = data.code;
  form.name = data.name;
  form.path = data.path;
  form.sorting = data.sorting;
  form.parentId = data.parentId;
  form.parentCode = data.parentCode;
  form.parentName = data.parentName;
});
</script>

<template>
  <form class="beicroon-dialog-view system-menu-update">
    <div class="beicroon-dialog-input" v-if="!loading.get">
      <beicroon-input class="column" label="名称" v-model="form.name"></beicroon-input>
      <beicroon-input class="column" label="路径" v-model="form.path"></beicroon-input>
      <beicroon-input class="column" type="number" label="排序" v-model="form.sorting"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
      <beicroon-button class="block warning" label="保存" @click="handleConfirm" :loading="loading.set"></beicroon-button>
    </div>
  </form>
</template>

<style scoped lang="less">
.system-menu-update {

}
</style>