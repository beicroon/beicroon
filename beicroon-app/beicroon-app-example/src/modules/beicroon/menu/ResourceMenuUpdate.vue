<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
import ResourceMenuSelect from "@/modules/beicroon/menu/ResourceMenuSelect.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {detail, ResourceMenuUpdateDTO as DTO, update} from "@/request/beicroon/resource-menu.http.ts";

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

const parentName = ref();

onMounted(async () => {
  loading.get = true;

  const {data} = await detail(props.id).finally(() => loading.get = false);

  form.parentId = data.parentId;
  form.code = data.code;
  form.name = data.name;
  form.path = data.path;
  form.sorting = data.sorting;

  parentName.value = data.parentName;
});
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main beicroon-dialog-input" v-if="!loading.get">
      <resource-menu-select class="column" label="父级" placeholder="父级" v-model="form.parentId" v-model:show-value="parentName"></resource-menu-select>
      <beicroon-input disabled required class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input required class="column" label="名称" placeholder="名称" v-model="form.name"></beicroon-input>
      <beicroon-input required class="column" label="路由" placeholder="路由" v-model="form.path"></beicroon-input>
      <beicroon-input class="column" label="排序" placeholder="排序" v-model="form.sorting"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
      <beicroon-button type="submit" class="block warning" label="保存" :loading="loading.set"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
</style>
