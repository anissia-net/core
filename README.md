# Anissia Core
애니시아 코어 프로젝트
- 주소 : [anissia.net](https://anissia.net)

## 개발환경
- JDK 23 / Kotlin
- [Elastic Search](https://www.elastic.co)
  ```
  # 도커 설치 예제 (8 버전)
  # 엘라스틱서치는 버전 호환성이 좋지 않아 메이저 버전 유의
  
  # x86_64
  docker run -d --name elasticsearch -p 9200:9200 \
  -e 'xpack.security.enabled=false' -e 'discovery.type=single-node' \
  -v 로컬볼륨:/usr/share/elasticsearch/data \
  docker.elastic.co/elasticsearch/elasticsearch:8.17.4
  
  # arm (mac)
  docker run -d --name elasticsearch -p 9200:9200 \
  -e 'CLI_JAVA_OPTS=-XX:UseSVE=0' -e 'ES_JAVA_OPTS=-XX:UseSVE=0' \
  -e 'xpack.security.enabled=false' -e 'discovery.type=single-node' \
  -v 로컬볼륨:/usr/share/elasticsearch/data \
  docker.elastic.co/elasticsearch/elasticsearch:8.17.4-arm64
  ```
- [Maria DB](https://mariadb.org)
  ```
  # 도커 설치 예제
  
  # 마리아 DB
  docker run -d --name mariadb -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -v 유저볼륨설정:/etc/mysql/conf.d \
  -v 유저볼륨데이터:/var/lib/mysql:Z \
  mariadb
  
  # 접속
  docker exec -it mariadb /bin/mariadb -u root -proot
  
  # DB 생성
  CREATE DATABASE anissia;
  CREATE USER 'anissia'@'%' IDENTIFIED BY 'anissia';
  GRANT ALL PRIVILEGES ON * . * TO 'anissia'@'%';
  FLUSH PRIVILEGES;
  ```

## 실행
각 IDE에서 실행하거나 직접 gradle wrapper를 이용하여 실행
```
# 프로파일별 실행
gradlew bootRun -Dspring.profiles.active=local
gradlew bootRun -Dspring.profiles.active=dev
gradlew bootRun -Dspring.profiles.active=prod

# 빌드 / 실행
gradlew build
java -jar anissia-core-1.0.jar --spring.profiles.active=prod
```

### 로컬 기본 데이터 생성
- http://localhost:8080/install (예정)

## 참고 
* [애니시아 문서](https://github.com/anissia-net/document)
* [애니편성표 API](https://github.com/anissia-net/document/blob/main/api_anime_schdule.md)
* [애니시아 CORE 프로젝트](https://github.com/anissia-net/core)
* [애니시아 WEB 프로젝트](https://github.com/anissia-net/web)
