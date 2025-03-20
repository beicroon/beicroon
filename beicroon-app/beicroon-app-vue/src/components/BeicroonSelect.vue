<script setup lang="ts">
import {computed, onMounted, ref, watch} from "vue";

interface Option {
  label: string,
  value: any,
}

interface Props {
  modelValue?: any,
  size?: "small" | "normal" | "large" | "huge",
  label?: string,
  placeholder?: string,
  disabled?: boolean,
  required?: boolean,
  options: Array<Option>,
}

const props = withDefaults(defineProps<Props>(), {
  size: "normal",
});

const emits = defineEmits(["update:modelValue"]);

const inputEl = ref<HTMLInputElement>();

const showValue = ref("");

const value = computed({
  get: () => props.modelValue,
  set: (val: any) => {
    if (inputEl.value) {
      inputEl.value.classList.remove("error");
    }

    emits("update:modelValue", val);
  },
});

const clazz = computed(() => {
  const res = [];

  if (props.size) {
    res.push(props.size);
  }

  if (!props.label) {
    res.push("single");
  }

  if (props.required) {
    res.push("required");
  }

  return res;
});

const setShowValue = (val: any) => {
  if (val && Array.isArray(props.options) && props.options.length > 0) {
    const option = props.options.find((option) => option.value === val);

    if (option) {
      showValue.value = option.label;
    }
  }
};

onMounted(() => setShowValue(value.value));

watch(value, (val: any) => setShowValue(val));
watch(() => props.options, () => setShowValue(value.value));

const handleChoose = (option: Option) => {
  value.value = option.value;

  hideOption();
};

const topOrBottom = ref<"top" | "bottom">("bottom");

const optionActive = ref(false);

const hidden = ref(true);

const handleMouseDown = () => {
  optionActive.value = true;

  document.addEventListener("mousedown", hideOption);
};

const handleFocusin = () => {
  hidden.value = false;

  const rect = inputEl.value?.getBoundingClientRect();

  if (rect && rect.top + rect.height + 180 > window.innerHeight) {
    topOrBottom.value = "top";
  } else {
    topOrBottom.value = "bottom";
  }
};

const handleFocusout = () => {
  if (optionActive.value) {
    return;
  }

  hideOption();
};

const hideOption = () => {
  if (optionActive.value) {
    document.removeEventListener("mousedown", hideOption);
  }

  optionActive.value = false;

  hidden.value = true;
};
</script>

<template>
  <div class="beicroon-input input select" :class="clazz" ref="inputEl" @mousedown.stop>
    <label class="label" v-if="label">{{ label }}</label>
    <input
      class="input"
      type="text"
      v-model="showValue"
      :placeholder="placeholder"
      :disabled="disabled"
      @focusin="handleFocusin"
      @focusout="handleFocusout"
    />
    <div class="option" :class="topOrBottom" v-show="!hidden" @mousedown="handleMouseDown">
      <div class="tip">请选择</div>
      <ul class="chooser">
        <li v-for="option in options" @click="handleChoose(option)">{{ option.label }}</li>
      </ul>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-input.select {
  .option {
    right: 50%;
    z-index: 9;
    display: flex;
    height: 180rem;
    position: absolute;
    border-radius: 4rem;
    width: calc(100% + 2rem);
    transform: translateX(50%);
    border: 1rem solid var(--color-primary);

    &.top {
      bottom: calc(100% + 2rem);
    }

    &.bottom {
      top: calc(100% + 2rem);
    }

    .tip {
      width: 108rem;
      display: flex;
      text-align: right;
      align-items: center;
      justify-content: center;
      border-top-left-radius: 4rem;
      border-bottom-left-radius: 4rem;
      background-color: var(--color-white-light);
      border-right: 1rem solid var(--color-grey-light);
    }

    .chooser {
      flex: 1;
      gap: 8rem;
      height: 100%;
      display: flex;
      padding: 8rem 0;
      overflow-y: auto;
      flex-direction: column;
      border-top-right-radius: 4rem;
      border-bottom-right-radius: 4rem;
      background-color: var(--color-white);

      li {
        cursor: pointer;
        padding: 6rem 8rem;
        background-color: var(--color-grey-lighter);

        &:hover {
          color: var(--color-white);
          background-color: var(--color-primary);
        }
      }
    }
  }
}
</style>