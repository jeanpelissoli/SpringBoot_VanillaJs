
function setImgToProduct(productId) {

    const fileInput = document.querySelector("#myFile");
    const formData = new FormData();
    const key = "image";
    formData.append(key, fileInput.files[0]);
    
    fetch(`http://localhost:8080/img/${productId}`, {
        method: 'POST',
        body: formData
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        window.location.assign("index.html");

    })
    .catch(err => console.log(err));
}

const button = document.querySelector(".form-button");

button.addEventListener("click", (event) => {

    event.preventDefault();

    const method = window.localStorage.getItem("method");
    let fetchUrl;

    const catDescrip = document.getElementById("categories").value;
    const catId = document.querySelector(`.${catDescrip}`).id;

    const productId = window.localStorage.getItem("productId");

    if(method == "PUT") {
        fetchUrl = `http://localhost:8080/products/${productId}`;
    }
    else {
        fetchUrl = 'http://localhost:8080/products';
    }

    const product = {
        description: document.getElementById("name").value,
        price: document.getElementById("price").value,
        amount: document.getElementById("amount").value,
        category: {
            id: catId,
            description: catDescrip
        }
    }

    fetch(fetchUrl, {
        method: method,
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(product)    
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        method == "POST" ? setImgToProduct(data.id) : setImgToProduct(productId);
    })
    .catch(err => console.log(err));
})

function renderProductName() {
    
    const div = document.querySelector(".form-header");
    const h3 = document.createElement("h3");
    const method = window.localStorage.getItem("method");

    if(method == "POST") {
        h3.textContent = "Create New Product";
        button.textContent = "Post";
    }
    else {
        h3.textContent = "Update: " + window.localStorage.getItem("productName");
        button.textContent = "Update";    
    }
    
    div.appendChild(h3);   
}

function renderCategories(categories) {

    renderProductName();

    const select = document.querySelector(".form-select");

    categories.map(e => {

        const option = document.createElement("option");
        option.textContent = e.description;
        option.setAttribute("id", e.id);
        option.setAttribute("class", e.description);

        select.appendChild(option);
    })
}

function getCategories() {

    let categories = [];

    fetch('http://localhost:8080/categories', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
    .then(resp => resp.json())
    .then(data => {
        categories = data;
        renderCategories(categories);
    })
    .catch(err => console.log(err));
}

getCategories();