/**
 *
 */

//Заполнение таблицы User

fetch("http://localhost:8080/api/users")
    .then(res => {res.json()
        .then(data => {
                console.log(data);
                if(data.length > 0){
                    let temp = "";

                    //----star for loop


                    data.forEach((u)=>{

                        let userRole = "";
                        for (let i = 0; i < u.roles.length; i++) {
                            userRole += " " + u.roles[i].authority.substring(5);
                        }

                        temp += `<tr>
                        <td>${u.username}</td>
                        <td>${u.surname}</td>
                        <td>${u.id}</td>
                        <td>${u.password}</td>
                        <td>${u.age}</td>
                        <td>${u.email}</td>
                        <td>${userRole}</td>
                        <td> <a href="/api/${u.id}" class="btn btn-danger dBtn"
                            data-toggle="modal">Delete</a> </td>
                         <td> <a href="/api/${u.id}" class="btn btn-primary eBtn">Edit</a> </td>
                        </tr>`;
                    })

                    //--close for loop

                    document.getElementById("data").innerHTML = temp;
                }
            }
        )

    })





// Заполнение модульного окна Edit
$(document).ready(function (){

    $('.table .eBtn').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        $.get(href, function (user, status) {
            $('.myForm #id').attr('readonly','readonly').val(user.id);
            $('.myForm #username').val(user.username);
            $('.myForm #surname').val(user.surname);
            $('.myForm #age').val(user.age);
            $('.myForm #email').val(user.email);
            $('.myForm #password').val(user.password);

        });

        $('.myForm #exampleModal').modal();

    });
});

// Заполнение модульного окна NewUser
$(document).ready(function (){

    $(' .table .dBtn').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.myFormDelete #id1').attr('readonly','readonly').val(user.id);
            $('.myFormDelete #username1').attr('readonly','readonly').val(user.username);
            $('.myFormDelete #surname1').attr('readonly','readonly').val(user.surname);
            $('.myFormDelete #age1').attr('readonly','readonly').val(user.age);
            $('.myFormDelete #email1').attr('readonly','readonly').val(user.email);
            $('.myFormDelete #password1').attr('readonly','readonly').val(user.password);

        });

        $('.deleteForm #exampleModal').modal();

    });
});

// Заполнение таблицы User



// Заполнение модульного окна Edit
document.addEventListener('click', function (event) {
    event.preventDefault()

    if ($(event.target).hasClass('eBtn')) {
        let href = $(event.target).attr("href");

        $.get(href, function (user) {
            $('.myForm #id').attr('readonly','readonly').val(user.id);
            $('.myForm #username').val(user.username);
            $('.myForm #surname').val(user.surname);
            $('.myForm #age').val(user.age);
            $('.myForm #email').val(user.email);
            $('.myForm #password').val(user.password);

        });

        $('.myForm #exampleModal').modal();

    };

    if ($(event.target).hasClass('delBtn')) {
        let href = $(event.target).attr("href");

        $.get(href, function (user) {
            $('.myFormDelete #id1').attr('readonly','readonly').val(user.id);
            $('.myFormDelete #username1').attr('readonly','readonly').val(user.username);
            $('.myFormDelete #surname1').attr('readonly','readonly').val(user.surname);
            $('.myFormDelete #age1').attr('readonly','readonly').val(user.age);
            $('.myFormDelete #email1').attr('readonly','readonly').val(user.email);
            $('.myFormDelete #password1').attr('readonly','readonly').val(user.password);

        });

        $('.myFormDelete #exampleModalDelete').modal();

    };

    //   if ($(event.target).hasClass('logout')) {
    //       logout();
    //   }
    //   if ($(event.target).hasClass('btnForUser')) {
    //       userTable();
    //   }



});

//document.removeEventListener();

function userTable() {
    document.location.replace("/client");
}