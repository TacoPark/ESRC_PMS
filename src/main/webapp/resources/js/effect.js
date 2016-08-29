//nav_bar 마우스 hover 효과
$(document).ready(function() {
    $('.nav_btn').hover(function() {
        $(this).find('i').addClass('icon-white')
    }, function() {
        $(this).find('i').removeClass('icon-white')
    });
});

//과제정보 session 보여주기
$(document).ready(function() {
    $(".pjInfo").click(function() {
        $('.session').removeClass('visited');
        $(this).addClass('visited');
    });
});

// 연구내용 session 보여주기
$(document).ready(function() {
    $(".pjContent").click(function() {
        $('.session').removeClass('visited');
        $(this).addClass('visited');
    });
});

// 연구참여자 session 보여주기
$(document).ready(function() {
    $(".pjMembers").click(function() {
        $('.session').removeClass('visited');
        $(this).addClass('visited');
    });
});

// 첨부파일 session 보여주기
$(document).ready(function() {
    $(".pjAttachment").click(function() {
        $('.session').removeClass('visited');
        $(this).addClass('visited');
    });
});

// phone_ver에서 nav 효과
$(document).ready(function() {
    $("#nav_phone_ver").hide();
    $('.header_btn').click(function() {
        $("#nav_phone_ver").slideToggle('fast');
    });
});
