import React from 'react';
import Button from '@material-ui/core/Button';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import { makeStyles } from '@material-ui/styles';
import { TransitionGroup, CSSTransition } from "react-transition-group";
import { Route, Switch, Link, withRouter } from 'react-router-dom';

import Login from '../components/Login';
import Registration from '../components/Registration';

const useStyles = makeStyles(theme => ({
    button: {
        margin: theme.spacing.unit,
        minWidth: '25vw',
    },
    container: {
        //background: 'linear-gradient(to bottom, #1db954 0%,#1db954 59%,#7cb791 100%)',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '100vh',
        width: '100vw',

        '& > *': {
            margin: '3rem',
        }
    },
    typography: {
        fontSize: '4rem',
    },
}));

function FrontPage(props) {
    const classes = useStyles();
    const { key, pathname } = props.location;
    return (
        <div className={classes.container}>
            {/* <Typography className={classes.typography}>Amusement Recruitment</Typography> */}
            <Tabs
                value={pathname === '/login' ? 0 : 1}
                indicatorColor="primary"
                textColor="primary"
                centered
            >
                <Tab label="Login" component={Link} to="/login" />
                <Tab label="Sign up" component={Link} to="/registration" />
            </Tabs>
            <TransitionGroup>
                <CSSTransition
                  key={key}
                  classNames="fade"
                  timeout={10}
                >
                    <Switch>
                        <Route path={'/login'} component={Login} />
                        <Route path={'/registration'} component={Registration} />
                    </Switch>
                </CSSTransition>
            </TransitionGroup>
            <Button
                variant="outlined"
                className={classes.button}
                size="large"
            >
                Login
            </Button>
        </div>
    );
}

export default withRouter(FrontPage);