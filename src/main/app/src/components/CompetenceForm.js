import React, { useState } from 'react';
import { makeStyles } from '@material-ui/styles';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import FormControl from '@material-ui/core/FormControl';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import InputAdornment from '@material-ui/core/InputAdornment';

const useStyles = makeStyles(theme => ({
  formControl: {
    margin: theme.spacing.unit,
    minWidth: 200
  },
  button: {
    margin: theme.spacing.unit
  }
}));

function CompetenceForm({ dispatch }) {
  const classes = useStyles();
  const [competence, setCompetence] = useState('');
  const [years, setYears] = useState('');

  return (
    <div>
      <div>
        <FormControl className={classes.formControl}>
          <InputLabel>Area of exertise</InputLabel>
          <Select
            value={competence}
            onChange={e => setCompetence(e.target.value)}
          >
            <MenuItem value="">
              <em>None</em>
            </MenuItem>
            <MenuItem value="Hot dogging">Hot dogging</MenuItem>
            <MenuItem value="Operating rollercoasters">
              Operating rollercoasters
            </MenuItem>
            <MenuItem value="Murdering donkeys">Murdering donkeys</MenuItem>
          </Select>
        </FormControl>
        <TextField
          label="Years of experience"
          className={classes.formControl}
          value={years}
          onChange={e => setYears(e.target.value)}
          margin="normal"
          InputProps={{
            endAdornment: <InputAdornment position="end">years</InputAdornment>
          }}
        />
      </div>
      <Button
        variant="contained"
        className={classes.button}
        onClick={() =>
          dispatch({
            type: 'add_competence',
            payload: { name: competence, years }
          })
        }
      >
        Save
      </Button>
      <Button
        variant="contained"
        color="secondary"
        className={classes.button}
        onClick={() => dispatch({ type: 'toggle_competence' })}
      >
        Cancel
      </Button>
    </div>
  );
}

export default CompetenceForm;
