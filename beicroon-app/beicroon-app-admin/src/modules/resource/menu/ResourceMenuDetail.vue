<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import FormInput from "@/components/form/FormInput.vue";
import FormButton from "@/components/form/FormButton.vue";
import {detail, ResourceMenuDetailVO as VO} from "./ResourceMenu.http.ts";

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
});

const data = ref<VO>({
  id: props.id,
});

onBeforeMount(async () => {
  const res = await detail(props.id);

  if (res.data) {
    data.value = res.data;
  }
});

const emits = defineEmits(["hide"]);

async function cancel() {
  emits("hide");
}
</script>

<template>
  <form class="detail disabled">
    <div class="view">
      <form-input disabled class="form-input" v-model="data.parentId">父级主键</form-input>
      <form-input disabled class="form-input" v-model="data.parentCode">父级编码</form-input>
      <form-input disabled class="form-input" v-model="data.parentName">父级名称</form-input>
      <form-input disabled class="form-input" v-model="data.code">编码</form-input>
      <form-input disabled class="form-input" v-model="data.name">名称</form-input>
      <form-input disabled class="form-input" v-model="data.path">路径</form-input>
      <form-input disabled class="form-input" v-model="data.sorting">排序</form-input>
    </div>
    <div class="button">
      <form-button class="cancel" @click="cancel">关闭</form-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
