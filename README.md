# TaskOverflow
   Grails project at ISIMA by Benoit Garçon
   
   ## Structure of the project
   You can find all this elements in this project:
   * The Grails project in the common folders with sources, tests, etc.
   * The documentation of the source is in documentation folder
   * The specification and mockups are in document folder.
   
   ## Run the application
   ```
   grails war
   cd build/libs       # see https://github.com/grails/grails-core/issues/9302
   java "-Dgrails.env=dev" -jar .\build\libs\TaskOverflow-0.1.war
   ```