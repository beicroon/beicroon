<script setup lang="ts">
import {computed, onMounted, reactive, ref, watch} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import {BeicroonTime, Hour, Minute, Second} from "@/utils/datetime.utils.ts";

type Props = {
  label: string,
  modelValue?: any,
  required?: boolean,
  disabled?: boolean,
  placeholder?: string,
  noTime?: boolean,
  noEnd?: boolean,
};

type Picker = {
  date: Date,
  active: boolean,
  current: boolean,
};

const weeks = ["日", "一", "二", "三", "四", "五", "六"];

const props = withDefaults(defineProps<Props>(), {
  noTime: false,
  noEnd: false,
});

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
})

watch(value, initValue);

function initValue() {
  if (value.value) {
    const [start, end] = value.value.split("~");

    startValue.value = start;

    endValue.value = end;
  } else {
    startValue.value = null;

    endValue.value = null;
  }
}

const startValue = ref();
const endValue = ref();

onMounted(initValue)

const active = ref(false);
const clicking = ref(false);

const current = ref();
const currentTime = reactive({} as BeicroonTime);

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

const className = ref("");

async function handlePrevFocusin() {
  className.value = "prev";

  if (startValue.value) {
    const date = new Date(startValue.value);

    setCurrent(date);
  } else {
    current.value = new Date();

    currentTime.hour = "00"
    currentTime.minute = "00"
    currentTime.second = "00"
  }

  if (props.noEnd) {
    clickHandler.value = handleNoEndClick;
  } else {
    clickHandler.value = handlePrevClick;
  }

  active.value = true;

  document.addEventListener("click", handleFocusout, {once: true});
}

function setCurrent(date: Date) {
  current.value = date;

  currentTime.hour = getFormatter(date.getHours()) as Hour;
  currentTime.minute = getFormatter(date.getMinutes()) as Minute;
  currentTime.second = getFormatter(date.getSeconds()) as Second;
}

async function handleNextFocusin() {
  className.value = "next";

  if (endValue.value) {
    const date = new Date(endValue.value);

    setCurrent(date);
  } else {
    current.value = new Date();

    currentTime.hour = "23"
    currentTime.minute = "59"
    currentTime.second = "59"
  }

  clickHandler.value = handleNextClick;

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

function pushFormatterDate(now: Date, formatter: Array<string>) {
  formatter.push(getFormatter(now.getFullYear()))
  formatter.push("-")
  formatter.push(getFormatter(now.getMonth() + 1));
  formatter.push("-")
  formatter.push(getFormatter(now.getDate()));
}

function pushFormatterTime(time: BeicroonTime, formatter: Array<string>) {
  formatter.push(" ");
  formatter.push(time.hour);
  formatter.push(":");
  formatter.push(time.minute);
  formatter.push(":");
  formatter.push(time.second);
}

const clickHandler = ref(null as null | (() => Promise<void>));

async function handleNoEndClick() {
  const res = [] as Array<string>;

  pushFormatterDate(current.value, res);

  if (!props.noTime) {
    pushFormatterTime(currentTime, res);
  }

  value.value = res.join("");
}

async function handlePrevClick() {
  const res = [] as Array<string>;

  pushFormatterDate(current.value, res);

  if (!props.noTime) {
    pushFormatterTime(currentTime, res);
  }

  startValue.value = res.join("");
}

async function handleNextClick() {
  const res = [] as Array<string>;

  pushFormatterDate(current.value, res);

  if (!props.noTime) {
    pushFormatterTime(currentTime, res);
  }

  endValue.value = res.join("");
}

async function handleClick(picker: Picker) {
  if (!picker.active) {
    return;
  }

  current.value = picker.date;
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

watch(current, () => pickers.value = getPickers(current.value));

function handleClear() {
  emits("update:modelValue", null);
}

function handleCurrent() {
  setCurrent(new Date());
}

async function handleConfirm() {
  clickHandler.value && await clickHandler.value();

  if (startValue.value && endValue.value) {
    emits("update:modelValue", `${startValue.value}~${endValue.value}`);
  }

  await handleFocusout();
}
</script>

<template>
  <div class="beicroon-input beicroon-datetime" :class="{required: required, active: active}" @click.stop>
    <span class="beicroon-input-label">{{ label }}</span>
    <input class="beicroon-input-area" type="text"
           :placeholder="placeholder"
           v-model="value"
           @focusin="handlePrevFocusin"
           @focusout="handleFocusout"
    />
    <div class="beicroon-datetime-area" :class="{hidden: noEnd}">
      <input class="start" type="text"
             :disabled="disabled"
             :placeholder="placeholder"
             v-model="startValue"
             @focusin="handlePrevFocusin"
             @focusout="handleFocusout"
      />
      <span>~</span>
      <input class="end" type="text"
             :disabled="disabled"
             :placeholder="placeholder"
             v-model="endValue"
             @focusin="handleNextFocusin"
             @focusout="handleFocusout"
      />
    </div>
    <div class="beicroon-datetime-view" :class="{hidden: !active}" @mousedown="handleMouseDown" @mouseup="handleMouseUp">
      <div class="beicroon-datetime-main" :class="className">
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
          <li v-for="week in weeks" class="week">{{ week }}</li>
          <li v-for="picker in pickers"
              :class="{active: picker.active, current: picker.current}"
              @click="handleClick(picker)"
          >{{ picker.date.getDate() }}
          </li>
        </ul>
        <div class="beicroon-datetime-foot" v-if="currentTime">
          <ul class="beicroon-time-picker" :class="{show: !noTime}">
            <li><input type="text" v-model="currentTime.hour" /></li>
            <li><span>:</span></li>
            <li><input type="text" v-model="currentTime.minute" /></li>
            <li><span>:</span></li>
            <li><input type="text" v-model="currentTime.second" /></li>
          </ul>
          <div class="beicroon-date-button">
            <beicroon-button class="warning" label="清空" @click="handleClear"></beicroon-button>
            <beicroon-button class="primary" label="当前" @click="handleCurrent"></beicroon-button>
            <beicroon-button class="primary" label="选取" @click="handleConfirm"></beicroon-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-datetime {
  &.active {
    z-index: 9;

    .beicroon-datetime-area {
      border-color: var(--color-primary);
    }
  }

  .beicroon-datetime-area {
    z-index: 1;
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
    z-index: 1;
    right: 18rem;
    width: 300rem;
    height: 328rem;
    overflow: hidden;
    user-select: none;
    position: absolute;
    top: calc(100% - 16rem);
    transition: all 180ms linear;
  }

  .beicroon-datetime-main {
    padding: 6rem;
    height: 320rem;
    margin-top: 8rem;
    border-radius: 6rem;
    background-color: var(--color-white);
    box-shadow: 0 0 8rem -3rem var(--color-black-30) inset;

    &.prev:before {
      top: 0;
      width: 0;
      height: 0;
      z-index: 1;
      content: "";
      right: 220rem;
      border-top: none;
      position: absolute;
      border-left: 8rem solid transparent;
      border-right: 8rem solid transparent;
      border-bottom: 8rem solid transparent;
      border-bottom-color: var(--color-white);
    }

    &.next:after {
      top: 0;
      width: 0;
      height: 0;
      z-index: 1;
      content: "";
      right: 60rem;
      border-top: none;
      position: absolute;
      border-left: 8rem solid transparent;
      border-right: 8rem solid transparent;
      border-bottom: 8rem solid transparent;
      border-bottom-color: var(--color-white);
    }
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
      cursor: text;
      height: 32rem;
      display: flex;
      border-radius: 6rem;
      align-items: center;
      justify-content: center;
      color: var(--color-grey-deeper);

      &.week {
        color: var(--color-black-light);
      }

      &.active {
        cursor: pointer;
        color: var(--color-black-light);

        &.current {
          color: var(--color-white);
          background-color: var(--color-primary);
        }
      }
    }
  }

  .beicroon-datetime-foot {
    display: flex;
    padding: 6rem 0;
    align-items: center;
    justify-content: space-between;
  }

  .beicroon-time-picker {
    gap: 4rem;
    opacity: 0;
    display: flex;
    visibility: hidden;
    align-items: center;

    &.show {
      opacity: 1;
      visibility: visible;
    }

    li {
      display: flex;
      align-items: center;
    }

    input {
      padding: 0;
      width: 32rem;
      height: 26rem;
      outline: none;
      background: none;
      text-align: center;
      border-radius: 6rem;
      border: 1rem solid var(--color-grey-deeper);
    }
  }

  .beicroon-date-button {
    gap: 2rem;
    display: flex;
  }
}
</style>