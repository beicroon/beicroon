<script setup lang="ts">
import {computed} from "vue";

const props = defineProps({
  modelValue: {
    type: [String, Number],
    default: null,
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

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get() {
    return props.modelValue;
  },
  set(val) {
    emits("update:modelValue", val);
  }
})
</script>

<template>
  <div class="el-input">
    <label :class="{required: required}">{{label}}</label>
    <input type="text" :placeholder="placeholder" v-model="value" />
  </div>
</template>

<style lang="less">
@import "@/less/colors";

.el-input {
  gap: 8rem;
  height: 80rem;
  width: 340rem;
  display: flex;
  flex-direction: column;
  justify-content: center;

  label {
    display: block;
    width: fit-content;
    padding: 8rem 32rem;
    border-radius: 6rem;
    letter-spacing: 4rem;
    background-color: @light-grey;
  }

  input {
    width: 100%;
    height: 38rem;
    display: block;
    padding: 0 18rem;
    border-radius: 6rem;
    border: 1rem solid @primary;
  }
}
</style>