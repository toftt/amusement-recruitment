import React from 'react';
import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/styles';

const useStyles = makeStyles(() => {
  return {
    labelClasses: {
      classes: {
        root: { '&$cssFocused': { color: 'white' } },
        focused: {}
      }
    },

    cssLabel: {
      '&$cssFocused': {
        color: 'white'
      }
    },
    cssFocused: {},
    cssOutlinedInput: {
      '&$cssFocused $notchedOutline': {
        borderColor: 'white'
      }
    },
    notchedOutline: {}
  };
});

function ColoredBorderTextField(props) {
  const classes = useStyles();

  return (
    <TextField
      {...props}
      InputLabelProps={{
        classes: {
          root: classes.cssLabel,
          focused: classes.cssFocused
        }
      }}
      InputProps={{
        classes: {
          root: classes.cssOutlinedInput,
          focused: classes.cssFocused,
          notchedOutline: classes.notchedOutline
        }
      }}
      variant="outlined"
    />
  );
}

export default ColoredBorderTextField;
