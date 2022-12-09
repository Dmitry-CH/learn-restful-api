(ns restful.handler
  (:require [mount.core :as mount]
            [reitit.ring :as ring]))

(defn handler [_]
  {:status 200, :body "ok"})

(declare app-routes)

(mount/defstate app-routes
  :start
  (ring/ring-handler
    (ring/router
      [["/" {:get handler}]])))

(defn app []
  #'app-routes)
