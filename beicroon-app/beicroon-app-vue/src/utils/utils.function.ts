import {SHA256} from "crypto-js";

export function sha256(str?: string): string {
    if (!str) {
        return "";
    }

    return SHA256(str).toString();
}

export function getDate(date: Date){
    return `${getYear(date)}-${getMonth(date)}-${getDay(date)}`;
}

export function getTime(date: Date){
    return `${getHour(date)}:${getMinute(date)}:${getSecond(date)}`;
}

export function getDatetime(date: Date){
    return `${getDate(date)} ${getTime(date)}`;
}

export function getYear(date: Date){
    return date.getFullYear();
}

export function getMonth(date: Date){
    const m = date.getMonth() + 1;

    if (m < 10) {
        return `0${m}`;
    }

    return m;
}

export function getDay(date: Date){
    const d = date.getDate();

    if (d < 10) {
        return `0${d}`;
    }

    return d;
}

export function getHour(date: Date){
    const h = date.getHours();

    if (h < 10) {
        return `0${h}`;
    }

    return h;
}

export function getMinute(date: Date){
    const m = date.getMinutes();

    if (m < 10) {
        return `0${m}`;
    }

    return m;
}

export function getSecond(date: Date){
    const s = date.getSeconds();

    if (s < 10) {
        return `0${s}`;
    }

    return s;
}