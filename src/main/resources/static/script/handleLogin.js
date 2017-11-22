
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/user",
        beforeSend: function (xhr, settings) {
            if (settings.type == 'POST' || settings.type == 'PUT'
                || settings.type == 'DELETE') {
                xhr.setRequestHeader("X-XSRF-TOKEN",
                    Cookies.get('XSRF-TOKEN'));
            }
        },
        success: function (data, textStatus, xhr) {
            console.log(data);
            if (xhr.status == 200 && data.hasOwnProperty("userAuthentication")) {
                console.log(xhr);
                $("#welcomeLabel").html(data.userAuthentication.details.name);
                $(".unauthenticated").hide();
                $(".authenticated").show();
            } else {
                $(".unauthenticated").show();
                $(".authenticated").hide();
            }
        },
        error: function (xhr, textStatus) {
            $(".unauthenticated").show();
            $(".authenticated").hide();
        }
    });
});



function logOutUser() {
    $.ajax({
        type: "POST",
        url: "/logout",
        beforeSend: function (xhr, settings) {
            if (settings.type == 'POST' || settings.type == 'PUT'
                || settings.type == 'DELETE') {
                xhr.setRequestHeader("X-XSRF-TOKEN",
                    Cookies.get('XSRF-TOKEN'));
            }
        },
        success: function (data, textStatus, xhr) {
            $("#welcomeLabel").html('');
            $(".unauthenticated").show();
            $(".authenticated").hide();
        },
        error: function (xhr, textStatus) {
            console.log(xhr)
        }
    });
}