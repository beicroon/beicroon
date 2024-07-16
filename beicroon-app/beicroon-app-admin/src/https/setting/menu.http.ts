export type MenuBaseVO = {
    name: string,
    path: string,
    active: boolean,
    children: Array<MenuBaseVO>
}