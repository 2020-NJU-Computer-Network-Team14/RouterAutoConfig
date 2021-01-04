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
            let std = "", ext = "", s, index;
            for (let acl of aclList) {
                 s = "<div class='am-panel-group' id='acl" + acl[0] + "'>" +
                            "<div class='am-panel am-panel-default'><div class='am-panel-hd am-g'>" +
                                    "<h4 class='am-panel-title am-u-lg-3'" +
                                        "data-am-collapse=\"{parent: '#acl" + acl[0][1] +
                                        "', target: '#list" + acl[0][1] + "'}\">" + acl[0][1] + "</h4>" +
                                    "<button id='del1' type='button' class='am-btn-sm am-btn-warning am-u-lg-2'" +
                                            "onclick='deleteList(id, acl[0][0])'>删除</button></div>" +
                                "<div id='list" + acl[0][1] + "' class='am-panel-collapse am-collapse'>" +
                                    "<div class='am-panel-bd'><table class='am-table'>";
                if (acl[0][0] === "Standard") {
                    s += "<tr><td>允许/禁止</td><td>IP</td><td>反掩码</td></tr>";
                    for (let i = 1; i < acl.length; i++) {
                        s += "<tr><td id='permit" + acl[0][1] + "_" + i + "'>" + acl[i][0] + "</td>" +
                                 "<td id='id" + acl[0][1] + "_" + i + "'>" + acl[i][1] + "</td>" +
                                 "<td id='mask" + acl[0][1] + "_" + i + "'>" + (acl[i][1] === "any" ? "" : acl[i][2]) + "</td>" +
                                 "<td><button id='del" + acl[0][1] + "_" + i + "' type='button'" +
                                             "class='am-btn-sm am-btn-warning' onclick='deleteStandardTerm(id)'>删除</button></td></tr>";
                    }
                    s += "</table></div></div></div></div>";
                    std += s;
                }
                else {
                    s += "<tr><td>允许/禁止</td><td>协议</td><td>源IP</td><td>源反掩码</td><td>目的IP</td>" +
                         "<td>目的反掩码</td><td>端口</td></tr>";
                    for (let i = 1; i < acl.length; i++) {
                        index = 0;
                        s += "<tr><td id='permit" + acl[0][1] + "_" + i + "'>" + acl[i][index++] + "</td>" +
                                 "<td id='protocol" + acl[0][1] + "_" + i + "'>" + acl[i][index++] + "</td>" +
                                 "<td id='srcId" + acl[0][1] + "_" + i + "'>";
                        if (acl[i][index] === "host") {
                            s += acl[i][++index] + "</td><td id='srcMask" + acl[0][1] + "_" + i + "'>0.0.0.0</td>";
                            index++;
                        }
                        else if (acl[i][index] === "any") {
                            s += "any</td><td id='srcMask" + acl[0][1] + "_" + i + "'></td>";
                            index++;
                        }
                        else
                            s += acl[i][index++] + "</td><td id='srcMask" + acl[0][1] + "_" + i + "'>" + acl[i][index++] + "</td>";
                        s += "<td id='destId" + acl[0][1] + "_" + i + "'>";
                        if (acl[i][index] === "host") {
                            s += acl[i][++index] + "</td><td id='destMask" + acl[0][1] + "_" + i + "'>0.0.0.0</td>";
                            index++;
                        }
                        else if (acl[i][index] === "any") {
                            s += "any</td><td id='destMask" + acl[0][1] + "_" + i + "'></td>";
                            index++;
                        }
                        else
                            s += acl[i][index++] + "</td><td id='destMask" + acl[0][1] + "_" + i + "'>" + acl[i][index++] + "</td>";
                        s += "<td id='port" + acl[0][1] + "_" + i + "'>" + (index < acl[i].length ? acl[i][index + 1] : "") + "</td>" +
                             "<td><button id='del" + acl[0][1] + "_" + i + "' type='button'" +
                                         "class='am-btn-sm am-btn-warning' onclick='deleteExtendedTerm(id)'>删除</button></td></tr>";
                    }
                    s += "</table></div></div></div></div>";
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
                "<button id='saveStandard' type='button' class='am-btn am-btn-secondary'>保存</button>" +
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