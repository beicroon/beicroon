<script setup lang="ts">
import {computed} from "vue";

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
  <div class="el-search">
    <label><slot></slot></label>
    <input type="text" :placeholder="placeholder" v-model="value"/>
  </div>
</template>

<style lang="less">
@import "@/less/colors";

.el-search {
  display: flex;
  width: 360rem;
  align-items: center;
  justify-content: space-between;

  label {
    flex: 1;
    display: flex;
    padding: 0 8rem;
    align-items: center;
    justify-content: space-between;
  }

  i {
    font-style: normal;
  }

  input {
    width: 280rem;
    display: block;
    padding: 4rem 8rem;
    border-radius: 8rem;
    border: 1rem solid @grey;

    &:focus {
      border-color: @blue;
    }

    &::placeholder {
      color: @grey;
    }
  }
}
</style>