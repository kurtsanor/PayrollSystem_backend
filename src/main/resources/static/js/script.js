window.addEventListener('DOMContentLoaded', () => {
    const popup = document.querySelector('.popup');
    if (popup) {
        // Trigger fade-in
        popup.classList.add('visible');

        // After 3 seconds, fade out
        setTimeout(() => {
            popup.classList.add('fade-out');

            // After fade-out duration (500ms), hide it completely
            setTimeout(() => {
                popup.style.display = 'none';
            }, 500);
        }, 3000);
    }
});

function searchEmployee() {
    const id = document.getElementById("searchbar").value.trim();

    fetch(`/api/employees/search?empId=${encodeURIComponent(id)}`)
        .then(response => response.json())
        .then(data => {
            console.log("Fetched data:", data);
            const table = document.getElementById("emp-table");
            table.innerHTML = ""; // clear old results

            if (!Array.isArray(data) || data.length === 0) {
                    table.innerHTML = "<tr><td style='text-align: center' colspan='5'>No results found</td></tr>";
                    return;
                }

            data.forEach(employee => {
                const row = `
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.position}</td>
                        <td>
                            <div class="employees-action-buttons">
                                <a class="employees-navbutton-edit" href="/employees/edit/${employee.id}">Edit</a>
                                <form action="/delete" method="post" style="display:inline;">
                                    <input type="hidden" name="employeeId" value="${employee.id}">
                                    <button class="employees-navbutton delete">Delete</button>
                                </form>
                            </div>
                        </td>
                    </tr>`;
                table.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("Error fetching employees:", error);
        });
}

function loadContent(event, url) {
    event.preventDefault();
    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.querySelector('.main-content').innerHTML = html;

            window.history.pushState({}, '', url);

            // Optionally, extract and update page title
            const tempDom = new DOMParser().parseFromString(html, 'text/html');
            const newTitle = tempDom.title;
            if (newTitle) {
                document.title = newTitle;
            }
        });
}


function setActiveNav(clickedLink) {
    const navLinks = document.querySelectorAll('.navbutton');
    navLinks.forEach(link => link.classList.remove('active'));
    clickedLink.classList.add('active');
}

// for emloyee form
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("employeeForm");

    if (form) {
        form.addEventListener("submit", function (event) {
            event.preventDefault(); // prevent normal form submission

            const formData = new URLSearchParams(new FormData(form));

            fetch(form.action, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: formData
            })
                .then(response => {
                    if (response.ok) {
                        // ✅ Redirect back to dashboard or employee list
                        window.location.href = "/dashboard?redirectTo=employees";
                    } else {
                        return response.text().then(text => alert("Save failed: " + text));
                    }
                })
                .catch(error => {
                    console.error("Submission error:", error);
                    alert("Submission failed");
                });
        });


    }
});

document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const redirectTo = params.get("redirectTo");

    if (redirectTo === "employees") {
        const employeesLink = document.querySelector('a[href="/employees"]');
        if (employeesLink) {
            loadContent(new Event("custom"), "/employees");
            setActiveNav(employeesLink);
        }
    }
});

document.addEventListener("submit", function (event) {
    const form = event.target;
    if (form.matches("form[action='/delete']")) {
        event.preventDefault();

        const formData = new URLSearchParams(new FormData(form));

        fetch(form.action, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    // Redirect after successful delete
                    window.location.href = "/dashboard?redirectTo=employees";
                } else {
                    return response.text().then(text => alert("Delete failed: " + text));
                }
            })
            .catch(error => {
                console.error("Delete error:", error);
                alert("Delete failed");
            });
    }
});












