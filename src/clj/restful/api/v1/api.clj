(ns restful.api.v1.api
  (:require [restful.api.v1.handler :as handler]))

(defn routes []
  ["/api/v1"
   ["/health"
    {:name ::health
     :get handler/health}]
   ["/user/:id"
    {:name ::user
     :get handler/get-user-by-id}]])
