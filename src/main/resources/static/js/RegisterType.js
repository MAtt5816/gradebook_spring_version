let teacherForm = document.getElementById('teacher_form');
let studentForm = document.getElementById('student_form');

function setPostValue(value){
    if (value == "STUDENT")
        studentForm.action = '/register/' + value;
    else if(value == "TEACHER")
        teacherForm.action = '/register/' + value;
}

document.getElementById('role').addEventListener('change', function() {
    setPostValue(this.value);
});

let actionValue = document.getElementById('role').value;
setPostValue(actionValue);
