$(document).ready(function () {
    $("div.header").click(function () {
        $("div.body").slideUp();
        $(this).next().slideDown();
    });
    $(".links DIV").each(function () {
        var strURL = $(this).find("A").attr("href");
        if (window.location.toString().indexOf(strURL) > 0)
            $(this).addClass("active");
    });
    var i = 0;
    var elList = $("#screenchoice");
    var elLink = $("<a href='#'>&lt;&lt;</a>");
    elLink.click(function () { setPage(intPage - 1); });
    elList.append(elLink);
    $("#screens DIV.screen").each(function () {
        var elPage = $("<a href='#' page='" + i.toString() + "'>" + (i + 1).toString() + "</a>");
        elList.append(elPage);
        elPage.click(screen_Click);
        i++;
    });
    elLink = $("<a href='#'>&gt;&gt;</a>");
    elLink.click(function () { setPage(intPage + 1); });
    elList.append(elLink);
    intPages = i;
    setPage(0);
});

var intPage = 0;
var intPages;
function screen_Click()
{
    setPage($(this).attr("page"));
    return false;
}

function setPage(i)
{
    if (i < 0 || i >= intPages) return;
    intPage = i;
    $("#screens DIV.screen").hide()
        .eq(i).show();
    $("#screenchoice A").removeClass("selected")
        .eq(i+1).addClass("selected");
}