(defproject restful "0.1.0-SNAPSHOT"

  :description ""
  :url ""

  :dependencies [[com.taoensso/timbre "6.0.4"]
                 [cprop "0.1.19"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit "0.5.18"]
                 [metosin/ring-http-response "0.9.3"]
                 [mount "0.1.16"]
                 [nrepl "1.0.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.cli "1.0.214"]
                 [ring/ring-core "1.9.6"]
                 [ring/ring-jetty-adapter "1.9.6"]]

  :min-lein-version "2.0.0"

  :main ^:skip-aot restful.core

  :source-paths ["src/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/" 

  :plugins [[lein-pprint "1.3.2"]]

  :profiles
  {:uberjar {:aot :all
             :omit-source true
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]
             :uberjar-name "restful.jar"}
   
   :dev [:project/dev :profiles/dev]
   
   :project/dev {:dependencies [[org.clojure/tools.namespace "1.3.0"]]
                 
                 :source-paths ["env/dev/clj"]
                 :resource-paths ["env/dev/resources"]
                 
                 :plugins [[cider/cider-nrepl "0.26.0"]
                           [jonase/eastwood "1.3.0"]]
                 
                 :repl-options {:init-ns user
                                :timeout 120000}}
   
   :profiles/dev {}})
