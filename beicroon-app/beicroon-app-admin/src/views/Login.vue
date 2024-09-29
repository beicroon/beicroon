<script setup lang="ts">
import {reactive, ref} from "vue";
import {CacheKeyEnums} from "@/enums/default-enums.ts";
import {initMenus, router} from "@/utils/auth.utils.ts";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import {AuthUser, indexMenu, login, LoginDTO} from "@/request/account-admin-auth.http.ts";

const form = reactive<LoginDTO>({
  username: "",
  password: "",
});

const loading = ref(false);

async function handleSubmit() {
  const from = router.currentRoute.value.query.t as string;

  const path = from ? from : indexMenu.path;

  loading.value = true;

  try {
    const res = await login(form);

    const user = {code: res.data.code, name: res.data.name} as AuthUser;

    localStorage.setItem(CacheKeyEnums.AUTHORIZATION_USER, JSON.stringify(user));
    localStorage.setItem(CacheKeyEnums.AUTHORIZATION_TOKEN, res.data.token);

    await initMenus(path, true);
  } finally {
    loading.value = false;
  }

  await router.push(path);
}
</script>

<template>
  <beicroon-form class="login-form" @submit="handleSubmit">
    <beicroon-input required label="账号" v-model="form.username"></beicroon-input>
    <beicroon-input required label="密码" v-model="form.password" type="password"></beicroon-input>
    <beicroon-button class="form-button block primary" type="submit" label="登录" :loading="loading"></beicroon-button>
  </beicroon-form>
</template>

<style lang="less">
.login-form {
  top: 18%;
  left: 50%;
  z-index: 9;
  gap: 32rem;
  display: flex;
  height: 320rem;
  position: fixed;
  padding: 0 52rem;
  width: fit-content;
  align-items: center;
  border-radius: 12rem;
  flex-direction: column;
  justify-content: center;
  transform: translateX(-50%);
  box-shadow: 0 0 8rem -3rem var(--color-black-30) inset;

  .form-button {
    width: 406rem;
  }
}
</style>