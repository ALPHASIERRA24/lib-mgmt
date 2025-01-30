document.addEventListener("DOMContentLoaded", function () {
    fetchBooks();
    fetchUsers();
    fetchBorrowRecords();
});

function fetchBooks() {
    fetch("/api/catalog")
        .then(response => response.json())
        .then(books => {
            const booksTable = document.querySelector("#booksTable tbody");
            booksTable.innerHTML = "";
            books.forEach(book => {
                const row = `<tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.available ? "Yes" : "No"}</td>
                </tr>`;
                booksTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching books:", error));
}

function fetchUsers() {
    fetch("/api/users")
        .then(response => response.json())
        .then(users => {
            const usersTable = document.querySelector("#usersTable tbody");
            usersTable.innerHTML = "";
            users.forEach(user => {
                const row = `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                </tr>`;
                usersTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching users:", error));
}

function fetchBorrowRecords() {
    fetch("/api/borrow")
        .then(response => response.json())
        .then(records => {
            const borrowTable = document.querySelector("#borrowTable tbody");
            borrowTable.innerHTML = "";
            records.forEach(record => {
                const row = `<tr>
                    <td>${record.userId}</td>
                    <td>${record.bookId}</td>
                    <td>${record.borrowDate}</td>
                    <td>${record.returnDate || "Not Returned"}</td>
                </tr>`;
                borrowTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching borrow records:", error));
}
