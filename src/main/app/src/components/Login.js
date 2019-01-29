import React from 'react';
import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/styles';

const useStyles = makeStyles(theme => {
  return {
    container: {
      display: 'flex',
      flexDirection: 'column',
      width: '400px',
    },
    textField: {
      root: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        dense: {
          marginTop: 16,
        },
      },
    },
  };
});

function Login() {
  const classes = useStyles();

  return (
    <form className={classes.container} noValidate autoComplete="off">
      <TextField
        fullWidth
        id="outlined-email-input"
        label="Email"
        classes={classes.textField}
        type="email"
        name="email"
        autoComplete="email"
        margin="normal"
        variant="outlined"
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
      />
    </form>
  );
}

export default Login;