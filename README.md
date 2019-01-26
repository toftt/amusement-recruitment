## Development

#### Requirements
To run the app locally for development you will need the following.
- __IntelliJ__ (not strictly needed but recommended)
- A __MySQL__ instance running on `localhost:3306` configured with a database
named req_system and a user with username `springuser` and password `springpwd`
- __Node.js__
- __Yarn__

#### Getting started
1. Open the project in IntelliJ. `File -> New -> Project from existing sources...`
2. Let IntelliJ install all the Maven dependencies.
3. In a terminal, navigate to `src/main/app`.
4. Run `yarn install` to install dependencies.
6. Run `yarn start` to start React Dev Server.
7. Run the project in IntelliJ.
8. Navigate to `localhost:3000` in your browser.

## Production

#### Requirements
To run your own instance of the app, you will need the following.
- A __Heroku__ account
- A __Github__ account
- __Heroku CLI__ installed

#### Getting started
1. Fork the Github repository (this is needed to configure Travis for your own instance).
2. Create a new Heroku app.
3. Add ClearDB to the app. `heroku addons:create cleardb:ignite -a APP_NAME`
4. Run `heroku config -a APP_NAME` and save `CLEARDB_DATABASE_URL` somewhere for later.
5. Run `heroku addons:open cleardb` and save the username and password (you can find it in system information) for later.
6. Visit the settings for your Heroku app and open `Config Vars`.
7. Add the vars `JDBC_DATABASE_USERNAME`, `JDBC_DATABASE_PASSWORD` and `JDBC_DATABASE_URL`
and enter the username, password and database url from earlier. The value of `JDBC_DATABASE_URL` need to be
prepended with `jdbc:`, e.g. `jdbc:URL_HERE`. 
8. Visit `https://travis-ci.org/`, login with Github and turn on Travis for your repo.
9. Create an auth token for Heroku with the CLI. `heroku authorizations:create`
10. In the Travis settings for the repo, add three environment variables:
```
HEROKU_APP_NAME={the name of your created Heroku app}
HEROKU_USERNAME={your e-mail on Heroku}
HEROKU_PASSWORD={the token created in step 10}
```

Lastly, visit `More options -> Trigger build` in Travis to create a new build and auto-deploy it to Heroku.