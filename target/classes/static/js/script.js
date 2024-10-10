// script.js

// Function to validate the job application form
document.getElementById("jobApplicationForm").addEventListener("submit", function(event) {
    // Get the form fields
    var company = document.getElementById("company").value;
    var position = document.getElementById("position").value;
    var status = document.getElementById("status").value;

    // Validate the form fields
    if (company === "" || position === "" || status === "") {
        alert("All fields are required!");
        event.preventDefault();  // Stop the form from submitting
    }
});

// JavaScript to make email editable
document.getElementById("editEmail").addEventListener("click", function() {
    var emailField = document.getElementById("emailField");
    var emailInput = document.getElementById("emailInput");

    emailInput.style.display = "block";
    emailInput.value = emailField.textContent;
    emailField.style.display = "none";

    // Save email on blur (lose focus)
    emailInput.addEventListener("blur", function() {
        emailField.textContent = emailInput.value;
        emailField.style.display = "block";
        emailInput.style.display = "none";
    });
});


// Bootstrap-enhanced form validation
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all forms
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over forms and prevent submission if invalid
        Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

// Resume upload interaction
$('#resumeUpload').on('change', function () {
    var fileName = $(this).val().split('\\').pop();
    $(this).next('.custom-file-label').addClass('selected').html(fileName);
});

// Smooth scroll for anchor links
$(document).on('click', 'a[href^="#"]', function (event) {
    event.preventDefault();
    $('html, body').animate({
        scrollTop: $($.attr(this, 'href')).offset().top
    }, 500);
});
