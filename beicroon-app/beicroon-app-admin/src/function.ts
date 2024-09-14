import {BooleanEnums} from "@/enums/default-enums.ts";

export function getBooleanDesc(value?: string): string {
    return value === BooleanEnums.TRUE ? "是" : "否";
}

export function getDisableDesc(value?: string): string {
    return !!value ? "是" : "否";
}
