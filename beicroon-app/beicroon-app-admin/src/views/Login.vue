<script setup lang="ts">
import {reactive} from "vue";
import router from "@/router.ts";
import {CacheKeyEnums} from "@/enums/default-enums.ts";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import {AuthUser, index, login, LoginDTO} from "@/request/account-admin-auth.http.ts";

const form = reactive<LoginDTO>({
  username: "",
  password: "",
});

async function handleSubmit() {
  const res = await login(form);

  const user = {code: res.data.code, nickname: res.data.nickname} as AuthUser;

  localStorage.setItem(CacheKeyEnums.AUTHORIZATION_USER, JSON.stringify(user));
  localStorage.setItem(CacheKeyEnums.AUTHORIZATION_TOKEN, res.data.token);

  const from = router.currentRoute.value.query.t as string;

  await router.push(from ? from : index.path);
}
</script>

<template>
  <beicroon-form class="login-form" @submit="handleSubmit">
    <beicroon-input required label="账号" v-model="form.username"></beicroon-input>
    <beicroon-input required label="密码" v-model="form.password" type="password"></beicroon-input>
    <beicroon-button class="form-button block primary" type="submit" label="登录"></beicroon-button>
  </beicroon-form>
</template>

<style lang="less">
.login-form {
  top: 18%;
  left: 50%;
  z-index: 9;
  gap: 32rem;
  display: flex;
  width: 480rem;
  height: 320rem;
  position: fixed;
  padding: 0 62rem;
  align-items: center;
  border-radius: 6rem;
  flex-direction: column;
  justify-content: center;
  transform: translateX(-50%);
  box-shadow: 0 0 8rem -3rem var(--color-black-30) inset;

  .form-button {
    width: 100%;
  }
}
</style>