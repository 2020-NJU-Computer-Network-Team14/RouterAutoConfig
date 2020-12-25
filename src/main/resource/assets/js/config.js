$("#confirm").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/binding",
        data: {
            interface: $("#interface").val(),
            in: $("input[name='radioNum']:checked").val(),
            name: $("#name").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
        }
    });
});