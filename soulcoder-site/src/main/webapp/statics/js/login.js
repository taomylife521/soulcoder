var vm=new Vue({
	el:"#loginbox",
	data:{
		username:"admin",
		password:"admin",
		error:false,
		errormsg:"",
		captchaObj:"",
		isRememberMe:false,
		isShowCodeTip:true
		
		
	},
	created:function(){
		//alert("创建完成created");
		this.loadVerifyCode();

    },
	beforeCreate:function(){
		//alert("beforeCreated");

	},
	methods:{
		login:function(){
			var result = vm.captchaObj.getValidate();	
			// if(result==null || result==undefined){
			// 	vm.error=true;
			// 	vm.errormsg="貌似还没有验证通过啊";
			// 	return;
			// }
			vm.error=false;
			
			//var data="userName="+vm.username+"&password="+vm.password+"&geetest_challenge="+result.geetest_challenge+"&geetest_validate="+result.geetest_validate+"&geetest_seccode="+result.geetest_seccode;
            var data="userName="+vm.username+"&password="+vm.password;//"&geetest_challenge="+result.geetest_challenge+"&geetest_validate="+result.geetest_validate+"&geetest_seccode="+result.geetest_seccode;
			$.ajax({
				type:"POST",
				url:"login",
				data:data,
				dataType:"json",
				success:function(result){
					if(result.status==0){
						vm.error=true;
						vm.errormsg=result.errormsg;
						return;
					}
					vm.error=false;
					parent.location.href="index";
				}	
			});
		},

		//载入极验验证码
		loadVerifyCode:function(){
			$.ajax({
		         url: "gt/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
		         type: "get",
		         dataType: "json",
		         success: function (data) {
		        	 
		             // 调用 initGeetest 初始化参数
		             // 参数1：配置参数
		             // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
		             initGeetest({
		                 gt: data.gt,
		                 challenge: data.challenge,
		                 new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
		                 offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
		                 product: "popup", // 产品形式，包括：float，popup
		                 width: "100%"
		                 // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
		             }, function(captchaObj){
		            	console.log(captchaObj);
		            	 vm.captchaObj=captchaObj;
		            	 // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
		                 captchaObj.appendTo("#captcha");
		                 captchaObj.onReady(function () {
		                	  vm.isShowCodeTip=false;
		                 });
		                 vm.validateVerifyCode();
		                 // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
		             });
		         }
		     });
		},

		//校验验证码
		validateVerifyCode:function(){
			var result = vm.captchaObj.getValidate();
			var flag=false;
            if (!result) {
                $("#notice").show();
                setTimeout(function () {
                    $("#notice").hide();
                }, 2000);
            } else {
                $.ajax({
                    url: 'gt/ajax-validate',
                    type: 'POST',
                    dataType: 'json',
                    async: false,
                    data: {
                        geetest_challenge: result.geetest_challenge,
                        geetest_validate: result.geetest_validate,
                        geetest_seccode: result.geetest_seccode
                    },
                    success: function (data) {
                        if (data.status === 'success') {
                        	vm.error=false;
                        	vm.errormsg="";
                        	flag= true;
                        } else if (data.status === 'fail') {
                        	vm.error=true;
                        	vm.errormsg="验证码不正确";
                        	flag= false;
                        }
                    }
                })
            }
           // e.preventDefault();
            return flag;
		}
	
		
	}
	
});