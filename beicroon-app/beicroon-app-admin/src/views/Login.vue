<script setup lang="ts">
import {ref} from "vue";
import router from "@/routers";
import toast from "@/utils/toast";
import {useRoute} from "vue-router";
import {validateForm} from "@/utils/function.ts";
import {CacheKeyEnums} from "@/enums/system.enums.ts";
import FormInput from "@/components/form/FormInput.vue";
import FormButton from "@/components/form/FormButton.vue";
import {AuthAdminBaseVO, AuthAdminLoginDTO, login} from "@/https/auth/auth.http.ts";

const form = ref();

const loading = ref(false);

const route = useRoute();

const to: string = route.query.t as string;

const dto = ref<AuthAdminLoginDTO>({
  username: "",
  password: "",
});

async function submit() {
  loading.value = true;

  if (!validateForm(form)) {
    loading.value = false;

    return await toast("请填写必填项", true)
  }

  const res = await login(dto.value).finally(() => loading.value = false);

  const user: AuthAdminBaseVO = {
    id: res.data.id,
    code: res.data.code,
    nickname: res.data.nickname
  };

  localStorage.setItem(CacheKeyEnums.AUTHORIZATION_USER, JSON.stringify(user))
  localStorage.setItem(CacheKeyEnums.AUTHORIZATION_TOKEN, res.data.token)

  await router.push(to ? to : "/");
}
</script>

<template>
  <div class="login">
    <form class="login-form" ref="form" @submit="submit">
      <form-input required class="form-input" placeholder="请输入账号" v-model="dto.username">账号</form-input>
      <form-input required class="form-input" type="password" placeholder="请输入密码" v-model="dto.password">密码</form-input>
      <div class="login-button">
        <form-button @click="submit" :loading="loading">登录</form-button>
      </div>
    </form>
  </div>
</template>

<style scoped lang="less">
@import "@/less/colors";

.login {
  z-index: 1;
  width: 100%;
  height: 100%;
  position: relative;
  background-color: @lighter-grey;

  .login-form {
    top: 38%;
    left: 50%;
    gap: 18rem;
    z-index: 2;
    display: flex;
    padding: 18rem;
    position: absolute;
    border-radius: 8rem;
    flex-direction: column;
    background-color: @white;
    transform: translate(-50%, -38%);
  }

  .login-button {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;

    .form-button {
      width: 100%;
      color: @white;
      padding: 8rem 0;
      min-width: 80rem;
      background-color: @primary;
    }
  }
}
</style>