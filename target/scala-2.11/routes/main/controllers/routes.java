
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/rafaelfeitosa/workspace/Scala/workspace/play-scala-restful-api-example/conf/routes
// @DATE:Tue Nov 21 19:14:57 BRT 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseApplication Application = new controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseApplication Application = new controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
