/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  const ctx = document.getElementById('mostWatchedMoviesChart').getContext('2d');
    const mostWatchedMoviesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Movie 1', 'Movie 2', 'Movie 3', 'Movie 4', 'Movie 5'], // Replace with actual movie names
            datasets: [{
                label: 'Number of Tickets',
                data: [150, 200, 120, 170, 220], // Replace with actual number of tickets
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Number of Tickets'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Movies'
                    }
                }
            }
        }
    });
