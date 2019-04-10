
// Dial on delete User
$(document).on('click', '#deleteButton', popup);


function popup() {
let confirmation = confirm('Confirmez vous la suppression de l\'utilisateur ?');

    if(confirmation) {
            $.ajax({
                url: '/admin/user-delete',
                type: 'DELETE',
                data: { uuid: $('#uuidUser').text() },
               success: function(){
               window.location.assign('http://localhost:8080/admin/users');
               }
            });   

}
}




