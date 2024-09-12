<script setup lang="ts">
import {onMounted, ref} from "vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {detail, ResourceMenuDetailVO as VO} from "./resource-menu.http.ts";

type Props = {
  id: string,
};

const props = defineProps<Props>();

const form = ref<VO | null>(null);

const emits = defineEmits(["cancel"]);

async function handleCancel() {
  emits("cancel");
}

onMounted(async () => {
  const {data} = await detail(props.id);

  form.value = data;
});
</script>

<template>
  <form class="beicroon-dialog-view">
    <div class="beicroon-dialog-input" v-if="form">
      <beicroon-input disabled class="column" label="父级主键" placeholder="父级主键" v-model="form.parentId"></beicroon-input>
      <beicroon-input disabled class="column" label="父级编码" placeholder="父级编码" v-model="form.parentCode"></beicroon-input>
      <beicroon-input disabled class="column" label="父级名称" placeholder="父级名称" v-model="form.parentName"></beicroon-input>
      <beicroon-input disabled class="column" label="编码" placeholder="编码" v-model="form.code"></beicroon-input>
      <beicroon-input disabled class="column" label="名称" placeholder="名称" v-model="form.name"></beicroon-input>
      <beicroon-input disabled class="column" label="路径" placeholder="路径" v-model="form.path"></beicroon-input>
      <beicroon-input disabled class="column" label="排序" placeholder="排序" v-model="form.sorting"></beicroon-input>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
    </div>
  </form>
</template>

<style scoped lang="less">
</style>
