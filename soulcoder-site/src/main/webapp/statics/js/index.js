/*
 * Copyright (c) 2017.所有代码版权归编码者所
 */

//创建一个Vue菜单组件
// var menuItemComponent=Vue.extend({
// 	name:"menu-item",
// 	props:{item:{}},
// 	template:[
// 		 '	 <li class="treeview">',
// 	     '   <a v-if="item.type === 0" href="#">',
// 	     '     <i v-if="item.icon != null":class="item.icon"></i>',
// 	     '      <span>{{item.menuname}}</span>',
// 	     '     <span class="pull-right-container">',
// 	     '       <i class="fa fa-angle-left pull-right"></i>',
// 	     '     </span>',
// 	     '   </a>',
// 	     '   <ul v-if="item.type === 0" class="treeview-menu">',
// 	     '     <menu-item :item="item" v-for="item in item.list"></menu-item>',
// 	     '   </ul>',
// 	     '	 <a v-if="item.type === 1" :href="\'#\'+item.url"  v-on:click="menuItemClick"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.menuname}}</a>',
// 	     ' </li>'
// 	].join(''),
//     methods:{
//         menuItemClick:function(){
//         	alert("bb");
//             this.$emit("itemClick");
//             alert("end");
// 		}
// 	}
// });


Vue.component('menuItem',{
    name:"menu-item",
    props:{item:{}},
    template:[
        '	 <li class="treeview">',
        '   <a v-if="item.type === 0" href="#">',
        '     <i v-if="item.icon != null":class="item.icon"></i>',
        '      <span>{{item.name}}</span>',
        '     <span class="pull-right-container">',
        '       <i class="fa fa-angle-left pull-right"></i>',
        '     </span>',
        '   </a>',
        '   <ul v-if="item.type === 0" class="treeview-menu">',
        '     <menu-item :item="item" v-for="item in item.list"></menu-item>',
        '   </ul>',
        '	 <a v-if="item.type === 1" :href="\'#\'+item.url"  ><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',//v-on:click="menuItemClick"
        ' </li>'
    ].join('')
});//注册菜单组件

//导航路由
//iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    $content.height($(this).height() - 120);
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();

var vm=new Vue({
	el:"#soulcoderbox",
	data:{
		user:{},
		menuList:{},
		mainpage:"main.html",
        password:'',
		confirmpassword:"",
		newpassword:''

		
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	updated: function(){
		 var router = new Router();
		 routerList(router, vm.menuList);
		 router.start();
		
	},

	methods:{
		//获取菜单列表
		getMenuList:function(){
		   // _G.Alert();
			$.getJSON("sys/menu/navlist",function(r){
				vm.menuList =r.data.menuList;
			});
		},

		//获取用户列表
		getUser:function(){
			$.getJSON("sys/user/userinfo",function(r){

				if(r.status!=1)
				{
					swal(r.message);
					return;
				}
  				vm.user=r.data;
			});
		},

		//修改密码
        updatePassword:function(){
				if(vm.password.length<=0 || vm.confirmpassword.length<=0){
					swal({text:"密码不能为空",icon:"error"});
					return;
				}
            if(vm.password != vm.confirmpassword){
                swal({text:"两次输入密码不一致",icon:"error"});
                return;
            }
            if(vm.newpassword.length<=0){
                swal({text:"新密码不能为空!",icon:"error"});
                return;
            }
            var data ="password="+vm.password+"&confirmpassword="+vm.confirmpassword+"&newpassword="+vm.newpassword
            $.ajax({
				type:"POST",
				url:"sys/user/modifypassword",
				data:data,
				dataType:"json",
				success:function(result){
                    if(result.status==0){
                        swal({text:result.errormsg,icon:"error"});
                        return;
                    }
                    swal({text:"修改成功",icon:"success"});
				}
			});

		}



	}
	
});

function routerList(router, menuList){
    for(var key in menuList){
        var menu = menuList[key];
        if(menu.type == 0){
            routerList(router, menu.list);
        }else if(menu.type == 1){
            router.add('#'+menu.url, function() {
                var url = window.location.hash;

                //替换iframe的url
                vm.mainpage = url.replace('#', '');

                //导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $("a[href='"+url+"']").parents("li").addClass("active");

                //vm.navTitle = $("a[href='"+url+"']").text();
            });
        }
    }
}