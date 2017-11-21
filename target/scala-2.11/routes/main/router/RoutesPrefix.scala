
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/rafaelfeitosa/workspace/Scala/workspace/play-scala-restful-api-example/conf/routes
// @DATE:Tue Nov 21 19:14:57 BRT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
