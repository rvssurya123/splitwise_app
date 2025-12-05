import React, { useEffect, useState } from "react";

function UserProfile() {
  const [user, setUser] = useState(null);
  const userId = 33;

  useEffect(() => {
    fetch(`http://localhost:8080/getuserdetailsbyid/${userId}`)
      .then((res) => res.json())
      .then((data) => {
        console.log("USER DATA:", data);  // <‑‑ add here
        setUser(data);
      })
      .catch((err) => console.error("Error fetching user:", err));
  }, [userId]);

  if (!user) return <div>Loading user profile...</div>;

  return (
    <div>
      <h2>User Profile</h2>
      <p>Id: {user.userId}</p>        {/* or whatever the actual key is */}
      <p>Name: {user.userName}</p>    {/* adjust to real field name */}
      <p>Email: {user.email}</p>
    </div>
  );

}

export default UserProfile;
