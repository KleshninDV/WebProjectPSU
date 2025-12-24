const api = "http://localhost:8081/api/comments"; // <-- ПРОВЕРЬ ПОРТ

function loadComments() {
    fetch(api)
        .then(r => r.json())
        .then(data => {
            const list = document.getElementById("commentList");
            list.innerHTML = "";
            data.forEach(c => {
                list.innerHTML += `
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.text}</td>
                        <td>${c.ticket ? c.ticket.id : '-'}</td>
                        <td class="actions">
                            <button class="btn-danger" onclick="del(${c.id})">Удалить</button>
                        </td>
                    </tr>`;
            });
        });
}

function del(id) { fetch(`${api}/${id}`, { method: "DELETE" }).then(loadComments); }

document.getElementById("commentForm").onsubmit = e => {
    e.preventDefault();
    fetch(api, {
        method: "POST", headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            text: document.getElementById("text").value,
            ticketId: document.getElementById("ticketId").value
        })
    }).then(() => { document.getElementById("commentForm").reset(); loadComments(); });
};

loadComments();