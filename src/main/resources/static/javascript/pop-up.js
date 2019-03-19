

$(document).on('click', '#deleteButton', popup);


function popup() {
let confirmation = confirm('Confirmez vous la suppression de l\'utilisateur ?');

    if(confirmation) {
            $.ajax({
                url: '/admin/user-delete',
                type: 'DELETE',
                data: { uuid: $('#uuidUser').text() },
               success: window.location.pathname='/admin/users'
            });   

}
}




