// Form validation for job application
document.getElementById("jobApplicationForm").addEventListener("submit", function(event) {
    var company = document.getElementById("company").value;
    var position = document.getElementById("position").value;
    var status = document.getElementById("status").value;

    if (company === "" || position === "" || status === "") {
        alert("All fields are required!");
        event.preventDefault();
    }
});

// Email edit functionality in profile
document.getElementById("editEmail").addEventListener("click", function() {
    var emailField = document.getElementById("emailField");
    var emailInput = document.getElementById("emailInput");

    emailInput.style.display = "block";
    emailInput.value = emailField.textContent;
    emailField.style.display = "none";

    emailInput.addEventListener("blur", function() {
        emailField.textContent = emailInput.value;
        emailField.style.display = "block";
        emailInput.style.display = "none";
    });
});

// Resume upload filename display
document.getElementById('resumeUpload').addEventListener('change', function() {
    var fileName = this.value.split('\\').pop();
    document.querySelector('.custom-file-label').textContent = fileName;
});

// Toastr Notifications for Form Submission
$("#jobApplicationForm").submit(function(e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/api/applications/create",
        data: $(this).serialize(),
        success: function(response) {
            toastr.success(response);
        },
        error: function(error) {
            toastr.error("Something went wrong. Please try again.");
        }
    });
});
