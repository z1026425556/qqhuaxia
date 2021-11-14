$(function(){

    addData();

    addEvent();

});

function addEvent(){
    $("#viewMoreAreaBtn").click(function(){
        $(".behindArea").show();
    });
}

function addData() {
    //加载大区
    $.ajax({
        url : "/sellOrder/listArea",
        async : false,
        success : function(data){
            if(data.code == "200"){
                var nameOfAllElement = '<div class="selectArea" style="width: 105px;">' +
                        '                <input type="radio" name="radio-area" checked />' +
                        '                <label>全部</label>' +
                        '            </div>';
                $(".bigArea").append(nameOfAllElement);
                for(var i=0;i<data.data.length;i++){
                    var areaElement;
                    if(data.data[i].id < 10){
                        areaElement = '<div class="selectArea" style="width: 105px;">' +
                            '                <input type="radio" name="radio-area" />' +
                            '                <label id="' + data.data[i].id + '">' + data.data[i].name + '</label>' +
                            '            </div>';
                    }else{
                        areaElement = '<div class="selectArea behindArea" style="width: 105px;">' +
                            '                <input type="radio" name="radio-area" />' +
                            '                <label id="' + data.data[i].id + '">' + data.data[i].name + '</label>' +
                            '            </div>';
                    }
                    $(".bigArea").append(areaElement);
                }
                $(".behindArea").hide();
                radioCssStyle();
            }else{
                alert("服务器错误，请稍后重试！");
            }
        }
    });
}

function radioCssStyle(){
    $('.bigArea input').each(function(){
        var self = $(this),
            label = self.next(),
            label_text = label.text();

        label.remove();
        self.iCheck({
            checkboxClass: 'icheckbox_sm-blue',
            radioClass: 'radio_sm-blue',
            insert: label_text
        });
    });
}