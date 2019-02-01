import React from 'react';
import { Route, Switch } from 'react-router-dom';

import FrontPage from '../pages/FrontPage';
import InsideApp from '../pages/InsideApp';

function Root() {
  return (
    <Switch>
      <Route path="/app" component={InsideApp} />
      <Route path="/" component={FrontPage} />
    </Switch>
  );
}

export default Root;
