
$(document).ready(function () {
loadTrainers();
})

$(document).on('click','.list-category', loadTrainers)



function loadTrainers() {
$('#content').load('/admin/trainers-template-list',

function (){
    $.ajax({
        url: "/admin/trainers-list",
        type: "GET",
        data: {pageNumber: 0},
        success: function(trainersPage) {
        let trainers = trainersPage['content'];
        //let trainers = trainersPage.content;
            $('#content').html(handleBarsGenerateTrainersList(trainers));
            console.log(trainers.length + ' trainers loaded');
        },
        error: function(){
                 console.log('error') ;
               }
    });
  })
}


function handleBarsGenerateTrainersList(trainers) {
    let source = $('#handlebars-list-template').html();
    let template = Handlebars.compile(source);
    return template ({ trainers: trainers });
}