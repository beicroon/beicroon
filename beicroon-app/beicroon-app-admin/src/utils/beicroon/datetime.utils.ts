const TWENTY_FOUR = [
    "00", "01", "02", "03", "04", "05",
    "06", "07", "08", "09", "10", "11",
    "12", "13", "14", "15", "16", "17",
    "18", "19", "20", "21", "22", "23",
] as const;

const SIXTY = [
    "00", "01", "02", "03", "04", "05",
    "06", "07", "08", "09", "10", "11",
    "12", "13", "14", "15", "16", "17",
    "18", "19", "20", "21", "22", "23",
    "24", "25", "26", "27", "28", "29",
    "30", "31", "32", "33", "34", "35",
    "36", "37", "38", "39", "40", "41",
    "42", "43", "44", "45", "46", "47",
    "48", "49", "50", "51", "52", "53",
    "54", "55", "56", "57", "58", "59",
] as const;

export type Hour = typeof TWENTY_FOUR[number];

export type Minute = typeof SIXTY[number];

export type Second = typeof SIXTY[number];

export type BeicroonTime = {
    hour: Hour,
    minute: Minute,
    second: Second,
};