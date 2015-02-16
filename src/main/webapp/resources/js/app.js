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
    //http://stackoverflow.com/questions/1184624/convert-form-data-to-js-object-with-jquery
    $.fn.serializeObject = function(){

        var self = this,
            json = {},
            push_counters = {},
            patterns = {
                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
                "push":     /^$/,
                "fixed":    /^\d+$/,
                "named":    /^[a-zA-Z0-9_]+$/
            };


        this.build = function(base, key, value){
            base[key] = value;
            return base;
        };

        this.push_counter = function(key){
            if(push_counters[key] === undefined){
                push_counters[key] = 0;
            }
            return push_counters[key]++;
        };

        $.each($(this).serializeArray(), function(){

            // skip invalid keys
            if(!patterns.validate.test(this.name)){
                return;
            }

            var k,
                keys = this.name.match(patterns.key),
                merge = this.value,
                reverse_key = this.name;

            while((k = keys.pop()) !== undefined){

                // adjust reverse_key
                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

                // push
                if(k.match(patterns.push)){
                    merge = self.build([], self.push_counter(reverse_key), merge);
                }

                // fixed
                else if(k.match(patterns.fixed)){
                    merge = self.build([], k, merge);
                }

                // named
                else if(k.match(patterns.named)){
                    merge = self.build({}, k, merge);
                }
            }

            json = $.extend(true, json, merge);
        });

        return json;
    };
//    var $out = $('#out');
//    $out.html('done loading');

//    $.ajax({
//        url:'user/list',
//        dataType:'JSON',
//        contentType: "application/json",
//        data:1
//    }).done(function(data){
//        console.log(JSON.stringify(data));
//    });

//    $.ajax({
//        url:'user/create',
//        type:'POST',
//        dataType:'JSON',
//        contentType: "application/json",
//        data:JSON.stringify({
//            userName: 'jason9',
//            password: 'monkey',
//            userInfo:{
//                email:'jasonmc@gmail.com'
//            }
//        })
//    }).done(function(data){
//        console.log(JSON.stringify(data));
//    });

//    $.ajax({
//        url:'user/signin',
//        type:'POST',
//        dataType:'JSON',
//        contentType: "application/json",
//        data:JSON.stringify({
//            userName: 'jason9',
//            password: 'monkey'
//        })
//    }).done(function(data){
//        console.log(JSON.stringify(data));
//    });
});

m(function($){
    var $form = $('#createAccountForm');
    $form.on('submit', function(e){
        e.preventDefault();
        var data = $form.serializeObject();

        $.ajax({
            url:'user/create',
            type:'POST',
            dataType:'JSON',
            contentType: "application/json",
            data:JSON.stringify(data)
        }).done(function(d){
                console.log(JSON.stringify(d));
                if(d.success){
                    //window.location.replace('members');
                    $('#account-section').hide();
                    window.alert('You have succesfully created an account, but it needs to be approved before you can access the members section. ask a coleader in chat to approve you. account info: ' + JSON.stringify(data, null, 2));
                }else{
                    //show error message
                    window.alert('you have failed creating an account. bummer. you probably need a different user name: ' + d.errorMessage);
                }
            });
        console.log(JSON.stringify(data));
    });

});

m(function($){
    $(document).on('submit', '#signinForm', function(e){
        e.preventDefault();
        var data = $(this).serializeObject();

        $.ajax({
            url:'user/signin',
            type:'POST',
            dataType:'JSON',
            contentType: "application/json",
            data:JSON.stringify(data)
        }).done(function(data){
                console.log(JSON.stringify(data));
                if(data.success){
                    window.location.replace('members');
                }else{
                    //show error message
                    window.alert('you have failed signing in. bummer.' + data.errorMessage);
                }
        });
        console.log(JSON.stringify(data));
    });

});

m(function($){
    $(document).on('submit', '#postMessageForm', function(e){
        e.preventDefault();
        var data = $(this).serializeObject();

        $.ajax({
            url:'message/create',
            type:'POST',
            dataType:'JSON',
            contentType: "application/json",
            data:JSON.stringify(data)
        }).done(function(data){
                console.log(JSON.stringify(data));
                if(data.success){
                    window.location.replace('members');
                }else{
                    //show error message
                    window.alert('you have failed posting a message. bummer.' + data.errorMessage);
                }
            });
        console.log(JSON.stringify(data));
    });

});

m(function($){
//    var socket = new WebSocket('ws://donatespirit.com/websocket');
//
//    console.log('Connection attempted');
//
//    socket.onopen = function(){
//        console.log('Connection open!');
//    };
//
//    socket.onclose = function(){
//        console.log('Disconnecting connection');
//    };
//
//    socket.onmessage = function (evt){
//        var received_msg = evt.data;
//        console.log(received_msg);
//        console.log('message received!');
//    };
//    socket.send("test");
});