<script setup lang="ts">
import router from "@/utils/router.utils.ts";
import {onBeforeMount, ref} from "vue";
import BeicroonLineCross from "@/components/BeicroonLineCross.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {AuthMenu, index, listAuthMenu} from "@/request/account-admin-auth.http.ts";

const menus = ref<Array<AuthMenu>>([]);

const prevMenu = ref<AuthMenu | null>(null);

const currentMenu = ref<AuthMenu | null>(null);

const links = ref<Array<AuthMenu>>([]);

function toLinkPath(link: AuthMenu) {
  if (prevMenu.value !== null) {
    prevMenu.value.children.forEach((child) => {
      child.children.forEach((item) => {
        item.active = false;
      });
    });

    prevMenu.value = null;
  }

  if (currentMenu.value !== null) {
    currentMenu.value.children.forEach((child) => {
      child.children.forEach((item) => {
        item.active = false;
      });
    });
  }

  link.active = true;

  router.push(link.path);
}

function setLinks(menu: AuthMenu) {
  menus.value.forEach((item) => item.active = false);

  prevMenu.value = currentMenu.value;

  currentMenu.value = menu;

  menu.active = true;

  links.value = menu.children;

  if ((!menu.children || menu.children.length == 0) && menu.path) {
    router.push(menu.path);
  }
}

onBeforeMount(async () => {
  const res = await listAuthMenu();

  menus.value = [index].concat(res.data);

  MENU_FOREACH: for (let i = 0; i < menus.value.length; i++) {
    const menu = menus.value[i];

    for (let j = 0; j < menu.children.length; j++) {
      const child = menu.children[j];

      for (let k = 0; k < child.children.length; k++) {
        const link = child.children[k];

        if (link.path === router.currentRoute.value.path) {
          setLinks(menu);

          link.active = true;

          break MENU_FOREACH;
        }
      }
    }
  }
});
</script>

<template>
  <section class="beicroon">
    <section class="beicroon-head">
      <div class="logo"></div>
      <ul class="menu">
        <li v-for="menu in menus" :class="{active: menu.active}" @click="setLinks(menu)">{{ menu.name }}</li>
      </ul>
    </section>
    <beicroon-line-vertical></beicroon-line-vertical>
    <section class="beicroon-body">
      <section class="beicroon-menu">
        <ul class="menu">
          <li v-for="menu in links">
            <h3>{{ menu.name }}</h3>
            <ul class="menu">
              <li v-for="link in menu.children" :class="{active: link.active}" @click="toLinkPath(link)">{{ link.name }}</li>
            </ul>
          </li>
        </ul>
      </section>
      <beicroon-line-cross></beicroon-line-cross>
      <section class="beicroon-main">
        <router-view class="beicroon-view"></router-view>
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
  z-index: 1;
  width: 100%;
  height: 100%;
  position: relative;
}

.beicroon-head {
  z-index: 1;
  gap: 30rem;
  width: 100%;
  display: flex;
  position: relative;
  height: var(--beicroon-height-head);

  .logo {
    width: 300rem;
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
}

.beicroon-body {
  z-index: 2;
  width: 100%;
  display: flex;
  position: relative;
  flex-direction: row;
  height: calc(100% - var(--beicroon-height-head) - var(--beicroon-height-line));
}

.beicroon-menu {
  z-index: 1;
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
  z-index: 2;
  height: 100%;
  position: relative;
  width: calc(100% - var(--beicroon-width-menu) - var(--beicroon-width-line));
}

.beicroon-view {
  height: 100%;
}
</style>