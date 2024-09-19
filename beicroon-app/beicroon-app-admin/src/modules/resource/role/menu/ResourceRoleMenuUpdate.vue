<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import {AuthMenu as Menu, listAuthMenu} from "@/auth.http.ts";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";

type Props = {
  roleId: string,
};

defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const menus = ref<Array<Menu>>([]);

onMounted(async () => {
  loading.get = true;

  const res = await listAuthMenu()
    .finally(() => loading.get = false);

  menus.value = res.data;
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  emits("confirm");
}

async function handleCheck(menu: Menu, child?: Menu, grand?: Menu) {
  if (grand) {
    grand.checked = "checked";

    if (child) {
      child.checked = "fixed";
    }

    if (menu) {
      menu.checked = "fixed";
    }

    return;
  }

  if (child) {
    child.checked = "checked";

    if (menu) {
      menu.checked = "fixed";
    }

    child.children.forEach((grandMenu) => {
      grandMenu.checked = "checked";
    });

    return;
  }

  menu.checked = "checked";

  menu.children.forEach((childMenu) => {
    childMenu.checked = "checked";

    childMenu.children.forEach((grandMenu) => {
      grandMenu.checked = "checked";
    });
  });
}

async function handleUncheck(menu: Menu, child?: Menu, grand?: Menu) {
  if (grand) {
    grand.checked = "unchecked";

    if (child) {
      child.checked = "fixed";
    }

    if (menu) {
      menu.checked = "fixed";
    }

    return;
  }

  if (child) {
    child.checked = "unchecked";

    if (menu) {
      menu.checked = "fixed";
    }

    child.children.forEach((grandMenu) => {
      grandMenu.checked = "unchecked";
    });

    return;
  }

  menu.checked = "unchecked";

  menu.children.forEach((childMenu) => {
    childMenu.checked = "unchecked";

    childMenu.children.forEach((grandMenu) => {
      grandMenu.checked = "unchecked";
    });
  });
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main" v-if="!loading.get">
      <ul class="menu-first">
        <template v-for="menu in menus">
          <li>
            <beicroon-checkbox
              :label="menu.name"
              :checked="menu.checked"
              @check="handleCheck(menu)"
              @uncheck="handleUncheck(menu)"
            ></beicroon-checkbox>
          </li>
          <ul class="menu-second">
            <template v-for="childMenu in menu.children">
              <li>
                <beicroon-checkbox
                  :label="childMenu.name"
                  :checked="childMenu.checked"
                  @check="handleCheck(menu, childMenu)"
                  @uncheck="handleUncheck(menu, childMenu)"
                ></beicroon-checkbox>
              </li>
              <ul class="menu-third">
                <template v-for="grandMenu in childMenu.children">
                  <li>
                    <beicroon-checkbox
                      :label="grandMenu.name"
                      :checked="grandMenu.checked"
                      @check="handleCheck(menu, childMenu, grandMenu)"
                      @uncheck="handleUncheck(menu, childMenu, grandMenu)"
                    ></beicroon-checkbox>
                  </li>
                </template>
              </ul>
            </template>
          </ul>
        </template>
      </ul>
    </div>
    <div class="beicroon-dialog-loading" v-else>
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-button">
      <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
      <beicroon-button type="submit" class="block warning" label="保存" :loading="loading.set"></beicroon-button>
    </div>
  </beicroon-form>
</template>

<style scoped lang="less">
.menu-first {
  padding: 24rem;
}

.menu-second {
  padding: 0 16rem;
}

.menu-third {
  padding: 0 16rem;
}
</style>
