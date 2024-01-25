document.addEventListener('DOMContentLoaded', function () {
    var form = document.forms['form'];

    form.addEventListener('submit', function (event) {
        var email = form.elements['username'].value;
        var password = form.elements['password'].value;
        var verifyPassword = form.elements['verifyPassword'].value;

        var errorDiv = document.getElementById('error');
        errorDiv.innerHTML = '';

        if (!email || !password || !verifyPassword) {
            displayError('Please fill out all fields.');
            event.preventDefault();
            return;
        }

        if (password !== verifyPassword) {
            displayError('Passwords do not match.');
            event.preventDefault();
        }
    });

    function displayError(message) {
        var errorDiv = document.getElementById('error');
        var errorText = document.createTextNode(message);
        errorDiv.appendChild(errorText);
    }
});
