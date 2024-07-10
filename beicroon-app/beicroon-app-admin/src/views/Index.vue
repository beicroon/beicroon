<script setup>
import router from '@/router/index.js';
import {defineComponent, ref, watch} from 'vue';

defineComponent({
  name: 'Index',
});

function handleNavigatorClick(navigator) {
  for (let i = 0; i < navigators.length; i++) {
    const item = navigators[i];

    if (item.active) {
      item.active = false;

      break;
    }
  }

  navigator.active = true;

  menus.value = navigator.children;
}

function handleLinkerClick(parent, linker) {
  for (let i = 0; i < menus.value.length; i++) {
    const menu = menus.value[i];

    if (menu.active) {
      menu.active = false;

      for (let j = 0; j < menu.children.length; j++) {
        const child = menu.children[j];

        if (child.active) {
          child.active = false;

          break;
        }
      }

      break;
    }
  }

  parent.active = true;

  linker.active = true;

  router.push({path: linker.path});
}

const navigators = [
  {
    name: '账号中心',
    path: '',
    children: [
      {
        name: '账号管理',
        path: '',
        children: [
          {
            name: '后台账号',
            path: '/account/admin',
          },
          {
            name: '用户账号',
            path: '/account/user',
          },
          {
            name: '司机账号',
            path: '/account/driver',
          },
          {
            name: '商户账号',
            path: '/account/business',
          },
        ],
      },
      {
        name: '系统设置',
        path: '',
        children: [
          {
            name: '系统设置',
            path: '/setting/system',
          },
          {
            name: '角色管理',
            path: '/setting/role',
          },
        ],
      },
    ],
  },
  {
    name: '设置中心',
    path: '',
    children: [
      {
        name: '系统设置',
        path: '',
        children: [
          {
            name: '系统设置',
            path: '/setting/system',
          },
          {
            name: '角色管理',
            path: '/setting/role',
          },
        ],
      },
    ],
  },
];

const menus = ref([]);

watch(menus, () => {
  for (let i = 0; i < menus.value.length; i++) {
    const children = menus.value[i].children;

    for (let j = 0; j < children.length; j++) {
      const linker = children[j];

      if (linker.active) {
        router.push({path: linker.path});

        break;
      }
    }
  }
});
</script>

<template>
  <div class="index">
    <div class="head">
      <ul class="navigator">
        <template v-for="navigator in navigators">
          <li class="navigator-item" :class="{active: navigator.active}" @click="handleNavigatorClick(navigator)">
            {{ navigator.name }}
          </li>
        </template>
      </ul>
    </div>
    <div class="menu">
      <div class="menu-item" v-for="menu in menus">
        <div class="parent" :class="{active: menu.active}">{{ menu.name }}</div>
        <ul class="linker">
          <template v-for="linker in menu.children">
            <li class="linker-item" :class="{active: linker.active}" @click="handleLinkerClick(menu, linker)">
              {{ linker.name }}
            </li>
          </template>
        </ul>
      </div>
    </div>
    <div class="main">
      <router-view class="view"></router-view>
    </div>
  </div>
</template>

<style scoped lang="less">
.head {
  top: 0;
  left: 0;
  width: 100%;
  height: 60rem;
  color: #ffffff;
  font-size: 16rem;
  padding: 0 38rem;
  position: absolute;
  background: linear-gradient(83deg, #0f71e2 0%, #0f71e2 100%);

  .navigator {
    height: 100%;
    display: flex;
    align-items: center;

    .navigator-item {
      margin: 0 8rem;
      cursor: pointer;
      padding: 8rem 12rem;
      border-radius: 8rem;
      border: solid 1rem #65a5ff;

      &:hover {
        border-color: #65a5ff;
        background-color: #65a5ff;
      }

      &.active {
        color: #65a5ff;
        font-weight: bold;
        border-color: #ffffff;
        background-color: #ffffff;
      }
    }
  }
}

.menu {
  left: 0;
  top: 60rem;
  width: 180rem;
  overflow-y: auto;
  padding: 38rem 0;
  overflow-x: hidden;
  position: absolute;
  height: calc(100% - 60rem);
  border-right: 1px solid #e0e4ed;

  .menu-item {
    width: 100%;
    height: auto;
  }

  .parent {
    cursor: pointer;
    padding: 8rem 0;
    font-size: 16rem;
    font-weight: bold;
    text-align: center;

    &:hover {
      color: #ffffff;
      background: linear-gradient(90deg, #287bf1 0%, #65a5ff 100%);
    }

    &.active {
      color: #287bf1;
    }
  }

  .linker-item {
    margin: 2rem 0;
    padding: 8rem 0;
    cursor: pointer;
    text-indent: 10rem;
    text-align: center;

    &:hover {
      color: #ffffff;
      background: linear-gradient(90deg, #65a5ff 0%, #8eb6ff 100%);
    }

    &.active {
      color: #ffffff;
      background: linear-gradient(90deg, #287bf1 0%, #65a5ff 100%);
    }
  }
}

.main {
  right: 0;
  top: 60rem;
  padding: 8rem;
  overflow-y: auto;
  position: absolute;
  width: calc(100% - 180rem);
  height: calc(100% - 60rem);

  .view {
    width: 100%;
    min-height: 100%;
  }
}
</style>