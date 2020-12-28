$.ajax({
    type : "post",
    url : "/show",
    data : "",
    success : function(aclList) {
        for (let acl of aclList)
            $("#tabNum").html("<div class='am-panel-group' id='acl" + acl[0] + "'>" +
                                  "<div class='am-panel am-panel-default'>" +
                                      "<div class='am-panel-hd'>" +
                                          "<h4 class='am-panel-title' data-am-collapse=\"{parent: '#acl" +
                                          acl[0] + "', target: '#list" + acl[0] + "'}\">" + acl[0] + "</h4></div>" +
                                      "<div id='list" + acl[0] + "' class='am-panel-collapse am-collapse'>" +
                                          "<div class='am-panel-bd'>" +
                                              "<ul class='am-list'>" +
                                                  "<li>添加人员</li>" +
                                                  "<li>人员列表</li>" +
                                              "</ul></div></div></div></div>");
    },
    error : function () {
    }
})