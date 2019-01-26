#!/bin/bash

docker images
docker tag org.toft.recsystem/recsystem:0.0.1-SNAPSHOT registry.heroku.com/$HEROKU_APP_NAME/web
docker push registry.heroku.com/$HEROKU_APP_NAME/web

export WEB_DOCKER_IMAGE_ID=`docker inspect registry.heroku.com/$HEROKU_APP_NAME/web --format={{.Id}}`

echo "$WEB_DOCKER_IMAGE_ID"

curl -n -X PATCH https://api.heroku.com/apps/$HEROKU_APP_NAME/formation \
  -d "{
  \"updates\": [
    {
      \"type\": \"web\",
      \"docker_image\": \"$WEB_DOCKER_IMAGE_ID\"
    }
  ]
}" \
  -H "Content-Type: application/json" \
  -H "Accept: application/vnd.heroku+json; version=3.docker-releases" \
  -H "Authorization: Bearer $HEROKU_PASSWORD"