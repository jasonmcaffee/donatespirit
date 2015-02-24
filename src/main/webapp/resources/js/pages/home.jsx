define(['../core/viewFactory', 'react'], function(V, React){

    var HomePage = V({
        signals:{
            "a":function(data){

            }
        },
        render:function(){
            console.log('render');

            return (
                <div className="home-page">
                    <h1>Home</h1>
                </div>
            )
        }
    });

    return HomePage;
});