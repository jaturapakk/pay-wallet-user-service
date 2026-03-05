
db_url  := "postgres://admin:password123@localhost:5432/pay_metadata?sslmode=disable"
dev_url := "docker://postgres/16-alpine"
schema  := "src/main/resources/db/schema.sql"

migrate:
    atlas schema apply -u {{db_url}} --to "file://{{schema}}" --dev-url {{dev_url}}


gen:
    ./gradlew jooqCodegen

test-db:
    atlas schema apply \
      -u {{dev_url}} \
      --to "file://{{schema}}" \
      --dev-url {{dev_url}} \
      --dry-run
    @echo "✅ Database Schema Syntax is Valid (Checked with Atlas Sandbox)!"

test-app:
    ./gradlew test


test-only name:
    ./gradlew test --tests {{name}}

all: migrate gen test-app

clean:
    ./gradlew clean
    docker-compose down -v
    docker-compose up -d

