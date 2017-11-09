
// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    console.log(response.status);
    document.getElementById("loginModelCloseButton").click();
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        FB.api('/me', function(userInfo) {
            document.getElementById("welcomeLabel").innerHTML="Welcome,"+userInfo.name;
            document.getElementById("login").setAttribute("hidden",true);
            document.getElementById("logOut").removeAttribute("hidden");
        });
    } else{
        document.getElementById("welcomeLabel").innerHTML="";
        document.getElementById("logOut").setAttribute("hidden",true);
        document.getElementById("login").removeAttribute("hidden");
    }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

// Logout The Current User with their FB Account
function logOutUser(){
    FB.logout(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '893012947524181',
        cookie     : true,
        xfbml      : true,
        version    : 'v2.11'
    });

    FB.AppEvents.logPageView();

    // Now that we've initialized the JavaScript SDK, we call
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};

(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
