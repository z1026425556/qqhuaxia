function addHeaderData() {

    $("#headerLogin").hide();
    $("#logout").hide();

    var tokenIsExpired = false;
    $.ajax({
        url : "/common/checkToken",
        async : false,
        headers : {
            "token" : $.cookie("token")
        },
        success : function (data) {
            if(data.status == "403"){
                tokenIsExpired = true;
            }
        }
    });

    if(!$.cookie("token") || tokenIsExpired){
        $("#headerLogin").show();
    }else{
        $("#logout").show();
    }

}

function addHeaderEvent(){

    $("#logout").click(function(){
        $.ajax({
            url : "/user/logout",
            headers : {
                token : $.cookie("token")
            },
            success : function(data){
                if(data.code == "200"){
                    alert(data.msg);
                    window.location.href = "/html/loginLogout/login.html";
                    return;
                }else if(data.code = "500"){
                    alert(data.msg);
                    window.location.reload();
                    return;
                }
                alert("登出异常，请联系老陈！");
                window.location.reload();
            }
        });
    });

}

