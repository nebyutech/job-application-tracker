// Function to delete a job application
function deleteApplication(id) {
    if (confirm("Are you sure you want to delete this application?")) {
        fetch(`/api/applications/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.text())
            .then(data => {
                if (data === "Job application deleted successfully.") {
                    alert(data);
                    location.reload(); // Reload the page after successful deletion
                } else {
                    alert(data);
                }
            })
            .catch(error => alert("Error deleting application: " + error));
    }
}

// Function to handle form submission for login
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/api/users/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: username, password: password })
    })
        .then(response => response.text())
        .then(data => {
            if (data === "Login successful.") {
                alert(data);
                window.location.href = "/job-applications"; // Redirect to job applications page
            } else {
                alert(data);
            }
        })
        .catch(error => alert("Error logging in: " + error));
});

// Function to update user email
function saveEmail(userId) {
    const newEmail = document.getElementById('emailInput').value;

    fetch(`/api/users/${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email: newEmail })
    })
        .then(response => response.text())
        .then(data => {
            if (data === "User updated successfully.") {
                alert(data);
                document.getElementById('emailField').textContent = newEmail;
                toggleEmailEdit();
            } else {
                alert(data);
            }
        })
        .catch(error => alert("Error updating email: " + error));
}
