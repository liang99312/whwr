var mkList = [];
$(document).ready(function () {
    $("dd, dt,li").hide();
    wdQuanXian();
    $.each($("dt"), function () {
        $(this).click(function () {
            $("#dt1 dd").not($(this).next()).slideUp();
            $(this).next().slideToggle();
            $("dt.selected").not($(this)).removeClass("selected");
            $(this).toggleClass("selected");
        });
    });

    $.each($("li"), function () {
        $(this).click(function () {
            $("li").removeClass("selected");
            $(this).addClass("selected");
        });
    });
});

function wdQuanXian2() {
    var json = {"ym": 12};
    json = {"jsonObj": JSON.stringify(json)};
    alert("123");
    var s = $.ajax({
        url: "/whwr/a01s.action",
        data: json,
        dataType: "json",
        type: "post",
        cache: false,
        error: function (msg, textStatus) {
            failureResp(msg.responseText);
        },
        success: function (json) {
            alert(JSON.stringify(json));
        }
    });
}

function wdQuanXian() {
    var json = {};
    json = {"jsonObj": JSON.stringify(json)};
    var s = $.ajax({
        url: "/whwr/getQx.action",
        data: json,
        dataType: "json",
        type: "post",
        cache: false,
        success: function (json) {
            saoMiao(json.qx);
        }
    });
}

function saoMiao(ja) {
    $("dt").each(function () {
        var id = parseInt($(this).attr("id") / 10000);
        for (var i = 0; i < ja.length; i++) {
            var j = parseInt(ja[i] / 10000);
            if (j == id) {
                $(this).show();
                break;
            }
        }
        $(".xs").show();
    });
    $("li").each(function () {
        var id = parseInt($(this).attr("id") / 10);
        for (var i = 0; i < ja.length; i++) {
            var j = parseInt(ja[i] / 10);
            if (j === id) {
                $(this).show();
                break;
            }
        }
        $(".xs").show();
    });
}

function gzq(url, mc, id) {
    if (mkList.indexOf(id) > -1) {
        $("#etabls li", parent.document).eq(mkList.indexOf(id)).click();
        return;
    }
    var li_width = 0;
    $("#etabls li", parent.document).each(function () {
        li_width +=parseInt($(this).css("width"));
    });
    if(parseInt($("#dvRwl", parent.document).css("width")) - li_width < 116){
        alert("任务栏已满");
        return;
    }
    mkList.push(id);
    $("#etabls li", parent.document).removeClass("tab active");
    $("#etabls li", parent.document).addClass("tab");
    $("#workspace .tabPanel", parent.document).hide();
    $("#workspace", parent.document).append("<div class='tabPanel' id='dv" + id + "' style='width:100%;height:100%;'><iframe src='" + "" + url + "?dt=" + new Date() + "' marginwidth='0' marginheight='0' topmargin='0' leftmargin='0' height='100%' width='100%' frameborder='0'></iframe></div>");
    $("#etabls", parent.document).append("<li class='tab active' id='li" + id + "'><a style='text-decoration:none;' href='#dv" + id + "'>" + mc + "<input id='btn" + id + "' type='button' /></a></li>");

    $("#etabls li", parent.document).each(function () {
        $(this).unbind("click");
        $(this).click(function () {
            $("#etabls li", parent.document).removeClass("tab active");
            $("#etabls li", parent.document).addClass("tab");
            $(this).addClass("tab active");
            var dvId = "dv" + $(this).attr("id").toString().substring(2);
            $("#workspace .tabPanel", parent.document).hide();
            $("#" + dvId, parent.document).show();
        });
    });

    $("#etabls input[type=button]", parent.document).each(function (i) {
        $(this).unbind("click");
        $(this).click(function () {
            var dvId = $(this).attr("id").toString().substring(3);
            mkList.splice(mkList.indexOf(dvId),1);
            if($("#li" + dvId, parent.document).attr('class') === "tab active"){
                var a = null;
                if ($("#li" + dvId, parent.document).prev('li').length > 0) {
                    a = $("#li" + dvId, parent.document).prev('li').find('a');
                }
                if (a === null && $("#li" + dvId, parent.document).next('li').length > 0) {
                    a = $("#li" + dvId, parent.document).next('li').find('a');
                }
                if(a !== null){
                    a.click();
                }
            }
            $("#li" + dvId, parent.document).remove();
            $("#dv" + dvId, parent.document).remove();
        });
    });

}

function exit() {
    if (!confirm("您确定要退出该系统吗?"))
        return;
    if (parent.webObject == null)
        parent.location.href = '/logout.jsp';
    else
        parent.webObject.close();
}