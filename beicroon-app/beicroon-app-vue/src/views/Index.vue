<script setup lang="ts">
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import Logo from "@/canvas/Logo.vue";
import BeicroonCacheEnums from "@/enums/beicroon-cache-enums";
import {BeicroonRequestConfig, moduleRequest, toast} from "@/index";

interface Menu {
  name: string,
  path: string,
  active: boolean,
  children: Menu[],
}

const router = useRouter();

const current = ref();

const children = ref([] as Menu[]);

const handleMouseEnter = (parent: Menu) => {
  if (current.value) {
    current.value.active = false;
  }

  if (!parent.children || parent.children.length <= 0) {
    children.value = parent.children;

    return;
  }

  parent.active = true;

  current.value = parent;

  children.value = parent.children;
};

const handleParentClick = (parent: Menu) => {
  if (parent.path) {
    router.push(parent.path);

    handleMouseLeave();
  }
};

const handleMouseLeave = () => {
  if (current.value) {
    current.value.active = false;
  }

  children.value = [];
};

const handleClick = (link: Menu) => {
  if (link.path) {
    router.push(link.path);

    handleMouseLeave();
  }
};

const handleGoHome = () => {
  router.push("/");
};

const resourceConfig: BeicroonRequestConfig = {
  url: "api/admin/admin-auth-resource",
  method: "GET",
};

const user = ref<any>({});

const userString = localStorage.getItem(BeicroonCacheEnums.USER);

if (userString) {
  user.value = JSON.parse(userString);
}

const resource = moduleRequest<Array<Menu>>(resourceConfig);

onMounted(resource.request);

const logout = moduleRequest({
  url: "api/admin/admin-auth-logout",
  method: "POST",
});

const handleLogout = async () => {
  await logout.request();

  await router.push("/login");

  toast.success("退出登录成功");

  localStorage.removeItem(BeicroonCacheEnums.USER);
  localStorage.removeItem(BeicroonCacheEnums.TOKEN);
};
</script>

<template>
  <div class="beicroon-index">
    <div class="head">
      <div class="logo">
        <logo text="BeiCroon" :width="180" :height="60"/>
      </div>
      <div class="menu" @mouseleave="handleMouseLeave">
        <ul class="parent">
          <li @click="handleGoHome">首页</li>
          <template v-for="parent in resource.data">
            <li
              @mouseenter="handleMouseEnter(parent)"
              @click="handleParentClick(parent)"
              :class="{active: parent.active}"
            >{{ parent.name }}</li>
          </template>
        </ul>
        <ul class="child" v-show="children && children.length > 0">
          <li v-for="child in children">
            <h6 class="group">{{ child.name }}</h6>
            <ul class="link">
              <li v-for="link in child.children" @click="handleClick(link)">{{ link.name }}</li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="user">
        <span>{{ user.name }}</span>
        <ul class="button">
          <li @click="handleLogout">退出登录</li>
        </ul>
      </div>
    </div>
    <div class="body">
      <router-view></router-view>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-index {
  width: 100%;
  height: 100%;
  position: relative;

  .head {
    z-index: 2;
    width: 100%;
    height: 60rem;
    display: flex;
    position: relative;
    align-items: center;
    color: var(--color-white);
    justify-content: space-between;
    background-color: var(--color-primary);

    .logo {
      width: 180rem;
    }

    .menu {
      flex: 1;
      height: 100%;
      display: flex;
      padding: 0 130rem;
      position: relative;
      align-items: center;
      justify-content: center;

      .parent {
        gap: 24rem;
        height: 100%;
        display: flex;
        align-items: center;

        li {
          display: flex;
          height: 34rem;
          cursor: pointer;
          padding: 0 16rem;
          min-width: 88rem;
          position: relative;
          align-items: center;
          border-radius: 6rem;
          justify-content: center;
          border: 1rem solid var(--color-white);

          &.active {
            color: var(--color-primary);
            background-color: var(--color-white);

            &:after {
              width: 0;
              height: 0;
              left: 50%;
              content: "";
              bottom: -13rem;
              z-index: 99999;
              position: absolute;
              transform: translateX(-50%);
              border: 6rem solid transparent;
              border-top-color: var(--color-white);
            }
          }
        }
      }

      .child {
        left: 50%;
        gap: 16rem;
        top: 52rem;
        display: flex;
        z-index: 9999;
        min-width: 800rem;
        min-height: 240rem;
        position: absolute;
        border-radius: 6rem;
        padding: 24rem 32rem;
        transform: translateX(-50%);
        color: var(--color-black-light);
        background-color: var(--color-white-light);
        border: 1rem solid var(--color-grey-light);

        li {
          display: flex;
          align-items: center;
          flex-direction: column;
          background-color: var(--color-white);
        }

        .group {
          width: 100%;
          padding: 4rem 6rem;
          text-align: center;
          border-bottom: 1rem solid var(--color-grey-light);
        }

        .link {
          gap: 6rem;
          display: flex;
          padding: 8rem;
          align-items: center;
          flex-direction: column;

          li {
            width: 110rem;
            height: 24rem;
            cursor: pointer;
            line-height: 24rem;
            border-radius: 4rem;

            &:hover {
              color: var(--color-white);
              background-color: var(--color-primary);
            }
          }
        }
      }
    }

    .user {
      height: 100%;
      width: 180rem;
      display: flex;
      position: relative;
      align-items: center;
      justify-content: center;

      &:hover {
        .button {
          display: flex;
        }
      }

      .button {
        left: 50%;
        gap: 8rem;
        top: 46rem;
        width: 130rem;
        display: none;
        position: absolute;
        align-items: center;
        border-radius: 4rem;
        justify-content: center;
        transform: translateX(-50%);
        color: var(--color-black-light);
        background-color: var(--color-grey-lighter);

        li {
          cursor: pointer;
          padding: 4rem 0;
        }
      }
    }
  }

  .body {
    z-index: 1;
    width: 100%;
    position: relative;
    height: calc(100% - 60rem);
  }
}
</style>