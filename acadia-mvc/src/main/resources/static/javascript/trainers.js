/**
 * Start just after loading html
 */
$(document).ready(function() {
loadTrainers();
});

//On click events
$(document).on('click','.list-trainer', function(){
loadTrainers();
});





/**
 * Load trainers
 */
function loadTrainers() {
$('#content').load('/admin/trainers-template-list',


function (){
//console.log($('#modal-loading'));
   // $('#modal-loading').modal('show');
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
            console.log('error');
            }
    });
//$('#modal-loading').modal('hide');
  });
}

/**
 * Generate HandleBars template => list trainers
 */
function handleBarsGenerateTrainersList(trainers) {
    let source = $('#handlebars-list-template').html();
    let template = Handlebars.compile(source);
    return template ({ trainers: trainers });
}