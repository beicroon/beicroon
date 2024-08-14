<script setup lang="ts">
import {ref} from "vue";
import router from "@/routers";
import {useRoute} from "vue-router";
import {CacheKeyEnums} from "@/enums/system.enums.ts";
import ElInput from "@/components/elements/ElInput.vue";
import ElButton from "@/components/elements/ElButton.vue";
import {AuthAdminBaseVO, AuthAdminLoginDTO, login} from "@/https/auth/auth.http.ts";
import {validateForm} from "@/utils/function.ts";
import toast from "@/utils/toast";

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

    return await toast("请重新填写标红的输入框", true)
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
      <el-input required class="form-input" label="账号" placeholder="请输入账号" v-model="dto.username"></el-input>
      <el-input required class="form-input" type="password" label="密码" placeholder="请输入密码" v-model="dto.password"></el-input>
      <div class="login-button">
        <el-button @click="submit" :loading="loading">登录</el-button>
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
    display: flex;
    align-items: center;
    justify-content: center;

    button {
      color: @white;
      padding: 8rem 32rem;
      border-radius: 8rem;
      background-color: @primary;
    }
  }
}
</style>