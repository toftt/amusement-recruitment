import axios from 'axios';

export const login = payload => {
  const { username, password } = payload;

  return axios.post('/login', { username, password });
};

export const register = () => ({});
