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
  disabled: {
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
  <div class="form-input" :class="{required: required}" ref="elInput">
    <label><slot></slot></label>
    <input :disabled="disabled" class="input-value" :type="type" :placeholder="placeholder" v-model="value"/>
  </div>
</template>

<style lang="less">
@import "@/less/colors";

.form-input {
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
    width: 50%;
    cursor: text;
    height: 36rem;
    display: flex;
    position: relative;
    align-items: center;
    border-radius: 6rem;
    letter-spacing: 4rem;
    justify-content: center;
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

    i {
      font-style: normal;
    }
  }

  input {
    width: 100%;
    cursor: text;
    height: 38rem;
    display: block;
    padding: 0 18rem;
    border-radius: 6rem;
    border: 1rem solid @light-grey;

    &::placeholder {
      color: @deep-grey;
    }

    &:focus {
      border-color: @light-blue;
    }

    &[disabled] {
      cursor: text;
    }
  }

  &.row {
    padding: 0;
    height: auto;
    flex-direction: row;

    label {
      flex: 1;
    }

    input {
      width: 260rem;
    }
  }
}
</style>