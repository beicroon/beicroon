<script setup lang="ts">
import {onUpdated, ref} from "vue";
import BeicroonCheckbox from "@/components/BeicroonCheckbox.vue";

interface Props {
  labelField: string,
  childField: string,
  checkField: string,
  parent?: any,
  data?: Array<any> | null,
  disabled?: boolean,
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
});

const emits = defineEmits(["checkParent"]);

const handleCheckParent = (parent: any, children?: Array<any>) => {
  if (!parent || !Array.isArray(children) || children.length === 0) {
    return;
  }

  const size = ref(0);

  for (const i = ref(0); i.value < children.length; i.value++) {
    const child = children[i.value];

    if (child.checked === "fixed") {
      parent.checked = "fixed";

      return;
    }

    if (child.checked === "checked") {
      size.value++;
    }
  }

  if (size.value === 0) {
    parent.checked = "unchecked";
  } else if (size.value === children.length) {
    parent.checked = "checked";
  } else {
    parent.checked = "fixed";
  }
};

const setCheckFlag = (item: any, flag: "checked" | "unchecked") => {
  item.checked = flag;

  const children = item[props.childField];

  if (Array.isArray(children) && children.length > 0) {
    children.forEach((child: any) => setCheckFlag(child, flag));
  }
};

const handleCheck = (item: any) => {
  setCheckFlag(item, "checked");
};

const handleUncheck = (item: any) => {
  setCheckFlag(item, "unchecked");
};

onUpdated(() => {
  if (props.parent) {
    emits("checkParent");
  }
});
</script>

<template>
  <div class="beicroon-tree" v-for="item in data">
    <beicroon-checkbox
      :label="item[labelField]"
      :checked="item.checked"
      :disabled="disabled"
      @check="handleCheck(item)"
      @uncheck="handleUncheck(item)"
    />
    <template v-if="item[childField]">
      <beicroon-checkbox-tree
        :labelField="labelField"
        :childField="childField"
        :check-field="checkField"
        :parent="item"
        :data="item[childField]"
        :disabled="disabled"
        @checkParent="handleCheckParent(item, item[childField])"
      />
    </template>
  </div>
</template>

<style lang="less">
.beicroon-tree {
  gap: 6rem;
  display: flex;
  flex-direction: column;

  .beicroon-tree {
    padding-left: 18rem;
  }
}
</style>