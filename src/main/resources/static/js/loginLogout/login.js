$(function(){

    addEvent();

    addData();

});

function addEvent(){

    $("#login").click(function(){
        //判断用户名是否为空
        if($("#username").val() == ""){
            alert("请填写用户名！");
            return;
        }
        //判断密码是否为空
        if($("#password").val() == ""){
            alert("请填写密码！");
            return;
        }
        setCookie("inputUsername", $("#username").val());
        if($("#rememberPwd").prop("checked")){
            setCookie("inputPwd", $("#password").val());
        }else{
            setCookie("inputPwd", "");
        }
        $.ajax({
            url : "/user/login",
            asycn : false,
            data : {
                username : $("#username").val(),
                password : $("#password").val()
            },
            success : function (data) {
                if(data.code == "411" || data.code == "412"){
                    alert(data.msg);
                    return;
                }
                if(data.code == "200"){
                    setCookie("token", data.data.token);
                }
                path = window.atob(getQueryString("path"));
                if(!path){
                    //跳转到主页

                }else{
                    window.location.href = path;
                }
            }
        });
    });

    $("#password").keyup(function (event) {
        if(event.keyCode == 13){
            $("#login").click();
        }
    });

}

function addData(){
    $("#username").val($.cookie("inputUsername"));
    $("#password").val($.cookie("inputPwd"));
}


































