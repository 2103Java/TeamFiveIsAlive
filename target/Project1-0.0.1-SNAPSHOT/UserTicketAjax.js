function loadTickets() {
    let docBody = document.getElementById('docBody');

    let xmltp = new XMLHttpRequest();

    xmltp.open('GET', 'api/user/id');
    xmltp.send();

    xmltp.onload = () => {
        let user = JSON.parse(xmltp.response);
        console.log(user);

        let xhttp = new XMLHttpRequest();

        xhttp.open('GET', '/api/tickets/id?userID=' + user.userID);
        xhttp.send();
        console.log(xhttp.response);
        xhttp.onload = () => {
            let data = JSON.parse(xhttp.response);
            data.forEach((ticket) => {
                docBody.innerHTML += `
        <div class="container d-flex justify-content-center">
    <div class="row no-gutters border rounded overflow-hidden flex-md-row my-4 shadow-sm h-md-250 position-relative bg-light w-75">
        <div class="col p-4 d-flex flex-column position-static">
            <div class="row">
                <div class="col-md">
                    <strong class="d-inline-block mb-2 text-primary">Status: ${ticket.status}</strong>
                </div>
                <div class="col-md">
                    <strong class="d-inline-block mb-2 text-success">Amount: $${ticket.amount}</strong>
                </div>
            </div>
        <h3 class="mb-0">${ticket.type}</h3>
        <div class="mb-3 text-muted">${ticket.timestamp}</div>
        <p class="card-text mb-auto">${ticket.desc ? ticket.desc : ''}</p>
        </div>
    </div>
</div>`;
            })
        }
    };


}

loadTickets();

let logoutButton = document.getElementById('logoutBtn');
filterButton.addEventListener('click', () => {
    logout();
});

function logout() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/api/user/logout");
    xhttp.send();
}