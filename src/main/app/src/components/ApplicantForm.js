import React, { useReducer } from 'react';
import { makeStyles } from '@material-ui/styles';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';

import CompetenceForm from './CompetenceForm';
import AvailabilityForm from './AvailabilityForm';
import ApplicationSummary from './ApplicationSummary';

const useStyles = makeStyles(theme => ({
    outerContainer: {
        display: 'flex',
        justifyContent: 'center',
        paddingTop: '3rem',
    },
    innerContainer: {
        display: 'flex',
        width: '40vw',
        flexDirection: 'column',
        justifyContent: 'center',
    },
    button: {
        margin: theme.spacing.unit,
    },
    divider: {
        marginTop: '2rem',
        marginBottom: '2rem',
    },
}));

function ApplicantForm() {
    const classes = useStyles();
    const [state, dispatch] = useReducer(reducer, initialState);

    return (
        <div className={classes.outerContainer}>
            <div className={classes.innerContainer}>
                <ApplicationSummary competences={state.competences} periods={state.periods} />
                <Button
                    onClick={() => dispatch({ type: 'toggle_competence'})}
                    variant="contained"
                    color="primary"
                    className={classes.button}
                >
                Add area of expertise
                </Button>
                {state.competenceFormVisible && <CompetenceForm dispatch={dispatch} />}
                <Button
                    onClick={() => dispatch({ type: 'toggle_period'})}
                    variant="contained"
                    color="primary"
                    className={classes.button}
                >
                Add availability period
                </Button>
                {state.periodFormVisible && <AvailabilityForm dispatch={dispatch} />}
                <Divider className={classes.divider} />
                <Button
                    variant="contained"
                    color="primary"
                    className={classes.button}
                >
                Submit Application
                </Button>
            </div>
        </div>
    );
}

export default ApplicantForm;

const initialState = {
    competenceFormVisible: false,
    periodFormVisible: false,
    competences: [],
    periods: [],
};

function reducer(state, action) {
    switch (action.type) {
        case 'toggle_competence':
            return { ...state, competenceFormVisible: !state.competenceFormVisible };
        case 'toggle_period':
            return { ...state, periodFormVisible: !state.periodFormVisible };
        case 'add_competence':
            return {
                ...state,
                competenceFormVisible: false,
                competences: [...state.competences, action.payload],
            };
        case 'add_period':
            return {
                ...state,
                periodFormVisible: false,
                periods: [...state.periods, action.payload],
            };
        default:
            return state;
    }
}