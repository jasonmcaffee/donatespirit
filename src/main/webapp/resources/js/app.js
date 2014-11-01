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
//    var $out = $('#out');
//    $out.html('done loading');

    $.ajax({
        url:'user/list',
        dataType:'JSON',
        contentType: "application/json",
        data:1
    }).done(function(data){
        console.log(JSON.stringify(data));
    });

    $.ajax({
        url:'user/create',
        type:'POST',
        dataType:'JSON',
        contentType: "application/json",
        data:JSON.stringify({
            userName: 'jason9',
            password: 'monkey',
            userInfo:{
                email:'jasonmc@gmail.com'
            }
        })
    }).done(function(data){
        console.log(JSON.stringify(data));
    });
});