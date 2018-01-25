

/**
 * Created by Aministrator on 2018-01-11.
 */
Vue.config.devtools = true;


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

// Array Remove - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
}