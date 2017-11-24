$(document).ready(function () {
    $(".charity-desc-p").each(function () {
        if ($(this).html().length > 300) {
            var words = $(this).html().substring(0, 300).split(" ");
            if (words.length > 1) {
                words = words.slice(0, -1);
            }
            $(this).html(words.join(" ") + " ...");
        }
    });


    /*$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/searchKeys",
        success: function (resultArray) {
            for (var i=0;i<resultArray.length;i++){
                //console.log(resultArray[i]);
                //var option = $("<option>").val(11).text(resultArray[i]);
                $("#conditionKey").append(   "<option value='"    + resultArray[i]+   "'>"   + resultArray[i]+  "</option>"   );
               // var option = $'<option value="'+i+'">'+resultArray[i]+'</option>';
                //$("#conditionKey").append(option);
            }
        },
        error: function (e) {
            console.log("There was an error communicating with the server");
            console.log("ERROR : ", e);
        }
    });*/

});
function fillConditionValue(){
    var selected = $('select  option:selected').val();

    if(selected == 'name'){
           $("#conditionValue").empty();
    }
    if(selected == 'cause'){
       $("#conditionValue").empty();
       $.ajax({
               type: "GET",
               contentType: "application/json",
               url: "/causes",
               success: function (resultArray) {
                    //alert(resultArray[0]);
                   for (var i=0;i<resultArray.length;i++){

                       //console.log(resultArray[i]);
                       //var option = $("<option>").val(11).text(resultArray[i]);

                       $("#conditionValue").append(   "<option value='"    + resultArray[i]+   "'>"   + resultArray[i]+  "</option>"   );

                      // var option = $'<option value="'+i+'">'+resultArray[i]+'</option>';
                       //$("#conditionKey").append(option);
                   }
               },
               error: function (e) {
                   console.log("There was an error communicating with the server");
                   console.log("ERROR : ", e);
               }
           });
    }


    if(selected == 'country'){
        $("#conditionValue").empty();
           $.ajax({
                   type: "GET",
                   contentType: "application/json",
                   url: "/countries",
                   success: function (resultArray) {
                       for (var i=0;i<resultArray.length;i++){
                           //console.log(resultArray[i]);
                           //var option = $("<option>").val(11).text(resultArray[i]);
                           $("#conditionValue").append(   "<option value='"    + resultArray[i]+   "'>"   + resultArray[i]+  "</option>"   );
                          // var option = $'<option value="'+i+'">'+resultArray[i]+'</option>';
                           //$("#conditionKey").append(option);
                       }
                   },
                   error: function (e) {
                       console.log("There was an error communicating with the server");
                       console.log("ERROR : ", e);
                   }
               });
        }


}

function searchBarKeyDown(key) {
    if (key.keyCode === 13)
        $('#searchFormSubmit').click();
}

function searchSubmit(){
var xmlhttp;
if (window.XMLHttpRequest)
{
    //  IE7+ Firefox Chrome Opera Safari
    xmlhttp=new XMLHttpRequest();
}
else
{
    // IE6 IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
}