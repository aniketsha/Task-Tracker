document.addEventListener('DOMContentLoaded', function () {
    var form = document.forms['loginForm'];

    form.addEventListener('submit', function (event) {
        var username = form.elements['username'].value;
        var password = form.elements['password'].value;

        var errorDiv = document.getElementById('error');
        errorDiv.innerHTML = '';

        if (!username || !password) {
            displayError('Please enter both username and password.');
            event.preventDefault();
        }
    });

    function displayError(message) {
        var errorDiv = document.getElementById('error');
        var errorText = document.createTextNode(message);
        errorDiv.appendChild(errorText);
    }
});
