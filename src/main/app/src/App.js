import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import { BrowserRouter } from 'react-router-dom';
import { createMuiTheme } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';

import Root from './components/Root';

const theme = createMuiTheme({
  overrides: {
    MuiOutlinedInput: {
      root: {
        '&$focused $notchedOutline': {
          borderColor: 'red',
          borderWidth: 3
        }
      }
    }
  }
});

const App = () => (
  <BrowserRouter>
    <ThemeProvider theme={theme}>
      <React.Fragment>
        <CssBaseline />
        <Root />
      </React.Fragment>
    </ThemeProvider>
  </BrowserRouter>
);

export default App;
