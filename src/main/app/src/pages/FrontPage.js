import React from 'react';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import { makeStyles } from '@material-ui/styles';
import { Route, Switch, Link, withRouter } from 'react-router-dom';

import Login from '../components/Login';
import Registration from '../components/Registration';

const useStyles = makeStyles(theme => ({
  button: {
    margin: theme.spacing.unit,
    minWidth: '25vw'
  },
  outerContainer: {
    // background: 'linear-gradient(to bottom, #1db954 0%,#1db954 59%,#7cb791 100%)',
    minHeight: '100vh',
    width: '100vw',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',

    '& > *': {
      margin: '3rem'
    }
  },
  typography: {
    fontSize: '4rem'
  },
  innerContainer: {}
}));

function FrontPage(props) {
  const classes = useStyles();
  const {
    location: { pathname }
  } = props;
  return (
    <div className={classes.outerContainer}>
      <div className={classes.innerContainer}>
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
        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/registration" component={Registration} />
        </Switch>
      </div>
    </div>
  );
}

export default withRouter(FrontPage);
