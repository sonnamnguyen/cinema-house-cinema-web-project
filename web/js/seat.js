        let totalTickets = 0;
        let selectedSeats = [];
        let ticketCounts = {}; // To keep track of ticket counts by category

        function updateTicketCount(type, change) {
            const countElement = document.getElementById(`${type}-count`);
            let count = parseInt(countElement.innerText) + change;
            if (count < 0) count = 0;
            if (count > 9) count = 9; // Limiting the count to max 9
            countElement.innerText = count;

            ticketCounts[type] = count;
            totalTickets = calculateTotalTickets();
            resetSelectedSeats();
            updateSeatButtons();
            updateTotalPrice();
        }

        function calculateTotalTickets() {
            let total = 0;
            const counts = document.querySelectorAll('.count');
            counts.forEach(countElement => {
                total += parseInt(countElement.innerText);
            });
            return total;
        }

        function resetSelectedSeats() {
            selectedSeats = [];
            const buttons = document.querySelectorAll('.seat-button');
            buttons.forEach(button => {
                button.classList.remove('selected');
                button.disabled = true;
            });
        }

        function updateSeatButtons() {
            const buttons = document.querySelectorAll('.seat-button');
            buttons.forEach(button => {
                const seatCategory = button.classList[1]; // Assuming the second class is the seat category
                if (selectedSeats.length < totalTickets && ticketCounts[seatCategory] > 0) {
                    button.disabled = false;
                } else if (!button.classList.contains('selected')) {
                    button.disabled = true;
                }
            });
        }

        function selectSeat(button) {
            const seatId = button.value;
            const seatCategory = button.classList[1]; // Assuming the second class is the seat category

            if (button.classList.contains('selected')) {
                button.classList.remove('selected');
                selectedSeats = selectedSeats.filter(id => id !== seatId);
                ticketCounts[seatCategory]++;
            } else {
                if (selectedSeats.length < totalTickets && ticketCounts[seatCategory] > 0) {
                    button.classList.add('selected');
                    selectedSeats.push(seatId);
                    ticketCounts[seatCategory]--;
                }
            }
            updateSeatButtons();
            updateTotalPrice();
        }

        function updateTotalPrice() {
            let totalPrice = 0;
            const ticketBoxes = document.querySelectorAll('.ticket-box');
            ticketBoxes.forEach(ticketBox => {
                const price = parseFloat(ticketBox.getAttribute('data-price'));
                const count = parseInt(ticketBox.querySelector('.count').innerText);
                totalPrice += price * count;
            });

            const totalPriceElement = document.querySelector('.total-price');
            totalPriceElement.innerText = `${totalPrice.toFixed(2)} VND`; // Display total price

            const totalPriceInput = document.getElementById('totalPrice');
            totalPriceInput.value = totalPrice.toFixed(2); // Set the hidden input field value
        }

        function prepareSeatInputs() {
            const container = document.getElementById('selectedSeatsContainer');
            container.innerHTML = ''; // Clear previous inputs

            selectedSeats.forEach(seatId => {
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'seatId';
                input.value = seatId;
                container.appendChild(input);
            });
        }