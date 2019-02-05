import React, { useState, useContext } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/styles';

import { login } from '../api';
import AuthContext from '../AuthContext';

const useStyles = makeStyles(theme => {
  return {
    container: {
      display: 'flex',
      flexDirection: 'column'
    },
    textField: {
      root: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        dense: {
          marginTop: 16
        }
      }
    },
    button: {
      margin: theme.spacing.unit,
      minWidth: '25vw'
    }
  };
});

function Login() {
  const classes = useStyles();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { setState } = useContext(AuthContext);

  const handleSubmit = e => {
    e.preventDefault();
    login({ username, password })
      .then(response => {
        const token = response.headers.authorization.split(' ')[1];
        setState(previousState => ({ ...previousState, token }));
      })
      .catch(() => {});
  };

  return (
    <form className={classes.container} noValidate autoComplete="off">
      <TextField
        autoFocus
        fullWidth
        id="outlined-email-input"
        label="Username"
        classes={classes.textField}
        type="text"
        autoComplete="username"
        margin="normal"
        variant="outlined"
        value={username}
        onChange={e => setUsername(e.target.value)}
      />
      <TextField
        fullWidth
        id="outlined-password-input"
        label="Password"
        classes={classes.textField}
        type="password"
        autoComplete="current-password"
        margin="normal"
        variant="outlined"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <Button
        variant="outlined"
        className={classes.button}
        onClick={handleSubmit}
      >
        Login
      </Button>
    </form>
  );
}

export default Login;
