<script setup lang="ts">
import toast from "@/utils/toast";
import {onBeforeMount, ref} from "vue";
import {validateForm} from "@/utils/function.ts";
import FormInput from "@/components/form/FormInput.vue";
import FormButton from "@/components/form/FormButton.vue";
import {detail as show, ResourceMenuUpdateDTO as DTO, update as submit} from "./ResourceMenu.http.ts";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
});

const form = ref();

const data = ref<DTO>({
  id: props.id,
});

onBeforeMount(async () => {
  const res = await show(props.id);

  data.value = res.data;
});

const loading = ref(false);

const emits = defineEmits(["hide", "reload"]);

function cancel() {
  emits("hide");
}

async function confirm() {
  loading.value = true;

  if (!validateForm(form)) {
    await toast("请填写必填项！", true);

    loading.value = false;

    return;
  }

  await submit(data.value).finally(() => loading.value = false);

  await toast("修改成功");

  emits("hide");

  emits("reload");
}
</script>

<template>
  <form class="create" ref="form">
    <div class="view">
      <form-input class="form-input" placeholder="请输入父级主键" v-model="data.parentId">父级主键</form-input>
      <form-input class="form-input" placeholder="请输入父级编码" v-model="data.parentCode">父级编码</form-input>
      <form-input class="form-input" placeholder="请输入父级名称" v-model="data.parentName">父级名称</form-input>
      <form-input class="form-input" placeholder="请输入编码" v-model="data.code">编码</form-input>
      <form-input class="form-input" placeholder="请输入名称" v-model="data.name">名称</form-input>
      <form-input class="form-input" placeholder="请输入路径" v-model="data.path">路径</form-input>
      <form-input class="form-input" placeholder="请输入排序" v-model="data.sorting">排序</form-input>
    </div>
    <div class="button">
      <form-button class="cancel" @click="cancel" :loading="loading">取消</form-button>
      <form-button class="confirm" @click="confirm" :loading="loading">保存</form-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
