let progress = $.AMUI.progress;

$("#saveStandard").click(function () {
    progress.start();
    $.ajax({
        type: "post",
        url: "/standard",
        data: {
            id: $("#idStandard").val(),
            permit: $("input[name='radioStandard']:checked").val(),
            ip: $("#ipStandard").val(),
            mask: $("#maskStandard").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
});

$("#saveExtended").click(function () {
    progress.start();
    $.ajax({
        type: "post",
        url: "/extend",
        data: {
            id: $("#idExtended").val(),
            permit: $("input[name='radioExtended']:checked").val(),
            srcIp: $("#srcIpExtended").val(),
            srcMask: $("#srcMaskExtended").val(),
            destIp: $("#destIpExtended").val(),
            destMask: $("#destMaskExtended").val(),
            protocol: $("#protocol").val(),
            port: $("#port").val(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
});

function deleteList(id) {
    progress.start();
    $.ajax({
        type: "post",
        url: "/deleteList",
        data: {
            id: id,
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
}

function deleteStandardTerm(id) {
    progress.start();
    let i = id.slice(3, id.length)
    $.ajax({
        type: "post",
        url: "/delStdTerm",
        data: {
            id: i.slice(0, i.indexOf("_")),
            permit: $("#permit" + i).html(),
            ip: $("#ip" + i).html(),
            mask: $("#mask" + i).html(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
}

function deleteExtendedTerm(id) {
    progress.start();
    let i = id.slice(3, id.length)
    $.ajax({
        type: "post",
        url: "/delExtTerm",
        data: {
            id: i.slice(0, i.indexOf("_")),
            permit: $("#permit" + i).html(),
            ip: $("#ip" + i).html(),
            mask: $("#mask" + i).html(),
            srcIp: $("#srcIp" + i).html(),
            srcMask: $("#srcMask" + i).html(),
            destIp: $("#destIp" + i).html(),
            destMask: $("#destMask" + i).html(),
            protocol: $("#protocol" + i).html(),
            port: $("#port" + i).html(),
            router: $("title").html()
        },
        success: function (e) {
            progress.done();
            window.location.reload();
        }
    });
}