<script setup lang="ts">
import {onMounted, reactive} from "vue";
import {batchRequest} from "@/utils/http.utils.ts";
import BeicroonTree from "@/components/BeicroonTree.vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import {createBeicroonCheckTree} from "@/utils/check.utils.ts";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";
import BeicroonTreeItem from "@/components/BeicroonTreeItem.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {assign, list as listMenuId} from "@/request/resource-role-menu.http.ts";
import {AuthMenu as Menu, listAuthMenuNoWaiting as listMenu} from "@/request/account-admin-auth.http.ts";

type Props = {
  roleId: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const check = createBeicroonCheckTree<Menu>()

onMounted(async () => {
  loading.get = true;

  const [menus, menuIds] = await batchRequest([
      async () => listMenu(),
      async () => listMenuId(props.roleId),
    ])
    .finally(() => loading.get = false);

  check.initCheck(menus, menuIds)
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.set = true;

  await assign(props.roleId, check.getCheckedAndIndeterminateIds())
    .finally(() => loading.set = false);

  emits("confirm");
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main" v-if="!loading.get">
      <beicroon-tree>
        <beicroon-tree-item v-for="menu in check.vos">
          <beicroon-checkbox
            :label="menu.name"
            :checked="menu.checked"
            @check="check.handleCheck(menu)"
            @uncheck="check.handleUncheck(menu)"
          ></beicroon-checkbox>
          <beicroon-tree>
            <beicroon-tree-item v-for="childMenu in menu.children">
              <beicroon-checkbox
                :label="childMenu.name"
                :checked="childMenu.checked"
                @check="check.handleCheck(menu, childMenu)"
                @uncheck="check.handleUncheck(menu, childMenu)"
              ></beicroon-checkbox>
              <beicroon-tree>
                <beicroon-tree-item v-for="grandMenu in childMenu.children">
                  <beicroon-checkbox
                    :label="grandMenu.name"
                    :checked="grandMenu.checked"
                    @check="check.handleCheck(menu, childMenu, grandMenu)"
                    @uncheck="check.handleUncheck(menu, childMenu, grandMenu)"
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
