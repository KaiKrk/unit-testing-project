[![Build Status](https://travis-ci.org/KaiKrk/P9OC.svg?branch=master)](https://travis-ci.org/KaiKrk/P9OC)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=KaiKrk_P9OC&metric=alert_status)](https://sonarcloud.io/dashboard?id=KaiKrk_P9OC)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=KaiKrk_P9OC&metric=coverage)](https://sonarcloud.io/dashboard?id=KaiKrk_P9OC)
# MyERP
https://github.com/KaiKrk/P9OC
## Organisation du répertoire

*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
    *   `dev` : environnement de développement
*   `src` : code source de l'application


## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)



### Lancement

    cd docker/dev
    docker-compose up


### Arrêt

    cd docker/dev
    docker-compose stop


### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up
    
    
### Lancement des Test D'integration 
     mvn verify -Ptestbusiness,test-consumer
     
### Lancement des test avec coverage Sonarcloud
      mvn verify sonar:sonar -Ptest-business,test-consumer,sonarcoverage
 
