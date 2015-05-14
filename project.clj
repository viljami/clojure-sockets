(defproject clojure-sockets "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"],
                 [http-kit "2.1.18"],
                 [selmer "0.8.2"]]
  :main ^:skip-aot clojure-sockets.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
