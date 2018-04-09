/**
 * @Author Falco Constantine
 * @Project Employee Management System
 * @Version 1.1
 * @Date 2018.04.08
 *
 */
$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        //for update user
        if (text == 'Edit') {
            $.get(href, function (users, status) {
                $('.myFormUpdate #id').val(users.id);
                $('.myFormUpdate #username').val(users.username);
                $('.myFormUpdate #password').val(users.password);
                $('.myFormUpdate #email').val(users.email);
            });
            $('.myFormUpdate #updateModal').modal();
        } else {
            //for creating user
            $('.myFormCreate #username').val('');
            $('.myFormCreate #password').val('');
            $('.myFormCreate #email').val('');
            $('.myFormCreate #myModalCreate').modal();
        }
    });
    //for delete user
    $('.table .delBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#removeModalCenter #delRef').attr('href', href);
        $('#removeModalCenter').modal();
    });
});



