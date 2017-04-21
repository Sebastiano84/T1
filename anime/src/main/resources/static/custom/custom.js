 $( document ).ready(function() {
      var transformText = {"<>":"font","color":"${color}","html":" ${text}"};
      var transformTMessages =  {"<>":"p","html":[{"<>":"font","color":"fa ${color}","html":" ${text}"}]};

      var token = $("meta[name=_csrf]").attr("content")
      var header = "X-CSRF-TOKEN";
      $(document).ajaxSend(function(e, xhr, options) {
          xhr.setRequestHeader(header, token);
      });
      var timeoutId;
      $("#animeCode").on("change paste keyup", function() {
        while (timeoutId--) {
          window.clearTimeout(timeoutId); // will do nothing if no timeout with id is present
        }
        timeoutId = setTimeout(function(){
           $.ajax({
             type: "POST",
             url: "validateCode",
             contentType: 'text/plain',
             data: $("#animeCode").val(),
             dataType: "json",
             success: function(data){
                $("#animeCodeValidation").html('')
                $("#animeCodeValidation").json2html(data['words'],transformText);
                $("#animeCodeValidation").append('<br>');
                $("#animeCodeValidation").json2html(data['errors'],transformTMessages);
             },
             error: function(data){
                $("#animeCodeValidation").html(data);
             }
           });

         },5000);
         console.log(timeoutId);

      });
  });