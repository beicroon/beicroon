<script setup lang="ts">
import router from "@/router.ts";
import BeicroonLineCross from "@/components/BeicroonLineCross.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";

function toAccountAdmin() {
  router.push("/account/admin");
}

function toSystemMenu() {
  router.push("/system/menu");
}
</script>

<template>
  <section class="beicroon">
    <section class="beicroon-head">
      <div class="logo"></div>
      <ul class="menu">
        <li>首页</li>
        <li>设置中心</li>
        <li>账号中心</li>
      </ul>
    </section>
    <beicroon-line-vertical></beicroon-line-vertical>
    <section class="beicroon-body">
      <section class="beicroon-menu">
        <ul class="menu">
          <li @click="toAccountAdmin">账号管理</li>
          <li @click="toSystemMenu">菜单管理</li>
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
  --beicroon-width-menu: 180rem;
  --beicroon-width-line: 1rem;
}

.beicroon {
  z-index: 1;
  width: 100%;
  height: 100%;
  position: relative;
}

.beicroon-head {
  gap: 30rem;
  width: 100%;
  display: flex;
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

      &:hover {
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }
}

.beicroon-body {
  width: 100%;
  display: flex;
  flex-direction: row;
  height: calc(100% - var(--beicroon-height-head) - var(--beicroon-height-line));
}

.beicroon-menu {
  height: 100%;
  padding: 30rem 0;
  overflow-y: auto;
  width: var(--beicroon-width-menu);

  .menu {
    gap: 8rem;
    display: flex;
    flex-direction: column;
    justify-content: center;

    li {
      width: 100%;
      display: flex;
      height: 38rem;
      cursor: pointer;
      align-items: center;
      justify-content: center;

      &:hover {
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }
}

.beicroon-main {
  height: 100%;
  width: calc(100% - var(--beicroon-width-menu) - var(--beicroon-width-line));
}

.beicroon-view {
  min-height: 100%;
}
</style>