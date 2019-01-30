import React, { useState } from 'react';
import { makeStyles } from '@material-ui/styles';
import Button from '@material-ui/core/Button';
import DateFnsUtils from '@date-io/date-fns';
import { MuiPickersUtilsProvider, DatePicker } from 'material-ui-pickers';


const useStyles = makeStyles(theme => ({
    button: {
        margin: theme.spacing.unit,
    },
}));

function AvailabilityForm({ dispatch }) {
    const classes = useStyles();
    const [dateFrom, setDateFrom] = useState(new Date());
    const [dateTo, setDateTo] = useState(new Date());

    return (
        <div>
            <div>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <DatePicker
                        className={classes.button}
                        margin="normal"
                        label="Date from"
                        value={dateFrom}
                        format="yyyy-MM-dd"
                        onChange={setDateFrom}
                    />
                    <DatePicker
                        className={classes.button}
                        margin="normal"
                        label="Date to"
                        value={dateTo}
                        format="yyyy-MM-dd"
                        onChange={setDateTo}
                    />
                </MuiPickersUtilsProvider>
            </div>
            <Button
                variant="contained"
                className={classes.button}
                onClick={() => dispatch({ type: 'add_period', payload: { from: dateFrom, to: dateTo } })}
            >
            Save
            </Button>
            <Button
                variant="contained"
                color="secondary"
                className={classes.button}
                onClick={() => dispatch({ type: 'toggle_period' })}
            >
            Cancel
            </Button>
        </div>
    );
}

export default AvailabilityForm;