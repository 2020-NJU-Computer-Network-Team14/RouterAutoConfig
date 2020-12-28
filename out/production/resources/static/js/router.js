let url = window.location.href;
$("title").html(url.slice(url.indexOf("=") + 1));
$("#int").attr("href", "inter?router=" + $("title").html());
$("#rout").attr("href", "rou?router=" + $("title").html());
$("#edit").attr("href", "ed?router=" + $("title").html());
$("#conf").attr("href", "conf?router=" + $("title").html());
$("#pi").attr("href", "pi?router=" + $("title").html());
$("#com").attr("href", "cmd?router=" + $("title").html());