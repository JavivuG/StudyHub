/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    $(document).on('click', '.vote', function () {
        var commentId = $(this).attr("data-id");
        var like = $(this).attr("data-like");
        $.ajax({
            type: "POST",
            url: "./VoteComment",
            data: {like: like, idComentario: commentId},
            success: function (response) {
                document.getElementById("likeCount-" + response.id).innerHTML = response.likes + " likes";
                document.getElementById("dislikeCount-" + response.id).innerHTML = response.dislikes + " dislikes";
                if (response.userVote == 1) {
                    document.getElementById("like-" + response.id).src = "images/like.svg";
                    document.getElementById("dislike-" + response.id).src = "images/dislike-blanco.svg";
                } else if (response.userVote == -1) {
                    document.getElementById("like-" + response.id).src = "images/like-blanco.svg";
                    document.getElementById("dislike-" + response.id).src = "images/dislike.svg";
                } else {
                    document.getElementById("like-" + response.id).src = "images/like-blanco.svg";
                    document.getElementById("dislike-" + response.id).src = "images/dislike-blanco.svg";
                }
            }
        });
    });
});