let buttonSubmit = document.querySelector(".button");
let input = document.querySelector("input[type='text']");
let p = document.querySelector(".result");

buttonSubmit.addEventListener("click", async (e) => {
    e.preventDefault();
    let data = {message: input.value};
    let response = await fetch('http://localhost:4567/message/send/', {method: 'POST', body: JSON.stringify(data)});
    if (response.ok) {
        p.innerText = "Your message sent successfully!";

    } else {
        p.innerText = `Error HTTP: ${response.status}`;
    }
    input.value = "";
});

