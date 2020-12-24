let url = window.location.href;
$("title").html(url.slice(url.indexOf("=") + 1));
$("#int").attr("href", "interface.html?router=" + $("title").html());
$("#rout").attr("href", "route.html?router=" + $("title").html());
$("#edit").attr("href", "edit.html?router=" + $("title").html());
$("#conf").attr("href", "config.html?router=" + $("title").html());
$("#pi").attr("href", "ping.html?router=" + $("title").html());
$("#com").attr("href", "command.html?router=" + $("title").html());