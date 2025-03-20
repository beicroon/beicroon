<script setup lang="ts">
import {computed, ref} from "vue";
import {BButton} from "@/components/index";
import {getDate, getDatetime, getMonth, getTime, getYear} from "@/utils/utils.function";

interface Day {
  no: number,
  state: "active" | "current" | "disabled",
}

interface Props {
  modelValue?: any,
  label?: string,
  placeholder?: string,
  disabled?: boolean,
  required?: boolean,
  time?: "none" | "start" | "end" | "current",
}

const props = withDefaults(defineProps<Props>(), {
  disabled: false,
  required: false,
  time: "none",
});

const emits = defineEmits(["update:modelValue"]);

const inputEl = ref<HTMLInputElement>();

const value = computed({
  get: () => props.modelValue,
  set: (val: string) => {
    if (props.required && inputEl.value) {
      inputEl.value.classList.remove("error");
    }

    emits("update:modelValue", val);
  },
});

const datetime = computed({
  get: () => props.modelValue ? new Date(props.modelValue) : new Date(),
  set: (val: Date) => {
    value.value = props.time === "none" ? getDate(val) : getDatetime(val);
  },
});

const year = computed(() => getYear(datetime.value));

const month = computed(() => getMonth(datetime.value));

const time = computed({
  get: () => getTime(datetime.value),
  set: (val: string) => {
    const times = val.split(":");

    const h = times[0];
    const m = times[1];
    const s = times[2];

    console.log(val, h, m, s);

    const isNumber = /^\d{1,2}$/;

    if (!isNumber.test(h) || !isNumber.test(m) || !isNumber.test(s)) {
      return;
    }

    console.log("success");

    datetime.value = new Date(
      datetime.value.getFullYear(),
      datetime.value.getMonth(),
      datetime.value.getDate(),
      parseInt(h),
      parseInt(m),
      parseInt(s)
    );
  },
});

const subYear = () => {
  const date = new Date(datetime.value);

  date.setFullYear(date.getFullYear() - 1);

  datetime.value = date;
};

const subMonth = () => {
  const date = new Date(datetime.value);

  date.setMonth(date.getMonth() - 1);

  datetime.value = date;
};

const addMonth = () => {
  const date = new Date(datetime.value);

  date.setMonth(date.getMonth() + 1);

  datetime.value = date;
};

const addYear = () => {
  const date = new Date(datetime.value);

  date.setFullYear(date.getFullYear() + 1);

  datetime.value = date;
};

const days = computed((): Day[][] => {
  const res: Day[][] = [];

  const firstDayOfMonth = new Date(datetime.value.getFullYear(), datetime.value.getMonth(), 1);
  const lastDayOfMonth = new Date(datetime.value.getFullYear(), datetime.value.getMonth() + 1, 0);
  const firstDayOfWeek = firstDayOfMonth.getDay();
  const lastDateOfMonth = lastDayOfMonth.getDate();

  let currentDay = 1;
  let prevMonthDay = new Date(datetime.value.getFullYear(), datetime.value.getMonth(), 0).getDate();
  let nextMonthDay = 1;

  for (let i = 0; i < 6; i++) {
    const week: Day[] = [];

    for (let j = 0; j < 7; j++) {
      // 上个月的日期
      if (i === 0 && j < firstDayOfWeek) {
        week.push({ no: prevMonthDay - firstDayOfWeek + j + 1, state: "disabled" });
      }
      // 下个月的日期
      else if (currentDay > lastDateOfMonth) {
        week.push({ no: nextMonthDay++, state: "disabled" });
      }
      // 当前月份的日期
      else {
        const isCurrentDay = currentDay === datetime.value.getDate();

        week.push({ no: currentDay, state: isCurrentDay ? "current" : "active" });

        currentDay++;
      }
    }

    res.push(week);
  }

  return res;
});

const clazz = computed(() => {
  const res = [];

  if (!props.label) {
    res.push("single");
  }

  if (props.required) {
    res.push("required");
  }

  return res;
});

const topOrBottom = ref<"top" | "bottom">("bottom");

const optionActive = ref(false);

const hidden = ref(true);

const handleMouseDown = () => {
  optionActive.value = true;

  document.addEventListener("mousedown", handleHide);
};

const handleFocusin = () => {
  hidden.value = false;

  const rect = inputEl.value?.getBoundingClientRect();

  if (rect) {
    if (rect.top + rect.height + 320 > window.innerHeight) {
      topOrBottom.value = "top";
    } else {
      topOrBottom.value = "bottom";
    }
  }
};

const handleFocusout = () => {
  if (optionActive.value) {
    return;
  }

  handleHide();
};

const handleChoose = (day: Day) => {
  if (day.state !== "active") {
    return;
  }

  const date = new Date(datetime.value);

  date.setDate(day.no);

  datetime.value = date;
};

const handleHide = () => {
  if (optionActive.value) {
    document.removeEventListener("mousedown", handleHide);
  }

  optionActive.value = false;

  hidden.value = true;
};

const handleSetStart = () => {
  time.value = "00:00:00";
};

const handleSetEnd = () => {
  time.value = "23:59:59";
};

const handleSetNow = () => {
  datetime.value = new Date();

  handleHide();
};

const handleConfirm = () => {
  handleHide();
};
</script>

<template>
  <div class="beicroon-input input normal datetime" :class="clazz" ref="inputEl" @mousedown.stop>
    <label class="label" v-if="label">{{ label }}</label>
    <input
      class="input"
      type="text"
      v-model="value"
      :placeholder="placeholder"
      :disabled="disabled"
      @focusin="handleFocusin"
      @focusout="handleFocusout"
    />
    <div class="datetime" :class="topOrBottom" v-show="!hidden" @mousedown="handleMouseDown">
      <div class="line">
        <button class="item" @click="subYear"><<</button>
        <button class="item" @click="subMonth"><</button>
        <span class="item year">{{year}}年</span>
        <span class="item month">{{month}}月</span>
        <button class="item" @click="addMonth">></button>
        <button class="item" @click="addYear">>></button>
      </div>
      <ul class="line">
        <li class="item">日</li>
        <li class="item">一</li>
        <li class="item">二</li>
        <li class="item">三</li>
        <li class="item">四</li>
        <li class="item">五</li>
        <li class="item">六</li>
      </ul>
      <ul class="line day" v-for="line in days">
        <li v-for="day in line" class="item" :class="day.state" @click="handleChoose(day)">{{ day.no }}</li>
      </ul>
      <div class="foot">
        <div class="time">
          <button @click="handleSetStart">始</button>
          <input type="text" v-model="time" />
          <button @click="handleSetEnd">末</button>
        </div>
        <div class="button">
          <b-button label="此刻" level="primary" size="small" @click="handleSetNow"/>
          <b-button label="确定" level="primary" size="small" @click="handleConfirm"/>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-input.datetime {
  .datetime {
    right: 50%;
    z-index: 9;
    display: flex;
    height: 320rem;
    padding: 4rem 0;
    position: absolute;
    border-radius: 4rem;
    flex-direction: column;
    width: calc(100% + 2rem);
    transform: translateX(50%);
    justify-content: space-around;
    background-color: var(--color-white);
    border: 1rem solid var(--color-primary);

    &.top {
      bottom: calc(100% + 2rem);
    }

    &.bottom {
      top: calc(100% + 2rem);
    }
  }

  .line {
    display: flex;
    padding: 0 14rem;
    justify-content: space-between;

    .item {
      width: 32rem;
      height: 28rem;
      display: flex;
      border-radius: 4rem;
      align-items: center;
      justify-content: center;
      background-color: var(--color-white-light);

      &.year {
        width: 64rem;
        background-color: transparent;
      }

      &.month {
        width: 48rem;
        background-color: transparent;
      }
    }
  }

  .day {
    .item {
      &:hover {
        color: var(--color-primary-lighter);
      }

      &.active {
        cursor: pointer;
      }

      &.current {
        color: var(--color-primary);
      }

      &.disabled {
        color: var(--color-grey-light);
      }
    }
  }

  .foot {
    height: 28rem;
    display: flex;
    padding: 0 14rem;
    align-items: center;
    justify-content: space-between;

    .time {
      gap: 8rem;
      display: flex;

      button {
        width: 26rem;
        border-radius: 4rem;
        color: var(--color-white);
        background-color: var(--color-primary-light);
      }

      input {
        width: 92rem;
        outline: none;
        text-align: center;
        border-radius: 4rem;
        border: 1rem solid var(--color-grey-light);

        &:focus {
          border-color: var(--color-primary);
        }
      }
    }

    .button {
      gap: 8rem;
      display: flex;
      justify-content: flex-end;
    }
  }
}
</style>