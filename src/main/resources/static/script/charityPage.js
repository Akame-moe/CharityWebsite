var areYouSureText = "Are you sure? You are about to delete the article";

$(document).ready(function(){
    $("#editLink").click(editModeToggle);
    $('[data-toggle="tooltip"]').tooltip({container: 'body'})

    $("#active-button").click(function(){
        var id = $("#active-button").attr("data-value");
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/charity/"+id+"/verify",
            success: function (data, textStatus, xhr) {
                alert("A verify message has been send to your email, please check it!");
            },
            error: function (xhr, textStatus) {
                if (xhr.status == 405)
                    alert("first add an email address for your charity please");
                else
                    alert("There was an error communicating with the server");
                console.log("ERROR : ", xhr.status);
            }
        });
    });
});

function editModeToggle() {

    $(".edit-badge").toggle();
}

function showArticle(id) {
    $('#viewArticleModal').modal('toggle');
    $('#viewArticleLabel').html($("#showArticle_" + id.toString()).html());
    $('#viewArticleBody').html($("#articleDesc_" + id.toString()).html());
}


function editArticle(id) {
    $('#inputEditArticleTitle').attr("value", $("#showArticle_" + id.toString()).html());

    $('#inputEditArticleBody').html($("#articleDesc_" + id.toString()).html());

    $('#inputEditArticleId').html(id);

    $('#editArticleModal').modal('toggle');

}

function addArticle() {
    $('#addArticleModal').modal('toggle');

}


function sendEditArticle() {
    id = $('#inputEditArticleId').html();
    var article = {
        "id": id,
        "title": $('#inputEditArticleTitle').val(),
        "body": $('#inputEditArticleBody').val(),
        "charityId": $("#charityIdDiv").html()
    };
    $.ajax({
        type: "PATCH",
        contentType: "application/json",
        url: "/article",
        data: JSON.stringify(article),
        success: function () {
            $("#showArticle_" + id).html(article.title);
            $("#articleDesc_" + id).html(article.body);
        },
        error: function (e) {
            alert("There was an error communicating with the server");
            console.log("ERROR : ", e);
        }
    });
    $('#editArticleModal').modal('toggle');
}

function sendAddArticle() {
    var article = {
        "title": $('#inputNewArticleTitle').val(),
        "body": $('#inputNewArticleBody').val(),
        "charityId": $("#charityIdDiv").html()
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/article",
        data: JSON.stringify(article),
        success: function () {
            location.reload(true);
        },
        error: function (e) {
            alert("There was an error communicating with the server");
            console.log("ERROR : ", e);
        }
    });
    $('#editArticleModal').modal('toggle');
}

function sendDeleteArticle() {
    var id = $('#inputDeleteArticleId').html();
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/article/" + id,
        success: function () {
            location.reload(true);
        },
        error: function (e) {
            alert("There was an error communicating with the server");
            console.log("ERROR : ", e);
        }
    });
    $('#editArticleModal').modal('toggle');
}

function deleteArticle(id) {
    $('#deleteArticleBody').html(areYouSureText + " \"" + $("#showArticle_" + id.toString()).html() + "\"");
    $('#inputDeleteArticleId').html(id);
    $('#deleteArticleModal').modal('toggle');
}

function loadDescriptionModal() {
    $("#inputDescription").html($("#charityDesc").html());
}

function editCharityName() {
    $("#charityName").html($("#inputName").val());
    sendUpdateCharity();
    $("#editNameModal").modal('toggle');
}

function editCharityDesc() {
    $("#charityDesc").html($("#inputDescription").val());
    sendUpdateCharity();
    $("#editDescriptionModal").modal('toggle');
}

function sendUpdateCharity() {
    var charity = {
        "id": $("#charityIdDiv").html(),
        "name": $("#charityName").html(),
        "description": $("#charityDesc").html()
    };
    $.ajax({
        type: "PATCH",
        contentType: "application/json",
        url: "/charity",
        data: JSON.stringify(charity),
        success: function () {

        },
        error: function (e) {
            alert("There was an error communicating with the server");
            console.log("ERROR : ", e);
        }
    });
}


function logOutUser(){
   var loginType = $("#logOut").attr("data-value");
   if(loginType=="fb")
       logOutFaceBookUser();
   else{
       logOutGoogleUser();
   }
}