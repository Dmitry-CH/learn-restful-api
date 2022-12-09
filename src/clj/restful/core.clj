(ns restful.core
  (:require [mount.core :as mount]
            [taoensso.timbre :as log]
            [restful.config :refer [env]]
            [restful.handler :refer [app]]
            [restful.http :as http]
            [restful.nrepl :as nrepl])
  (:gen-class))

(declare http-server)

(mount/defstate ^{:on-reload :noop} http-server
  :start
  (http/start {:port (or (:port env) 3000)
               :handler (app)})
  :stop
  (when-some [server http-server]
    (http/stop server)))

(declare repl-server)

(mount/defstate ^{:on-reload :noop} repl-server
  :start
  (nrepl/start {:port (or (:nrepl-port env) 7000)})
  :stop
  (when-some [server repl-server]
    (nrepl/stop server)))

(defn stop-app []
  (doseq [component (-> (mount/stop)
                        :stopped)]
    (log/info component "stopped"))
  ; Принудительно завершить `thread pools agent system`
  (shutdown-agents))

(defn start-app []
  (doseq [component (-> (mount/start)
                        :started)]
    (log/info component "started"))
  ; TODO: Добавить описание
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app)))

(defn -main [#_#_& args]
  (start-app))
