<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import BeicroonTree from "@/components/BeicroonTree.vue";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";
import BeicroonTreeItem from "@/components/BeicroonTreeItem.vue";
import {assign, list} from "@/request/account-admin-role.http.ts";
import {BooleanEnums, CheckedEnums} from "@/enums/default-enums.ts";
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

const roles = ref<Array<Role>>([]);

onMounted(async () => {
  loading.get = true;

  try {
    const res = await roleList({disabledFlag: BooleanEnums.FALSE});

    const {data: adminRoleIds} = await list(props.adminId);

    roles.value = res.data.map((role: Role) => {
      return {
        ...role,
        checked: adminRoleIds.includes(role.id) ? CheckedEnums.CHECKED : CheckedEnums.UNCHECKED,
      };
    });
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

  const roleIds = roles.value.filter(role => role.checked === CheckedEnums.CHECKED).map(role => role.id);

  await assign(props.adminId, roleIds).finally(() => loading.set = false);

  emits("confirm");
}

async function handleCheck(role: Role) {
  role.checked = CheckedEnums.CHECKED;
}

async function handleUncheck(role: Role) {
  role.checked = CheckedEnums.UNCHECKED;
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main" v-if="!loading.get">
      <beicroon-tree>
        <beicroon-tree-item v-for="role in roles">
          <beicroon-checkbox
            :label="role.name"
            :checked="role.checked"
            @check="handleCheck(role)"
            @uncheck="handleUncheck(role)"
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
