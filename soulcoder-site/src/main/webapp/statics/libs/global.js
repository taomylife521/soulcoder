

/**
 * Created by Aministrator on 2018-01-11.
 */
Vue.config.devtools = true;

//定义vue全局函数
Vue.prototype.ajaxCallInterceptor=function(result){
    var status=parseInt(result.status);
    if(status==0){
        swal(result.errormsg);
        return false;
    }
    if(status == 2){//认证权限异常
        if(result.errormsg.indexOf("unauthor") > -1) {
            location.href = "/unauthor";
        }else{
            swal(result.errormsg);
        }
        return false;
    }
    return true;
}
//部门树设置
var deptTreeSettings = {
    view: {
        selectedMulti: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",// id编号命名 默认
            pIdKey: "parentid",//父id编号命名 默认
            rootPId: 0//// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
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