let progress = $.AMUI.progress;

function saveStandard() {
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
            if (e === "Success")
                window.location.reload();
            else
                alert(e);
        }
    });
}

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
            if (e === "Success")
                window.location.reload();
            else
                alert(e);
        }
    });
});

function deleteList(id, std) {
    progress.start();
    $.ajax({
        type: "post",
        url: "/deleteList",
        data: {
            id: id,
            std: std,
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
}

function deleteTerm(id, std) {
    progress.start();
    let i = id.slice(3, id.length)
    $.ajax({
        type: "post",
        url: "/deleteTerm",
        data: {
            id: i.slice(0, i.indexOf("_")),
            std: std,
            term: $("#term" + i).html(),
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
}

// function deleteExtendedTerm(id, std) {
//     progress.start();
//     let i = id.slice(3, id.length)
//     $.ajax({
//         type: "post",
//         url: "/delExtTerm",
//         data: {
//             id: i.slice(0, i.indexOf("_")),
//             permit: $("#permit" + i).html(),
//             protocol: $("#protocol" + i).html(),
//             srcIp: $("#srcIp" + i).html(),
//             srcMask: $("#srcMask" + i).html(),
//             destIp: $("#destIp" + i).html(),
//             destMask: $("#destMask" + i).html(),
//             port: $("#port" + i).html(),
//             router: $("title").html()
//         },
//         success: function (e) {
//             progress.done();
//             if (e === "Success")
//                 window.location.reload();
//             else
//                 alert(e);
//         }
//     });
// }