import React, { Component } from 'react';
import axios from 'axios';

class App extends Component {
  
  componentDidMount() {
    axios.get('/api/v1/competences').then(data => console.log(data));
  }

  render() {
    return (
      <div>
        hi
      </div>
    );
  }
}

export default App;
