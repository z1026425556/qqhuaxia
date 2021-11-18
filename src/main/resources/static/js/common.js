


function setCookie(cookieName, cookieValue, cookieExpireTime){
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

function checkRates(str){
    //判断字符串如果是整数不能以0开头后面加正整数，如果是浮点数整数部分不能为两个0：如00.00，如果是整数，
    var re = /^(([1-9][0-9]*\.[0-9][0-9]*)|([0]\.[0-9][0-9]*)|([1-9][0-9]*)|([0]{1}))$/;
    var Sure;
    if (!re.test(str)){
        Sure = 0;
    }else{
        Sure = 1;
    }
    return Sure;
}

function checkPositiveInt(str){
    //正整数
    var re = /^[0-9]*[1-9][0-9]*$/;
    var Sure;
    if (!re.test(str)){
        Sure = 0;
    }else{
        Sure = 1;
    }
    return Sure;
}

