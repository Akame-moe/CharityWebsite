function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log(googleUser);
    document.getElementById("welcomeLabel").innerHTML="Welcome,"+profile.getName();
    document.getElementById("login").setAttribute("hidden",true);
    document.getElementById("logOut").removeAttribute("hidden");
    $("#logOut").attr("data-value","google");
    document.getElementById("loginModelCloseButton").click();

    // Send the id_token to back_end
    var xhr = new XMLHttpRequest();
    var id_token = googleUser.getAuthResponse().id_token;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/oauth/google/tokenString",
        data: JSON.stringify(id_token),
        success: function () {
            console.log("Send the id_token to the backend successfully!");
        },
        error: function (e) {
            console.log("There was an error communicating with the server");
            console.log("ERROR : ", e);
        }
    });

}

function logOutGoogleUser() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
        document.getElementById("welcomeLabel").innerHTML="";
        document.getElementById("logOut").setAttribute("hidden",true);
        document.getElementById("login").removeAttribute("hidden");
        document.getElementById("loginModelCloseButton").click();
    });
}