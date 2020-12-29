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
              $("#output").append(e)
          }
      });
      $("#command").val("")
  }
})

