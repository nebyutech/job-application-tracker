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


// Function to delete a job application
function deleteApplication(id) {
    const deleteButton = document.querySelector(`#delete-btn-${id}`);

    if (confirm("Are you sure you want to delete this application?")) {
        deleteButton.disabled = true; // Disable the button
        deleteButton.innerHTML = "Deleting..."; // Change button text to show progress

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
            .catch(error => {
                alert("Error deleting application: " + error);
            })
            .finally(() => {
                deleteButton.disabled = false; // Enable the button
                deleteButton.innerHTML = "Delete"; // Reset button text
            });
    }
}


// Function to update user email
function saveEmail(userId) {
    const newEmail = document.getElementById('emailInput').value;

    // Validate email format using a simple regex
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(newEmail)) {
        alert("Please enter a valid email address.");
        return;
    }

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

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Ensure this URL matches your back-end route
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


function deleteApplication(id) {
    if (confirm("Are you sure you want to delete this application?")) {
        // Ensure this URL matches your back-end route
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
