document.addEventListener('DOMContentLoaded', function() {

    let render = function (template, node) {
	    node.innerHTML = template;
    };

    let template = '<h1>Hello world!</h1>';
    render(template, document.querySelector('#instagram'));

})