(ns restful.nrepl
  (:require [nrepl.server :as nrepl]
            [taoensso.timbre :as log]))

(defn stop [server]
  (log/info "stopping nREPL server")
  (nrepl/stop-server server))

(defn start [{:keys [port]}]
  (try
    (log/info "starting nREPL server on port" port)
    (nrepl/start-server :port port)

    (catch Throwable t
      (log/info "failed to start nREPL")
      (throw t))))
