start:
	docker compose up -d

start-db:
	docker compose run --rm --service-ports db

