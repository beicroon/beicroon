<script setup lang="ts">
import {Select} from "@/select.ts";
import {escToggle} from "@/event.ts";
import {computed, reactive, ref} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  select: Select<any, any>,
  label: string,
  showValue?: any,
  modelValue?: any,
  required?: boolean,
  disabled?: boolean,
  placeholder?: string,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:showValue", "update:modelValue"]);

const timer = ref();

const showValue = computed({
  get: () => props.showValue,
  set: async (val?: any) => {
    emits("update:showValue", val);

    clearTimeout(timer.value);

    timer.value = setTimeout(() => props.select.search(val), 300);
  },
});

const loadMoreLabel = computed(() => props.select.noMore ? "没有更多了!" : "加载更多");

const active = ref(false);

const clicking = reactive({
  mouseDown: false,
  mouseUp: false,
});

async function handleFocusin() {
  active.value = true;

  props.select.show();
  props.select.load();

  await escToggle(handleFocusout);
}

async function handleFocusout() {
  clicking.mouseDown = false;
  clicking.mouseUp = false;

  props.select.hide();
}

async function handleMouseDown() {
  clicking.mouseDown = true;
}

async function handleMouseUp() {
  clicking.mouseUp = true;
}

async function handleClick(option: any) {
  if (clicking.mouseDown && clicking.mouseUp) {
    emits("update:showValue", props.select.getLabel(option));
    emits("update:modelValue", props.select.getValue(option));
  }

  await escToggle(false);

  await handleFocusout();
}
</script>

<template>
  <div class="beicroon-input beicroon-select"
       :class="{required: required, active: active}"
       @click.stop
  >
    <span class="beicroon-input-label">{{ label }}</span>
    <input class="beicroon-input-area"
           type="text"
           :disabled="disabled"
           :placeholder="placeholder"
           v-model="showValue"
           @focusin="handleFocusin"
    />
    <ul class="select" :class="{hidden: select.hidden}" @click.stop>
      <li class="option"
          v-for="option in select.options"
          @click="handleClick(option)"
          @mousedown="handleMouseDown"
          @mouseup="handleMouseUp"
      >
        {{ select.getLabel(option) }}
      </li>
      <template v-for="options in select.moreOptions">
        <li class="option"
            v-for="option in options"
            @click="handleClick(option)"
            @mousedown="handleMouseDown"
            @mouseup="handleMouseUp"
        >
          {{ select.getLabel(option) }}
        </li>
      </template>
      <li class="option loading">
        <beicroon-loading
          :class="{hidden: !select.loading}"
          fill="#b3e5fc"
          width="38"
          height="38"
        ></beicroon-loading>
        <beicroon-button
          :class="{hidden: select.loading}"
          :label="loadMoreLabel"
          @click="props.select.loadMore"
        ></beicroon-button>
      </li>
    </ul>
  </div>
</template>

<style lang="less">
.beicroon-select {
  z-index: 666;
  position: relative;

  &.active {
    background-color: var(--color-primary-lighter);

    .beicroon-input-label {
      color: var(--color-white);
      background-color: var(--color-primary-deeper);
    }

    .beicroon-input-area {
      border-color: var(--color-primary);
    }
  }

  &.column {
    .select {
      right: 18rem;
      top: calc(100% - 12rem);
    }
  }

  .select {
    gap: 2rem;
    right: 6rem;
    z-index: 666;
    width: 300rem;
    display: flex;
    height: 160rem;
    padding: 8rem 0;
    overflow-y: auto;
    position: absolute;
    border-radius: 6rem;
    top: calc(100% - 4rem);
    flex-direction: column;
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);

    .option {
      width: 100%;
      display: flex;
      height: 34rem;
      flex-shrink: 0;
      cursor: pointer;
      padding: 0 18rem;
      overflow: hidden;
      align-items: center;

      &:hover {
        background-color: var(--color-primary-light);
      }
    }

    .loading {
      cursor: auto;
      justify-content: center;

      &:hover {
        background-color: var(--color-white);
      }
    }
  }
}
</style>