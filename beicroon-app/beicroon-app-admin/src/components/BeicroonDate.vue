<script setup lang="ts">
import {computed, onMounted, ref, watch} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";

type Props = {
  label: string,
  modelValue?: any,
  required?: boolean,
  disabled?: boolean,
  placeholder?: string,
};

type Picker = {
  date: Date,
  active: boolean,
  current: boolean,
};

const days = ["日", "一", "二", "三", "四", "五", "六"];

const props = defineProps<Props>();

const emits = defineEmits(["update:modelValue"]);

const modelValue = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
})

const active = ref(false);

const clicking = ref(false);

const current = ref();
const prev = ref();
const next = ref();

const start = ref("");
const end = ref("");

onMounted(() => {
  const now = new Date();

  prev.value = new Date(now.getFullYear(), now.getMonth(), 1);
  next.value = new Date(now.getFullYear(), now.getMonth() + 1, 1);
});

const pickers = ref([] as Array<Picker>);

function getPickers(now: Date) {
  const date = new Date(now.getFullYear(), now.getMonth(), 1);

  const pickers: Array<Picker> = [];

  const month = date.getMonth();

  const prefix = date.getDay() === 0 ? 7 : date.getDay();

  date.setTime(date.getTime() - prefix * 24 * 60 * 60 * 1000);

  for (let i = 1; i <= 42; i++) {
    const pickerDate = new Date(date);

    pickers.push({
      date: pickerDate,
      active: pickerDate.getMonth() === month && pickerDate.getTime() > prev.value.getTime(),
      current: pickerDate.getFullYear() === now.getFullYear()
        && pickerDate.getMonth() === now.getMonth()
        && pickerDate.getDate() === now.getDate(),
    });

    date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
  }

  return pickers;
}

async function handleFocusin(date: Date, handler: (picker: Picker) => Promise<void>) {
  current.value = date;

  pickers.value = getPickers(date);

  clickHandler.value = handler;

  active.value = true;

  document.addEventListener("click", handleFocusout, {once: true});
}

async function handleFocusout() {
  if (clicking.value) {
    return;
  }

  document.removeEventListener("click", handleFocusout);

  active.value = false;
}

async function handleMouseDown() {
  clicking.value = true;

  document.addEventListener("mouseup", handleMouseUp, {once: true});
}

async function handleMouseUp() {
  clicking.value = false;

  document.removeEventListener("mouseup", handleMouseUp);
}

function getFormatter(num: number) {
  return num < 10 ? "0" + num : `${num}`;
}

function pushFormatter(now: Date, formatter: Array<string>) {
  formatter.push(getFormatter(now.getFullYear()))
  formatter.push("-")
  formatter.push(getFormatter(now.getMonth() + 1));
  formatter.push("-")
  formatter.push(getFormatter(now.getDate()));
}

const clickHandler = ref(null as null | ((picker: Picker) => Promise<void>));

async function handlePrevClick(picker: Picker) {
  prev.value = picker.date;

  const res = [] as Array<string>;

  pushFormatter(picker.date, res);

  start.value = res.join("");
}

async function handleNextClick(picker: Picker) {
  next.value = picker.date;

  const res = [] as Array<string>;

  pushFormatter(picker.date, res);

  end.value = res.join("");
}

async function handleClick(picker: Picker) {
  if (!picker.active) {
    return;
  }

  clickHandler.value && await clickHandler.value(picker);

  if (start && end) {
    emits("update:modelValue", `${start.value}~${end.value}`);
  }

  await handleFocusout();
}

function subMonth() {
  current.value = new Date(current.value.getFullYear(), current.value.getMonth() - 1, current.value.getDate());
}

function addMonth() {
  current.value = new Date(current.value.getFullYear(), current.value.getMonth() + 1, current.value.getDate());
}

function subYear() {
  current.value = new Date(current.value.getFullYear() - 1, current.value.getMonth(), current.value.getDate());
}

function addYear() {
  current.value = new Date(current.value.getFullYear() + 1, current.value.getMonth(), current.value.getDate());
}

watch(current, () => {
  pickers.value = getPickers(current.value);
});
</script>

<template>
  <div class="beicroon-input beicroon-datetime" :class="{required: required, active: active}" @click.stop>
    <span class="beicroon-input-label">{{ label }}</span>
    <input class="beicroon-input-area" type="text" :disabled="disabled" :placeholder="placeholder" v-model="modelValue"/>
    <div class="beicroon-datetime-area">
      <input class="start" type="text"
             :disabled="disabled"
             :placeholder="placeholder"
             v-model="start"
             @focusin="handleFocusin(prev, handlePrevClick)"
             @focusout="handleFocusout"
      />
      <span>~</span>
      <input class="end" type="text"
             :disabled="disabled"
             :placeholder="placeholder"
             v-model="end"
             @focusin="handleFocusin(next, handleNextClick)"
             @focusout="handleFocusout"
      />
    </div>
    <div class="beicroon-datetime-view" v-if="active" @mousedown="handleMouseDown" @mouseup="handleMouseUp">
      <div class="beicroon-month-picker">
        <beicroon-button label="<<" @click="subYear"></beicroon-button>
        <beicroon-button label="<" @click="subMonth"></beicroon-button>
        <div class="title">
          <span>{{ current.getFullYear() }}年</span>
          <span>{{ current.getMonth() + 1 }}月</span>
        </div>
        <beicroon-button label=">" @click="addMonth"></beicroon-button>
        <beicroon-button label=">>" @click="addYear"></beicroon-button>
      </div>
      <ul class="beicroon-date-picker">
        <li v-for="day in days" class="day">{{ day }}</li>
        <li v-for="picker in pickers"
            :class="{active: picker.active, current: picker.current}"
            @click="handleClick(picker)"
        >{{ picker.date.getDate() }}
        </li>
      </ul>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-datetime {
  &.active {
    .beicroon-datetime-area {
      border-color: var(--color-primary);
    }
  }

  .beicroon-input-area {
    opacity: 0;
  }

  .beicroon-datetime-area {
    left: 18rem;
    bottom: 14rem;
    width: 300rem;
    height: 32rem;
    display: flex;
    position: absolute;
    border-radius: 6rem;
    align-items: center;
    justify-content: space-between;
    border: 1rem solid var(--color-grey-deeper);

    input {
      padding: 0;
      height: 100%;
      border: none;
      outline: none;
      background: none;
      text-align: center;
    }
  }

  .beicroon-datetime-view {
    left: 50%;
    z-index: 1;
    cursor: text;
    padding: 8rem;
    width: 300rem;
    user-select: none;
    position: absolute;
    border-radius: 6rem;
    top: calc(100% - 8rem);
    transform: translateX(-50%);
    background-color: var(--color-white);
    box-shadow: 0 0 8rem -3rem var(--color-black-30) inset;
  }

  .beicroon-month-picker {
    display: flex;
    padding: 6rem 0;
    font-size: 16rem;
    align-items: center;
    justify-content: space-around;

    .title {
      gap: 6rem;
      display: flex;
    }
  }

  .beicroon-date-picker {
    gap: 2rem;
    display: grid;
    grid-template-columns: repeat(7, 1fr);

    li {
      height: 32rem;
      display: flex;
      border-radius: 6rem;
      align-items: center;
      justify-content: center;
      color: var(--color-grey-deeper);

      &.day {
        color: var(--color-black-light);
      }

      &.active {
        cursor: pointer;
        color: var(--color-black-light);
      }

      &.current {
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }
}
</style>