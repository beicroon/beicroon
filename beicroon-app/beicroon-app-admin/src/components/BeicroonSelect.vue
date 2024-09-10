<script setup lang="ts">
import {escToggle} from "@/event.ts";
import {computed, reactive, ref} from "vue";
import {BaseVO, QueryDTO, Response} from "@/http.ts";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type T = Record<string, any> & QueryDTO;

type R = Record<string, any> & BaseVO;

type Props = {
  label: string,
  placeholder?: string,
  required?: boolean,
  disabled?: boolean,
  showValue?: string
  modelValue?: string
  request?: (data: T) => Promise<Response<Array<R>>>,
  options?: Array<R>,
  fieldKey?: keyof R,
  fieldLabel?: keyof R,
  pageSize?: number,
};

const props = withDefaults(defineProps<Props>(), {
  fieldKey: "id",
  fieldLabel: "name",
  pageSize: 30,
});

const emits = defineEmits(["update:modelValue"]);

const pageNum = ref(1);

const items = ref<Array<Array<R>>>([]);

const noMore = ref(true);

const params = reactive<T>({
  pageSize: props.pageSize,
});

const showValue = ref(props.showValue);

const loading = ref(true);

const timer = ref<any>(0);

const value = computed({
  get: () => showValue.value,
  set: async (val: string | undefined) => {
    showValue.value = val;

    await handleSearch();
  },
})

async function handleSearch() {
  clearTimeout(timer.value);

  timer.value = setTimeout(handleRequest, 300);
}

async function handleRequest() {
  if (!props.request) {
    return;
  }

  params.pageNum = pageNum.value;
  params.keywords = showValue.value;

  loading.value = true;

  const res = await props.request(params).finally(() => loading.value = false);

  items.value[pageNum.value - 1] = res.data;

  noMore.value = res.data.length < res.page.size;
}

const visible = ref(false);

const hidden = ref(true);

async function handleFocusin() {
  hidden.value = false;

  if (items.value.length <= 0) {
    await handleRequest();
  }
}

async function handleFocusout() {
  if (visible.value) {
    return;
  }

  hidden.value = true;
}

async function handleMousedown() {
  if (visible.value) {
    await escToggle(false);
  }

  visible.value = true;

  await escToggle(async () => {
    hidden.value = true;
    visible.value = false;
  });
}

async function handleClick(item: R) {
  hidden.value = true;
  visible.value = false;

  showValue.value = item[props.fieldLabel];

  emits("update:modelValue", item[props.fieldKey]);
}

const select = ref();

async function handleScrollEnd() {
  if (loading.value) {
    return;
  }

  if (noMore.value || select.value.scrollHeight - select.value.scrollTop - select.value.clientHeight > 0) {
    return;
  }

  pageNum.value++;

  await handleRequest();
}

function isLoading() {
  if (!props.request) {
    return true;
  }

  if (hidden.value) {
    return true;
  }

  return !loading.value;
}
</script>

<template>
  <div class="beicroon-input beicroon-select" :class="{required: required}" @focusin="handleFocusin"
       @focusout="handleFocusout" @click.stop>
    <span class="input-label">{{ label }}</span>
    <input class="input-area" :disabled="disabled" type="text" :placeholder="placeholder" v-model="value"/>
    <div class="loading" :class="{hidden: isLoading()}">
      <beicroon-loading fill="#b3e5fc" width="60" height="60"></beicroon-loading>
    </div>
    <ul class="select" :class="{hidden: hidden}" @mousedown="handleMousedown" @scrollend="handleScrollEnd" ref="select">
      <li class="option" v-for="option in options" @click="handleClick(option)">
        {{ option[fieldLabel] }}
      </li>
      <template v-for="options in items">
        <li class="option" v-for="option in options" @click="handleClick(option)">
          {{ option[fieldLabel] }}
        </li>
      </template>
    </ul>
  </div>
</template>

<style lang="less">
.beicroon-select {
  z-index: 666;
  position: relative;

  &.column {
    .select, .loading {
      right: 18rem;
      width: 300rem;
      top: calc(100% - 12rem);
    }
  }

  .loading, .select {
    right: 6rem;
    z-index: 777;
    display: flex;
    width: 240rem;
    height: 160rem;
    position: absolute;
    border-radius: 6rem;
    align-items: center;
    top: calc(100% - 4rem);
    justify-content: center;
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);
  }

  .select {
    gap: 2rem;
    z-index: 666;
    padding: 8rem 0;
    overflow-y: auto;
    flex-direction: column;

    .option {
      width: 100%;
      display: flex;
      height: 34rem;
      flex-shrink: 0;
      cursor: pointer;
      padding: 0 18rem;
      align-items: center;

      &:hover {
        background-color: var(--color-primary-light);
      }
    }
  }
}
</style>