declare type MenuBaseVO = {
    name: string,
    path: string,
    active: boolean,
    children: Array<MenuBaseVO>
}

export default MenuBaseVO;