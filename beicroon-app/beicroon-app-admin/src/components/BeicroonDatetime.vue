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

const hours = [
  "0", "01", "02", "03", "04", "05",
  "06", "07", "08", "09", "10", "11",
  "12", "13", "14", "15", "16", "17",
  "18", "19", "20", "21", "22", "23"
] as const;

const minutesAndSeconds = [
  "00", "01", "02", "03", "04", "05",
  "06", "07", "08", "09", "10", "11",
  "12", "13", "14", "15", "16", "17",
  "18", "19", "20", "21", "22", "23",
  "24", "25", "26", "27", "28", "29",
  "30", "31", "32", "33", "34", "35",
  "36", "37", "38", "39", "40", "41",
  "42", "43", "44", "45", "46", "47",
  "48", "49", "50", "51", "52", "53",
  "54", "55", "56", "57", "58", "59"
] as const;

type Hours = typeof hours[number];

type MinuteAndSecond = typeof minutesAndSeconds[number];

type Time = {
  hour: Hours,
  minute: MinuteAndSecond,
  second: MinuteAndSecond,
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

const currentTime = ref({});
const startTime = ref(null);
const endTime = ref(null);

onMounted(init);

const currentPicker = ref(undefined as Picker);
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
      active: pickerDate.getMonth() === month,
      current: pickerDate.getFullYear() === now.getFullYear()
        && pickerDate.getMonth() === now.getMonth()
        && pickerDate.getDate() === now.getDate(),
    });

    date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
  }

  return pickers;
}

async function handleFocusin(date: Date, time: Time, handler: (picker: Picker) => Promise<void>) {
  current.value = date;

  currentTime.value = time;

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

function pushFormatter(now: Date, time: Time, formatter: Array<string>) {
  formatter.push(getFormatter(now.getFullYear()))
  formatter.push("-")
  formatter.push(getFormatter(now.getMonth() + 1));
  formatter.push("-")
  formatter.push(getFormatter(now.getDate()));
  formatter.push(" ")
  formatter.push(time.hour);
  formatter.push(":")
  formatter.push(time.minute);
  formatter.push(":")
  formatter.push(time.second);
}

const clickHandler = ref(null as null | ((picker: Picker) => Promise<void>));

async function handlePrevClick(picker: Picker) {
  current.value = picker.date;

  prev.value = picker.date;

  const res = [] as Array<string>;

  pushFormatter(picker.date, currentTime.value, res);

  start.value = res.join("");
}

async function handleNextClick(picker: Picker) {
  current.value = picker.date;

  next.value = picker.date;

  const res = [] as Array<string>;

  pushFormatter(picker.date, currentTime.value, res);

  end.value = res.join("");
}

async function handleClick(picker: Picker) {
  currentPicker.value = picker;

  current.value = picker.date;
}

async function handleClear() {
  await init();

  emits("update:modelValue", null);
}

async function handleConfirm() {
  clickHandler.value && await clickHandler.value(currentPicker.value);

  if (start && end) {
    emits("update:modelValue", `${start.value}~${end.value}`);
  }

  await handleFocusout();
}

async function init() {
  const now = new Date();

  prev.value = new Date(now.getFullYear(), now.getMonth(), 1);
  next.value = new Date(now.getFullYear(), now.getMonth() + 1, 1);

  start.value = "";
  end.value = "";

  startTime.value = {
    hour: "00",
    minute: "00",
    second: "00",
  };

  endTime.value = {
    hour: "23",
    minute: "59",
    second: "59",
  };
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
    <input class="beicroon-input-area" type="text" :disabled="disabled" :placeholder="placeholder"
           v-model="modelValue"/>
    <div class="beicroon-datetime-area">
      <input class="start" type="text"
             :disabled="disabled"
             :placeholder="placeholder"
             v-model="start"
             @focusin="handleFocusin(prev, startTime, handlePrevClick)"
             @focusout="handleFocusout"
      />
      <span>~</span>
      <input class="end" type="text"
             :disabled="disabled"
             :placeholder="placeholder"
             v-model="end"
             @focusin="handleFocusin(next, endTime, handleNextClick)"
             @focusout="handleFocusout"
      />
    </div>
    <div class="beicroon-datetime-view" :class="{hidden: !active}" @mousedown="handleMouseDown"
         @mouseup="handleMouseUp">
      <div class="beicroon-month-picker">
        <beicroon-button label="<<" @click="subYear"></beicroon-button>
        <beicroon-button label="<" @click="subMonth"></beicroon-button>
        <div class="title">
          <span>{{ current && current.getFullYear() }}年</span>
          <span>{{ current && current.getMonth() + 1 }}月</span>
        </div>
        <beicroon-button label=">" @click="addMonth"></beicroon-button>
        <beicroon-button label=">>" @click="addYear"></beicroon-button>
      </div>
      <ul class="beicroon-date-picker">
        <li v-for="day in days" class="day">{{ day }}</li>
        <li v-for="picker in pickers"
            :class="{active: picker.active, current: picker.current}"
            @click.stop="handleClick(picker)"
        >{{ picker.date.getDate() }}
        </li>
      </ul>
      <div class="beicroon-datetime-foot">
        <ul class="beicroon-time-picker">
          <li><input type="text" v-model="currentTime.hour"/></li>
          <li><span>:</span></li>
          <li><input type="text" v-model="currentTime.minute"/></li>
          <li><span>:</span></li>
          <li><input type="text" v-model="currentTime.second"/></li>
        </ul>
        <ul class="beicroon-datetime-button">
          <beicroon-button class="warning" label="清空" @click="handleClear"></beicroon-button>
          <beicroon-button class="primary" label="确认" @click="handleConfirm"></beicroon-button>
        </ul>
      </div>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-datetime {
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
      border: none;
      outline: none;
      background: none;
      text-align: center;
    }
  }

  .beicroon-datetime-view {
    left: 50%;
    z-index: 1;
    opacity: 1;
    cursor: text;
    padding: 8rem;
    height: 320rem;
    overflow: hidden;
    user-select: none;
    position: absolute;
    border-radius: 6rem;
    top: calc(100% - 8rem);
    width: 300rem !important;
    transform: translateX(-50%);
    transition: all 180ms linear;
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

  .beicroon-datetime-foot {
    display: flex;
    padding: 4rem;
    align-items: center;
    justify-content: space-between;
  }

  .beicroon-time-picker {
    gap: 4rem;
    display: flex;

    input {
      width: 32rem;
      height: 24rem;
      outline: none;
      font-size: 12rem;
      background: none;
      line-height: 16rem;
      text-align: center;
      border-radius: 4rem;
      border: 1rem solid var(--color-grey-deeper);
    }
  }

  .beicroon-datetime-button {
    display: flex;
  }
}
</style>