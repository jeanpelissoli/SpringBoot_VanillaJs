function handleCategory() {

    window.location.assign("handleCategory.html");
}

function handleCreate() {
    
    window.localStorage.setItem("method", "POST");
    window.location.assign("handleProduct.html");
}

function handleEdit(id, description) {

    window.localStorage.setItem("productId", id);
    window.localStorage.setItem("productName", description);
    window.localStorage.setItem("method", "PUT");
    window.location.assign("handleProduct.html");    
}

function handleDelete(id) {
    
    fetch(`http://localhost:8080/products/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type' : 'Application/json'
        },
    })
    .then(resp => console.log(resp))
    .then(window.location.reload())
    .catch(err => console.log(err));
}

function renderProducts(products) {

    const div = document.querySelector(".container");
    const getImageUrl = "http://localhost:8080/img/"

    products.map(e => {

        const card = document.createElement("div");
        card.setAttribute("class", "card");
        div.appendChild(card);

        const cardHeader = document.createElement("div");
        cardHeader.setAttribute("class", "card-header");
        card.appendChild(cardHeader);

        if(e.img) {
            const img = document.createElement("img");
            img.src = getImageUrl + e.img.id;
            cardHeader.appendChild(img);
        }

        const cardCenter = document.createElement("div");
        cardCenter.setAttribute("class", "card-center");
        card.appendChild(cardCenter);

        const name = document.createElement("h3");
        name.textContent = `name: ${e.description}`;
        cardCenter.appendChild(name);

        const price = document.createElement("h3");
        price.textContent = `price: ${e.price}`;
        cardCenter.appendChild(price);

        const category = document.createElement("h3");
        category.textContent = `category: ${e.category.description}`;
        cardCenter.appendChild(category);

        const cardFooter = document.createElement("div");
        cardFooter.setAttribute("class", "card-footer");
        card.appendChild(cardFooter);

        const editButton = document.createElement("button");
        editButton.setAttribute("class", "edit");
        editButton.setAttribute("onclick", `handleEdit(${e.id}, "${e.description}")`);
        editButton.textContent = "edit";
        
        cardFooter.appendChild(editButton);

        const deleteButton = document.createElement("button");
        deleteButton.setAttribute("class", "delete");
        deleteButton.setAttribute("onclick", `handleDelete(${e.id})`);
        deleteButton.textContent = "delete";

        cardFooter.appendChild(deleteButton);
    })
}

window.addEventListener("load", (event) => {

    event.preventDefault();

    let products = [];

    fetch('http://localhost:8080/products', {
        method: 'GET',
        headers: {
            'Content-Type' : 'application/json'
        },
    })
    .then(resp => resp.json())
    .then(data => {
        products = data;
        renderProducts(products);
    })
    .catch(err => console.log(err));
})

