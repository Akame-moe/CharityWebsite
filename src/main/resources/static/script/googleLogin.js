function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    document.getElementById("welcomeLabel").innerHTML="Welcome,"+profile.getName();
    document.getElementById("login").setAttribute("hidden",true);
    document.getElementById("logOut").removeAttribute("hidden");
    $("#logOut").attr("data-value","google");
    document.getElementById("loginModelCloseButton").click();
    // console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    // console.log('Name: ' + profile.getName());
    // console.log('Image URL: ' + profile.getImageUrl());
    // console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
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