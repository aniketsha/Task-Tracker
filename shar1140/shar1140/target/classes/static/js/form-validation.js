document.addEventListener('DOMContentLoaded', function () {
    var form = document.getElementById('taskForm');
    
    form.addEventListener('submit', function (event) {
        var title = document.getElementById('title').value;
        var description = document.getElementById('description').value;
        var dueDate = document.getElementById('dueDate').value;

        if (!title || !description || !dueDate) {
            alert('Please fill out all the fields before submitting the form.');
            event.preventDefault();
        }
    });
});
