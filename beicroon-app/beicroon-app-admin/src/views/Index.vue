<script setup lang="ts">
import {onMounted} from "vue";
import {useRouter} from "vue-router";
import BeicroonLineCross from "@/components/BeicroonLineCross.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import auth, {initMenus, setLinks, toLinkPath} from "@/utils/auth.utils.ts";

onMounted(() => initMenus(useRouter().currentRoute.value.path));
</script>

<template>
  <section class="beicroon">
    <section class="beicroon-head">
      <div class="logo"></div>
      <ul class="menu">
        <li v-for="menu in auth.menus" :class="{active: menu.active}" @click="setLinks(menu)">{{ menu.name }}</li>
      </ul>
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
        <router-view class="beicroon-view" v-if="auth.ready"></router-view>
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