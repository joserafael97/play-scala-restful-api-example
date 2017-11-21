
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/rafaelfeitosa/workspace/Scala/workspace/play-scala-restful-api-example/conf/routes
// @DATE:Tue Nov 21 19:14:57 BRT 2017

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:1
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
