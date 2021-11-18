$(function(){

    checkToken();

    addStyle();

    addEvent();

    addData();

});

function addEvent() {

    $(".itemTypeContent").hide();
    $("#gameNumberContent").show();
    $("#typeSelectCtrl").change(function(){
        $(".itemTypeContent").hide();
        if($(this).val() == "gameNumber" || "equipment" || "spirit" || "eternal" || "combatPet"){
            $("#" + $(this).val() + "Content").show();
        }
    });

    //加载图片上传
    let imgUpload = new WpFileUpload("imgUploadContent");
    imgUpload.initUpload({
        "uploadUrl" : "/multiUpload",//上传文件信息地址
        "maxFileNumber" : 12,
        "size" : 2048,
        // "resultData" : imgReturnPath,
        onUpload : successForImg,
        // "progressUrl":"#",//获取进度信息地址，可选，注意需要返回的data格式如下（{bytesRead: 102516060, contentLength: 102516060, items: 1, percent: 100, startTime: 1489223136317, useTime: 2767}）
        // "scheduleStandard":false,
    });
    function successForImg(){
        $("#imgPathSave").val(imgUpload.resultData);
        imgUpload.uploadSuccess();
    }
    //加载视频上传
    let videoUpload = new WpFileUpload("videoUploadContent");
    videoUpload.initUpload({
        "uploadUrl" : "/multiUpload",//上传文件信息地址
        "maxFileNumber" : 1,
        // "size" : 2048,
        // "resultData" : imgReturnPath,
        onUpload : successForVideo,
        // "progressUrl":"#",//获取进度信息地址，可选，注意需要返回的data格式如下（{bytesRead: 102516060, contentLength: 102516060, items: 1, percent: 100, startTime: 1489223136317, useTime: 2767}）
        // "scheduleStandard":false,
    });
    function successForVideo(){
        $("#videoPathSave").val(videoUpload.resultData);
        videoUpload.uploadSuccess();
    }

    //发布操作
    $("body").on("click", "#viewMoreAreaBtn", function(){
        //判断标题
        if($("#title").val() == ""){
            alert("请填写标题！");
            return;
        }
        //判断详细说明
        if($("#detail").val() == ""){
            alert("详细说明不能为空，请填写！");
            return;
        }
        //判断价格
        if($("#price").val() == ""){
            alert("价格不能为空！");
            return;
        }
        if(!checkRates($("#price").val())){
            alert("请填写正确的价格！");
            return;
        }
        //判断账号字段和战魂字段
        if($("#typeSelectCtrl").val() == "gameNumber"){
            if($("#level").val() == ""){
                alert("等级不能为空！");
                return;
            }
            if(!checkPositiveInt($("#level").val())){
                alert("请输入正确的等级！");
                return;
            }
        }else if($("#typeSelectCtrl").val() == "combatPet"){
            if($("#combatLevel").val() == ""){
                alert("战魂等级不能为空！");
                return;
            }
            if(!checkPositiveInt($("#combatLevel").val())){
                alert("请输入正确的战魂等级！");
                return;
            }
        }
        //图片和视频处理
        //1、$("#imgUploadContent .box").html()不等于""，说明用户选择了文件
        //2、$("#imgUploadContent .subberProgress .progress div").width()等于0，说明用户没有点击开始上传
        //orderType的值判断
        var orderType;
        if($("#typeSelectCtrl").val() == "gameNumber"){
            orderType = "0";
        }else if($("#typeSelectCtrl").val() == "equipment"){
            orderType = "1";
        }else if($("#typeSelectCtrl").val() == "spirit"){
            orderType = "2";
        }else if($("#typeSelectCtrl").val() == "eternal"){
            orderType = "3";
        }else if($("#typeSelectCtrl").val() == "combatPet"){
            orderType = "4";
        }else{
            orderType = $("#typeSelectCtrl").val();
        }
        //微信的值判断
        var wxNumber;
        if($("#wxNumber")){
            wxNumber = $("#wxNumber").attr("placeholder");
        }else{
            wxNumber = $("#wxNumber").val();
        }
        $.ajax({
            url : "/sellOrder/issuaSell",
            async : false,
            headers : {
                token : $.cookie("token")
            },
            data : {
                orderType : orderType,
                areaId : $("#areaSelect").val(),
                areaSonId : $("#areaSonSelect").val(),
                title : $("#title").val(),
                description : $("#detail").val(),
                price : $("#price").val(),
                wxNumber : wxNumber,
                bondsmanId : $("#bondsman").val(),
                gameNumberProfession : $("#gameNumberProfession").val(),
                gameNumberIsBetter : $("#gameNumberIsBetter").val(),
                gameNumberLevel : $("#level").val(),
                equipmentGrade : $("#equipmentGrade").val(),
                equipmentProfession : $("#equipmentProfession").val(),
                equipmentGroup : $("#equipmentGroup").val(),
                equipmentLocation : $("#equipmentLocation").val(),
                spiritLevel : $("#spiritLevel").val(),
                spiritProfession : $("#spiritProfession").val(),
                spiritGroup : $("#spiritGroup").val(),
                eternalCategory : $("#eternalCategory").val(),
                eternalCategorySon : $("#eternalCategorySon").val(),
                eternalProfession : $("#eternalProfession").val(),
                combatPetProp : $("#combatPetProp").val(),
                combatPetIsBetter : $("#combatPetIsBetter").val(),
                combatPetLevel : $("#combatPetLevel").val(),
                imgPath : $("#imgPathSave").val(),
                videoPath : $("#videoPathSave").val()
            },
            success : function (data) {
                if(data == "200"){
                    alert(data.msg);
                    window.location.href = "/sell/list.html";
                }else{
                    alert(data.msg);
                    return;
                }
            }
        });

    });

}

function addData(){

    //添加大区
    $.ajax({
        url : "/sellOrder/listArea",
        async : false,
        success : function(data){
            if(data.code == "200"){
                for(var i=0;i<data.data.length;i++){
                    var areaSelectElement = '<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>';
                    $("#areaSelect").append(areaSelectElement);
                    $("#areaSelect").next().remove();
                    $("#areaSelect").updateOptionData();
                    $("#areaSelect").next().show();
                }
                //添加小区的数据及事件
                areaSonData();
                $("#areaSelect").change(function(){
                    areaSonData();
                });
            }else{
                alert("服务器繁忙，请稍后重试！");
                return;
            }
        }
    });

    $.ajax({
        url : "/user/userInfo",
        async : false,
        headers : {
            token : $.cookie("token")
        },
        success : function (data) {
            $("#wxNumber").attr("placeholder", data.wxNumber);
        }
    });

    //添加担保人
    $.ajax({
        url : "/sellOrder/listBondsman",
        async : false,
        success : function(data){
            var bondsmanElement = '<option value="0"><b>不接受担保</b></option>';
            $("#bondsman").append(bondsmanElement);
            if(data.code == "200"){
                for(var i=0;i<data.data.length;i++){
                    var bondsmanElement = '<option value="' + data.data[i].id + '"><b>' + data.data[i].name + '</b>(微信：<b>' + data.data[i].wxNumber + ')</b></option>';
                    $("#bondsman").append(bondsmanElement);
                    $("#bondsman").next().remove();
                    $("#bondsman").updateOptionData();
                    $("#bondsman").next().show();
                }
            }
        }
    });


}

function addStyle(){
    //文本框样式
    new TextMagnifier({inputElem: '.inputElem', align: 'bottom', splitType: [18,0,0,0]});
    //添加css下拉框样式
    $(".cssStyleSelect").fancyspinbox();
}

function areaSonData(){
    $.ajax({
        url : "/sellOrder/listSonByAreaId",
        async : false,
        data : {
            areaId : $("#areaSelect").val()
        },
        success : function (data) {
            $("#areaSonSelect").empty();
            $("#areaSonSelect").append('<option value="0">全部</option>');
            if(data.code == "200"){
                for(var i=0;i<data.data.length;i++){
                    var areaSonOptionElement = '<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>';
                    $("#areaSonSelect").append(areaSonOptionElement);
                }
                $("#areaSonSelect").next().remove();
                $("#areaSonSelect").updateOptionData();
                $("#areaSonSelect").next().show();
            }else{
                alert("服务器繁忙，请稍后重试！");
            }
        }
    });
}

