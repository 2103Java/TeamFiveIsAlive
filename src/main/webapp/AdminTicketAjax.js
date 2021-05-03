function loadTickets() {
    let docBody = document.getElementById('docBody');

    console.log("loadtickets");
    let xhttp = new XMLHttpRequest();
    xhttp.open('GET', '/api/tickets');
    xhttp.send();
    console.log(xhttp.response);
    xhttp.onload = () => {
        data = JSON.parse(xhttp.response);
        data.forEach((ticket) => {
            let status = ticket.status;
            let amount = ticket.amount;
            docBody.innerHTML += `
        <div class="container d-flex justify-content-center">
    <div class="row no-gutters border rounded overflow-hidden flex-md-row my-4 shadow-sm h-md-250 position-relative bg-light w-75">
        <div class="col p-4 d-flex flex-column position-static">
            <div class="row">
                <div class="col-md">
                    <strong class="d-inline-block mb-2 text-primary">Status: ${status}</strong>
                </div>
                <div class="col-md">
                    <strong class="d-inline-block mb-2 text-success">Amount: $${amount}</strong>
                </div>
            </div>
        <h3 class="mb-0">Name</h3>
        <div class="mb-3 text-muted">Timestamp</div>
        <p class="card-text mb-auto">Description</p>
        <div class="row mt-4">
            <div class="col-md d-flex justify-content-center">
                <button class="btn btn-lg btn-primary w-50">Approve</button>
            </div>
            <div class="col-md d-flex justify-content-center">
                <button class="btn btn-lg btn-danger w-50">Reject</button>
            </div>
        </div>
        </div>
    </div>
</div>`;
        })
    }
}


let filterButton = document.getElementById('btnFilter');
filterButton.addEventListener('click', () => {
    let filter = document.getElementById('expenseType');
    filterTickets(filter.value);
});

function approveTicket(ticketID) {
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', '/api/ticket/approve');
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send('ticketID=' + ticketID);
}

function rejectTicket(ticketID) {
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', '/api/ticket/reject');
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send('ticketID=' + ticketID);
}

function filterTickets(value) {
    let docBody = document.getElementById('docBody');
    docBody.innerHTML = `
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
        <a class="navbar-brand" href="#">ExpenseReport</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a id="logoutBtn" class="nav-link" href="/alt.html">Logout</a>
            </li>
          </ul>
        </div>
      </nav>
        <div class="container">
        <form class="w-25">
            <select class='form-control' name="type" id="expenseType">
                <option value="PENDING">PENDING</option>
                <option value="APPROVED">APPROVED</option>
                <option value="REJECTED">REJECTED</option>
              </select>
              <button id="btnFilter" class='btn btn-primary'>Filter</button>
        </form>
    </div>`;

    let xhttp = new XMLHttpRequest();
    xhttp.open('GET', '/api/tickets?status=' + value);
    xhttp.send();
    console.log(xhttp.response);
    xhttp.onload = () => {
        data = JSON.parse(xhttp.response);
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
        <p class="card-text mb-auto">${ticket.desc}</p>
        ${ticket.status === 'PENDING' && `        
        <div class="row mt-4">
            <div class="col-md d-flex justify-content-center">
                <button type="submit" class="btn btn-lg btn-primary w-50 approveButton" onclick="approveTicket(${ticket.ticketID})">Approve</button>
            </div>
            <div class="col-md d-flex justify-content-center">
                <button class="btn btn-lg btn-danger w-50 rejectButton" onclick="rejectTicket(${ticket.ticketID})">Reject</button>
            </div>
        </div>`
            }

        </div>
    </div>
</div>`;
        })
    }
}

let logoutButton = document.getElementById('logoutBtn');
filterButton.addEventListener('click', () => {
    logout();
});

function logout() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/api/user/logout");
    xhttp.send();
}
