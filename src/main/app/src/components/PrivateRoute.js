import React, { useContext } from 'react';
import { Route, Redirect } from 'react-router-dom';

import AuthContext from '../AuthContext';

export default function PrivateRoute({ component: Component, ...rest }) {
  const { isAuthenticated, isAuthenticating } = useContext(AuthContext);

  return (
    <div>
      {!isAuthenticating && (
        <Route
          {...rest}
          render={props =>
            isAuthenticated ? (
              <Component {...props} />
            ) : (
              <Redirect
                to={{
                  pathname: '/login',
                  state: { from: props.location }
                }}
              />
            )
          }
        />
      )}
    </div>
  );
}
