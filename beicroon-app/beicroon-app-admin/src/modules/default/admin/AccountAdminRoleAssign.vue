<script setup lang="ts">
import {onMounted, reactive} from "vue";
import {BooleanEnums} from "@/enums/default-enums.ts";
import BeicroonTree from "@/components/BeicroonTree.vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import {createBeicroonCheck} from "@/utils/check.utils.ts";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";
import BeicroonTreeItem from "@/components/BeicroonTreeItem.vue";
import {assign, list} from "@/request/account-admin-role.http.ts";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {list as roleList, ResourceRoleBaseVO as Role} from "@/request/resource-role.http.ts"

type Props = {
  adminId: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const check = createBeicroonCheck<Role>();

onMounted(async () => {
  loading.get = true;

  try {
    const {data: roles} = await roleList({disabledFlag: BooleanEnums.FALSE});

    const {data: roleIds} = await list(props.adminId);

    check.initCheck(roles, roleIds);
  } finally {
    loading.get = false
  }
});

const emits = defineEmits(["cancel", "confirm"]);

async function handleCancel() {
  emits("cancel");
}

async function handleConfirm() {
  loading.set = true;

  await assign(props.adminId, check.getCheckedIds())
    .finally(() => loading.set = false);

  emits("confirm");
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main" v-if="!loading.get">
      <beicroon-tree>
        <beicroon-tree-item v-for="role in check.vos">
          <beicroon-checkbox
            :label="role.name"
            :checked="role.checked"
            @check="check.handleCheck(role)"
            @uncheck="check.handleUncheck(role)"
          ></beicroon-checkbox>
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
