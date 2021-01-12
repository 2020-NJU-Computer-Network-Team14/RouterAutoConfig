$("#add").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/binding",
        data: {
            interface: $("#interface").val(),
            in: $("input[name='inOut']:checked").val(),
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
            in: $("input[name='inOut']:checked").val(),
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

$("#search").click(function () {
    let progress = $.AMUI.progress;
    progress.start();
    $.ajax({
        type: "post",
        url: "/show_interface",
        data: {
            interface: $("#interface1").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            if (e !== null) {
                let outACL = e[0] === "set" ? "未设置" : e[0];
                let inACL = e[1] === "set" ? "未设置" : e[1];
                alert("出站：" + outACL + "，入站：" + inACL);
            }
        }
    });
});