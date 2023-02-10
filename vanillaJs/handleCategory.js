const button = document.querySelector(".form-button");

button.addEventListener("click", (e) => {

    e.preventDefault();

    const catDescription = document.querySelector(".form-input").value;

    const category = {
        description: catDescription
    }

    fetch('http://localhost:8080/categories', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(category)
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        window.location.assign("index.html")
    })
    .catch(err => console.log(err))
})