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
        }
    });
});