<script setup lang="ts">
import {computed, ref} from "vue";

const props = defineProps({
  modelValue: {
    type: [String, Number],
    default: null,
    required: false,
  },
  type: {
    type: String,
    default: "text",
    required: false,
  },
  label: {
    type: String,
    required: true,
  },
  placeholder: {
    type: String,
    default: null,
    required: false,
  },
  required: {
    type: Boolean,
    default: false,
    required: false,
  },
});

const elInput = ref();

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get() {
    return props.modelValue;
  },
  set(val) {
    if (elInput.value) {
      elInput.value.classList.remove("invalid")
    }

    emits("update:modelValue", val);
  }
})
</script>

<template>
  <div class="el-input" :class="{required: required}" ref="elInput">
    <label>{{ label }}</label>
    <input class="input-value" :type="type" :placeholder="placeholder" v-model="value"/>
  </div>
</template>

<style lang="less">
@import "@/less/colors";

.el-input {
  gap: 8rem;
  z-index: 1;
  width: 360rem;
  display: flex;
  height: 120rem;
  position: relative;
  border-radius: 8rem;
  padding: 8rem 24rem;
  flex-direction: column;
  justify-content: center;
  transition: all 300ms linear;

  &:focus-within {
    background-color: @lighter-blue;
  }

  &.invalid {
    background-color: @lighter-red;

    &:after {
      top: 8rem;
      right: 8rem;
      color: @white;
      content: "必填";
      position: absolute;
      padding: 4rem 8rem;
      border-radius: 8rem;
      background-color: @dangerous;
    }
  }

  &.required {
    label {
      &:before {
        top: 0;
        left: 0;
        width: 0;
        height: 0;
        content: "";
        font-size: 18rem;
        font-weight: bold;
        text-align: center;
        position: absolute;
        border-top: 24rem solid @red;
        border-top-left-radius: 8rem;
        background-color: transparent;
        border-right: 24rem solid transparent;
      }

      &:after {
        width: 0;
        height: 0;
        top: -1rem;
        left: 1rem;
        content: "*";
        color: @yellow;
        font-size: 18rem;
        font-weight: bold;
        position: absolute;
      }
    }
  }

  label {
    z-index: 1;
    display: block;
    position: relative;
    width: fit-content;
    padding: 8rem 32rem;
    border-radius: 6rem;
    letter-spacing: 4rem;
    background-color: @light-grey;

    &.required:before {
      top: 0;
      left: 0;
      width: 0;
      height: 0;
      content: "";
      font-size: 18rem;
      font-weight: bold;
      text-align: center;
      position: absolute;
      border-top: 24rem solid @red;
      border-top-left-radius: 8rem;
      background-color: transparent;
      border-right: 24rem solid transparent;
    }

    &.required:after {
      width: 0;
      height: 0;
      top: -1rem;
      left: 1rem;
      content: "*";
      color: @yellow;
      font-size: 18rem;
      font-weight: bold;
      position: absolute;
    }
  }

  input {
    width: 100%;
    height: 38rem;
    display: block;
    padding: 0 18rem;
    border-radius: 6rem;
    border: 1rem solid @light-grey;

    &:focus {
      border-color: @light-blue;
    }
  }
}
</style>