import React, { ChangeEvent, useState } from 'react';
import logo from './logo.svg';
import './App.css';

type SignUpForm = {
  username : string,
  password: string,
  email : string,
  surname: string,
  name: string,
  birthDate: string,
  role: [string],
};

type SignInForm = {
  username : string,
  password : string
}

function App() {
  const [token, setToken] = useState("pusto")
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  function logowanie(signInForm : SignInForm ) {
    // const data = {"username": "ola", "password": "123456"};
    fetch('http://localhost:8080/api/auth/signin', {
        method: "POST", 
        body: JSON.stringify(signInForm),
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setToken(data.accessToken);
      })
      .catch((err) => {
        console.log(err);
      })
  }

  function rejestracja(form : SignUpForm) {
    fetch('http://localhost:8080/api/auth/signup', {
      method: "POST", 
      body: JSON.stringify(form),
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
    })
    .catch((err) => {
      console.log(err);
    })
  }

  function handleChangeUsername(e : any) {
    setUsername(e.target.value);
  };

  function handleChangePassword(e : any) {
    setPassword(e.target.value);
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div>
          <label>Username: </label>
          <input type="text" name='xyz' value={username} onChange={handleChangeUsername}/>
          <label>Password: </label>
          <input type="password" name='xyz' value={password} onChange={handleChangePassword}/>
        </div>
        <div>
        <button onClick={() => logowanie({"username": username, "password": password})}>
          Test logowania
        </button>
        </div>
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        
        <p>
          {token}
        </p>

        

        <button onClick={() => rejestracja({birthDate: '2000-01-01', email: 'jozek@tester.pl', name: 'Jozef', surname: 'Testowy', password: "helikopter", role: ['ROLE_USER'], username: 'juser'})}>
          Test rejestracji
        </button>


        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
