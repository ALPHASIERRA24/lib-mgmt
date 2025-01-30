function borrowBook(bookId) {
    fetch(`/borrow-records`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: 1,  // Replace with actual user ID
            bookId: bookId,
            borrowDate: new Date().toISOString().split('T')[0],
            dueDate: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toISOString().split('T')[0],
            returnStatus: false
        })
    }).then(response => response.json())
      .then(data => alert("Book Borrowed Successfully!"))
      .catch(error => console.error("Error:", error));
}