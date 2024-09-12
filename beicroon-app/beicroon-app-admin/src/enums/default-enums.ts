export enum BooleanEnums {
    TRUE = "true",
    FALSE = "false",
}

export enum CacheKeyEnums {
    AUTHORIZATION_USER = "Auth-User",
    AUTHORIZATION_TOKEN = "Auth-Token",
}

export enum AppNameEnums {
    APP = "beicroon-app",
    TOAST = "beicroon-toast",
    DIALOG = "beicroon-dialog",
}

export enum AppSelectorEnums {
    APP = "#" + AppNameEnums.APP,
    TOAST = "#" + AppNameEnums.TOAST,
    DIALOG = "#" + AppNameEnums.DIALOG,
}

export enum SelectorEnums {
    INPUT = ".beicroon-input",
    INPUT_REQUIRED = ".beicroon-input.required",
    INPUT_AREA = ".beicroon-input-area",
}