<script setup lang="ts">
import toast from "@/utils/toast";
import {onBeforeMount, ref} from "vue";
import {validateForm} from "@/utils/function.ts";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
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

  await submit(data.value).finally(() => loading.value = false);;

  await toast("修改成功");

  emits("hide");

  emits("reload");
}
</script>

<template>
  <form class="create" ref="form">
    <div class="view">
      <el-input class="form-input" label="父级主键" placeholder="请输入父级主键" v-model="data.parentId"></el-input>
      <el-input class="form-input" label="父级编码" placeholder="请输入父级编码" v-model="data.parentCode"></el-input>
      <el-input class="form-input" label="父级名称" placeholder="请输入父级名称" v-model="data.parentName"></el-input>
      <el-input class="form-input" label="编码" placeholder="请输入编码" v-model="data.code"></el-input>
      <el-input class="form-input" label="名称" placeholder="请输入名称" v-model="data.name"></el-input>
      <el-input class="form-input" label="路径" placeholder="请输入路径" v-model="data.path"></el-input>
      <el-input class="form-input" label="排序" placeholder="请输入排序" v-model="data.sorting"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel" :loading="loading">取消</el-button>
      <el-button class="confirm" @click="confirm" :loading="loading">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>
