<script setup lang="ts">
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {CacheKeyEnums} from "@/enums/beicroon/beicroon-enums.ts";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLineCross from "@/components/beicroon/BeicroonLineCross.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {AuthUser, loginMenu, logout} from "@/request/beicroon/account-admin-auth.http.ts";
import auth, {clearAuth, initMenus, router, setLinks, toLinkPath} from "@/utils/beicroon/auth.utils.ts";

onMounted(() => initMenus(useRouter().currentRoute.value.path));

const user: AuthUser = JSON.parse(localStorage.getItem(CacheKeyEnums.AUTHORIZATION_USER) as string);

const hidden = ref(true);

function handleShow() {
  hidden.value = false;
}

function handleHide() {
  hidden.value = true;
}

async function handleLogout() {
  await logout();

  await clearAuth()

  localStorage.removeItem(CacheKeyEnums.AUTHORIZATION_USER);
  localStorage.removeItem(CacheKeyEnums.AUTHORIZATION_TOKEN);

  await router.push(loginMenu.path);
}
</script>

<template>
  <section class="beicroon">
    <section class="beicroon-head">
      <div class="logo"></div>
      <ul class="menu">
        <li v-for="menu in auth.menus" :class="{active: menu.active}" @click="setLinks(menu)">{{ menu.name }}</li>
      </ul>
      <div class="user" @mouseenter="handleShow" @mouseleave="handleHide">
        <h3>{{user.name}}</h3>
        <ul class="button" :class="{hidden: hidden}">
          <li><beicroon-button label="退出登录" @click="handleLogout"></beicroon-button></li>
        </ul>
      </div>
    </section>
    <beicroon-line-vertical></beicroon-line-vertical>
    <section class="beicroon-body">
      <section class="beicroon-menu">
        <ul class="menu">
          <li v-for="menu in auth.links">
            <h3>{{ menu.name }}</h3>
            <ul class="menu">
              <template v-for="link in menu.children">
                <li :class="{active: link.active}" @click="toLinkPath(link)">{{link.name}}</li>
              </template>
            </ul>
          </li>
        </ul>
      </section>
      <beicroon-line-cross></beicroon-line-cross>
      <section class="beicroon-main">
        <router-view v-if="auth.ready"></router-view>
      </section>
    </section>
  </section>
</template>

<style lang="less">
:root {
  --beicroon-height-head: 60rem;
  --beicroon-height-line: 1rem;
  --beicroon-width-menu: 169rem;
  --beicroon-width-line: 1rem;
}

.beicroon {
  width: 100%;
  height: 100%;
  position: relative;
}

.beicroon-head {
  gap: 30rem;
  width: 100%;
  display: flex;
  padding: 0 30rem;
  position: relative;
  height: var(--beicroon-height-head);
  background-color: var(--color-white);

  .logo {
    width: 240rem;
  }

  .menu {
    flex: 1;
    gap: 30rem;
    display: flex;
    flex-direction: row;
    align-items: center;

    li {
      display: flex;
      height: 34rem;
      cursor: pointer;
      min-width: 88rem;
      border-radius: 8rem;
      align-items: center;
      justify-content: center;
      border: 1rem solid var(--color-primary);

      &:hover, &.active {
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }

  .user {
    gap: 6rem;
    z-index: 8;
    display: flex;
    width: 120rem;
    position: relative;
    align-items: center;
    justify-content: center;

    .button {
      left: 50%;
      width: 80rem;
      position: absolute;
      border-radius: 6rem;
      transform: translateX(-50%);
      background-color: var(--color-white);
      top: calc(var(--beicroon-height-head) - 8rem);
      box-shadow: 0 0 8rem -3rem var(--color-black-30) inset;

      li {
        width: 100%;
        height: 32rem;
        display: flex;
        align-items: center;
        justify-content: center;

        .beicroon-button {
          width: 100%;
          height: 100%;
          align-items: center;
          justify-content: center;
        }
      }
    }
  }
}

.beicroon-head-body {
  position: relative;
}

.beicroon-body {
  width: 100%;
  display: flex;
  position: relative;
  flex-direction: row;
  height: calc(100% - var(--beicroon-height-head) - var(--beicroon-height-line));

  .beicroon-menu {
    height: 100%;
    padding: 30rem 0;
    overflow-y: auto;
    position: relative;
    width: var(--beicroon-width-menu);

    .menu {
      gap: 2rem;
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;

      h3 {
        width: 100%;
        height: 38rem;
        display: flex;
        font-size: 14rem;
        line-height: 38rem;
        font-weight: normal;
        align-items: center;
        justify-content: center;
        transform: translateX(-14rem);
      }

      li {
        gap: 6rem;
        width: 100%;
        display: flex;
        cursor: pointer;
        line-height: 38rem;
        align-items: center;
        flex-direction: column;
        justify-content: center;

        .menu li {
          &:hover, &.active {
            color: var(--color-white);
            background-color: var(--color-primary);
          }
        }
      }
    }
  }

  .beicroon-main {
    height: 100%;
    position: relative;
    width: calc(100% - var(--beicroon-width-menu) - var(--beicroon-width-line));
  }
}
</style>