const api = "http://localhost:8081/api/tickets"; // <-- ПРОВЕРЬ ПОРТ

function loadTickets() {
    fetch(api)
        .then(r => r.json())
        .then(data => {
            const list = document.getElementById("ticketList");
            list.innerHTML = "";
            data.forEach(t => {
                const statusColor = t.status === 'OPEN' ? '#27ae60' : '#7f8c8d';
                list.innerHTML += `
                    <tr>
                        <td>${t.id}</td>
                        <td style="font-weight:600; color:#333;">${t.title}</td>
                        <td style="color:#666;">${t.description || '-'}</td>
                        <td><span style="color:${statusColor}; font-weight:bold; font-size:12px; border:1px solid ${statusColor}; padding:2px 6px; border-radius:4px;">${t.status}</span></td>
                        <td class="actions">
                            <button class="btn-warning" onclick="edit(${t.id}, '${t.title}', '${t.description}', '${t.status}')">Изменить</button>
                            <button class="btn-danger" onclick="del(${t.id})">Удалить</button>
                        </td>
                    </tr>`;
            });
        });
}

function edit(id, oldTitle, oldDesc, oldStatus) {
    const newTitle = prompt("Заголовок:", oldTitle); if(newTitle===null)return;
    const newDesc = prompt("Описание:", oldDesc); if(newDesc===null)return;
    const newStatus = prompt("Статус (OPEN/CLOSED):", oldStatus); if(newStatus===null)return;

    fetch(`${api}/${id}`, {
        method: "PUT", headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ title: newTitle, description: newDesc, status: newStatus })
    }).then(loadTickets);
}

function del(id) { fetch(`${api}/${id}`, { method: "DELETE" }).then(loadTickets); }

document.getElementById("ticketForm").onsubmit = e => {
    e.preventDefault();
    fetch(api, {
        method: "POST", headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            title: document.getElementById("title").value,
            description: document.getElementById("description").value,
            userId: document.getElementById("userId").value
        })
    }).then(() => { document.getElementById("ticketForm").reset(); loadTickets(); });
};

loadTickets();