let u = window.location.href;
$("title").html(u.slice(u.indexOf("=") + 1));

$.ajax({
    type : "post",
    url : "/show_acl",
    data : {
        router: $("title").html()
    },
    success : function(aclList) {
        if (aclList === "null")
            alert("路由器未连接或出现未知IO异常……");
        else {
            let std = "", ext = "", ss, s;
            for (let acl of aclList) {
                ss = acl[0].split(" ");
                s = "<div class='am-panel-group' id='acl" + ss[4] + "'>" +
                        "<div class='am-panel am-panel-default'><div class='am-panel-hd am-g'>" +
                            "<h4 class='am-panel-title am-u-lg-3'" +
                                "data-am-collapse=\"{parent: '#acl" + ss[4] +
                                "', target: '#list" + ss[4] + "'}\">" + ss[4] + "</h4>" +
                            "<button id='del" + ss[4] + "' type='button' class='am-btn-sm am-btn-warning am-u-lg-2'" +
                                    "onclick=\"deleteList('" + ss[4] + "', '" + ss[0] + "')\">删除</button></div>" +
                        "<div id='list" + ss[4] + "' class='am-panel-collapse am-collapse'>" +
                            "<div class='am-panel-bd'><ul class='am-list'>";
                if (ss[0] === "Standard") {
                    for (let i = 1; i < acl.length; i++)
                        s += "<li><p id='term" + ss[4] + "_" + i + "'>" + acl[i] + "</p><button id='del" + ss[4] + "_" + i + "' type='button'" +
                             "class='am-btn-sm am-btn-warning' onclick=\"deleteTerm(id, 'standard')\">删除</button></li>";
                    s += "</ul></div></div></div></div>";
                    std += s;
                }
                else {
                    for (let i = 1; i < acl.length; i++)
                        s += "<li><p id='term" + ss[4] + "_" + i + "'>" + acl[i] + "</p><button id='del" + ss[4] + "_" + i + "' type='button'" +
                             "class='am-btn-sm am-btn-warning' onclick=\"deleteTerm(id, 'extended')\">删除</button></li>";
                    s += "</ul></div></div></div></div>";
                    ext += s;
                }
            }
            std += "<div class='am-panel-group' id='addStandard'><div class='am-panel am-panel-default'>" +
                "<div class='am-panel-hd'><h4 class='am-panel-title'" +
                     "data-am-collapse=\"{parent: '#addStandard', target: '#addStandard_'}\">添加……</h4></div>" +
                "<div id='addStandard_' class='am-panel-collapse am-collapse'>" +
                "<div class='am-panel-bd'><div class='am-g' style='font-size: 20px'><div class='am-u-lg-3'>" +
                "<input id='idStandard' type='text' class='am-form-field' placeholder='编号/名称'></div><div class='am-u-lg-6'>" +
                "<label class='am-radio-inline' style='font-size: 20px'>" +
                "<input type='radio' name='radioStandard' value='permit' style='width: 20px; height: 20px' checked>&nbsp;允许</label>" +
                "<label class='am-radio-inline' style='font-size: 20px'>" +
                "<input type='radio' name='radioStandard' value='deny' style='width: 20px; height: 20px'>&nbsp;禁止</label>" +
                "</div><div class='am-u-lg-3'>" +
                "<button id='saveStandard' type='button' class='am-btn am-btn-secondary' onclick='saveStandard()'>保存</button>" +
                "</div></div><br><div class='am-g'><div class='am-u-lg-6'>" +
                "<input id='ipStandard' type='text' class='am-form-field' placeholder='IP'></div><div class='am-u-lg-6'>" +
                "<input id='maskStandard' type='text' class='am-form-field' placeholder='反掩码'>" +
                "</div></div></div></div></div></div>";
            $("#tabStandard").html(std);

            ext += "<div class='am-panel-group' id='addExtended'><div class='am-panel am-panel-default'>" +
                       "<div class='am-panel-hd'><h4 class='am-panel-title'" +
                            "data-am-collapse=\"{parent: '#addExtended', target: '#addExtended_'}\">添加……</h4></div>" +
                       "<div id='addExtended_' class='am-panel-collapse am-collapse'><div class='am-panel-bd'>" +
                           "<div class='am-g' style='font-size: 20px'><div class='am-u-lg-3'>" +
                               "<input id='idExtended' type='text' class='am-form-field' placeholder='编号/名称'></div>" +
                               "<div class='am-u-lg-6'>" +
                               "<label class='am-radio-inline' style='font-size: 20px'>" +
                                   "<input type='radio' name='radioExtended' value='permit' style='width: 20px; height: 20px' checked>&nbsp;允许</label>" +
                               "<label class='am-radio-inline' style='font-size: 20px'>" +
                                   "<input type='radio' name='radioExtended' value='deny' style='width: 20px; height: 20px'>&nbsp;禁止</label></div>" +
                               "<div class='am-u-lg-3'><button id='saveExtended' type='button' class='am-btn am-btn-secondary'>保存</button>" +
                               "</div></div><br><div class='am-g'><div class='am-u-lg-6'>" +
                                   "<input id='srcIpExtended' type='text' class='am-form-field' placeholder='源IP'>" +
                       "</div><div class='am-u-lg-6'>" +
                       "<input id='srcMaskExtended' type='text' class='am-form-field' placeholder='源反掩码'>" +
                       "</div></div><br><div class='am-g'><div class='am-u-lg-6'>" +
                       "<input id='destIpExtended' type='text' class='am-form-field' placeholder='目的IP'>" +
                       "</div><div class='am-u-lg-6'>" +
                       "<input id='destMaskExtended' type='text' class='am-form-field' placeholder='目的反掩码'>" +
                       "</div></div><br><div class='am-g'><div class='am-u-lg-6'>" +
                       "<input id='protocol' type='text' class='am-form-field' placeholder='协议'>" +
                       "</div><div class='am-u-lg-6'>" +
                       "<input id='port' type='text' class='am-form-field' placeholder='端口'>" +
                       "</div></div></div></div></div>"
            $("#tabExtended").html(ext);
        }
    }
})