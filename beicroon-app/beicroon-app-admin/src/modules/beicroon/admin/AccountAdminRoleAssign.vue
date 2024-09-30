<script setup lang="ts">
import {onMounted, reactive} from "vue";
import {batchRequest} from "@/utils/beicroon/http.utils.ts";
import {BooleanEnums} from "@/enums/beicroon/beicroon-enums.ts";
import BeicroonTree from "@/components/beicroon/BeicroonTree.vue";
import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
import {createBeicroonCheck} from "@/utils/beicroon/check.utils.ts";
import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
import BeicroonCheckbox from "@/components/beicroon/BeicroonCheckbox.vue";
import BeicroonTreeItem from "@/components/beicroon/BeicroonTreeItem.vue";
import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
import {assign, list as listRoleId} from "@/request/beicroon/account-admin-role.http.ts";
import {listNoWaiting as listRole, ResourceRoleBaseVO as Role} from "@/request/beicroon/resource-role.http.ts"

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

  const [roles, roleIds] = await batchRequest([
      async () => listRole({disabledFlag: BooleanEnums.FALSE}),
      async () => listRoleId(props.adminId),
    ])
    .finally(() => loading.get = false);

  check.initCheck(roles, roleIds)
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
