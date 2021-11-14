$(function(){

    addEvent();

});

function addEvent(){

    $("#register").click(function(){

        //判断用户名是否为空
        if($("#username").val() == ""){
            alert("用户名不能为空！");
            return;
        }
        //判断用户名长度是否为6-12位字符串
        if($("#username").val().length < 6 || $("#username").val().length > 12){
            alert("用户名长度必须为6-12位！");
            return;
        }
        //判断密码是否为空
        if($("#password").val() == ""){
            alert("密码不能为空！");
            return;
        }
        //判断密码长度是否为6-12位字符串
        if($("#password").val().length < 6 || $("#password").val().length > 12){
            alert("密码长度必须为6-12位！");
            return;
        }
        //判断两次密码是否一致
        if($("#password").val() != $("#againPwd").val()){
            alert("两次输入的密码不一致！");
            return;
        }
        //判断密保是否为空
        if($("#superPwd").val() == ""){
            alert("密保不能为空！");
            return;
        }
        //判断密保长度是否为6-12位字符串
        if($("#superPwd").val().length < 6 || $("#superPwd").val().length > 12){
            alert("密保长度必须为6-12位！");
            return;
        }
        //判断邀请码是否为空
        if($("#inviteCode").val() == ""){
            alert("邀请码不能为空！");
            return;
        }
        //判断昵称是否为空
        if($("#nickname").val() == ""){
            alert("昵称不能为空！");
            return;
        }
        //判断昵称长度是否为6-12位字符串
        if($("#nickname").val().length > 8){
            alert("昵称过长，请保持在8个字符之内！");
            return;
        }
        //判断微信是否为空
        if($("#wxNumber").val() == ""){
            alert("微信不能为空！");
            return;
        }

        //判断用户名是否已经被注册了
        var userIsExist = false;
        $.ajax({
            url : "/user/isExistUser",
            async : false,
            data : {
                username : $("#username").val(),
            },
            success : function(data){
                if(data){
                    userIsExist = true;
                }
            }
        });
        if(userIsExist){
            alert("该用户名已经被注册了，请重新输入！");
            return;
        }
        //判断邀请码是否正确
        var isHavingInviteCode = false;
        $.ajax({
            url : "/user/isHavingInviteCode",
            async : false,
            data : {
                inputInviteCode : $("#inviteCode").val(),
            },
            success : function (data) {
                if(data){
                    isHavingInviteCode = true;
                }
            }
        });
        if(!isHavingInviteCode){
            alert("邀请码错误，请检查，或者联系老陈！")
            return;
        }
        //进行注册
        $.ajax({
            url : "/user/register",
            async : false,
            data : {
                username : $("#username").val(),
                password : $("#password").val(),
                nickname : $("#nickname").val(),
                superPwd : $("#superPwd").val(),
                inviteCode : $("#inviteCode").val(),
                wxNumber : $("#wxNumber").val()
            },
            success : function(data){
                setCookie("token", data.data.token);
            }
        });

    });

}












































