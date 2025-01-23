// Donation-specific JavaScript logic
document.addEventListener('DOMContentLoaded', function() {
    const donateButton = document.getElementById('donate-button');
    
    // Listen for a click on the Donate button
    donateButton.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent form submission
        handleDonation(); // Call the handleDonation function
    });
});

// Function to handle the donation form and validation
function handleDonation() {
    // Get user input for food donation details
    const foodType = document.querySelector('input[name="food-type"]:checked');
    const foodQuantity = document.getElementById('food-quantity').value;
    const donorDetails = document.getElementById('donor-details').value;

    // Check if all necessary fields are filled
    if (!foodType || !foodQuantity || !donorDetails) {
        alert('Please fill in all fields!');
        return;
    }

    // Log the donation details (you can replace this with an API call to send data to backend)
    console.log('Donation Details:', {
        foodType: foodType.value,
        foodQuantity,
        donorDetails
    });

    // Optional: Send donation data to your backend via AJAX or fetch
    // Example:
    // fetch('/api/donate', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({ foodType: foodType.value, foodQuantity, donorDetails })
    // })
    // .then(response => response.json())
    // .then(data => {
    //     alert('Donation successful!');
    //     console.log(data);
    // })
    // .catch(error => {
    //     console.error('Error:', error);
    // });
}
