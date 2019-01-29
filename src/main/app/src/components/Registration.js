import React from 'react';
import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/styles';

import ColoredBorderTextField from './ColoredBorderTextField';

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

function Registration() {
  const classes = useStyles();

  return (
    <form className={classes.container} noValidate autoComplete="off">
        <ColoredBorderTextField
            fullWidth
            id="outlined-email-input"
            label="First Name"
            classes={classes.textField}
            type="text"
            name="firstName"
            margin="normal"
        />
        <ColoredBorderTextField
            fullWidth
            id="outlined-email-input"
            label="Last Name"
            classes={classes.textField}
            type="text"
            name="lastName"
            margin="normal"
        />
        <ColoredBorderTextField
            fullWidth
            id="outlined-email-input"
            label="Social Security Number"
            classes={classes.textField}
            type="text"
            name="socialSecurityNumber"
            margin="normal"
        />
        <ColoredBorderTextField
            fullWidth
            id="outlined-email-input"
            label="Username"
            classes={classes.textField}
            type="text"
            margin="normal"
        />
        <ColoredBorderTextField
            fullWidth
            id="outlined-email-input"
            label="Email"
            classes={classes.textField}
            type="email"
            name="email"
            autoComplete="email"
            margin="normal"
        />
        <ColoredBorderTextField
            fullWidth
            id="outlined-password-input"
            label="Password"
            classes={classes.textField}
            type="password"
            autoComplete="new-password"
            margin="normal"
        />
        <ColoredBorderTextField
            fullWidth
            id="outlined-password-input"
            label="Confirm Password"
            classes={classes.textField}
            type="password"
            autoComplete="new-password"
            margin="normal"
        />
    </form>
  );
}

export default Registration;