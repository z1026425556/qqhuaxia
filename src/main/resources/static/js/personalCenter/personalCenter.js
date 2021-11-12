$(function () {

    checkToken();

    addEvent();

    addData();

});

function addEvent() {
    $("#personalInfo").addClass("current");
    $(".text-content").hide();
    $("#personalInfoContent").show();
    $(".newpic").click(function () {
        $(".newpic").removeClass("current");
        $(this).addClass("current");
        $(".text-content").hide();
        $("#" + $(this).attr("id") + "Content").show();
    });


}

function addData() {
    $.ajax({
        url : "/user/queryPersonalInfo",
        async : false,
        headers : {
            "token" : $.cookie("token")
        },
        success : function(data){
            $("#nickname").html(data.data.nickname);
            $("#username").html(data.data.username);
            $("#type").html(data.data.type);
            $("#bannedCount").html(data.data.bannedCount);
            $("#watchCount").html(data.data.watchCount);
            $("#currentVipType").html(data.data.type);
            if(data.data.vipExpireTime){
                $("#vipExpireTime").html(data.data.vipExpireTime);
            }
        }
    });
}























