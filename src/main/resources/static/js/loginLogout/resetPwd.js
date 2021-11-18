$(function () {

    addEvent();

    addData();

});

function addEvent(){
    $("#sure").click(function(){

        //判断用户名不能为空
        if($("#username").val() == null){
            alert("用户名不能为空！");
            return;
        }
        //判断密保不能为空
        if($("#superPwd").val() == null){
            alert("密保不能为空！");
            return;
        }
        //判断密码不能为空
        if($("#password").val() == null){
            alert("密码不能为空！");
            return;
        }
        //判断两次密码是否一致
        if($("#password").val() != $("#resetPwd").val()){
            alert("输入的两次密码不一致！");
            return;
        }
        $.ajax({
            url : "/user/resetPwd",
            async : false,
            data : {
                username : $("#username").val(),
                password : $("#password").val(),
                superPwd : $("#superPwd").val(),
                resetPwd : $("#resetPwd").val()
            },
            success : function (data) {
                if(data.code == "411" || data.code == "413" || data.code == "414"){
                    alert(data.msg);
                    return;
                }
                if(data.code == "200"){
                    setCookie("token", data.data.token, 12);
                }
            }
        });

    });
}

function addData(){
    $.cookie($("#username").val());
}







































