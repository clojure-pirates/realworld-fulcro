(defproject conduit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]

                 [duct/core "0.7.0"]
                 [duct/module.logging "0.4.0"]
                 [duct/module.web "0.7.0"]
                 [duct/module.ataraxy "0.3.0"]
                 [duct/module.cljs "0.4.0" :exclusions [org.clojure/clojurescript]]
                 [duct/module.sql "0.5.0"]

                 [duct/middleware.buddy "0.1.0"]

                 ;; this version of clojurescript doesn't cause compiling error to specter
                 ;; it's used by specter itself
                 [org.clojure/clojurescript "1.10.439"]
                 [com.rpl/specter "1.1.1"]
                 [metosin/reitit "0.1.3"]
                 [kibu/pushy "0.3.8"]
                 [duct/handler.sql "0.3.1"]
                 [buddy/buddy-hashers "1.3.0"]
                 [fulcrologic/fulcro "2.5.3"]
                 [walkable "1.2.0-SNAPSHOT"]
                 [org.clojure/core.async "0.4.474"]
                 [org.postgresql/postgresql "42.1.4"]]
  :middleware [lein-duct.plugin/middleware]
  :plugins [[duct/lein-duct "0.11.2"]]
  :main ^:skip-aot conduit.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user
                         :timeout          120000
                         :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [ ;; cljs
                                   [duct/server.figwheel "0.3.0" :exclusions [org.clojure/clojurescript]]
                                   [org.clojure/test.check "0.10.0-alpha3"]
                                   [devcards "0.2.4" :exclusions [org.clojure/clojurescript]]

                                   [fulcrologic/fulcro-inspect "2.2.5" :exclusions [fulcrologic/fulcro-css]]

                                   [integrant/repl "0.3.1"]
                                   [eftest "0.5.4"]
                                   [kerodon "0.9.0"]]}})
