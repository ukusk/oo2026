async function lisaSportlane() {

    const nimi = document.getElementById("nimi").value;

    if (nimi === "") {
        alert("Sisesta nimi!");
        return;
    }

    const response = await fetch("http://localhost:8080/sportlased", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nimi: nimi
        })
    });

    const data = await response.json();

    alert("Sportlane lisatud: " + data.nimi);

    document.getElementById("nimi").value = "";

    laeSportlased();
}



async function lisaTulemus() {

    const id = document.getElementById("sportlaneId").value;
    const ala = document.getElementById("ala").value;
    const punktid = document.getElementById("punktid").value;

    const response = await fetch(`http://localhost:8080/sportlased/${id}/tulemused`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            ala: ala,
            punktid: punktid
        })
    });

    if (!response.ok) {
        alert("Tulemuse lisamine ebaõnnestus!");
        return;
    }

    alert("Tulemus lisatud!");
}



async function vaataKogusumma() {

    const id = document.getElementById("summaId").value;

    const response = await fetch(`http://localhost:8080/sportlased/${id}/kogusumma`);

    const data = await response.text();

    document.getElementById("kogusumma").innerText =
        "Kogusumma: " + data;
}



async function laeSportlased() {

    const response = await fetch("http://localhost:8080/sportlased");

    const sportlased = await response.json();

    const list = document.getElementById("sportlased");

    list.innerHTML = "";

    sportlased.forEach(s => {

        const li = document.createElement("li");

        li.innerText =
            "ID: " + s.id + " | Nimi: " + s.nimi;

        list.appendChild(li);
    });
}