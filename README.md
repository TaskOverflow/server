# TaskOverflow
   Grails project at ISIMA by Benoît Garçon
   
   ## Structure of the project
   You can find all this elements in this project:
   * The Grails project in the common folders with sources, tests, etc.
   * The documentation of the source is in documentation folder
   * The specification and mockups are in document folder.
   
   ## Run the application
   By default the server will be reachable on [http://localhost:8080](http://localhost:8080).

   ```
   grails war
   cd build/libs       # see https://github.com/grails/grails-core/issues/9302
   java "-Dgrails.env=dev" -jar .\TaskOverflow-0.1.war
   ```

  ## Stateless application
  This application is totally stateless.

  ## Authentification mechanism
  This application is secured through spring-security-rest.

  ## Feature Flipping
  The Feature Flipping is implemented: it use the [configuration server](http://github.com/TaskOverflow/conf) of this project. To test the feature flipping on a feature, you just need to switch between 'ok' and 'ko' in the corresponding file in [conf/data/](http://github.com/TaskOverflow/conf/data). Warning: it is important to run the configuration server from root (like it is said in the corresponding README). Every five seconds the client ping each features to know if the feature is activated.
   
  ## Health check
  The complete Health Check is available at [http://localhost:8080/health](http://localhost:8080/health).
  Some simplified Health Check are available for each features (user, badge, tag, question) at http://localhost:8080/featureName/healthcheck, it is used in Circuit Breaker feature.

  ## Circuit Breaker
  The Circuit Breaker is implemented with the help of previously described feature healthcheck. Every five seconds the client ping each features to know if they respond 200 or an error. In the opened mode the circuit waits 20 seconds. If you want to play with the circuit breaker, you have to launch the server with `grails run-app` and modify the code returned by healthcheck() action of the desired controller. Or you can just break the server.

  ## Graceful Degradation
  If there is a problem, some features are simply deleted from the UI (badge, tag), other features are replaced by new third party service : when user feature is down a list of random people is displayed, when question feature is down random gif of cats are displayed instead.
