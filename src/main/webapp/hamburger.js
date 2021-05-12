/* Toggle between showing and hiding the navigation menu links when the user clicks on the hamburger menu / bar icon */
function myFunction() {
  let hamburger = document.getElementById("myLinks");
  if (hamburger.style.display === "block") {
    hamburger.style.display = "none";
  } else {
    hamburger.style.display = "block";
  }
}