$(function () {

    checkToken();

    addEvent();

    addData();

});

function addEvent() {
    addHeaderEvent();
    $("#personalInfo").addClass("current");
    $(".text-content").hide();
    $("#personalInfoContent").show();
    $(".newpic").click(function () {
        $(".newpic").removeClass("current");
        $(this).addClass("current");
        $(".text-content").hide();
        $("#" + $(this).attr("id") + "Content").show();
        if($(this).prop("id") == "vipCenter"){
            $(".payCode").hide();
            $("#buyVipBtn").parent().show();
        }
    });
    //支付码区域隐藏
    $(".payCode").hide();
    $("#buyVipBtn").click(function(){
        $.ajax({
            url : "/pay/createVipMonthOrder",
            async : false,
            headers: {
                token : $.cookie("token")
            },
            success : function(data){
                console.log(data);
                if(data.code == "200"){
                    $(".payCode").show();
                    $("#buyVipBtn").parent().hide();
                    $("#payMoneyArea").html(data.data);
                }else{
                    alert("支付ip地址过期，请联系老陈！");
                }
            }
        });
    });
    //签到按钮区域隐藏
    $("#todayNoSignIn").hide();
    $("#todaySignIned").hide();
    $("#sign").click(function(){
        $.ajax({
            url : "/user/sign",
            async : false,
            headers : {
                token : $.cookie("token")
            },
            success : function(data){
                if(data.code == "420"){
                    alert(data.msg);
                }else if(data.code == "200"){
                    alert(data.msg);
                    $(".watchCount").html(data.data.watchCount);
                    $("#monthCount").html(data.data.monthCount);
                    $("#todayNoSignIn").hide();
                    $("#todaySignIned").show();
                }else{
                    alert("未知异常，请联系老陈！");
                }
            }
        });
    });

}

function addData() {
    addHeaderData();
    $.ajax({
        url : "/user/queryPersonalInfo",
        async : false,
        headers : {
            "token" : $.cookie("token")
        },
        success : function(data){
            $("#nickname").html(data.data.nickname);
            $("#username").html(data.data.username);
            $(".type").html(data.data.type);
            $("#bannedCount").html(data.data.bannedCount);
            $(".watchCount").html(data.data.watchCount);
            if(data.data.vipExpireTime){
                $("#vipExpireTime").html(data.data.vipExpireTime);
            }
            $("#monthCount").html(data.data.monthCount);
            if(data.data.todaySignIn){
                $("#todaySignIned").show();
            }else{
                $("#todayNoSignIn").show();
            }
        }
    });
}























