<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import BeicroonTree from "@/components/BeicroonTree.vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";
import BeicroonTreeItem from "@/components/BeicroonTreeItem.vue";
import {assign, list} from "@/request/resource-role-menu.http.ts";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {AuthMenu as Menu, listAuthMenu} from "@/request/account-admin-auth.http.ts";

type Props = {
  roleId: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const menus = ref<Array<Menu>>([]);

onMounted(async () => {
  loading.get = true;

  try {
    const res = await listAuthMenu();

    const {data: roleMenuIds} = await list(props.roleId);

    menus.value = res.data.map((menu) => {
      menu.children = menu.children.map((child) => {
        let grandCheck = false;

        let grandUnchecked = false;

        child.children = child.children.map((grand) => {
          if (roleMenuIds.includes(grand.id)) {
            grand.checked = "checked";

            grandCheck = true;
          } else {
            grand.checked = "unchecked";

            grandUnchecked = true;
          }

          return grand;
        });

        if (grandCheck && grandUnchecked) {
          child.checked = "indeterminate";
        } else if (grandCheck) {
          child.checked = "checked";
        } else {
          child.checked = "unchecked";
        }

        return child;
      });

      let childCheck = false;

      let childFixed = false;

      let childUnchecked = false;

      menu.children.forEach((child) => {
        if (child.checked === "checked") {
          childCheck = true;
        } else if (child.checked === "indeterminate") {
          childFixed = true;
        } else {
          childUnchecked = true;
        }
      });

      if ((childCheck && childUnchecked) || childFixed) {
        menu.checked = "indeterminate";
      } else if (childCheck) {
        menu.checked = "checked";
      } else {
        menu.checked = "unchecked";
      }

      return menu;
    });
  } finally {
    loading.get = false;
  }
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.set = true;

  const menuIds: Array<string> = [];

  menus.value.forEach((menu) => {
    if (menu.checked === "checked" || menu.checked === "indeterminate") {
      menuIds.push(menu.id);
    }

    menu.children.forEach((child) => {
      if (child.checked === "checked" || child.checked === "indeterminate") {
        menuIds.push(child.id);
      }

      child.children.forEach((grand) => {
        if (grand.checked === "checked" || grand.checked === "indeterminate") {
          menuIds.push(grand.id);
        }
      });
    });
  });

  await assign(props.roleId, menuIds).finally(() => loading.set = false);

  emits("confirm");
}

async function handleCheckGrand(menu: Menu, child: Menu, grand: Menu) {
  grand.checked = "checked";

  for (let i = 0; i < child.children.length; i++) {
    if (child.children[i].checked !== "checked") {
      child.checked = "indeterminate";

      menu.checked = "indeterminate";

      return;
    }
  }

  await handleCheckChild(menu, child);
}

async function handleCheckChild(menu: Menu, child: Menu) {
  child.checked = "checked";

  child.children.forEach((grandMenu) => {
    grandMenu.checked = "checked";
  });

  for (let i = 0; i < menu.children.length; i++) {
    if (menu.children[i].checked !== "checked") {
      menu.checked = "indeterminate";

      return;
    }
  }

  menu.checked = "checked";
}

async function handleCheckMenu(menu: Menu) {
  menu.checked = "checked";

  menu.children.forEach((childMenu) => {
    childMenu.checked = "checked";

    childMenu.children.forEach((grandMenu) => {
      grandMenu.checked = "checked";
    });
  });
}

async function handleCheck(menu: Menu, child?: Menu, grand?: Menu) {
  if (grand && child) {
    await handleCheckGrand(menu, child, grand);

    return;
  }

  if (child) {
    await handleCheckChild(menu, child);

    return;
  }

  await handleCheckMenu(menu)
}

async function handleUncheckGrand(menu: Menu, child: Menu, grand: Menu) {
  grand.checked = "unchecked";

  for (let i = 0; i < child.children.length; i++) {
    if (child.children[i].checked !== "unchecked") {
      child.checked = "indeterminate";

      menu.checked = "indeterminate";

      return;
    }
  }

  await handleUncheckChild(menu, child);
}

async function handleUncheckChild(menu: Menu, child: Menu) {
  child.checked = "unchecked";

  child.children.forEach((grandMenu) => {
    grandMenu.checked = "unchecked";
  });

  for (let i = 0; i < menu.children.length; i++) {
    if (menu.children[i].checked !== "unchecked") {
      menu.checked = "indeterminate";

      return;
    }
  }

  menu.checked = "unchecked";
}

async function handleUncheckMenu(menu: Menu) {
  menu.checked = "unchecked";

  menu.children.forEach((childMenu) => {
    childMenu.checked = "unchecked";

    childMenu.children.forEach((grandMenu) => {
      grandMenu.checked = "unchecked";
    });
  });
}

async function handleUncheck(menu: Menu, child?: Menu, grand?: Menu) {
  if (grand && child) {
    await handleUncheckGrand(menu, child, grand);

    return;
  }

  if (child) {
    await handleUncheckChild(menu, child);

    return;
  }

  await handleUncheckMenu(menu);
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main" v-if="!loading.get">
      <beicroon-tree>
        <beicroon-tree-item v-for="menu in menus">
          <beicroon-checkbox
            :label="menu.name"
            :checked="menu.checked"
            @check="handleCheck(menu)"
            @uncheck="handleUncheck(menu)"
          ></beicroon-checkbox>
          <beicroon-tree>
            <beicroon-tree-item v-for="childMenu in menu.children">
              <beicroon-checkbox
                :label="childMenu.name"
                :checked="childMenu.checked"
                @check="handleCheck(menu, childMenu)"
                @uncheck="handleUncheck(menu, childMenu)"
              ></beicroon-checkbox>
              <beicroon-tree>
                <beicroon-tree-item v-for="grandMenu in childMenu.children">
                  <beicroon-checkbox
                    :label="grandMenu.name"
                    :checked="grandMenu.checked"
                    @check="handleCheck(menu, childMenu, grandMenu)"
                    @uncheck="handleUncheck(menu, childMenu, grandMenu)"
                  ></beicroon-checkbox>
                </beicroon-tree-item>
              </beicroon-tree>
            </beicroon-tree-item>
          </beicroon-tree>
        </beicroon-tree-item>
      </beicroon-tree>
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

<style lang="less">
</style>
