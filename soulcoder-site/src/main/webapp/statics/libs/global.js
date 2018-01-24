

/**
 * Created by Aministrator on 2018-01-11.
 */
Vue.config.devtools = true;

/*ztree的设置*/
var ztree;
//部门树设置
var deptTreeSettings = {
    view: {
        selectedMulti: false
    },
    check: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentid",//父id对应的键
            rootPId: 0
        }
    },
    edit: {
        enable: false
    }
};