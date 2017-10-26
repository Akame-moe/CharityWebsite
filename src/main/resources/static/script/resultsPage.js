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
});