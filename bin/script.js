const addButton = document.querySelector(".add");
const input = document.querySelector(".input-group");
const submit = document.querySelector(".submit");
const data = document.getElementsByClassName("text");

function submitForm() {
    // let isName = true;
    // let returnString = "";

    isValid = true;
    Array.from(data).forEach((element) => {
        if(element.value == "") {
            console.log("Invalid Input")
            invalidInput();
            isValid = false;
        }
        console.log(element.value);
        // let data = element.value;
        // if(isName == true) {
        //     returnString += data + ",";
        //     isName = false;
        // }
        // else{
        //     returnString += data + "\n";
        //     isName = true;
        // }
    });

    if(isValid == false) {return;}
    
    validInput();

    // Send data to server
        // fetch("http://localhost:3000", {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json",
        //     },
        //     body: JSON.stringify({
        //         name: data,
        //     }),
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
submit.addEventListener("click", submitForm);
