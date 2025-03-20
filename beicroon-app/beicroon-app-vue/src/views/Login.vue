<script setup lang="ts">
import {useRouter} from "vue-router";
import {BeicroonCacheEnums} from "@/enums";
import {BButton, BForm, BInput} from "@/components";
import {BeicroonRequestConfig, moduleRequest, sha256, toast} from "@/utils";

const router = useRouter();

type DTO = {
  username: string,
  password: string,
};

type VO = {
  code: string,
  name: string,
  username: string,
  phone: string,
  email: string,
  token: string,
};

const loginConfig: BeicroonRequestConfig = {
  url: "api/admin/rbac-auth-login",
  method: "POST",
};

const login = moduleRequest<VO, DTO>(loginConfig);

const handleLogin = async () => {
  localStorage.removeItem(BeicroonCacheEnums.USER);
  localStorage.removeItem(BeicroonCacheEnums.TOKEN);

  await login.request({password: sha256(login.param.password)});

  if (login.data) {
    const user = {
      code: login.data.code,
      name: login.data.name,
      username: login.data.username,
      phone: login.data.phone,
      email: login.data.email,
    }

    localStorage.setItem(BeicroonCacheEnums.USER, JSON.stringify(user));
    localStorage.setItem(BeicroonCacheEnums.TOKEN, login.data.token);
  }

  toast.success("登录成功");

  await router.push({path: "/"});
};
</script>

<template>
  <div class="beicroon-login">
    <div class="login-form">
      <b-form @submit="handleLogin">
        <b-input required label="账号" v-model="login.param.username"/>
        <b-input required label="密码" v-model="login.param.password" type="password"/>
        <template v-slot:button>
          <b-button label="登录" size="input" type="submit" :loading="login.loading"/>
        </template>
      </b-form>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-login {
  width: 100%;
  height: 100%;
  position: relative;
  background-color: var(--color-grey-lighter);

  .login-form {
    top: 38%;
    left: 50%;
    width: 520rem;
    height: 240rem;
    position: absolute;
    border-radius: 8rem;
    transform: translate(-50%, -50%);
    border: 1rem solid var(--color-primary);
  }

  .beicroon-form-view {
    justify-content: center;
  }

  .beicroon-form-button {
    border-top-color: var(--color-primary);
  }
}
</style>