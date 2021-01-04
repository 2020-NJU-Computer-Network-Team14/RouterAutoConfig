$("#add").click(function () {
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
            if (e === "Success")
                window.location.reload();
            else
                alert(e);
        }
    });
});

$("#remove").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/noBinding",
        data: {
            interface: $("#interface").val(),
            in: $("input[name='radioNum']:checked").val(),
            name: $("#name").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            if (e === "Success")
                window.location.reload();
            else
                alert(e);
        }
    });
});