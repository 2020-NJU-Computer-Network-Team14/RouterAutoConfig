$("#confirm").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/route",
        data: {
            prefix: $("#prefix").val(),
            mask: $("#mask").val(),
            next: $("#next").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
        }
    });
});