m.init({
    baseUrl: 'resources/js/',
    paths:{
        '$' : 'jquery-2.1.1.js'
    },
    shim:{
        '$':{
            exports:['$']
        }
    }
});

m(function($){
    var $out = $('#out');
    $out.html('done loading');

    $.ajax({url:'ajaxTest', dataType:'JSON',contentType: "application/json", data:1}).done(function(data){console.log(JSON.stringify(data));});
});