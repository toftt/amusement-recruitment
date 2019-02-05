import React from 'react';
import { Route, Switch } from 'react-router-dom';

import FrontPage from '../pages/FrontPage';
import InsideApp from '../pages/InsideApp';
import PrivateRoute from './PrivateRoute';
import { AuthProvider } from '../AuthContext';

function Root() {
  return (
    <AuthProvider>
      <Switch>
        <PrivateRoute path="/app" component={InsideApp} />
        <Route path="/" component={FrontPage} />
      </Switch>
    </AuthProvider>
  );
}

export default Root;
