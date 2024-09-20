<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {BooleanEnums} from "@/enums/default-enums.ts";
import BeicroonForm from "@/components/BeicroonForm.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";
import {assign, list} from "@/request/account-admin-role.http.ts";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import {list as roleList, ResourceRoleBaseVO as Role} from "@/request/resource-role.http.ts"

type RoleWithChecked = Role & {
  checked: "checked" | "indeterminate" | "unchecked",
};

type Props = {
  adminId: string,
};

const props = defineProps<Props>();

const loading = reactive({
  set: false,
  get: false,
});

const roles = ref<Array<RoleWithChecked>>([]);

onMounted(async () => {
  loading.get = true;

  try {
    const res = await roleList({disabledFlag: BooleanEnums.FALSE});

    const {data: adminRoleIds} = await list(props.adminId);

    roles.value = res.data.map((role: Role) => {
      return {
        ...role,
        checked: adminRoleIds.includes(role.id) ? "checked" : "unchecked",
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

  const roleIds = roles.value.filter(role => role.checked === "checked").map(role => role.id);

  await assign(props.adminId, roleIds).finally(() => loading.set = false);

  emits("confirm");
}

async function handleCheck(role: RoleWithChecked) {
  role.checked = "checked";
}

async function handleUncheck(role: RoleWithChecked) {
  role.checked = "unchecked";
}
</script>

<template>
  <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
    <div class="beicroon-dialog-main" v-if="!loading.get">
      <ul class="menu-first">
        <template v-for="role in roles">
          <li>
            <beicroon-checkbox
              :label="role.name"
              :checked="role.checked"
              @check="handleCheck(role)"
              @uncheck="handleUncheck(role)"
            ></beicroon-checkbox>
          </li>
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
