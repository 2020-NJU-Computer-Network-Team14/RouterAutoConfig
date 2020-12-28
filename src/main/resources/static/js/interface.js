$("#confirm").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/interface",
        data: {
            interface: $("#interface").val(),
            ip: $("#ip").val(),
            mask: $("#mask").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
        }
    });
});