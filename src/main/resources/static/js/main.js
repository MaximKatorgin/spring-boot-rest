
let userTable = document.querySelector('.user-table');
const url = "http://localhost:8080/admin/users";


function showUsers() {
// UserTable request
    fetch(url)
        .then(res => res.json())
        .then(data => {
            renderUsers(data);
        })
}

//UserTable render
const renderUsers = (usersData) => {

    userTable.innerHTML = '';
    let table = '';
    // usersData = JSON.parse(usersData);
    usersData.forEach(row => {
        table += `<tr><td>${row.id}</td>
                        <td>${row.name}</td>
                        <td>${row.lastName}</td>
                        <td>${row.age}</td>
                        <td>${row.email}</td>
                        <td>${row.rolesString}</td>
                        <td>
                            <a href="admin/users/${row.id}" class="btn btn-info editModalBtn" 
                            data-toggle="modal" data-target="#editUserModal">Edit</a>
                        </td>
                        <td>
                            <a href="admin/users/${row.id}" class="btn btn-danger delModalBtn" 
                            data-toggle="modal" data-target="#deleteUserModal">Delete</a>
                        </td>
                        </tr>`
    });
    userTable.innerHTML = table;
}



//Листенер на кнопку открытия модалки на изменение
$(document).delegate('.editModalBtn', 'click', function (event) {
    event.preventDefault();

    let href = $(this).attr('href');
    $.get(href, function (user, status) {
        $('.editUserForm #edit_id').val(user.id);
        $('.editUserForm #edit_name').val(user.name);
        $('.editUserForm #edit_lastName').val(user.lastName);
        $('.editUserForm #edit_age').val(user.age);
        $('.editUserForm #edit_email').val(user.email);
        $('.editUserForm #edit_password').val();
        $('.editUserForm #edit_role').val(user.roles);
    });
    $('#editModal').modal("show");
});

//Лисенер на кнопку подтверждения изменения
const editButton = document.querySelector('.edit_confirm')

editButton.addEventListener('click', (e) => {
    e.preventDefault();

    fetch(url + "/" + document.querySelector('.editUserForm #edit_id').value, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.querySelector('#edit_id').value,
            name: document.querySelector('#edit_name').value,
            lastName: document.querySelector('#edit_lastName').value,
            age: document.querySelector('#edit_age').value,
            email: document.querySelector('#edit_email').value,
            password: document.querySelector('#edit_password').value,
            roles: getSelectedRoles(document.querySelector('#edit_roles'))
        })
    })
        .then(() => showUsers())
    $('#editUserModal').modal('hide');
})





//лисенер на кнопку открытия формы на удаление
$(document).delegate('.delModalBtn', 'click' ,function (e) {
    e.preventDefault();
    let href = $(this).attr('href');

    $.get(href, function (user, status) {
        $('.deleteUserForm #del_id').val(user.id);
        $('.deleteUserForm #del_name').val(user.name);
        $('.deleteUserForm #del_lastName').val(user.lastName);
        $('.deleteUserForm #del_age').val(user.age);
        $('.deleteUserForm #del_email').val(user.email);
        $('.deleteUserForm #del_roles').val(user.roles.forEach(role => console.log(role)));
    });
});

//лисенер на кнопку подтверждения удаления
const delButton = document.querySelector('.del_confirm')

delButton.addEventListener('click', (e) => {
    e.preventDefault();

    fetch(url + '/' + document.querySelector('.deleteUserForm #del_id').value, {
        method: 'DELETE',
    })
        // .then((e) => console.log(e))
        .then(() => showUsers())

    $('#deleteUserModal').modal('hide');

})






//лисенер на подтверждение создания нового пользователя
const addNewUserForm = document.querySelector('.addNewUser');

addNewUserForm.addEventListener('submit', (e) => {
    e.preventDefault();

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: document.getElementById('new_name').value,
            lastName: document.getElementById('new_lastName').value,
            age: document.getElementById('new_age').value,
            email: document.getElementById('new_email').value,
            password: document.getElementById('new_password').value,
            roles: getSelectedRoles(document.getElementById('new_userRoles'))
        })
    })
        .then(res => res.json())
        .then(() => {
            showUsers();
        });

        document.getElementById('new_name').value = '';
        document.getElementById('new_lastName').value = '';
        document.getElementById('new_age').value = '';
        document.getElementById('new_email').value = '';
        document.getElementById('new_password').value = '';
        document.getElementById('new_userRoles').value = '';

})


//функция обертки выбранных ролей в объект для вставки в поле пользователя
function getSelectedRoles(select) {
    let roleSelect = select;

    let rolesArray = [];

    for (let i = 0; i < roleSelect.length; i++) {
        if (roleSelect.options[i].selected) {
            let roleObject = {};
            roleObject['id'] = +roleSelect.options[i].value;
            roleObject['role'] = roleSelect.options[i].id;
            rolesArray.push(roleObject);
        }
    }
    return rolesArray;
}



//первоначальная подгрузка таблицы пользователей;
showUsers();

