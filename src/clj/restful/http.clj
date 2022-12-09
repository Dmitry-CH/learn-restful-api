(ns restful.http
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [taoensso.timbre :as log]))

(defn stop [server]
  (log/info "stopping HTTP server")
  (.stop server))

(defn start [{:keys [handler port]}]
  (try
    (log/info "starting HTTP server on port" port)
    (run-jetty handler {:port port
                        :join? false})
    
    (catch Throwable t
      (log/info "failed to start HTTP")
      (throw t))))
