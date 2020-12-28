$("#telnet").click(function () {
    window.location.href = "interface.html?router=" + $("#router").val();
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
            window.location.href = "interface.html?router=" + $("#router").val();
        }
    });
});
