import {SHA256} from "crypto-js";

export function sha256(str?: string): string {
    if (!str) {
        return "";
    }

    return SHA256(str).toString();
}