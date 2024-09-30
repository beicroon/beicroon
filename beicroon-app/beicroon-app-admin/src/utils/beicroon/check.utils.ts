import {reactive} from "vue";
import {CheckedEnums} from "@/enums/default-enums.ts";
import {BaseVO, ChildrenVO} from "@/utils/beicroon/http.utils.ts";

type CheckUtils<VO extends BaseVO> = {
    vos: Array<VO>,
    initCheck: (vos: Array<VO>, ids: Array<string>) => void,
    getCheckedIds: () => Array<string>,
    handleCheck: (vo: VO) => void,
    handleUncheck: (vo: VO) => void,
};

type CheckTree<VO extends ChildrenVO<VO>> = {
    vos: Array<VO>,
    initCheck: (vos: Array<VO>, ids: Array<string>) => void,
    getCheckedIds: () => Array<string>,
    getCheckedAndIndeterminateIds: () => Array<string>,
    handleCheckGrand: (vo: VO, child: VO, grand: VO) => void,
    handleCheckChild: (vo: VO, child: VO) => void,
    handleCheckParent: (vo: VO) => void,
    handleCheck: (vo: VO, child?: VO, grand?: VO) => void,
    handleUncheckGrand: (vo: VO, child?: VO, grand?: VO) => void,
    handleUncheckChild: (vo: VO, child?: VO) => void,
    handleUncheckParent: (vo: VO) => void,
    handleUncheck: (vo: VO, child?: VO, grand?: VO) => void,
};

export function createBeicroonCheck<VO extends BaseVO>() {
    const check = reactive({
        vos: [] as Array<VO>,
        initCheck: (vos: Array<VO>, ids: Array<string>) => {
            check.vos = vos.map((vo: VO) => {
                if (ids.includes(vo.id)) {
                    vo.checked = CheckedEnums.CHECKED;
                } else {
                    vo.checked = CheckedEnums.UNCHECKED;
                }

                return vo;
            });
        },
        getCheckedIds: () => {
            return check.vos.filter((vo: VO) => vo.checked === CheckedEnums.CHECKED)
                .map((vo: VO) => vo.id);
        },
        handleCheck: (vo: VO) => {
            vo.checked = CheckedEnums.CHECKED;
        },
        handleUncheck: (vo: VO) => {
            vo.checked = CheckedEnums.UNCHECKED;
        },
    }) as CheckUtils<VO>;

    return check;
}

export function createBeicroonCheckTree<VO extends ChildrenVO<VO>>() {
    const check = reactive({
        vos: [] as Array<VO>,
        initCheck: (vos: Array<VO>, ids: Array<string>) => {
            check.vos = vos.map((vo: VO) => {
                let parentChecked = false;

                let parentUnchecked = false;

                let parentIndeterminate = false;

                vo.children = vo.children.map((child: VO) => {
                    let checked = false;

                    let unchecked = false;

                    child.children = child.children.map((grand) => {
                        if (ids.includes(grand.id)) {
                            grand.checked = CheckedEnums.CHECKED;

                            checked = true;
                        } else {
                            grand.checked = CheckedEnums.UNCHECKED;

                            unchecked = true;
                        }

                        return grand;
                    });

                    if (checked && unchecked) {
                        child.checked = CheckedEnums.INDETERMINATE;

                        parentIndeterminate = true;
                    } else if (checked) {
                        child.checked = CheckedEnums.CHECKED;

                        parentChecked = true;
                    } else {
                        child.checked = CheckedEnums.UNCHECKED;

                        parentUnchecked = true;
                    }

                    return child;
                });

                if ((parentChecked && parentUnchecked) || parentIndeterminate) {
                    vo.checked = CheckedEnums.INDETERMINATE;
                } else if (parentChecked) {
                    vo.checked = CheckedEnums.CHECKED;
                } else {
                    vo.checked = CheckedEnums.UNCHECKED;
                }

                return vo;
            });
        },
        getCheckedIds: () => {
            const ids: Array<string> = [];

            check.vos.forEach((vo: VO) => {
                if (vo.checked === CheckedEnums.CHECKED) {
                    ids.push(vo.id);
                }

                vo.children.forEach((child: VO) => {
                    if (child.checked === CheckedEnums.CHECKED) {
                        ids.push(child.id);
                    }

                    child.children.forEach((grand: VO) => {
                        if (grand.checked === CheckedEnums.CHECKED) {
                            ids.push(grand.id);
                        }
                    });
                });
            });

            return ids;
        },
        getCheckedAndIndeterminateIds: () => {
            const ids: Array<string> = [];

            check.vos.forEach((vo: VO) => {
                if (vo.checked === CheckedEnums.CHECKED || vo.checked === CheckedEnums.INDETERMINATE) {
                    ids.push(vo.id);
                }

                vo.children.forEach((child: VO) => {
                    if (child.checked === CheckedEnums.CHECKED || child.checked === CheckedEnums.INDETERMINATE) {
                        ids.push(child.id);
                    }

                    child.children.forEach((grand: VO) => {
                        if (grand.checked === CheckedEnums.CHECKED || grand.checked === CheckedEnums.INDETERMINATE) {
                            ids.push(grand.id);
                        }
                    });
                });
            });

            return ids;
        },
        handleCheckGrand: (vo: VO, child: VO, grand: VO) => {
            grand.checked = CheckedEnums.CHECKED;

            for (let i = 0; i < child.children.length; i++) {
                if (child.children[i].checked !== CheckedEnums.CHECKED) {
                    child.checked = CheckedEnums.INDETERMINATE;

                    vo.checked = CheckedEnums.INDETERMINATE;

                    return;
                }
            }

            check.handleCheckChild(vo, child);
        },
        handleCheckChild: (vo: VO, child: VO) => {
            child.checked = CheckedEnums.CHECKED;

            child.children.forEach((grandMenu) => {
                grandMenu.checked = CheckedEnums.CHECKED;
            });

            for (let i = 0; i < vo.children.length; i++) {
                if (vo.children[i].checked !== CheckedEnums.CHECKED) {
                    vo.checked = CheckedEnums.INDETERMINATE;

                    return;
                }
            }

            vo.checked = CheckedEnums.CHECKED;
        },
        handleCheckParent: (vo: VO) => {
            vo.checked = CheckedEnums.CHECKED;

            vo.children.forEach((childMenu) => {
                childMenu.checked = CheckedEnums.CHECKED;

                childMenu.children.forEach((grandMenu) => {
                    grandMenu.checked = CheckedEnums.CHECKED;
                });
            });
        },
        handleCheck: (vo: VO, child?: VO, grand?: VO) => {
            if (grand && child) {
                check.handleCheckGrand(vo, child, grand);

                return;
            }

            if (child) {
                check.handleCheckChild(vo, child);

                return;
            }

            check.handleCheckParent(vo)
        },
        handleUncheckGrand: (vo: VO, child: VO, grand: VO) => {
            grand.checked = CheckedEnums.UNCHECKED;

            for (let i = 0; i < child.children.length; i++) {
                if (child.children[i].checked !== CheckedEnums.UNCHECKED) {
                    child.checked = CheckedEnums.INDETERMINATE;

                    vo.checked = CheckedEnums.INDETERMINATE;

                    return;
                }
            }

            check.handleUncheckChild(vo, child);
        },
        handleUncheckChild: (vo: VO, child: VO) => {
            child.checked = CheckedEnums.UNCHECKED;

            child.children.forEach((grandMenu) => {
                grandMenu.checked = CheckedEnums.UNCHECKED;
            });

            for (let i = 0; i < vo.children.length; i++) {
                if (vo.children[i].checked !== CheckedEnums.UNCHECKED) {
                    vo.checked = CheckedEnums.INDETERMINATE;

                    return;
                }
            }

            vo.checked = CheckedEnums.UNCHECKED;
        },
        handleUncheckParent: (vo: VO) => {
            vo.checked = CheckedEnums.UNCHECKED;

            vo.children.forEach((childMenu) => {
                childMenu.checked = CheckedEnums.UNCHECKED;

                childMenu.children.forEach((grandMenu) => {
                    grandMenu.checked = CheckedEnums.UNCHECKED;
                });
            });
        },
        handleUncheck: (vo: VO, child?: VO, grand?: VO) => {
            if (grand && child) {
                check.handleUncheckGrand(vo, child, grand);

                return;
            }

            if (child) {
                check.handleUncheckChild(vo, child);

                return;
            }

            check.handleUncheckParent(vo);
        },
    }) as CheckTree<VO>;

    return check;
}
