const addButton = document.querySelector(".add");
const input = document.querySelector(".input-group");

function deleteInput() {
    const form = this.parentElement;
    form.remove();
}

function addInput() {
    console.log("addInput");

    const name = document.createElement('input');
    name.type = "text";
    name.placeholder = "Name";

    const email = document.createElement("input");
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

