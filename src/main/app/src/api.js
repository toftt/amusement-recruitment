import axios from 'axios';

const LOGIN_URL = '/login';
const REGISTER_URL = '/api/v1/users';
const CURRENT_USER_URL = '/api/v1/users/me';

export const login = payload => {
  const { username, password } = payload;

  return axios.post(LOGIN_URL, { username, password });
};

export const register = data => {
  const {
    firstName,
    lastName,
    socialSecurityNumber,
    email,
    username,
    password
  } = data;

  return axios.post(REGISTER_URL, {
    firstName,
    lastName,
    socialSecurityNumber,
    email,
    username,
    password
  });
};

export const getCurrentUser = token => {
  return axios.get(CURRENT_USER_URL, {
    headers: { Authorization: `Bearer ${token}` }
  });
};
