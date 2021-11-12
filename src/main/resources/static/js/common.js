//cookie的有效时间为12小时
var cookieExpireTime = 12;

function setCookie(cookieName, cookieValue, ){
    var date = new Date();
    date.setTime(date.getTime() + (cookieExpireTime * 60 * 60 * 1000));
    $.cookie(cookieName, cookieValue, { path: '/', expires: date });
}

function checkToken(){
    var pathname = window.location.pathname;
    // var btoaStr = window.btoa(pathname)      //加密
    // var atobStr = window.atob(btoaStr);      //解密
    if(!$.cookie("token")){
        window.location.href = "/html/loginLogout/login.html?path=" + window.btoa(pathname);
    }else{
        $.ajax({
            url : "/common/checkToken",
            async : false,
            headers : {
                "token" : $.cookie("token")
            },
            success : function (data) {
                if(data.status == "403"){
                    window.location.href = "/html/loginLogout/login.html?path=" + window.btoa(pathname);
                }
            }
        });
    }
}

function getQueryString(param){
    var reg = new RegExp("(^|&)"+ param +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null){
        return unescape(r[2]);
    }
    return null;
}

