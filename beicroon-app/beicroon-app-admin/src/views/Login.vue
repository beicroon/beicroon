<script setup lang="ts">
import {ref} from "vue";
import router from "@/routers";
import {useRoute} from "vue-router";
import {CacheKeyEnums} from "@/enums/system.enums.ts";
import {AuthAdminBaseVO, AuthAdminLoginDTO, login} from "@/https/auth/auth.http.ts";

const route = useRoute();

const to: string = route.query.t as string;

const form = ref<AuthAdminLoginDTO>({
  username: "",
  password: "",
});

async function submit() {
  const res = await login(form.value);

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
    <form>
      <input type="text" placeholder="账号" v-model="form.username"/>
      <input type="text" placeholder="密码" v-model="form.password"/>
      <button type="button" @click="submit">登录</button>
    </form>
  </div>
</template>

<style scoped lang="less">

</style>