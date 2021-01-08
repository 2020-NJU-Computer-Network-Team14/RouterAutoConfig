$("#telnet").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/telnet",
        data: {
            ip: $("#ip").val(),
            password: $("#password").val(),
            enable: $("#enable").val(),
            router: $("#router").val()
        },
        success: function (e) {
            progress.done();
            if (e === "Success"||e ==="路由器telnet已经处于连接状态，无需重新连接")
                window.location.href = "inter?router=" + $("#router").val();
            else
                alert(e);
        }
    });
});
