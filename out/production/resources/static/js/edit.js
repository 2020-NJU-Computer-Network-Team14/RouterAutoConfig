let progress = $.AMUI.progress;

$("#saveNum").click(function () {
    progress.start();
    $.ajax({
        type: "post",
        url: "/",
        data: {
            num: $("#num").val(),
            permit: $("input[name='radioNum']:checked").val(),
            ip: $("#ipNum").val(),
            mask: $("#maskNum").val(),
            protocol: $("#protocolNum").val(),
            port: $("#portNum").val()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
});

$("#saveName").click(function () {
    progress.start();
    $.ajax({
        type: "post",
        url: "/",
        data: {
            name: $("#name").val(),
            permit: $("input[name='radioNum']:checked").val(),
            ip: $("#ipName").val(),
            mask: $("#maskName").val(),
            protocol: $("#protocolName").val(),
            port: $("#portName").val()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
});