all: build

build: build-jar build-docker

build-jar:
	mvn package

build-docker:
	docker build -t csms/coffee-society-product-catalog:latest .

clean:
	mvn clean

run-jar:
	java -jar target/coffee-society-product-catalog*.jar

run-docker:
	docker run -d --name coffee-society-product-catalog csms/coffee-society-product-catalog

tag:
	docker tag csms/coffee-society-product-catalog csms/coffee-society-product-catalog:${TAG}

push-latest:
	docker push csms/coffee-society-product-catalog:latest

push-tag:
	docker push csms/coffee-society-product-catalog:${TAG}

docker-login:
	@docker login -u "${DOCKER_ID}" -p "${DOCKER_PASS}"
	
docker-run: run-docker

docker-remove:
	docker rm -f coffee-society-product-catalog

docker-logs:
	docker logs coffee-society-product-catalog
