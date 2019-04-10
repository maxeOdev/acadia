////logout standard
//$(document).on('click', '#logout', logout);
//
//function logout() {
//let confirmation = prompt('Pour vous déconnecter, veuillez saisir le mot clé \'logout\' ?');
//
//    if(confirmation=='logout') {
//            $.ajax({
//                url: '/logout',
//                type: 'POST',
//                data: {},
//               success: function(){
//               window.location.assign('http://localhost:8080/login');
//               }
//            });
//
//}
//}

//logout advanced
$(document).on("click", ".valid-logout", function() {
  let input = $("#form-delete").val();
  if (input == "logout") {
    $.ajax({
      url: "/logout",
      type: "POST",
      data: {},
      success: function() {
        window.location.assign("http://localhost:8080/login");
      }
    });
  }
});

$(document).on("keydown", "#form-delete", function(event) {
  let key = event.which;
  if(key>=48 && key<=57){
    console.log('Numeric forbidden', key);
    event.preventDefault();
  }

  }
);
