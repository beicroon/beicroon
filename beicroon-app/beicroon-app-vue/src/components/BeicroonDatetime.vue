<script setup lang="ts">
import {computed, ref} from "vue";
import {BButton} from "@/components/index";

interface Day {
  no: number,
  active: "active" | "current" | "disabled",
}

interface Props {
  modelValue?: any,
  label?: string,
  placeholder?: string,
  disabled?: boolean,
  required?: boolean,
  time?: "none" | "start" | "end" | "current" | "currentOrStart" | "currentOrEnd",
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
  set: (val: Date) => {
    if (props.required && inputEl.value) {
      inputEl.value.classList.remove("error");
    }

    const emitVal = `${y(val)}-${m(val)}-${d(val)}`;

    emits("update:modelValue", props.time === "none" ? emitVal : `${emitVal} ${hh(val)}:${mm(val)}:${ss(val)}`);
  },
});

const y = (date: Date) => date.getFullYear();

const m = (date: Date) => {
  const m = date.getMonth() + 1;

  if (m < 10) {
    return `0${m}`;
  }

  return m;
};

const d = (date: Date) => {
  const d = date.getDate();

  if (d < 10) {
    return `0${d}`;
  }

  return d;
};

const hh = (date: Date) => {
  const h = date.getHours();

  if (h < 10) {
    return `0${h}`;
  }

  return h;
};

const mm = (date: Date) => {
  const m = date.getMinutes();

  if (m < 10) {
    return `0${m}`;
  }

  return m;
};

const ss = (date: Date) => {
  const s = date.getSeconds();

  if (s < 10) {
    return `0${s}`;
  }

  return s;
};

const datetime = ref(props.modelValue ? new Date(props.modelValue) : new Date());

const current = ref(new Date(datetime.value.getFullYear(), datetime.value.getMonth(), datetime.value.getDate()));

switch (props.time) {
  case "current":
    current.value.setHours(datetime.value.getHours());
    current.value.setMinutes(datetime.value.getMinutes());
    current.value.setSeconds(datetime.value.getSeconds());
    break;
  case "currentOrStart":
    if (props.modelValue) {
      current.value.setHours(datetime.value.getHours());
      current.value.setMinutes(datetime.value.getMinutes());
      current.value.setSeconds(datetime.value.getSeconds());
    } else {
      current.value.setHours(0);
      current.value.setMinutes(0);
      current.value.setSeconds(0);
    }
    break;
  case "currentOrEnd":
    if (props.modelValue) {
      current.value.setHours(datetime.value.getHours());
      current.value.setMinutes(datetime.value.getMinutes());
      current.value.setSeconds(datetime.value.getSeconds());
    } else {
      current.value.setHours(23);
      current.value.setMinutes(59);
      current.value.setSeconds(59);
    }
    break;
  case "none":
  case "start":
    current.value.setHours(0);
    current.value.setMinutes(0);
    current.value.setSeconds(0);
    break;
  case "end":
    current.value.setHours(23);
    current.value.setMinutes(59);
    current.value.setSeconds(59);
    break;
}

const year = computed({
  get: () => y(current.value),
  set: (val: number) => {
    current.value.setFullYear(val);

    current.value = new Date(current.value.getTime());
  },
});

const month = computed({
  get: () => m(current.value),
  set: (val: number) => {
    current.value.setMonth(val - 1);

    current.value = new Date(current.value.getTime());
  },
});

const day = computed({
  get: () => d(current.value),
  set: (val: number) => {
    current.value.setDate(val);

    current.value = new Date(current.value.getTime());
  },
});

const hour = computed({
  get: () => hh(current.value),
  set: (val: number) => {
    current.value.setHours(val);

    current.value = new Date(current.value.getTime());
  },
});

const minute = computed({
  get: () => mm(current.value),
  set: (val: number) => {
    current.value.setMinutes(val);

    current.value = new Date(current.value.getTime());
  },
});

const second = computed({
  get: () => ss(current.value),
  set: (val: number) => {
    current.value.setSeconds(val);

    current.value = new Date(current.value.getTime());
  },
});

const subYear = () => {
  current.value.setFullYear(current.value.getFullYear() - 1);

  current.value = new Date(current.value.getTime());
};

const subMonth = () => {
  current.value.setMonth(current.value.getMonth() - 1);

  current.value = new Date(current.value.getTime());
};

const addMonth = () => {
  current.value.setMonth(current.value.getMonth() + 1);

  current.value = new Date(current.value.getTime());
};

const addYear = () => {
  current.value.setFullYear(current.value.getFullYear() + 1);

  current.value = new Date(current.value.getTime());
};

const days = computed((): Day[][] => {
  const res: Day[][] = [];

  const firstDayOfMonth = new Date(current.value.getFullYear(), current.value.getMonth(), 1);
  const lastDayOfMonth = new Date(current.value.getFullYear(), current.value.getMonth() + 1, 0);
  const firstDayOfWeek = firstDayOfMonth.getDay(); // 当前月份的第一天是星期几（0-6，0 表示星期日）
  const lastDateOfMonth = lastDayOfMonth.getDate(); // 当前月份的最后一天是几号

  let day = 1; // 当前月份的日期
  let prevMonthDay = new Date(current.value.getFullYear(), current.value.getMonth(), 0).getDate(); // 上个月的最后一天
  let nextMonthDay = 1; // 下个月的日期

  for (let i = 0; i < 6; i++) {
    const week: Day[] = [];

    for (let j = 0; j < 7; j++) {
      // 上个月的日期
      if (i === 0 && j < firstDayOfWeek) {
        week.push({ no: prevMonthDay - firstDayOfWeek + j + 1, active: "disabled" });
      }
      // 下个月的日期
      else if (day > lastDateOfMonth) {
        week.push({ no: nextMonthDay++, active: "disabled" });
      }
      // 当前月份的日期
      else {
        const isCurrentDay = day === current.value.getDate();

        week.push({ no: day, active: isCurrentDay ? "current" : "active" });

        day++;
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
    if (rect.top + rect.height + 312 > window.innerHeight) {
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
  if (day.active !== "active") {
    return;
  }

  current.value.setDate(day.no);

  current.value = new Date(current.value.getTime());
};

const handleHide = () => {
  if (optionActive.value) {
    document.removeEventListener("mousedown", handleHide);
  }

  optionActive.value = false;

  hidden.value = true;
};

const handleConfirm = () => {
  value.value = new Date(current.value.getTime());

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
      <div class="year-month">
        <button @click="subYear"><<</button>
        <button @click="subMonth"><</button>
        <button @click="addMonth">></button>
        <button @click="addYear">>></button>
      </div>
      <div class="current">
        <input class="year" type="text" v-model="year" />
        <span>年</span>
        <input type="text" v-model="month" />
        <span>月</span>
        <input type="text" v-model="day" />
        <span>日</span>
        <input type="text" v-model="hour" />
        <span>:</span>
        <input type="text" v-model="minute" />
        <span>:</span>
        <input type="text" v-model="second" />
      </div>
      <ul class="week">
        <li class="item">日</li>
        <li class="item">一</li>
        <li class="item">二</li>
        <li class="item">三</li>
        <li class="item">四</li>
        <li class="item">五</li>
        <li class="item">六</li>
      </ul>
      <ul class="line" v-for="line in days">
        <li v-for="day in line" class="item" :class="day.active" @click="handleChoose(day)">{{ day.no }}</li>
      </ul>
      <div class="button">
        <b-button label="关闭" level="warning" size="small" @click="handleHide"/>
        <b-button label="确定" level="primary" size="small" @click="handleConfirm"/>
      </div>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-input.datetime {
  .datetime {
    gap: 4rem;
    right: 50%;
    z-index: 9;
    display: flex;
    height: 312rem;
    padding: 4rem 0;
    position: absolute;
    border-radius: 4rem;
    flex-direction: column;
    width: calc(100% + 2rem);
    transform: translateX(50%);
    background-color: var(--color-white);
    border: 1rem solid var(--color-primary);

    &.top {
      bottom: calc(100% + 2rem);
    }

    &.bottom {
      top: calc(100% + 2rem);
    }
  }

  .year-month {
    display: flex;
    padding: 0 14rem;
    justify-content: space-between;

    button {
      cursor: pointer;
      padding: 4rem 8rem;
      border-radius: 4rem;
      background-color: var(--color-grey-lighter);
    }
  }

  .current {
    display: flex;
    padding: 0 14rem;
    align-items: center;
    justify-content: space-between;

    input {
      width: 32rem;
      height: 24rem;
      outline: none;
      text-align: center;
      border-radius: 4rem;
      border: 1rem solid var(--color-grey-light);

      &.year {
        width: 64rem;
      }

      &:focus {
        border-color: var(--color-primary);
      }
    }
  }

  .week {
    display: flex;
    justify-content: space-evenly;

    .item {
      width: 32rem;
      height: 28rem;
      display: flex;
      border-radius: 4rem;
      align-items: center;
      justify-content: center;
      background-color: var(--color-grey-lighter);
    }
  }

  .line {
    display: flex;
    justify-content: space-evenly;

    .item {
      width: 32rem;
      height: 28rem;
      display: flex;
      cursor: pointer;
      border-radius: 4rem;
      align-items: center;
      justify-content: center;
      background-color: var(--color-grey-lighter);

      &.current {
        color: var(--color-primary);
      }

      &.disabled {
        cursor: auto;
        color: var(--color-grey-light);
      }
    }
  }

  .button {
    display: flex;
    padding: 0 14rem;
    justify-content: space-between;
  }
}
</style>