<script setup lang="ts">
import toast from "@/utils/toast";
import {onBeforeMount, ref} from "vue";
import {validateForm} from "@/utils/function.ts";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {AdminUpdateDTO as DTO, detail as show, update as submit} from "@/https/account/admin.http.ts";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
});

const form = ref();

const params = ref<DTO>({
  id: props.id,
});

onBeforeMount(async () => {
  const res = await show(props.id);

  params.value.nickname = res.data.nickname;
  params.value.phone = res.data.phone;
  params.value.email = res.data.email;
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

  await submit(params.value).finally(() => loading.value = false);;

  await toast("修改成功");

  emits("hide");

  emits("reload");
}
</script>

<template>
  <form class="create" ref="form">
    <div class="view">
      <el-input required class="form-input" label="昵称" placeholder="请输入昵称" v-model="params.nickname"></el-input>
      <el-input class="form-input" label="电话" placeholder="请输入电话" v-model="params.phone"></el-input>
      <el-input class="form-input" label="邮箱" placeholder="请输入邮箱" v-model="params.email"></el-input>
    </div>
    <div class="button">
      <el-button class="cancel" @click="cancel" :loading="loading">取消</el-button>
      <el-button class="confirm" @click="confirm" :loading="loading">保存</el-button>
    </div>
  </form>
</template>

<style scoped lang="less">

</style>