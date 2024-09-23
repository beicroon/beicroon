<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {CheckedEnums} from "@/enums/default-enums.ts";
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

function initChildCheckStatus(menu: Menu, roleMenuIds: Array<string>) {
  menu.children = menu.children.map((child) => {
    let checked = false;

    let unchecked = false;

    child.children = child.children.map((grand) => {
      if (roleMenuIds.includes(grand.id)) {
        grand.checked = CheckedEnums.CHECKED;

        checked = true;
      } else {
        grand.checked = CheckedEnums.UNCHECKED;

        unchecked = true;
      }

      return grand;
    });

    if (checked && unchecked) {
      child.checked = CheckedEnums.INDETERMINATE;
    } else if (checked) {
      child.checked = CheckedEnums.CHECKED;
    } else {
      child.checked = CheckedEnums.UNCHECKED;
    }

    return child;
  });
}

function initCheckStatus(menu: Menu) {
  let checked = false;

  let unchecked = false;

  let indeterminate = false;

  menu.children.forEach((child) => {
    if (child.checked === CheckedEnums.CHECKED) {
      checked = true;
    } else if (child.checked === CheckedEnums.INDETERMINATE) {
      indeterminate = true;
    } else {
      unchecked = true;
    }
  });

  if ((checked && unchecked) || indeterminate) {
    menu.checked = CheckedEnums.INDETERMINATE;
  } else if (checked) {
    menu.checked = CheckedEnums.CHECKED;
  } else {
    menu.checked = CheckedEnums.UNCHECKED;
  }
}

onMounted(async () => {
  loading.get = true;

  try {
    const res = await listAuthMenu();

    const {data: roleMenuIds} = await list(props.roleId);

    menus.value = res.data.map((menu) => {
      initChildCheckStatus(menu, roleMenuIds);

      initCheckStatus(menu);

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
    if (menu.checked === CheckedEnums.CHECKED || menu.checked === CheckedEnums.INDETERMINATE) {
      menuIds.push(menu.id);
    }

    menu.children.forEach((child) => {
      if (child.checked === CheckedEnums.CHECKED || child.checked === CheckedEnums.INDETERMINATE) {
        menuIds.push(child.id);
      }

      child.children.forEach((grand) => {
        if (grand.checked === CheckedEnums.CHECKED || grand.checked === CheckedEnums.INDETERMINATE) {
          menuIds.push(grand.id);
        }
      });
    });
  });

  await assign(props.roleId, menuIds).finally(() => loading.set = false);

  emits("confirm");
}

async function handleCheckGrand(menu: Menu, child: Menu, grand: Menu) {
  grand.checked = CheckedEnums.CHECKED;

  for (let i = 0; i < child.children.length; i++) {
    if (child.children[i].checked !== CheckedEnums.CHECKED) {
      child.checked = CheckedEnums.INDETERMINATE;

      menu.checked = CheckedEnums.INDETERMINATE;

      return;
    }
  }

  await handleCheckChild(menu, child);
}

async function handleCheckChild(menu: Menu, child: Menu) {
  child.checked = CheckedEnums.CHECKED;

  child.children.forEach((grandMenu) => {
    grandMenu.checked = CheckedEnums.CHECKED;
  });

  for (let i = 0; i < menu.children.length; i++) {
    if (menu.children[i].checked !== CheckedEnums.CHECKED) {
      menu.checked = CheckedEnums.INDETERMINATE;

      return;
    }
  }

  menu.checked = CheckedEnums.CHECKED;
}

async function handleCheckMenu(menu: Menu) {
  menu.checked = CheckedEnums.CHECKED;

  menu.children.forEach((childMenu) => {
    childMenu.checked = CheckedEnums.CHECKED;

    childMenu.children.forEach((grandMenu) => {
      grandMenu.checked = CheckedEnums.CHECKED;
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
  grand.checked = CheckedEnums.UNCHECKED;

  for (let i = 0; i < child.children.length; i++) {
    if (child.children[i].checked !== CheckedEnums.UNCHECKED) {
      child.checked = CheckedEnums.INDETERMINATE;

      menu.checked = CheckedEnums.INDETERMINATE;

      return;
    }
  }

  await handleUncheckChild(menu, child);
}

async function handleUncheckChild(menu: Menu, child: Menu) {
  child.checked = CheckedEnums.UNCHECKED;

  child.children.forEach((grandMenu) => {
    grandMenu.checked = CheckedEnums.UNCHECKED;
  });

  for (let i = 0; i < menu.children.length; i++) {
    if (menu.children[i].checked !== CheckedEnums.UNCHECKED) {
      menu.checked = CheckedEnums.INDETERMINATE;

      return;
    }
  }

  menu.checked = CheckedEnums.UNCHECKED;
}

async function handleUncheckMenu(menu: Menu) {
  menu.checked = CheckedEnums.UNCHECKED;

  menu.children.forEach((childMenu) => {
    childMenu.checked = CheckedEnums.UNCHECKED;

    childMenu.children.forEach((grandMenu) => {
      grandMenu.checked = CheckedEnums.UNCHECKED;
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
