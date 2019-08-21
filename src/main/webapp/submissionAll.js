let url = 'http://localhost:8080/submission/all';
fetch(url, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
  }
});