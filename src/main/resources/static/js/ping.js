$("#confirm").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/ping",
        data: {
            ip: $("#ip").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            switch (e) {
                case "Success":
                    alert("链路正常！");
                    break;
                case "Overtime":
                    alert("连接超时，请检查链路……");
                    break;
                case "Not accessible":
                    alert("数据包不可达，请检查链路，并确认过滤器设置");
                    break;
                default:
                    alert("未知错误……")
            }
        }
    });
});