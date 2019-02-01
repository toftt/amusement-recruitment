import React, { useState } from 'react';
import { makeStyles } from '@material-ui/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import reduce from 'lodash/reduce';

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
    button: {
      margin: theme.spacing.unit,
      minWidth: '25vw',
    },
  };
});

function Registration() {
  const classes = useStyles();
  const [state, setState] = useState({
    firstName: '',
    lastName: '',
    socialSecurityNumber: '',
    email: '',
    username: '',
    password: '',
    repeatPassword: '',
    hasFailedSubmit: false,
  });

  const isEmptyError = name => state.hasFailedSubmit && state[name] === '';
  const handleChange = name => e => { setState({ ...state, [name]: e.target.value }); };
  const handleSubmit = (e) => {
    e.preventDefault();
    
    const formIsValid = reduce(state, (isValid, field) => isValid && field !== '', true) && state.password === state.repeatPassword;

    if (!formIsValid) {
      setState({ ...state, hasFailedSubmit: true });
      return;
    }
    
    return;
  };

  return (
    <form className={classes.container} noValidate autoComplete="off">
        <TextField
            autoFocus
            fullWidth
            label="First Name"
            classes={classes.textField}
            type="text"
            name="firstName"
            margin="normal"
            variant="outlined"
            value={state.firstName}
            onChange={handleChange('firstName')}
            error={isEmptyError('firstName')}
        />
        <TextField
            fullWidth
            label="Last Name"
            classes={classes.textField}
            type="text"
            name="lastName"
            margin="normal"
            variant="outlined"
            value={state.lastName}
            onChange={handleChange('lastName')}
            error={isEmptyError('lastName')}
        />
        <TextField
            fullWidth
            label="Social Security Number"
            classes={classes.textField}
            type="text"
            name="socialSecurityNumber"
            margin="normal"
            variant="outlined"
            value={state.socialSecurityNumber}
            onChange={handleChange('socialSecurityNumber')}
            error={isEmptyError('socialSecurityNumber')}
        />
        <TextField
            fullWidth
            label="Email"
            classes={classes.textField}
            type="email"
            name="email"
            autoComplete="email"
            margin="normal"
            variant="outlined"
            value={state.email}
            onChange={handleChange('email')}
            error={isEmptyError('email')}
        />
        <TextField
            fullWidth
            label="Username"
            classes={classes.textField}
            type="text"
            name="username"
            margin="normal"
            variant="outlined"
            value={state.username}
            onChange={handleChange('username')}
            error={isEmptyError('username')}
        />
        <TextField
            fullWidth
            label="Password"
            classes={classes.textField}
            type="password"
            name="password"
            autoComplete="new-password"
            margin="normal"
            variant="outlined"
            value={state.password}
            onChange={handleChange('password')}
            error={isEmptyError('password')}
        />
        <TextField
            fullWidth
            label="Confirm Password"
            classes={classes.textField}
            type="password"
            name="repeatPassword"
            autoComplete="new-password"
            margin="normal"
            variant="outlined"
            value={state.repeatPassword}
            onChange={handleChange('repeatPassword')}
            error={(state.repeatPassword !== '' && state.password !== state.repeatPassword) || isEmptyError('repeatPassword')}
        />
        <Button
          variant="outlined"
          className={classes.button}
          onClick={handleSubmit}
        >
        Sign up
        </Button>
    </form>
  );
}

export default Registration;