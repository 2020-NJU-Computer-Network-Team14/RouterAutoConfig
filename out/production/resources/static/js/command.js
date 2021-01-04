$("#command").keypress(function(e) {
　　if (e.keyCode == 13) {
      $("#output").append($("#command").val() + "<br>")
      $.ajax({
          type: "post",
          url: "/command",
          data: {
              command: $("#command").val(),
              router: $("title").html()
          },
          success: function (e) {
              if (e === "路由器未连接！" || e === "未知IO异常……")
                  alert(e);
              else
                $("#output").append(e)
          }
      });
      $("#command").val("")
  }
})

