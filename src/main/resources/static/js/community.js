$(document).ready(function () {
    get_posts()
})

//댓글 작성
function post() {
    let comment = $("#textarea-post").val().replace(/(?:\r\n|\r|\n)/g, '<br />');
    let today = new Date().toISOString()
    let matjip_name = $("#matjip_name").text()

    $.ajax({
        type: "POST",
        url: "/community/posting",
        data: {
            matjip_name_give: matjip_name,
            comment_give: comment,
            date_give: today
        },
        success: function (response) {
            window.location.reload()
        }
    })
}

// 댓글 리스트 가져오기
function get_posts() {
    let matjip_name = $("#matjip_name").text()
    $("#post-box").empty()

    $.ajax({
        type: "GET",
        url: `/community/get_post?matjip_name_give=${matjip_name}`,
        data: {},
        success: function (response) {
            let posts = response["posts"]
            for (let i = 0; i < posts.length; i++) {
                let post = posts[i]
                let time_post = new Date(post["date"])
                let time_before = time2str(time_post)

                let html_temp = `<div class="box comment-list">
                                    <article class="media">
                                        <div class="media-left">
                                            <a class="image is-64x64" href="/user/${post['user_mail']}">
                                                <img class="is-rounded" src="/static/${post['user_pic_real']}"
                                                         alt="Image">
                                            </a>                                        
                                        </div>                                      
                                        <div class="media-content">
                                            <div class="content">
                                                <p>
                                                    <strong>${post['user_name']}</strong> <small>${time_before}</small>
                                                    <a type="button" class="delete-comment" onclick="delete_comment('${post['user_mail']}','${post['user_name']}','${post['date']}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                                    <br>
                                                    ${post['comment']}                                                  
                                                </p>
                                            </div>
                                        </div>
                                    </article>
                                </div>`
                $("#post-box").append(html_temp)

            }

        }
    })

}

//댓글 작성 시간 계산
function time2str(date) {
    let today = new Date()
    let time = (today - date) / 1000 / 60  // 분

    if (time < 60) {
        return parseInt(time) + "분 전"
    }
    time = time / 60  // 시간
    if (time < 24) {
        return parseInt(time) + "시간 전"
    }
    time = time / 24
    if (time < 7) {
        return parseInt(time) + "일 전"
    }
    return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`
}


//댓글 삭제
function delete_comment(user_mail, user_name, date){
    $.ajax({
        type: "DELETE",
        url: `/community/delete`,
        data: {
            user_mail_give:user_mail,
            user_name_give:user_name,
            date_give:date
        },
        success: function (response) {
            if (response["msg"] == "삭제 완료!!") {
                alert(response["msg"])
                window.location.reload()
            } else {
                alert(response["msg"])
            }
        }

    })

}
