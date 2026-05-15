import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [sportlased, setSportlased] = useState([]);

  const [nimi, setNimi] = useState("");
  const [riik, setRiik] = useState("");

  const [sportlaneId, setSportlaneId] = useState("");
  const [ala, setAla] = useState("");
  const [punktid, setPunktid] = useState("");

  const backendUrl = "http://localhost:8080";

  // SPORTLASTE LAADIMINE
  const laeSportlased = () => {
    fetch(`${backendUrl}/sportlased`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);

        // kuna backend tagastab Page objekti
        setSportlased(data.content || []);
      });
  };

  useEffect(() => {
    laeSportlased();
  }, []);

  // SPORTLASE LISAMINE
  const lisaSportlane = () => {
    fetch(`${backendUrl}/sportlased`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nimi: nimi,
        riik: riik,
      }),
    })
      .then((res) => res.json())
      .then(() => {
        setNimi("");
        setRiik("");
        laeSportlased();
      });
  };

  // TULEMUSE LISAMINE
  const lisaTulemus = () => {
    fetch(`${backendUrl}/sportlased/${sportlaneId}/tulemused`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        ala: ala,
        punktid: Number(punktid),
      }),
    })
      .then((res) => res.json())
      .then(() => {
        setSportlaneId("");
        setAla("");
        setPunktid("");
        laeSportlased();
      });
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Decathlon</h1>

      <hr />

      <h2>Lisa sportlane</h2>

      <input
        type="text"
        placeholder="Nimi"
        value={nimi}
        onChange={(e) => setNimi(e.target.value)}
      />

      <input
        type="text"
        placeholder="Riik"
        value={riik}
        onChange={(e) => setRiik(e.target.value)}
      />

      <button onClick={lisaSportlane}>Lisa sportlane</button>

      <hr />

      <h2>Lisa tulemus</h2>

      <input
        type="number"
        placeholder="Sportlase ID"
        value={sportlaneId}
        onChange={(e) => setSportlaneId(e.target.value)}
      />

      <input
        type="text"
        placeholder="Ala"
        value={ala}
        onChange={(e) => setAla(e.target.value)}
      />

      <input
        type="number"
        placeholder="Punktid"
        value={punktid}
        onChange={(e) => setPunktid(e.target.value)}
      />

      <button onClick={lisaTulemus}>Lisa tulemus</button>

      <hr />

      <h2>Sportlased</h2>

      {sportlased.map((sportlane) => (
        <div
          key={sportlane.id}
          style={{
            border: "1px solid gray",
            marginBottom: "10px",
            padding: "10px",
          }}
        >
          <h3>{sportlane.nimi}</h3>

          <p>Riik: {sportlane.riik}</p>

          <p>Kogupunktid: {sportlane.kogupunktid}</p>

          <h4>Tulemused:</h4>

          {sportlane.tulemused &&
            sportlane.tulemused.map((tulemus) => (
              <div key={tulemus.id}>
                {tulemus.ala} - {tulemus.punktid} punkti
              </div>
            ))}
        </div>
      ))}
    </div>
  );
}

export default App;