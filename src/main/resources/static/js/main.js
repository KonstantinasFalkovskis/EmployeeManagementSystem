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


        // if (text == 'View') {
        //     $.get(href, function (employee, status) {
        //         $('.myFormView #id').val(employee.id);
        //         $('.myFormView #name').val(employee.name);
        //         $('.myFormView #email').val(employee.email);
        //         $('.myFormView #salary').val(employee.salary);
        //         $('.myFormView #departamentId').val(employee.departamentId);
        //     });
        //      $('.myFormView #viewModal').modal();
        // }
        //for update user
        if (text == 'Edit') {
            $.get(href, function (employee, status) {
                $('.myFormUpdate #id').val(employee.id);
                $('.myFormUpdate #name').val(employee.name);
                $('.myFormUpdate #email').val(employee.email);
                $('.myFormUpdate #salary').val(employee.salary);
                $('.myFormUpdate #departamentId').val(employee.departamentId);
            });
            $('.myFormUpdate #updateModal').modal();
        } else {
            //for creating user
            $('.myFormCreate #name').val('');
            $('.myFormCreate #email').val('');
            $('.myFormCreate #salary').val('');
            $('.myFormCreate #departamentId').val('');
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



