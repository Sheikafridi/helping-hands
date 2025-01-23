// Main JavaScript file
document.addEventListener('DOMContentLoaded', function() {
    // Attach event listeners to navigation links for page transitions
    const navLinks = document.querySelectorAll('nav a');
    navLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // Prevent default anchor behavior
            const pageId = link.getAttribute('href').substring(1); // Get the page ID from the href
            showPage(pageId); // Show the corresponding page
        });
    });
});

// Function to show a specific page by adding 'active' class
function showPage(pageId) {
    document.querySelectorAll('.page').forEach(page => {
        page.classList.remove('active'); // Hide all pages
    });
    document.getElementById(pageId).classList.add('active'); // Show the active page
}
