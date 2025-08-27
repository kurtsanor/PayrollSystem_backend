document.addEventListener("DOMContentLoaded", function () {
    const monthPicker = document.getElementById("monthYear");

    if (monthPicker) {
        monthPicker.addEventListener("change", function () {
            const selectedMonth = monthPicker.value; // ex: "2025-07"

            fetch(`/attendance/filter?monthPicker=${selectedMonth}`)
                .then(response => response.text())
                .then(html => {
                    document.getElementById('attendance-body').innerHTML = html;

                    // ✅ One-liner to update Hours Worked
                    document.getElementById('hoursWorked').textContent =
                        "Hours Worked: " +
                        (new DOMParser().parseFromString(html, 'text/html'))
                            .querySelector('#newHoursWorked')
                            .dataset.hours +
                        " Hours";
                })
                .catch(error => {
                    console.error("Error loading filtered attendance:", error);
                });
        });
    }
});
