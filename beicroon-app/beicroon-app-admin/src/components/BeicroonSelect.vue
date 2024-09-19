<script setup lang="ts">
import {Select} from "@/select.ts";
import {computed, ref, watch} from "vue";
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

const keywords = ref(null);

watch(keywords, (val: any) => {
  clearTimeout(timer.value);

  timer.value = setTimeout(() => props.select.search(val), 300);
});

const loadMoreLabel = computed(() => {
  if (!props.select.request) {
    return "~ 到底了 ~";
  }

  if (props.select.noMore) {
    return props.select.moreOptions.length > 0 ? "! 没有更多了 !" : "~ 暂无数据 ~";
  }

  return "! 加载更多 !";
});

const active = ref(false);

const clicking = ref(false);

async function handleFocusin() {
  active.value = true;

  await props.select.show();
  await props.select.load();

  document.addEventListener("click", handleFocusout, {once: true});
}

async function handleFocusout() {
  if (clicking.value) {
    return;
  }

  document.removeEventListener("click", handleFocusout);

  active.value = false;

  await props.select.hide();
}

async function handleMouseDown() {
  clicking.value = true;
}

async function handleMouseUp() {
  clicking.value = false;
}

async function handleClick(option: any) {
  emits("update:showValue", props.select.getLabel(option));
  emits("update:modelValue", props.select.getValue(option));

  await handleFocusout();
}

async function handleClear() {
  emits("update:showValue", null);
  emits("update:modelValue", null);
}
</script>

<template>
  <div class="beicroon-input beicroon-select" :class="{required: required, active: active}" @click.stop>
    <span class="beicroon-input-label">{{ label }}</span>
    <input class="beicroon-input-area" type="text"
           :disabled="disabled"
           :placeholder="placeholder"
           v-model="keywords"
           @focusin="handleFocusin"
           @focusout="handleFocusout"
    />
    <div class="beicroon-select-label"><span>{{ showValue || placeholder }}</span></div>
    <beicroon-button
      class="beicroon-select-clear"
      :class="{hidden: !active}"
      label="x"
      @click="handleClear"
      @mousedown="handleMouseDown"
      @mouseup="handleMouseUp"
    ></beicroon-button>
    <ul class="select" :class="{hidden: select.hidden}">
      <li class="option" v-for="option in select.options"
          @click="handleClick(option)"
          @mousedown="handleMouseDown"
          @mouseup="handleMouseUp"
      >
        {{ select.getLabel(option) }}
      </li>
      <template v-for="options in select.moreOptions">
        <li class="option" v-for="option in options"
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

    .beicroon-select-label {
      top: 0;
      transform: translateY(-28rem);
    }

    .beicroon-select-clear {
      top: 0;
      transform: translateY(-26rem);
    }

    .beicroon-input-label {
      color: var(--color-white);
      background-color: var(--color-primary-deeper);
    }

    .beicroon-input-area {
      opacity: 1;
      border-color: var(--color-primary);
    }
  }

  &.column {
    .beicroon-select-label {
      top: 0;
      right: 18rem;
      transform: translateY(58rem) !important;
    }

    .beicroon-select-clear {
      top: 0;
      right: 36rem;
      transform: translateY(62rem) !important;
    }

    .select {
      right: 18rem;
      top: calc(100% - 12rem);
    }

    &.active {
      .beicroon-select-label {
        top: -34rem;
      }

      .beicroon-select-clear {
        top: -34rem;
      }
    }
  }

  .beicroon-select-label {
    top: 50%;
    z-index: 1;
    right: 6rem;
    width: 300rem;
    height: 32rem;
    display: flex;
    position: absolute;
    align-items: center;
    border-radius: 6rem;
    padding: 8rem 18rem;
    transform: translateY(-50%);
    transition: all 130ms linear;
    justify-content: space-between;
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-deeper);
  }

  .beicroon-select-clear {
    top: 50%;
    z-index: 3;
    right: 24rem;
    position: absolute;
    align-items: center;
    justify-content: center;
    width: 24rem !important;
    height: 24rem !important;
    transform: translateY(-50%);
    transition: all 130ms linear;
  }

  .beicroon-input-area {
    opacity: 0;
    z-index: 2;
    position: relative;
  }

  .select {
    gap: 2rem;
    right: 6rem;
    z-index: 666;
    display: flex;
    height: 160rem;
    padding: 8rem 0;
    overflow-y: auto;
    position: absolute;
    border-radius: 6rem;
    top: calc(100% - 4rem);
    flex-direction: column;
    width: 300rem !important;
    transition: all 130ms linear;
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-deeper);

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