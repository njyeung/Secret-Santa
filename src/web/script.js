const addButton = document.querySelector(".add");
const input = document.querySelector(".input-group");
const data = document.getElementsByClassName("text");
const submit = document.querySelector(".submit");

function submitForm() {
    const dataArray = [];
    Array.from(data).forEach((element) => {
        dataArray.push(element.value);
    });
    
    eel.generate(dataArray)(function(ret) {
        if(ret == true)
            validInput();
        else
            invalidInput();
    });
}

function invalidInput() {
    submit.value = "INVALID"
    submit.style.backgroundColor = "darkred";
    submit.style.color = "white";
}

function validInput() {
    submit.value = "SUBMITTED"
    submit.style.backgroundColor = "green";
    submit.style.color = "white";
}

function deleteInput() {
    const form = this.parentElement;
    form.remove();
}

function addInput() {
    console.log("addInput");

    const name = document.createElement('input');
    name.className = "text";
    name.type = "text";
    name.placeholder = "Name";

    const email = document.createElement("input");
    email.className = "text";
    email.type = "text";
    email.placeholder = "Email";
    
    const button = document.createElement("a");
    button.className = "delete";
    button.innerHTML = "&times";

    button.addEventListener("click", deleteInput);

    const hiddenButton = document.createElement("a");
    hiddenButton.className = "falseDelete";
    hiddenButton.innerHTML = "&times";

    const form = document.createElement("form");
    form.className = "form";

    input.appendChild(form);
    form.appendChild(hiddenButton);
    form.appendChild(name);
    form.appendChild(email);
    form.appendChild(button);
}

addButton.addEventListener("click", addInput);
