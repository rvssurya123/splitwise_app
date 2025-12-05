//import logo from './logo.svg';
//import './App.css';
//
//function App() {
//  return (
//    <div className="App">
//      <header className="App-header">
//        <img src={logo} className="App-logo" alt="logo" />
//        <p>
//          Edit <code>src/App.js</code> and save to reload.
//        </p>
//        <a
//          className="App-link"
//          href="https://reactjs.org"
//          target="_blank"
//          rel="noopener noreferrer"
//        >
//          Learn React
//        </a>
//      </header>
//    </div>
//  );
//}
//
//export default App;



import React, { useEffect, useState } from "react";
import UserDetails from "./UserDetails";
import UserProfile from "./UserProfile";


const API_BASE = "http://localhost:8080/api";

function App() {
  const [groups, setGroups] = useState([]);
  const [selectedGroup, setSelectedGroup] = useState(null);
  const [expenses, setExpenses] = useState([]);
  const [newExpenseDesc, setNewExpenseDesc] = useState("");
  const [newExpenseAmount, setNewExpenseAmount] = useState("");

  useEffect(() => {
    // Fetch groups on load
    fetch(`${API_BASE}/groups`)
      .then((res) => res.json())
      .then(setGroups)
      .catch((err) => console.error("Failed loading groups", err));
  }, []);

  useEffect(() => {
    if (selectedGroup) {
      // Fetch group expenses and balances when a group is selected
      fetch(`${API_BASE}/groups/${selectedGroup.id}/expenses`)
        .then((res) => res.json())
        .then(setExpenses)
        .catch((err) => console.error("Failed loading expenses", err));
    }
  }, [selectedGroup]);

  const addExpense = () => {
    if (!newExpenseDesc || !newExpenseAmount) return;

    fetch(`${API_BASE}/groups/${selectedGroup.id}/expenses`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        description: newExpenseDesc,
        amount: parseFloat(newExpenseAmount),
      }),
    })
      .then((res) => {
        if (res.ok) {
          // Refresh expenses
          return fetch(`${API_BASE}/groups/${selectedGroup.id}/expenses`)
            .then((res) => res.json())
            .then(setExpenses);
        }
        throw new Error("Add expense failed");
      })
      .catch((err) => console.error(err));

    setNewExpenseDesc("");
    setNewExpenseAmount("");
  };

  return (

    <div style={{ padding: 20 }}>
      <UserProfile />
      <h1>Splitwise Clone</h1>
      <h2>Groups</h2>

      <h2>Profile <UserDetails /> </h2>

      <ul>
        {groups.map((g) => (
          <li
            key={g.id}
            style={{
              cursor: "pointer",
              fontWeight: selectedGroup?.id === g.id ? "bold" : "normal",
            }}
            onClick={() => setSelectedGroup(g)}
          >
            {g.name}
          </li>
        ))}
      </ul>

      {selectedGroup && (
        <div>
          <h2>Expenses for {selectedGroup.name}</h2>
          <ul>
            {expenses.map((e) => (
              <li key={e.id}>
                {e.description} - ₹{e.amount.toFixed(2)}
              </li>
            ))}
          </ul>

          <h3>Add New Expense</h3>
          <input
            placeholder="Description"
            value={newExpenseDesc}
            onChange={(e) => setNewExpenseDesc(e.target.value)}
          />
          <input
            type="number"
            placeholder="Amount"
            value={newExpenseAmount}
            onChange={(e) => setNewExpenseAmount(e.target.value)}
          />
          <button onClick={addExpense}>Add</button>
        </div>
      )}
    </div>
  );
}

export default App;
