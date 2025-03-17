<script setup lang="ts">
import {computed, ref} from "vue";
import Search from "@/svg/Search.vue";

interface Props {
  modelValue?: any,
  size?: "small" | "normal" | "large" | "huge",
  type?: "text" | "number" | "password",
  label?: string,
  placeholder?: string,
  search?: boolean,
  disabled?: boolean,
  required?: boolean,
}

const props = withDefaults(defineProps<Props>(), {
  size: "normal",
  type: "text",
  search: false,
});

const emits = defineEmits(["update:modelValue", "search"]);

const input = ref<HTMLInputElement>();

const value = computed({
  get: () => props.modelValue,
  set: (val: any) => {
    if (props.required && input.value) {
      input.value.classList.remove("error");
    }

    if (val && typeof val === "string") {
      val = val.trim();
    }

    emits("update:modelValue", val);
  },
});

const clazz = computed(() => {
  const res = [];

  if (props.size) {
    res.push(props.size);
  }

  if (props.search) {
    res.push("search");
  }

  if (!props.label) {
    res.push("single");
  }

  if (props.required) {
    res.push("required");
  }

  return res;
});

const handleSearch = () => emits("search", value.value);
</script>

<template>
  <div class="beicroon-input input" :class="clazz" ref="input">
    <label class="label" v-if="label">{{ label }}</label>
    <input class="input" :type="type" v-model="value" :placeholder="placeholder" :disabled="disabled" />
    <search class="search" v-if="search" @click="handleSearch" />
  </div>
</template>

<style lang="less">
.beicroon-input.input {
  height: 32rem;
  position: relative;
  border-radius: 4rem;
  display: inline-flex;
  border: 1rem solid var(--color-grey-light);

  &:focus-within {
    border-color: var(--color-primary);

    .label {
      border-color: var(--color-primary);
    }
  }

  &.required {
    .label:before {
      top: -2rem;
      left: 1rem;
      content: "*";
      line-height: 1;
      font-size: 16rem;
      position: absolute;
      color: var(--color-danger);
    }
  }

  &.single {
    width: 210rem;

    .input {
      border-top-left-radius: 4rem;
      border-bottom-left-radius: 4rem;
    }
  }

  &.error {
    border-color: var(--color-danger) !important;

    .label {
      border-color: var(--color-danger) !important;
    }
  }

  &.small {
    width: 210rem;
  }

  &.normal {
    width: 340rem;
  }

  &.large {
    width: 704rem;
  }

  &.huge {
    width: 1068rem;
  }

  .label {
    height: 100%;
    cursor: text;
    width: 108rem;
    display: flex;
    position: relative;
    align-items: center;
    letter-spacing: 2rem;
    justify-content: center;
    border-top-left-radius: 4rem;
    border-bottom-left-radius: 4rem;
    background-color: var(--color-white-light);
    border-right: 1rem solid var(--color-grey-light);
  }

  .input {
    flex: 1;
    height: 100%;
    border: none;
    outline: none;
    padding: 0 12rem;
    border-top-right-radius: 4rem;
    border-bottom-right-radius: 4rem;

    &:disabled {
      cursor: text;
      background-color: var(--color-grey-lighter);
    }
  }

  .search {
    top: 50%;
    right: 0;
    width: 24rem;
    height: 24rem;
    display: flex;
    cursor: pointer;
    position: absolute;
    border-radius: 8rem;
    align-items: center;
    justify-content: center;
    transform: translateY(-50%);
  }
}
</style>