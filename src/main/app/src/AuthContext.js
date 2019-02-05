import React, { createContext, useState, useEffect } from 'react';

import { getCurrentUser } from './api';

const AuthContext = createContext();
export default AuthContext;

const storageToken = localStorage.getItem('token');

const initialState = {
  token: storageToken,
  user: null,
  isAuthenticating: !!storageToken
};

export function AuthProvider({ children }) {
  const [state, setState] = useState(initialState);
  const value = {
    state,
    setState,
    get isAuthenticated() {
      return state.user !== null;
    },
    isAuthenticating: state.isAuthenticating
  };
  const { token } = state;

  useEffect(() => {
    if (token) {
      setState({ ...state, isAuthenticating: true });
      getCurrentUser(token)
        .then(({ data }) => {
          localStorage.setItem('token', token);
          setState({
            ...state,
            isAuthenticating: false,
            user: { username: data.username }
          });
        })
        .catch(() =>
          setState({
            ...state,
            isAuthenticating: false,
            token: null,
            user: null
          })
        );
    }
  }, [token]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
