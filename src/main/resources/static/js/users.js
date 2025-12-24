const api = "http://localhost:8081/api/users"; // <-- ПРОВЕРЬ ПОРТ

function loadUsers() {
    fetch(api)
        .then(r => r.json())
        .then(data => {
            const list = document.getElementById("userList");
            list.innerHTML = "";
            data.forEach(u => {
                list.innerHTML += `
                    <tr>
                        <td>${u.id}</td>
                        <td><b>${u.username}</b></td>
                        <td>${u.email}</td>
                        <td class="actions">
                            <button class="btn-danger" onclick="del(${u.id})">Удалить</button>
                        </td>
                    </tr>`;
            });
        });
}

function del(id) { fetch(`${api}/${id}`, { method: "DELETE" }).then(loadUsers); }

document.getElementById("userForm").onsubmit = e => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;

    fetch(api, {
        method: "POST", headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ username, email })
    }).then(() => {
        document.getElementById("userForm").reset();
        loadUsers();
    });
};

loadUsers();