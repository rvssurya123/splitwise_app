import React, { useState } from "react";

function GroupDetails() {
  const [userId, setUserId] = useState("");
  const [groups, setGroups] = useState([]);
  const [error, setError] = useState(null);

  const fetchGroupDetails = () => {
    if (!userId) {
      setError("Please enter a user ID");
      setGroups([]);
      return;
    }
    fetch(`http://localhost:8080/users/groups/${userId}`)
      .then((res) => {
        if (!res.ok) {
          throw new Error("API returned error: " + res.status);
        }
        return res.json();
      })
      .then((data) => {
        setGroups(data);
        setError(null);
      })
      .catch((err) => {
        setError(err.message);
        setGroups([]);
      });
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Enter User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
      />
      <button onClick={fetchGroupDetails}>Get Group Details</button>
      {error && <p style={{ color: "red" }}>Error: {error}</p>}
      {groups.length > 0 && (
        <ul>
          {groups.map((group) => (
            <li key={group.groupId}>
              <strong>{group.groupName}</strong> (Created By: {group.groupCreatedBy})<br />
              Created At: {new Date(group.createdAt).toLocaleString()}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default GroupDetails;
