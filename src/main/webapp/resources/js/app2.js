define(['core/viewFactory', 'pages/home', 'react'], function(V, HomePage, React){
   console.log('app2 has loaded.' + JSON.stringify(V));

   React.render(<HomePage/>, document.getElementById('pageContainer'));

});